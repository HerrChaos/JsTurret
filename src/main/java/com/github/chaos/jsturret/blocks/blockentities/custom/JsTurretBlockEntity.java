package com.github.chaos.jsturret.blocks.blockentities.custom;

import com.caoccao.javet.annotations.V8Function;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.V8Runtime;
import com.caoccao.javet.interop.callback.JavetCallbackContext;
import com.caoccao.javet.values.reference.V8ValueArray;
import com.caoccao.javet.values.reference.V8ValueObject;
import com.github.chaos.jsturret.blocks.blockentities.ModBlockEntities;
import com.github.chaos.jsturret.networking.packets.JsTurretLoadPacket;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.joml.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsTurretBlockEntity extends BlockEntity {
    private String jsCode = "";
    private JSAPI jsAPI = new JSAPI();

    public double shootCooldown = 0;

    private double yaw = 0;
    private double pitch = 0;

    public double wishYaw = 0;
    public double wishPitch = 0;

    public int age = 0;

    public boolean initialized = false;

    public JsTurretBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        JsTurretLoadPacket payload = new JsTurretLoadPacket(jsCode, getPos());

        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    public JsTurretBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JS_TURRET_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, JsTurretBlockEntity entity) {
        if (world != null && !world.isClient() && !entity.initialized) {
            // Only run this on server-side and ensure world isn't null
            try {
                JsTurretLoadPacket payload = new JsTurretLoadPacket(entity.jsCode, entity.getPos());

                for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
                    ServerPlayNetworking.send(player, payload);
                }
            } catch (Exception e) {
                // Log the exception instead of crashing
                System.err.println("Error sending JsTurret packet: " + e.getMessage());
            }

            entity.initialized = true;
        }

        entity.age++;

        if (entity.shootCooldown > 0) {
            entity.shootCooldown--;
        }

        entity.yaw = entity.wishYaw;
        entity.pitch = entity.wishPitch;

        if (Objects.equals(entity.jsCode, "")) {
            return;
        }

        List<Entity> entities = world.getOtherEntities(null, Box.from(Vec3d.of(entity.getPos())).expand(20));

        List<MinecraftEntity> minecraftEntities = entities.stream().filter(Entity::isAlive).map((e) -> {
            Vec3d entityPos = EntityAnchorArgumentType.EntityAnchor.EYES.positionAt(e);
            return new MinecraftEntity(new Vec3dJS(entityPos), e.getType().toString(), e.getName().getString());
        }).toList();


        try (V8Runtime v8Runtime = V8Host.getV8Instance().createV8Runtime()) {
            v8Runtime.lowMemoryNotification();

            List<AutoCloseable> toClose = new ArrayList<>();
            List<JavetCallbackContext> callbacks = new ArrayList<>();
            // bind block API
            V8ValueObject v8BlockObject = v8Runtime.createV8ValueObject();
            toClose.add(v8BlockObject);

            v8Runtime.getGlobalObject().set("block", v8BlockObject);
            callbacks.addAll(v8BlockObject.bind(entity.jsAPI));

            // bind entities
            V8ValueArray v8EntitiesArray = v8Runtime.createV8ValueArray();
            toClose.add(v8EntitiesArray);

            for (MinecraftEntity mcEntity : minecraftEntities) {
                V8ValueObject obj = v8Runtime.createV8ValueObject();
                toClose.add(obj);
                V8ValueObject pos = v8Runtime.createV8ValueObject();
                toClose.add(pos);

                pos.set("x", mcEntity.position().x());
                pos.set("y", mcEntity.position().y());
                pos.set("z", mcEntity.position().z());

                obj.set("position", pos);
                obj.set("type", mcEntity.type());
                obj.set("name", mcEntity.name());

                v8EntitiesArray.push(obj);
            }

            v8Runtime.getGlobalObject().set("creatures", v8EntitiesArray);
            callbacks.addAll(v8EntitiesArray.bind(minecraftEntities));

            // run script
            v8Runtime.getExecutor("const entities = Array.from(creatures); \n" + entity.jsCode).executeVoid();

            v8EntitiesArray.unbind(minecraftEntities);
            v8Runtime.getGlobalObject().delete("creatures");

            v8BlockObject.unbind(entity.jsAPI);
            v8Runtime.getGlobalObject().delete("block");

            for (JavetCallbackContext callback : callbacks) {
                v8Runtime.removeCallbackContext(callback.getHandle());
            }

            for (AutoCloseable o : toClose) {
                o.close();
            }
        } catch (Exception ignored) {}
    }

    private void shoot() {
        if (shootCooldown != 0) {
            return;
        }

        if (world == null) {
            return;
        }

        Vec3d pos = new Vec3d(this.getPos().getX() + 0.5, this.getPos().getY() + 0.7, this.getPos().getZ() + 0.5);

        Vec3d direction = new Vec3d(-MathHelper.sin((float) Math.toRadians(yaw)) * MathHelper.cos((float) Math.toRadians(pitch)), -MathHelper.sin((float) Math.toRadians(pitch)), MathHelper.cos((float) Math.toRadians(yaw)) * MathHelper.cos((float) Math.toRadians(pitch)));

        Vec3d barrel = pos.add(direction.multiply(1.7));

        BlockHitResult blockHitResult = world.getCollisionsIncludingWorldBorder(new RaycastContext(barrel, pos.add(direction.multiply(100)), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, ShapeContext.absent()));
        //@Nullable Entity entity, double x, double y, double z, float power, boolean createFire, ExplosionSourceType explosionSourceType) {

        EntityHitResult entityHitResult = ProjectileUtil.getEntityCollision(
                world,
                null,
                barrel,
                blockHitResult.getPos(),
                new Box(barrel, blockHitResult.getPos()).expand(0.5),
                entity -> true,
                1.0f);


        /*
        for (int i = 0; i < 10; i++) {
            world.addParticleClient(ParticleTypes.LARGE_SMOKE, barrel.x + random.nextDouble(), barrel.y + random.nextDouble(), barrel.z + random.nextDouble(), 0, 0, 0);
        }

         */

        if (entityHitResult != null) {
            Entity hitEntity = entityHitResult.getEntity();
            world.createExplosion(null, hitEntity.getPos().x, hitEntity.getPos().y, hitEntity.getPos().z, 10, false, World.ExplosionSourceType.NONE);

            world.addImportantParticleClient(ParticleTypes.EXPLOSION_EMITTER, hitEntity.getPos().x, hitEntity.getPos().y, hitEntity.getPos().z, 0, 0, 0);
            //ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ

        } else {
            world.createExplosion(null, blockHitResult.getPos().x, blockHitResult.getPos().y, blockHitResult.getPos().z, 10, false, World.ExplosionSourceType.NONE);

            world.addImportantParticleClient(ParticleTypes.EXPLOSION_EMITTER, blockHitResult.getPos().x, blockHitResult.getPos().y, blockHitResult.getPos().z, 0, 0, 0);
        }

        shootCooldown = 20;
    }

    public boolean justShot() {
        return shootCooldown == 20;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public record MinecraftEntity(Vec3dJS position, String type, String name) {}

    public record Vec3dJS(double x, double y, double z) {
        public Vec3dJS(Vec3d vec) {
            this(vec.x, vec.y, vec.z);
        }
    }

    public class JSAPI {
        @V8Function
        public void rotateYaw(double degrees) {
            wishYaw = MathHelper.wrapDegrees(wishYaw + degrees);
        }

        @V8Function
        public void rotatePitch(double degrees) {
            wishPitch = MathHelper.wrapDegrees(wishPitch + degrees);
        }

        @V8Function
        public Vec3dJS getPosition() {
            return new Vec3dJS(getPos().getX(), getPos().getY(), getPos().getZ());
        }

        @V8Function
        public void shoot() {
            JsTurretBlockEntity.this.shoot();
        }

        @V8Function
        public void lookAt(double x, double y, double z) {
            Vector2d angles = getLookAtAngles(x, y, z);

            wishYaw = angles.y;
            wishPitch = angles.x;
        }

        public Vector2d getLookAtAngles(double x, double y, double z) {
            double d = x - (JsTurretBlockEntity.this.getPos().getX() + 0.5);
            double e = y - (JsTurretBlockEntity.this.getPos().getY() + 1);
            double f = z - (JsTurretBlockEntity.this.getPos().getZ() + 0.5);
            double g = Math.sqrt(d * d + f * f);

            return new Vector2d(MathHelper.wrapDegrees((float)(-(MathHelper.atan2(e, g) * (double)(180F / (float)Math.PI)))), MathHelper.wrapDegrees((float)(MathHelper.atan2(f, d) * (double)(180F / (float)Math.PI)) - 90.0F));
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);

        if (nbt.contains("code")) {
            jsCode = nbt.getString("code").get().replaceFirst("Optional\\[Optional\\[", "").replaceAll("]]", "");
        } else {
            jsCode = "";
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);

        nbt.putString("code", jsCode);
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }
}

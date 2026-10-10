package com.silver.issoccultspells.Entity.Spells.Mudo;

import com.silver.issoccultspells.registries.EntityRegistry;
import com.silver.issoccultspells.registries.MobEffectRegistry;
import com.silver.issoccultspells.registries.OccultSpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.acetheeldritchking.aces_spell_utils.registries.ASParticleRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static software.bernie.geckolib.util.ClientUtil.getLevel;

public class MudoEntity extends AbstractMagicProjectile {
    private static final EntityDataAccessor<Float> DATA_Z_ROT = SynchedEntityData.defineId(MudoEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_SCALE = SynchedEntityData.defineId(MudoEntity.class, EntityDataSerializers.FLOAT);
    public int hexpotency = 1;
    public MudoEntity(EntityType<? extends MudoEntity> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
    }

    public MudoEntity(Level levelIn, LivingEntity shooter) {
        super(EntityRegistry.MUDO.get(), levelIn);
        setOwner(shooter);
    }

    public void setZRot(float zRot) {
        if (!getLevel().isClientSide)
            entityData.set(DATA_Z_ROT, zRot);
    }

    public void setScale(float scale) {
        if (!getLevel().isClientSide)
            entityData.set(DATA_SCALE, scale);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(DATA_Z_ROT, 0f);
        pBuilder.define(DATA_SCALE, 1f);
        super.defineSynchedData(pBuilder);
    }

    public float getZRot() {
        return entityData.get(DATA_Z_ROT);
    }

    public float getScale() {
        return entityData.get(DATA_SCALE);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("zRot", getZRot());
        if (getScale() != 1)
            tag.putFloat("Scale", getScale());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setZRot(tag.getFloat("zRot"));
        if (tag.contains("Scale"))
            setScale(tag.getFloat("Scale"));
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        DamageSources.applyDamage(entityHitResult.getEntity(), getDamage(), OccultSpellRegistry.MUDO.get().getDamageSource(this, getOwner()));
        int hex = 0;
        for (MobEffectInstance effectInstance : ((LivingEntity)entityHitResult.getEntity()).getActiveEffects()){
            if(effectInstance.getEffect() == MobEffectRegistry.HEX.getDelegate() ){
                hex = effectInstance.getAmplifier();
                break;
            }
        }
        ((LivingEntity)entityHitResult.getEntity()).addEffect(new MobEffectInstance(MobEffectRegistry.HEX.getDelegate(), 20*40, hex+hexpotency));
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        discard();
    }
    private static int soundTimestamp;

    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        if (soundTimestamp != this.tickCount) {
            super.doImpactSound(sound);
            soundTimestamp = this.tickCount;
        }
    }

    @Override
    public void trailParticles() {
        for (int i = 0; i < 2; i++) {
            double speed = .05;
            double dx = Utils.random.nextDouble() * 2 * speed - speed;
            double dy = Utils.random.nextDouble() * 2 * speed - speed;
            double dz = Utils.random.nextDouble() * 2 * speed - speed;
            getLevel().addParticle(ParticleHelper.SOUL_FIRE, this.getX() + dx, this.getY() + dy, this.getZ() + dz, dx, dy, dz);
        }
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), new BlastwaveParticleOptions(ASSchoolRegistry.RITUAL.get().getTargetingColor(), 1.2f), x, y, z, 15, .1, .1, .1, .18, true);
    }

    @Override
    public float getSpeed() {
        return 2.0f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.BLOOD_NEEDLE_IMPACT);
    }
}
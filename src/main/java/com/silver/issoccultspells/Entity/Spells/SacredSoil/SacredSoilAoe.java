package com.silver.issoccultspells.Entity.Spells.SacredSoil;

import com.silver.issoccultspells.registries.EntityRegistry;
import com.silver.issoccultspells.registries.MobEffectRegistry;
import com.silver.issoccultspells.registries.OccultSpellRegistry;
import io.redspace.ironsspellbooks.api.events.SpellHealEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.spells.root.RootEntity;
import io.redspace.ironsspellbooks.player.ClientSpellCastHelper;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ShriekParticleOption;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;

import java.util.Optional;

public class SacredSoilAoe extends AoeEntity implements AntiMagicSusceptible {
    public float RootHealth = 40;
    public float RootDuration = 5 * 20;
    public SacredSoilAoe(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public SacredSoilAoe(Level level) {
        this(EntityRegistry.SACRED_SOIL_AOE.get(), level);
    }

    @Override
    public void applyEffect(LivingEntity target) {
        if (getOwner() instanceof LivingEntity owner && !Utils.shouldHealEntity((Entity) owner, target)) {
            if (!target.getType().is(EntityTypeTags.RAIDERS) && !target.getType().is(ModTags.CANT_ROOT)) {
                    if ((target.getVehicle() == null) || !(target.getVehicle() instanceof RootEntity)){
                        Vec3 spawn = target.position();
                        RootEntity rootEntity = new RootEntity(target.level(), (LivingEntity) getOwner());
                        rootEntity.setDuration((int) RootDuration);
                        rootEntity.setTarget(target);
                        rootEntity.moveTo(spawn);
                        rootEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(RootHealth);
                        rootEntity.setHealth(RootHealth);
                        target.level().addFreshEntity(rootEntity);
                        target.stopRiding();
                        target.startRiding(rootEntity, true);
                    } else if (target.getType().is(EntityTypeTags.UNDEAD)){
                        DamageSources.applyDamage(target, getDamage(), OccultSpellRegistry.SACRED_SOIL.get().getDamageSource(this, getOwner()));
                    }

            }
            int hex = 0;
            for (MobEffectInstance effectInstance : target.getActiveEffects()){
                if(effectInstance.getEffect() == MobEffectRegistry.HEX.getDelegate() ){
                    hex = effectInstance.getAmplifier();
                    break;
                }
            }
            target.addEffect(new MobEffectInstance(MobEffectRegistry.HEX.getDelegate(), 20*40, hex+0));
        }
    }

    @Override
    protected boolean canHitEntity(Entity pTarget) {
        return !pTarget.isSpectator() && pTarget.isAlive() && pTarget.isPickable();
    }

    @Override
    public float getParticleCount() {
        return 0.35f;
    }

    @Override
    protected float getParticleSpeedModifier() {
        return 0f;
    }

    @Override
    protected Vec3 getInflation() {
        return new Vec3(0, 1, 0);
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(new ShriekParticleOption(0));//Optional.of(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, MobEffects.HEAL.value().getColor()));
    }

    @Override
    public void onAntiMagic(MagicData magicData) {
        discard();
    }
}
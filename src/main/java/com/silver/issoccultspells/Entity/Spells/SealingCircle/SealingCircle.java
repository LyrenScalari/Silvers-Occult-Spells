package com.silver.issoccultspells.Entity.Spells.SealingCircle;

import com.silver.issoccultspells.registries.EntityRegistry;
import com.silver.issoccultspells.registries.MobEffectRegistry;
import com.silver.issoccultspells.registries.OccultSpellRegistry;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import io.redspace.ironsspellbooks.api.events.CounterSpellEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastResult;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.effect.MagicMobEffect;
import io.redspace.ironsspellbooks.entity.mobs.AntiMagicSusceptible;
import io.redspace.ironsspellbooks.entity.mobs.IMagicSummon;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.entity.spells.root.RootEntity;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.particle.EnderSlashParticleOptions;
import io.redspace.ironsspellbooks.particle.SoulfireRayParticleOptions;
import io.redspace.ironsspellbooks.player.ClientSpellCastHelper;
import io.redspace.ironsspellbooks.spells.ender.CounterspellSpell;
import io.redspace.ironsspellbooks.util.ModTags;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import org.joml.Vector3f;

import java.util.Optional;

public class SealingCircle extends AoeEntity implements AntiMagicSusceptible {
    public float RootHealth = 40;
    public float RootDuration = 5 * 20;
    public SealingCircle(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public SealingCircle(Level level) {
        this(EntityRegistry.SEALING_CIRCLE.get(), level);
    }

    @Override
    public void applyEffect(LivingEntity target) {
        if (getOwner() instanceof LivingEntity owner && Utils.validAntiMagicTarget(target)) {
            if (!(NeoForge.EVENT_BUS.post(new CounterSpellEvent(owner, target)).isCanceled())) {
                if (target instanceof AntiMagicSusceptible antiMagicSusceptible) {
                    if (antiMagicSusceptible instanceof IMagicSummon summon) {
                        if (summon.getSummoner() == owner) {
                            if (summon instanceof Mob mob && mob.getTarget() == null) {
                                antiMagicSusceptible.onAntiMagic(MagicData.getPlayerMagicData(owner));
                            }
                        } else {
                            antiMagicSusceptible.onAntiMagic(MagicData.getPlayerMagicData(owner));
                        }
                    } else {
                        antiMagicSusceptible.onAntiMagic(MagicData.getPlayerMagicData(owner));
                    }
                } else if (target instanceof ServerPlayer serverPlayer) {
                    Utils.serverSideCancelCast(serverPlayer, true);
                    MagicData.getPlayerMagicData(serverPlayer).getPlayerRecasts().removeAll(RecastResult.COUNTERSPELL);
                } else if (target instanceof IMagicEntity abstractSpellCastingMob) {
                    abstractSpellCastingMob.cancelCast();
                }
                if (target instanceof LivingEntity livingEntity) {
                    //toList to avoid concurrent modification
                    for (Holder<MobEffect> mobEffect : livingEntity.getActiveEffectsMap().keySet().stream().toList()) {
                        if (mobEffect.value() instanceof MagicMobEffect) {
                            livingEntity.removeEffect(mobEffect);
                        }
                    }
                }
            }
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
        return Optional.of(new SoulfireRayParticleOptions(getX(),getY(),getZ()));//Optional.of(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, MobEffects.HEAL.value().getColor()));
    }

    @Override
    public void onAntiMagic(MagicData magicData) {
        magicData.setMana(0);
        magicData.getPlayerCooldowns().addCooldown(magicData.getCastingSpellId(),1200);
    }
}

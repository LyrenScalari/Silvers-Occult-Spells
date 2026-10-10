package com.silver.issoccultspells.Spells;

import com.silver.issoccultspells.Entity.Spells.SacredSoil.SacredSoilAoe;
import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import com.silver.issoccultspells.registries.MobEffectRegistry;
import com.silver.issoccultspells.registries.OccultSpellRegistry;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.config.SpellConfigManager;
import io.redspace.ironsspellbooks.api.config.SpellConfigParameter;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.RaycastBuilder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.target_area.TargetedAreaEntity;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class CircleofScornSpell extends AbstractSpell {
    private static final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(IronsSpellbooksOccultSpells.MODID,"circleofscorn");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getHealing(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(5, 1)) );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(ASSchoolRegistry.RITUAL_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(35)
            .build();

    public CircleofScornSpell() {
        this.manaCostPerLevel = 3;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 4;
        this.castTime = 60;
        this.baseManaCost = 10;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }
    private float getHealing(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster) * .5f;
    }

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .15f, false);
        return true;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(ASSchoolRegistry.RITUAL.get().getTargetingColor(), 1.2f), entity.getX(), entity.getY(), entity.getZ(), 15, .1, .1, .1, .18, true);
        level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(5)).forEach((target) -> {
            if (target.distanceToSqr(entity.position()) < 5 * 5 && !Utils.shouldHealEntity((Entity) entity, target)) {
                DamageSources.applyDamage(entity, getDamage(playerMagicData.getCastingSpellLevel(),entity), OccultSpellRegistry.CIRCLE_OF_SCORN.get().getDamageSource(entity, entity));
            }
        });
        MagicManager.spawnParticles(level, new SculkChargeParticleOptions(0), entity.getX(), entity.getY(), entity.getZ(), 15, .1, .1, .1, .18, true);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity);
    }

    public float getSpellPower(int spellLevel, @Nullable Entity sourceEntity) {

        double entitySpellPowerModifier = 1;
        double entitySchoolPowerModifier = 1;

        float configPowerModifier = SpellConfigManager.getSpellConfigValue(this, SpellConfigParameter.POWER_MULTIPLIER).floatValue();
        //int level = getLevel(spellLevel, null);
        if (sourceEntity instanceof LivingEntity livingEntity) {
            //level = getLevel(spellLevel, livingEntity);
            entitySpellPowerModifier = (float) livingEntity.getAttributeValue(AttributeRegistry.SPELL_POWER);
            entitySchoolPowerModifier = (this.getSchoolType().getPowerFor(livingEntity) - 1) + (SchoolRegistry.NATURE.get().getPowerFor(livingEntity) * 0.5) + (SchoolRegistry.HOLY.get().getPowerFor(livingEntity) * 0.5);
        }

        return (float) ((baseSpellPower + spellPowerPerLevel * (spellLevel - 1)) * entitySpellPowerModifier * entitySchoolPowerModifier * configPowerModifier);
    }
    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.CAST_KNEELING_PRAYER;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.SELF_CAST_TWO_HANDS;
    }
}

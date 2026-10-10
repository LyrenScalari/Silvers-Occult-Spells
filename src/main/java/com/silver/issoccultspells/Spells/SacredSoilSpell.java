package com.silver.issoccultspells.Spells;

import com.silver.issoccultspells.Entity.Spells.Mudo.MudoEntity;
import com.silver.issoccultspells.Entity.Spells.SacredSoil.SacredSoilAoe;
import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
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
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.entity.spells.target_area.TargetedAreaEntity;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class SacredSoilSpell extends AbstractSpell {
    private static final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(IronsSpellbooksOccultSpells.MODID,"sacredsoil");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 1)),
                Component.translatable("ui.irons_spellbooks.duration", Utils.timeFromTicks(getDuration(spellLevel, caster), 1)),
                Component.translatable("ui.issoccultspells.root_duration", Utils.timeFromTicks((20*5) * getEntityPowerMultiplier(caster), 1)),
                Component.translatable("ui.irons_spellbooks.hp", Utils.stringTruncation(5 * (getSpellPower(spellLevel, caster)), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(ASSchoolRegistry.RITUAL_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(300)
            .build();

    public SacredSoilSpell() {
        this.manaCostPerLevel = 80;
        this.baseSpellPower = 2;
        this.spellPowerPerLevel = 2;
        this.castTime = 200;
        this.baseManaCost = 100;
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
        return getSpellPower(spellLevel, caster) * .25f;
    }

    private float getRadius(int spellLevel, LivingEntity caster) {
        return 5+getSpellPower(spellLevel, caster);
    }

    private int getDuration(int spellLevel, LivingEntity caster) {
        return (int)(1200+((getSpellPower(spellLevel, caster)) * 20));
    }
    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        Utils.preCastTargetHelper(level, entity, playerMagicData, this, 32, .15f, false);
        return true;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        Vec3 spawn = null;
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData castTargetingData) {
            var target = castTargetingData.getTarget((ServerLevel) level);
            if (target != null)
                spawn = target.position();
        }
        if (spawn == null) {
            spawn = RaycastBuilder.begin(level, entity)
                    .range(32)
                    .checkForBlocks(true)
                    .bbInflation(.15f)
                    .build()
                    .getLocation();
            spawn = Utils.moveToRelativeGroundLevel(level, spawn, 6);
        }

        int duration = getDuration(spellLevel, entity);
        float radius = getRadius(spellLevel, entity);


        SacredSoilAoe aoeEntity = new SacredSoilAoe(level);
        aoeEntity.setOwner(entity);
        aoeEntity.setCircular();
        aoeEntity.setRadius(radius);
        aoeEntity.setDuration(duration);
        aoeEntity.setDamage(getHealing(spellLevel, entity));
        aoeEntity.setPos(spawn);
        aoeEntity.RootHealth = 40 * getEntityPowerMultiplier(entity);
        aoeEntity.RootDuration = 20 * (5 * (getSpellPower(spellLevel, entity)));
        level.addFreshEntity(aoeEntity);

        TargetedAreaEntity visualEntity = TargetedAreaEntity.createTargetAreaEntity(level, spawn, radius, 0x870b32);
        visualEntity.setDuration(duration);
        visualEntity.setOwner(aoeEntity);
        visualEntity.setShouldFade(true);

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return  getSpellPower(spellLevel, entity) * .5f;
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

package com.silver.issoccultspells.Spells;

import com.silver.issoccultspells.Entity.Spells.Mudo.MudoEntity;
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
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.List;

public class MudoSpell extends AbstractSpell {
    private static final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(IronsSpellbooksOccultSpells.MODID,"mudo");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getSpellPower(spellLevel, caster), 1)),
                Component.translatable("ui.issoccultspells.hexpotency", Utils.stringTruncation(Math.max(Math.floor(1*((getSpellPower(spellLevel, caster) / 6)-1)),1), 1)));

    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(ASSchoolRegistry.RITUAL_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(15)
            .build();

    public MudoSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 3;
        this.castTime = 40;
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

    @Override
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 64, .35f);
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        MudoEntity mudoWisp = new MudoEntity(level, entity);
        mudoWisp.setDamage(getSpellPower(spellLevel, entity));
        mudoWisp.hexpotency = (int) Math.max(1*((getSpellPower(spellLevel, entity) / 6)-1),0);
        mudoWisp.setPos(entity.position().add(0, entity.getEyeHeight() - mudoWisp.getBoundingBox().getYsize() * .5f,0));
        mudoWisp.shoot(entity.getLookAngle());
        level.addFreshEntity(mudoWisp);

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
        return SpellAnimations.PREPARE_CROSS_ARMS;
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.ONE_HANDED_RAY_SHOOT;
    }
}

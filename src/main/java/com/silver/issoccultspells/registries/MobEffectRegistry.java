package com.silver.issoccultspells.registries;

import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import com.silver.issoccultspells.effect.HealersProvidenceEffect;
import com.silver.issoccultspells.effect.HexEffect;
import com.silver.issoccultspells.effect.NaturesGraceEffect;
import io.redspace.ironslib.registry.IronsLibRegistries;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.effect.*;
import io.redspace.ironsspellbooks.effect.guiding_bolt.GuidingBoltEffect;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MobEffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, IronsSpellbooksOccultSpells.MODID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }
    public static final DeferredHolder<MobEffect, MobEffect> HEX = MOB_EFFECT_DEFERRED_REGISTER.register("hex", () -> new HexEffect(MobEffectCategory.HARMFUL, 0x870b32)
            .addAttributeModifier(AttributeRegistry.NATURE_MAGIC_RESIST, IronsSpellbooksOccultSpells.id("hex"), -.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
            .addAttributeModifier(AttributeRegistry.HOLY_MAGIC_RESIST, IronsSpellbooksOccultSpells.id("hex"), -.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
            .addAttributeModifier(ASAttributeRegistry.RITUAL_MAGIC_RESIST, IronsSpellbooksOccultSpells.id("hex"), -.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    public static final DeferredHolder<MobEffect, MobEffect> NATURES_GRACE = MOB_EFFECT_DEFERRED_REGISTER.register("natures_grace", () ->
            new NaturesGraceEffect(MobEffectCategory.BENEFICIAL, 0x0b6317)
            .addAttributeModifier(AttributeRegistry.MANA_REGEN, IronsSpellbooksOccultSpells.id("natures_grace"), .1, AttributeModifier.Operation.ADD_VALUE));
    public static final DeferredHolder<MobEffect, MobEffect> HEALERS_PROVIDENCE = MOB_EFFECT_DEFERRED_REGISTER.register("healers_providence", () ->
            new HealersProvidenceEffect(MobEffectCategory.BENEFICIAL, 0xEFBF04)
                    .addAttributeModifier(ASAttributeRegistry.LIFE_RECOVERY, IronsSpellbooksOccultSpells.id("healers_providence"), .1, AttributeModifier.Operation.ADD_VALUE));

}
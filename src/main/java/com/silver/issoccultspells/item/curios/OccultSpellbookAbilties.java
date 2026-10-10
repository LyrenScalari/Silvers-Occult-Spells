package com.silver.issoccultspells.item.curios;

import com.silver.issoccultspells.item.BottleSpellItem;
import com.silver.issoccultspells.registries.MobEffectRegistry;
import com.silver.issoccultspells.registries.OccultItemRegistry;
import io.redspace.ironsspellbooks.api.events.SpellOnCastEvent;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.item.Scroll;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class OccultSpellbookAbilties {
    @SubscribeEvent
    public static void NatureManaRegenAbility(SpellOnCastEvent event)
    {
        var spellbook = ((OccultPassiveAbilitySpellbook) OccultItemRegistry.HERBALIST_SPELL_BOOK.get());
        if (event.getSchoolType() == SchoolRegistry.NATURE.get()){
            if (spellbook.tryProcCooldown(event.getEntity())){
                event.getEntity().addEffect(new MobEffectInstance(MobEffectRegistry.NATURES_GRACE.getDelegate(), 20*10, 0));
            }
        }
    }
    @SubscribeEvent
    public static void HolyLifeRecoveryAbility(SpellOnCastEvent event)
    {
        var spellbook =  ((OccultPassiveAbilitySpellbook) OccultItemRegistry.CLERIC_SPELL_BOOK.get());
        if (event.getSchoolType() == SchoolRegistry.HOLY.get()){
            if (spellbook.tryProcCooldown(event.getEntity())){
                event.getEntity().addEffect(new MobEffectInstance(MobEffectRegistry.HEALERS_PROVIDENCE.getDelegate(), 20*10, 0));
            }
        }
    }
    @SubscribeEvent
    public static void ApothecaryNotebookAbility(SpellOnCastEvent event)
    {
        var spellbook = ((OccultPassiveAbilitySpellbook) OccultItemRegistry.ALCHEMIST_SPELL_BOOK.get());

        if (event.getSchoolType() == SchoolRegistry.HOLY.get()){
            if (spellbook.tryProcCooldown(event.getEntity())){
                event.getEntity().addEffect(new MobEffectInstance(MobEffectRegistry.HEALERS_PROVIDENCE.getDelegate(), 20*10, 0));
            }
        }
        if (event.getSchoolType() == SchoolRegistry.NATURE.get()){
            if (spellbook.tryProcCooldown(event.getEntity())){
                event.getEntity().addEffect(new MobEffectInstance(MobEffectRegistry.NATURES_GRACE.getDelegate(), 20*10, 0));
            }
        }
        if (event.getSchoolType() == ASSchoolRegistry.RITUAL.get()){
            if (spellbook.tryProcCooldown(event.getEntity())){
                event.getEntity().addEffect(new MobEffectInstance(MobEffectRegistry.HEALERS_PROVIDENCE.getDelegate(), 20*10, 0));
                event.getEntity().addEffect(new MobEffectInstance(MobEffectRegistry.NATURES_GRACE.getDelegate(), 20*10, 0));
            }
        }
    }
    @SubscribeEvent
    public static void BottledSpellRemoval(SpellOnCastEvent event)
    {
        if (event.getCastSource() == CastSource.SCROLL) {
            BottleSpellItem.attemptRemoveScrollAfterCast((ServerPlayer) event.getEntity());
        }
    }
}

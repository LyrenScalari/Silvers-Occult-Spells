package com.silver.issoccultspells.registries;

import com.silver.issoccultspells.Entity.Spells.SealingCircle.SealingCircle;
import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import com.silver.issoccultspells.Spells.CircleofScornSpell;
import com.silver.issoccultspells.Spells.MudoSpell;
import com.silver.issoccultspells.Spells.SacredSoilSpell;
import com.silver.issoccultspells.Spells.SealingCircleSpell;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class OccultSpellRegistry extends io.redspace.ironsspellbooks.api.registry.SpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(OccultSpellRegistry.SPELL_REGISTRY_KEY, IronsSpellbooksOccultSpells.MODID);

    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    private static Supplier<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    //Occult Spells
    public static final Supplier<AbstractSpell> MUDO = registerSpell(new MudoSpell());
    public static final Supplier<AbstractSpell> SACRED_SOIL = registerSpell(new SacredSoilSpell());

    public static final Supplier<AbstractSpell> CIRCLE_OF_SCORN = registerSpell(new CircleofScornSpell());
    public static final Supplier<AbstractSpell> SEALING_CIRCLE = registerSpell(new SealingCircleSpell());
}

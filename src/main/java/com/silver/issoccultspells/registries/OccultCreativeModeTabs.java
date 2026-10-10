package com.silver.issoccultspells.registries;
import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
public class OccultCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IronsSpellbooksOccultSpells.MODID);

    public static final Supplier<CreativeModeTab> ASU_ITEMS_TAB = CREATIVE_MODE_TAB.register("occultitems",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(OccultItemRegistry.MANDRAKE_ROOT))
                    .title(Component.translatable("creative_tab.issoccultspells.items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        // Items
                        output.accept(OccultItemRegistry.OCCULT_RUNE.get());
                        output.accept(OccultItemRegistry.MANDRAKE_ROOT.get());
                        output.accept(OccultItemRegistry.HEDGE_WIZARD_STAFF.get());
                        output.accept(OccultItemRegistry.HERBALIST_SPELL_BOOK.get());
                        output.accept(OccultItemRegistry.ACOLYTE_STAFF.get());
                        output.accept(OccultItemRegistry.CLERIC_SPELL_BOOK.get());
                        output.accept(OccultItemRegistry.CRONES_CANE.get());
                        output.accept(OccultItemRegistry.CRONE_SPELL_BOOK.get());
                        output.accept(OccultItemRegistry.ALCHEMIST_SPELL_BOOK.get());
                        output.accept(OccultItemRegistry.CADUCEUS.get());
                        // Armor
                        output.accept(OccultItemRegistry.HEDGE_WIZARD_HAT.get());
                        output.accept(OccultItemRegistry.HEDGE_WIZARD_CHESTPLATE.get());
                        output.accept(OccultItemRegistry.HEDGE_WIZARD_LEGGINGS.get());
                        output.accept(OccultItemRegistry.HEDGE_WIZARD_BOOTS.get());

                        output.accept(OccultItemRegistry.ACOLYTE_HAT.get());
                        output.accept(OccultItemRegistry.ACOLYTE_CHESTPLATE.get());
                        output.accept(OccultItemRegistry.ACOLYTE_LEGGINGS.get());
                        output.accept(OccultItemRegistry.ACOLYTE_BOOTS.get());

                        output.accept(OccultItemRegistry.CRONE_HELMET.get());
                        output.accept(OccultItemRegistry.CRONE_CHESTPLATE.get());
                        output.accept(OccultItemRegistry.CRONE_LEGGINGS.get());
                        output.accept(OccultItemRegistry.CRONE_BOOTS.get());

                        output.accept(OccultItemRegistry.APOTHECARY_HELMET.get());
                        output.accept(OccultItemRegistry.APOTHECARY_CHESTPLATE.get());
                        output.accept(OccultItemRegistry.APOTHECARY_LEGGINGS.get());
                        output.accept(OccultItemRegistry.APOTHECARY_BOOTS.get());

                        output.accept(OccultItemRegistry.CAUSTIC_BREW.get());
                        output.accept(OccultItemRegistry.ACIDIC_SPRAYMIST.get());
                        output.accept(OccultItemRegistry.BLIGHTED_AROMATIC.get());
                        output.accept(OccultItemRegistry.BINDING_AROMATIC.get());
                        output.accept(OccultItemRegistry.JAR_OF_FIREFLIES.get());

                        output.accept(OccultItemRegistry.CURATIVE_BREW.get());
                        output.accept(OccultItemRegistry.FORTIFYING_AROMATIC.get());
                        output.accept(OccultItemRegistry.LUMINOUS_SPRAYMIST.get());
                        output.accept(OccultItemRegistry.REJUVENATING_AROMATIC.get());
                        output.accept(OccultItemRegistry.WISP_JAR.get());
                    }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
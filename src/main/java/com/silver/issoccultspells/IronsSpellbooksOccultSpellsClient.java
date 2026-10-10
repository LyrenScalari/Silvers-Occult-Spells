package com.silver.issoccultspells;

import com.silver.issoccultspells.registries.OccultFluidRegistry;
import com.silver.issoccultspells.registries.OccultItemRegistry;
import io.redspace.ironsspellbooks.fluids.SimpleClientFluidType;
import io.redspace.ironsspellbooks.fluids.SimpleTintedClientFluidType;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.minecraft.world.item.component.DyedItemColor;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = IronsSpellbooksOccultSpells.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = IronsSpellbooksOccultSpells.MODID, value = Dist.CLIENT)
public class IronsSpellbooksOccultSpellsClient {
    public IronsSpellbooksOccultSpellsClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        IronsSpellbooksOccultSpells.LOGGER.info("HELLO FROM CLIENT SETUP");
        IronsSpellbooksOccultSpells.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/water_still"), 0xFFcaff95), OccultFluidRegistry.FOREST_BREW_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFbcf1a3), OccultFluidRegistry.FOREST_BREW_INCOMPLETE2_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFF23af26), OccultFluidRegistry.FOREST_BREW_INCOMPLETE3_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF23af26), OccultFluidRegistry.FOREST_BREW_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF3BC61B), OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF52DD0F), OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE2_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF6af404), OccultFluidRegistry.CAUSTIC_BREW_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"),0xFF6af404), OccultFluidRegistry.ACIDIC_SPRAYMIST_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF0b6138), OccultFluidRegistry.BLIGHTED_AROMATIC_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF2F8E31), OccultFluidRegistry.BINDING_AROMATIC_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF3C6E3D), OccultFluidRegistry.BINDING_AROMATIC_INCOMPLETE2_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF484D48), OccultFluidRegistry.BINDING_AROMATIC_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFF67B41B), OccultFluidRegistry.JAR_OF_FIREFLIES_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFFABBA0F), OccultFluidRegistry.JAR_OF_FIREFLIES_INCOMPLETE2_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/lava_still"), 0xFFEFBF04), OccultFluidRegistry.JAR_OF_FIREFLIES_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/water_still"), 0xFFFADF65), OccultFluidRegistry.RADIANT_BREW_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFF7D74D), OccultFluidRegistry.RADIANT_BREW_INCOMPLETE2_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFF2C71C), OccultFluidRegistry.RADIANT_BREW_INCOMPLETE3_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFfcd94b), OccultFluidRegistry.RADIANT_BREW_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFE6A432), OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFD36D8F), OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE2_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/water_still"), 0xFFCD5CAB), OccultFluidRegistry.CURATIVE_BREW_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/water_still"),0xFFAE51CC), OccultFluidRegistry.FORTIFYING_AROMATIC_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.withDefaultNamespace("block/water_still"), 0xFFE36D67), OccultFluidRegistry.REJUVENATING_AROMATIC_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFfcd94b), OccultFluidRegistry.LUMINOUS_SPRAYMIST_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFfcd94b), OccultFluidRegistry.LUMINOUS_SPRAYMIST_INCOMPLETE2_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFFfcd94b), OccultFluidRegistry.LUMINOUS_SPRAYMIST_TYPE);

        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFF90C37E), OccultFluidRegistry.WISP_JAR_INCOMPLETE1_TYPE);
        event.registerFluidType(new SimpleTintedClientFluidType(ResourceLocation.fromNamespaceAndPath("neoforge","block/milk_still"), 0xFF48B4A0), OccultFluidRegistry.WISP_JAR_INCOMPLETE2_TYPE);
        event.registerFluidType(new SimpleClientFluidType(IronsSpellbooksOccultSpells.id("block/wisp_jar")), OccultFluidRegistry.WISP_JAR_TYPE);
    }
    @SubscribeEvent
    public static void registerDyeables(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, layer) -> layer > 0 ? DyedItemColor.getOrDefault(stack, 0xFFffc617) : DyedItemColor.getOrDefault(stack, 0xFF234e2e),
                OccultItemRegistry.HEDGE_WIZARD_HAT.get(), OccultItemRegistry.HEDGE_WIZARD_LEGGINGS.get(), OccultItemRegistry.HEDGE_WIZARD_CHESTPLATE.get(), OccultItemRegistry.HEDGE_WIZARD_BOOTS.get());

        event.register((stack, layer) -> layer > 0 ? DyedItemColor.getOrDefault(stack, 0xFFDEA193) : DyedItemColor.getOrDefault(stack, 0xFF705D46),
            OccultItemRegistry.ACOLYTE_HAT.get(), OccultItemRegistry.ACOLYTE_LEGGINGS.get(), OccultItemRegistry.ACOLYTE_CHESTPLATE.get(), OccultItemRegistry.ACOLYTE_BOOTS.get());
    }
}

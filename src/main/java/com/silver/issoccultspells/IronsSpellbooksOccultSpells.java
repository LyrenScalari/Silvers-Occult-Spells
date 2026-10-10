package com.silver.issoccultspells;

import com.silver.issoccultspells.Entity.Spells.Mudo.MudoEntityRenderer;
import com.silver.issoccultspells.registries.*;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(IronsSpellbooksOccultSpells.MODID)
public class IronsSpellbooksOccultSpells {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "issoccultspells";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public IronsSpellbooksOccultSpells(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (IronsSpellbooksOccultSpells) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        EntityRegistry.register(modEventBus);
        MobEffectRegistry.register(modEventBus);
        OccultSpellRegistry.register(modEventBus);
        OccultFluidRegistry.register(modEventBus);
        OccultItemRegistry.register(modEventBus);
        OccultCreativeModeTabs.register(modEventBus);
        //ItemRegistry.register(modEventBus);
        // Register the item to a creative tab

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    private void clientSetup(FMLClientSetupEvent event) {
        // Some common setup code
        EntityRenderers.register(EntityRegistry.MUDO.get(), MudoEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.SACRED_SOIL_AOE.get(), NoopRenderer::new);
        EntityRenderers.register(EntityRegistry.SEALING_CIRCLE.get(), NoopRenderer::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    public static ResourceLocation id(@NotNull String path) {
        return ResourceLocation.fromNamespaceAndPath(IronsSpellbooksOccultSpells.MODID, path);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}

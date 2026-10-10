package com.silver.issoccultspells.registries;

import com.silver.issoccultspells.Entity.Spells.Mudo.MudoEntity;
import com.silver.issoccultspells.Entity.Spells.SacredSoil.SacredSoilAoe;
import com.silver.issoccultspells.Entity.Spells.SealingCircle.SealingCircle;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.entity.spells.HealingAoe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class EntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, IronsSpellbooks.MODID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final DeferredHolder<EntityType<?>, EntityType<MudoEntity>> MUDO =
            ENTITIES.register("mudo", () -> EntityType.Builder.<MudoEntity>of(MudoEntity::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "wisp").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<SacredSoilAoe>> SACRED_SOIL_AOE =
            ENTITIES.register("sacred_soil", () -> EntityType.Builder.<SacredSoilAoe>of(SacredSoilAoe::new, MobCategory.MISC)
                    .sized(4f, .8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "healing_aoe").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<SealingCircle>> SEALING_CIRCLE =
            ENTITIES.register("sealing_circle", () -> EntityType.Builder.<SealingCircle>of(SealingCircle::new, MobCategory.MISC)
                    .sized(4f, .8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(IronsSpellbooks.MODID, "healing_aoe").toString()));

}
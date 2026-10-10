package com.silver.issoccultspells.registries;

import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.fluids.NoopFluid;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;
public class OccultFluidRegistry {
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, IronsSpellbooksOccultSpells.MODID);
    private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, IronsSpellbooksOccultSpells.MODID);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
        FLUID_TYPES.register(eventBus);
    }

    public static final DeferredHolder<FluidType, FluidType> FOREST_BREW_TYPE = FLUID_TYPES.register("forest_brew", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> FOREST_BREW = registerNoop("forest_brew", FOREST_BREW_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> FOREST_BREW_INCOMPLETE1_TYPE = FLUID_TYPES.register("forest_brew_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> FOREST_BREW_INCOMPLETE1 = registerNoop("forest_brew_incomplete1", FOREST_BREW_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> FOREST_BREW_INCOMPLETE2_TYPE = FLUID_TYPES.register("forest_brew_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> FOREST_BREW_INCOMPLETE2 = registerNoop("forest_brew_incomplete2", FOREST_BREW_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> FOREST_BREW_INCOMPLETE3_TYPE = FLUID_TYPES.register("forest_brew_incomplete3", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> FOREST_BREW_INCOMPLETE3 = registerNoop("forest_brew_incomplete3", FOREST_BREW_INCOMPLETE3_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType>  CAUSTIC_BREW_TYPE = FLUID_TYPES.register("caustic_brew", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> CAUSTIC_BREW = registerNoop("caustic_brew", CAUSTIC_BREW_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> CAUSTIC_BREW_INCOMPLETE1_TYPE = FLUID_TYPES.register("caustic_brew_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> CAUSTIC_BREW_INCOMPLETE1 = registerNoop("caustic_brew_incomplete1", CAUSTIC_BREW_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> CAUSTIC_BREW_INCOMPLETE2_TYPE = FLUID_TYPES.register("caustic_brew_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> CAUSTIC_BREW_INCOMPLETE2 = registerNoop("caustic_brew_incomplete2", CAUSTIC_BREW_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> BLIGHTED_AROMATIC_TYPE = FLUID_TYPES.register("blighted_aromatic", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> BLIGHTED_AROMATIC = registerNoop("blighted_aromatic", BLIGHTED_AROMATIC_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> ACIDIC_SPRAYMIST_TYPE = FLUID_TYPES.register("acidic_spraymist", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> ACIDIC_SPRAYMIST = registerNoop("acidic_spraymist", ACIDIC_SPRAYMIST_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType>  BINDING_AROMATIC_TYPE = FLUID_TYPES.register("binding_aromatic", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> BINDING_AROMATIC = registerNoop("binding_aromatic", BINDING_AROMATIC_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> BINDING_AROMATIC_INCOMPLETE1_TYPE = FLUID_TYPES.register("binding_aromatic_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> BINDING_AROMATIC_INCOMPLETE1 = registerNoop("binding_aromatic_incomplete1", BINDING_AROMATIC_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> BINDING_AROMATIC_INCOMPLETE2_TYPE = FLUID_TYPES.register("binding_aromatic_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> BINDING_AROMATIC_INCOMPLETE2 = registerNoop("binding_aromatic_incomplete2", BINDING_AROMATIC_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType>  JAR_OF_FIREFLIES_TYPE = FLUID_TYPES.register("jar_of_fireflies", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> JAR_OF_FIREFLIES = registerNoop("jar_of_fireflies", JAR_OF_FIREFLIES_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> JAR_OF_FIREFLIES_INCOMPLETE1_TYPE = FLUID_TYPES.register("jar_of_fireflies_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> JAR_OF_FIREFLIES_INCOMPLETE1 = registerNoop("jar_of_fireflies_incomplete1", JAR_OF_FIREFLIES_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> JAR_OF_FIREFLIES_INCOMPLETE2_TYPE = FLUID_TYPES.register("jar_of_fireflies_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> JAR_OF_FIREFLIES_INCOMPLETE2 = registerNoop("jar_of_fireflies_incomplete2", JAR_OF_FIREFLIES_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> RADIANT_BREW_TYPE = FLUID_TYPES.register("radiant_brew", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> RADIANT_BREW = registerNoop("radiant_brew", RADIANT_BREW_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> RADIANT_BREW_INCOMPLETE1_TYPE = FLUID_TYPES.register("radiant_brew_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> RADIANT_BREW_INCOMPLETE1 = registerNoop("radiant_brew_incomplete1", RADIANT_BREW_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> RADIANT_BREW_INCOMPLETE2_TYPE = FLUID_TYPES.register("radiant_brew_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> RADIANT_BREW_INCOMPLETE2 = registerNoop("radiant_brew_incomplete2", RADIANT_BREW_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> RADIANT_BREW_INCOMPLETE3_TYPE = FLUID_TYPES.register("radiant_brew_incomplete3", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> RADIANT_BREW_INCOMPLETE3 = registerNoop("radiant_brew_incomplete3", RADIANT_BREW_INCOMPLETE3_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType>  CURATIVE_BREW_TYPE = FLUID_TYPES.register("curative_brew", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> CURATIVE_BREW = registerNoop("curative_brew", CURATIVE_BREW_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> CURATIVE_BREW_INCOMPLETE1_TYPE = FLUID_TYPES.register("curative_brew_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> CURATIVE_BREW_INCOMPLETE1 = registerNoop("curative_brew_incomplete1", CURATIVE_BREW_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> CURATIVE_BREW_INCOMPLETE2_TYPE = FLUID_TYPES.register("curative_brew_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> CURATIVE_BREW_INCOMPLETE2 = registerNoop("curative_brew_incomplete2", CURATIVE_BREW_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> FORTIFYING_AROMATIC_TYPE = FLUID_TYPES.register("fortifying_aromatic", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> FORTIFYING_AROMATIC = registerNoop("fortifying_aromatic", FORTIFYING_AROMATIC_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> REJUVENATING_AROMATIC_TYPE = FLUID_TYPES.register("rejuvenating_aromatic", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> REJUVENATING_AROMATIC = registerNoop("rejuvenating_aromatic", REJUVENATING_AROMATIC_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType>  LUMINOUS_SPRAYMIST_TYPE = FLUID_TYPES.register("luminous_spraymist", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> LUMINOUS_SPRAYMIST = registerNoop("luminous_spraymist", LUMINOUS_SPRAYMIST_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> LUMINOUS_SPRAYMIST_INCOMPLETE1_TYPE = FLUID_TYPES.register("luminous_spraymist_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> LUMINOUS_SPRAYMIST_INCOMPLETE1 = registerNoop("luminous_spraymist_incomplete1", LUMINOUS_SPRAYMIST_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> LUMINOUS_SPRAYMIST_INCOMPLETE2_TYPE = FLUID_TYPES.register("bluminous_spraymist_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> LUMINOUS_SPRAYMIST_INCOMPLETE2 = registerNoop("luminous_spraymist_incomplete2", LUMINOUS_SPRAYMIST_INCOMPLETE2_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> WISP_JAR_TYPE = FLUID_TYPES.register("wisp_jar", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> WISP_JAR = registerNoop("wisp_jar", WISP_JAR_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> WISP_JAR_INCOMPLETE1_TYPE = FLUID_TYPES.register("wisp_jar_incomplete1", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> WISP_JAR_INCOMPLETE1 = registerNoop("wisp_jar_incomplete1", WISP_JAR_INCOMPLETE1_TYPE::value);

    public static final DeferredHolder<FluidType, FluidType> WISP_JAR_INCOMPLETE2_TYPE = FLUID_TYPES.register("wisp_jar_incomplete2", () -> new FluidType(FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, NoopFluid> WISP_JAR_INCOMPLETE2 = registerNoop("wisp_jar_incomplete2", WISP_JAR_INCOMPLETE2_TYPE::value);

    private static DeferredHolder<Fluid, NoopFluid> registerNoop(String name, Supplier<FluidType> fluidType) {
        DeferredHolder<Fluid, NoopFluid> holder = DeferredHolder.create(Registries.FLUID, IronsSpellbooksOccultSpells.id(name));
        BaseFlowingFluid.Properties properties = new BaseFlowingFluid.Properties(fluidType, holder::value, holder::value).bucket(() -> Items.AIR);
        FLUIDS.register(name, () -> new NoopFluid(properties));
        return holder;
    }
}

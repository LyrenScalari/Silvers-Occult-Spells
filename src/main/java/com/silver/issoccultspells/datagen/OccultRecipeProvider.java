package com.silver.issoccultspells.datagen;
import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import com.silver.issoccultspells.registries.OccultFluidRegistry;
import com.silver.issoccultspells.registries.OccultItemRegistry;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.SchoolType;
import io.redspace.ironsspellbooks.fluids.PotionFluid;
import io.redspace.ironsspellbooks.recipe_types.alchemist_cauldron.BrewAlchemistCauldronRecipe;
import io.redspace.ironsspellbooks.recipe_types.alchemist_cauldron.EmptyAlchemistCauldronRecipe;
import io.redspace.ironsspellbooks.recipe_types.alchemist_cauldron.FillAlchemistCauldronRecipe;
import io.redspace.ironsspellbooks.registries.FluidRegistry;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import io.redspace.ironsspellbooks.registries.PotionRegistry;
import io.redspace.ironsspellbooks.util.ModTags;
import mezz.jei.api.constants.Tags;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.concurrent.CompletableFuture;
public class OccultRecipeProvider extends RecipeProvider {
    public OccultRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        occultArmorSmithing(recipeOutput, "crone");
        occultArmorSmithing(recipeOutput, "apothecary");

        // Misc
        BrewAlchemistCauldronRecipe.builder()
                .withInput(OccultFluidRegistry.FOREST_BREW, 1000)
                .withReagent(ItemRegistry.GOLD_SPELL_BOOK.get())
                .withResult(FluidRegistry.COMMON_INK, 250)
                .withByproduct(OccultItemRegistry.HERBALIST_SPELL_BOOK)
                .saveSoak(recipeOutput);
        // School Brews
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(FluidRegistry.OAKSKIN_ELIXIR_FLUID, 500))
                .withReagent(Items.POISONOUS_POTATO)
                .withResult(OccultFluidRegistry.FOREST_BREW_INCOMPLETE1, 500)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.FOREST_BREW_INCOMPLETE1, 500))
                .withReagent(Items.BROWN_MUSHROOM)
                .withResult(OccultFluidRegistry.FOREST_BREW_INCOMPLETE2, 750)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.FOREST_BREW_INCOMPLETE2, 750))
                .withReagent(Items.DANDELION)
                .withResult(OccultFluidRegistry.FOREST_BREW_INCOMPLETE3, 1000)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.FOREST_BREW_INCOMPLETE3, 1000))
                .withReagent(Items.HANGING_ROOTS)
                .withResult(OccultFluidRegistry.FOREST_BREW, 1000)
                .save(recipeOutput);


        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(FluidRegistry.GREATER_HEALING_ELIXIR_FLUID, 500))
                .withReagent(ItemRegistry.DIVINE_PEARL.get())
                .withResult(OccultFluidRegistry.RADIANT_BREW_INCOMPLETE1, 500)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.RADIANT_BREW_INCOMPLETE1, 500))
                .withReagent(Items.GHAST_TEAR)
                .withResult(OccultFluidRegistry.RADIANT_BREW_INCOMPLETE2, 750)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.RADIANT_BREW_INCOMPLETE2, 750))
                .withReagent(Items.OXEYE_DAISY)
                .withResult(OccultFluidRegistry.RADIANT_BREW_INCOMPLETE3, 1000)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.RADIANT_BREW_INCOMPLETE3, 1000))
                .withReagent(Items.SUGAR)
                .withResult(OccultFluidRegistry.RADIANT_BREW, 1000)
                .save(recipeOutput);
        // Bottled Spells
        //Caustic Brew, also base for Blighted Elixir and Sizzling Brew
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.FOREST_BREW, 500))
                .withReagent(Items.LILY_OF_THE_VALLEY)
                .withResult(OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE1, 500)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE1, 500))
                .withReagent(Items.PUFFERFISH)
                .withResult(OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE2, 750)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE2, 750))
                .withReagent(Items.SPIDER_EYE)
                .withResult(OccultFluidRegistry.CAUSTIC_BREW, 1000)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE2, 750))
                .withReagent(Items.FERMENTED_SPIDER_EYE)
                .withResult(OccultFluidRegistry.BLIGHTED_AROMATIC, 1000)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CAUSTIC_BREW_INCOMPLETE2, 750))
                .withReagent(Items.RED_MUSHROOM)
                .withResult(OccultFluidRegistry.ACIDIC_SPRAYMIST, 1000)
                .save(recipeOutput);

        //Binding Brew
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.FOREST_BREW, 500))
                .withReagent(Items.AZURE_BLUET)
                .withResult(OccultFluidRegistry.BINDING_AROMATIC_INCOMPLETE1, 500)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.BINDING_AROMATIC_INCOMPLETE1, 500))
                .withReagent(Items.COBWEB)
                .withResult(OccultFluidRegistry.BINDING_AROMATIC_INCOMPLETE2, 750)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.BINDING_AROMATIC_INCOMPLETE2, 500))
                .withReagent(Items.TURTLE_SCUTE)
                .withResult(OccultFluidRegistry.BINDING_AROMATIC, 1000)
                .save(recipeOutput);
        //Forest Glow Tonic
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.FOREST_BREW, 500))
                .withReagent(Items.GOLD_NUGGET)
                .withResult(OccultFluidRegistry.JAR_OF_FIREFLIES_INCOMPLETE1, 500)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.JAR_OF_FIREFLIES_INCOMPLETE1, 500))
                .withReagent(Items.GLOW_BERRIES)
                .withResult(OccultFluidRegistry.JAR_OF_FIREFLIES_INCOMPLETE2, 750)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.JAR_OF_FIREFLIES_INCOMPLETE2, 500))
                .withReagent(Items.FIRE_CHARGE)
                .withResult(OccultFluidRegistry.JAR_OF_FIREFLIES, 1000)
                .save(recipeOutput);

        //Curative Brew, also base for Fortifying Aromatic and Rejuvenating Aromatic
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.RADIANT_BREW, 500))
                .withReagent(Items.BLUE_ORCHID)
                .withResult(OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE1, 500)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE1, 500))
                .withReagent(Items.CARROT)
                .withResult(OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE2, 750)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE2, 750))
                .withReagent(Items.POPPY)
                .withResult(OccultFluidRegistry.CURATIVE_BREW, 1000)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE2, 750))
                .withReagent(Items.TURTLE_SCUTE)
                .withResult(OccultFluidRegistry.FORTIFYING_AROMATIC, 1000)
                .save(recipeOutput);

        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.CURATIVE_BREW_INCOMPLETE2, 750))
                .withReagent(Items.CAKE)
                .withResult(OccultFluidRegistry.REJUVENATING_AROMATIC, 1000)
                .save(recipeOutput);

        //Solar Tonic
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.RADIANT_BREW, 500))
                .withReagent(Items.DIAMOND)
                .withResult(OccultFluidRegistry.LUMINOUS_SPRAYMIST_INCOMPLETE1, 500)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.LUMINOUS_SPRAYMIST_INCOMPLETE1, 500))
                .withReagent(Items.GLOWSTONE)
                .withResult(OccultFluidRegistry.LUMINOUS_SPRAYMIST_INCOMPLETE2, 750)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.LUMINOUS_SPRAYMIST_INCOMPLETE2, 500))
                .withReagent(Items.GOLD_NUGGET)
                .withResult(OccultFluidRegistry.LUMINOUS_SPRAYMIST, 1000)
                .save(recipeOutput);
        //Essence of Wisp
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.RADIANT_BREW, 500))
                .withReagent(Items.GLOW_BERRIES)
                .withResult(OccultFluidRegistry.WISP_JAR_INCOMPLETE1, 500)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.WISP_JAR_INCOMPLETE1, 500))
                .withReagent(Items.AMETHYST_SHARD)
                .withResult(OccultFluidRegistry.WISP_JAR_INCOMPLETE2, 750)
                .save(recipeOutput);
        BrewAlchemistCauldronRecipe.builder()
                .withInput(new FluidStack(OccultFluidRegistry.WISP_JAR_INCOMPLETE2, 500))
                .withReagent(Items.GOLDEN_APPLE)
                .withResult(OccultFluidRegistry.WISP_JAR, 1000)
                .save(recipeOutput);

        // Extraction
        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.HANGING_ROOTS)
                .withReturnItem(OccultItemRegistry.MANDRAKE_ROOT.get())
                .withFluid(OccultFluidRegistry.FOREST_BREW, 50)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.CAUSTIC_BREW.get())
                .withFluid(OccultFluidRegistry.CAUSTIC_BREW, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.ACIDIC_SPRAYMIST.get())
                .withFluid(OccultFluidRegistry.ACIDIC_SPRAYMIST, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.BLIGHTED_AROMATIC.get())
                .withFluid(OccultFluidRegistry.BLIGHTED_AROMATIC, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.BINDING_AROMATIC.get())
                .withFluid(OccultFluidRegistry.BINDING_AROMATIC, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.JAR_OF_FIREFLIES.get())
                .withFluid(OccultFluidRegistry.JAR_OF_FIREFLIES, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.CURATIVE_BREW.get())
                .withFluid(OccultFluidRegistry.CURATIVE_BREW, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.FORTIFYING_AROMATIC.get())
                .withFluid(OccultFluidRegistry.FORTIFYING_AROMATIC, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.REJUVENATING_AROMATIC.get())
                .withFluid(OccultFluidRegistry.REJUVENATING_AROMATIC, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.LUMINOUS_SPRAYMIST.get())
                .withFluid(OccultFluidRegistry.LUMINOUS_SPRAYMIST, 250)
                .save(recipeOutput);

        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(Items.GLASS_BOTTLE)
                .withReturnItem(OccultItemRegistry.WISP_JAR.get())
                .withFluid(OccultFluidRegistry.WISP_JAR, 250)
                .save(recipeOutput);
    }

    /**
     * creates smithing recipe for school rune + wizard armor = school armor, for boots, leggings, chestplate, helmet
     */
    public static void occultArmorSmithing(RecipeOutput output, String armorName) {
        var base = new TagKey<?>[]{ModTags.BASE_WIZARD_BOOTS, ModTags.BASE_WIZARD_LEGGINGS, ModTags.BASE_WIZARD_CHESTPLATE, ModTags.BASE_WIZARD_HELMET};
        var slots = new ArmorItem.Type[]{ArmorItem.Type.BOOTS, ArmorItem.Type.LEGGINGS, ArmorItem.Type.CHESTPLATE, ArmorItem.Type.HELMET};
//        var armors = new Item[]{ItemRegistry.WIZARD_BOOTS.get(), ItemRegistry.WIZARD_LEGGINGS.get(), ItemRegistry.WIZARD_CHESTPLATE.get(), ItemRegistry.WIZARD_HELMET.get()};
//        var slots = new ArmorItem.Type[]{ArmorItem.Type.BOOTS, ArmorItem.Type.LEGGINGS, ArmorItem.Type.CHESTPLATE, ArmorItem.Type.HELMET};
        for (int i = 0; i < 4; i++) {
            var tag = (TagKey<Item>) base[i];
            Ingredient baseArmor = Ingredient.of(tag);
//            if (baseArmor.hasNoItems()) continue;
//            Item armorItem = baseArmor.getItems()[0].getItem();
            ResourceLocation itemId = ResourceLocation.fromNamespaceAndPath(IronsSpellbooksOccultSpells.MODID, String.format("%s_%s", armorName, slots[i].getName()));
            Item rune = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(ASSchoolRegistry.RITUAL_RESOURCE.getNamespace(), String.format("%s_rune", ASSchoolRegistry.RITUAL_RESOURCE.getPath())));
            ItemStack result = BuiltInRegistries.ITEM.get(itemId).getDefaultInstance();
            Item essence = ItemRegistry.ARCANE_ESSENCE.get();
            output.accept(itemId.withSuffix("_smithing"),
                    new SmithingTransformRecipe(Ingredient.of(rune), baseArmor, Ingredient.of(essence), result),
                    null
            );
        }
    }

    public static void cauldronBottledInteraction(RecipeOutput output, Holder<Item> item, Holder<Fluid> fluid) {
        cauldronTwoWayInteraction(output, item, Holder.direct(Items.GLASS_BOTTLE), fluid, 250);
    }

    /**
     * creates recipe for filling the cauldron via this item, and emptying the cauldron to this item
     */
    public static void cauldronTwoWayInteraction(RecipeOutput output, Holder<Item> item, Holder<Item> vessel, Holder<Fluid> fluid, int amount) {
        new FillAlchemistCauldronRecipe.Builder()
                .withFluid(fluid, amount)
                .withInput(item.value())
                .withReturnItem(vessel.value())
                .save(output);
        new EmptyAlchemistCauldronRecipe.Builder()
                .withInput(vessel.value())
                .withReturnItem(item.value())
                .withFluid(fluid, amount)
                .save(output);
    }

    protected void simpleRingSalvageRecipe(RecipeOutput output, Item result, Ingredient modifier) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .define('M', modifier)
                .define('X', ItemRegistry.MITHRIL_SCRAP.get())
                .pattern("M ")
                .pattern(" X")
                .unlockedBy("mithril_scrap", has(ItemRegistry.MITHRIL_SCRAP.get()))
                .save(output);
    }

    protected void simpleNecklaceSalvageRecipe(RecipeOutput output, Item result, Ingredient modifier, Ingredient strap) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .define('M', modifier)
                .define('X', ItemRegistry.MITHRIL_SCRAP.get())
                .define('S', strap)
                .pattern(" S ")
                .pattern("SXS")
                .pattern(" M ")
                .unlockedBy("mithril_scrap", has(ItemRegistry.MITHRIL_SCRAP.get()))
                .save(output);
    }

    protected void quadRingSalvageRecipe(RecipeOutput output, Item result, Ingredient modifier) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .define('M', modifier)
                .define('X', ItemRegistry.MITHRIL_SCRAP.get())
                .pattern(" M ")
                .pattern("MXM")
                .pattern(" M ")
                .unlockedBy("mithril_scrap", has(ItemRegistry.MITHRIL_SCRAP.get()))
                .save(output);
    }

}

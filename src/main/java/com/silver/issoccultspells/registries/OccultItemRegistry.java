package com.silver.issoccultspells.registries;
import com.silver.issoccultspells.IronsSpellbooksOccultSpells;
import com.silver.issoccultspells.item.BottleSpellItem;
import com.silver.issoccultspells.item.curios.OccultPassiveAbilitySpellbook;
import com.silver.issoccultspells.item.armor.AcolyteArmorItem;
import com.silver.issoccultspells.item.armor.ApothecaryArmorItem;
import com.silver.issoccultspells.item.armor.CroneArmorItem;
import com.silver.issoccultspells.item.armor.HedgeWizardArmorItem;
import com.silver.issoccultspells.item.weapon.OccultTeir;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.function.Function;

public class OccultItemRegistry {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, IronsSpellbooksOccultSpells.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    /**
     * Spell items
     */
    public static final DeferredHolder<Item, Item> HERBALIST_SPELL_BOOK = registerItem("herbalist_spell_book",
            (properties) -> new OccultPassiveAbilitySpellbook(8, properties.stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant())
                    .withSpellbookAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA, 75, AttributeModifier.Operation.ADD_VALUE),
                            new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)));
    public static final DeferredHolder<Item, Item> CLERIC_SPELL_BOOK = registerItem("cleric_spell_book",
            (properties) -> new OccultPassiveAbilitySpellbook(8, properties.stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant())
                    .withSpellbookAttributes(new AttributeContainer(AttributeRegistry.MAX_MANA, 75, AttributeModifier.Operation.ADD_VALUE),
                            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)));

    public static final DeferredHolder<Item, Item> CRONE_SPELL_BOOK = registerItem("crone_spell_book",
            (properties) -> new SpellBook(10, properties.stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant())
                    .withSpellbookAttributes(new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            new AttributeContainer(ASAttributeRegistry.RITUAL_MAGIC_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADD_VALUE)));
    public static final DeferredHolder<Item, Item> ALCHEMIST_SPELL_BOOK = registerItem("alchemist_spell_book",
            (properties) -> new OccultPassiveAbilitySpellbook(8, properties.stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), 5 * 20)
                    .withSpellbookAttributes(new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                            new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE)));

    public static final DeferredHolder<Item, Item> HEDGE_WIZARD_STAFF = registerItem("hedge_wizard_staff",
            (properties) -> new StaffItem(properties.stacksTo(1).fireResistant().attributes(ExtendedSwordItem.createAttributes(OccultTeir.HEDGE_WIZARD))));
    public static final DeferredHolder<Item, Item> ACOLYTE_STAFF = registerItem("acolyte_staff",
            (properties) -> new StaffItem(properties.stacksTo(1).fireResistant().attributes(ExtendedSwordItem.createAttributes(OccultTeir.ACOLYTE))));
    public static final DeferredHolder<Item, Item> CRONES_CANE = registerItem("crones_cane",
            (properties) -> new StaffItem(properties.stacksTo(1).fireResistant().attributes(ExtendedSwordItem.createAttributes(OccultTeir.CRONE))));
    public static final DeferredHolder<Item, Item> CADUCEUS = registerItem("caduceus_staff",
            (properties) -> new StaffItem(properties.stacksTo(1).fireResistant().attributes(ExtendedSwordItem.createAttributes(OccultTeir.CADUCEUS))));

    /**
     * Generic Items
     */
    public static final DeferredHolder<Item, Item> MANDRAKE_ROOT = registerItem("mandrake_root", Item::new);
    public static final DeferredHolder<Item, Item> OCCULT_RUNE = registerItem("occult_rune", Item::new);

    /**
     * Bottled Spells
     */
    public static final DeferredHolder<Item, Item> CAUSTIC_BREW = registerItem("caustic_brew", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.POISON_SPLASH_SPELL, 6)},properties.stacksTo(4)));
    public static final DeferredHolder<Item, Item> BLIGHTED_AROMATIC = registerItem("blighted_aromatic", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.BLIGHT_SPELL, 5)},properties.stacksTo(4)));
    public static final DeferredHolder<Item, Item> ACIDIC_SPRAYMIST = registerItem("acidic_spraymist", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.ACID_ORB_SPELL, 5)},properties.stacksTo(4)));

    public static final DeferredHolder<Item, Item> BINDING_AROMATIC = registerItem("binding_aromatic", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.ROOT_SPELL, 6)},properties.stacksTo(4)));

    public static final DeferredHolder<Item, Item> JAR_OF_FIREFLIES = registerItem("jar_of_fireflies", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.FIREFLY_SWARM_SPELL, 6)},properties.stacksTo(4)));

    public static final DeferredHolder<Item, Item> CURATIVE_BREW = registerItem("curative_brew", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.BLESSING_OF_LIFE_SPELL, 6)},properties.stacksTo(4)));
    public static final DeferredHolder<Item, Item> FORTIFYING_AROMATIC = registerItem("fortifying_aromatic", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.FORTIFY_SPELL, 5)},properties.stacksTo(4)));
    public static final DeferredHolder<Item, Item> LUMINOUS_SPRAYMIST = registerItem("luminous_spraymist", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.GUIDING_BOLT_SPELL, 5)},properties.stacksTo(4)));

    public static final DeferredHolder<Item, Item> REJUVENATING_AROMATIC = registerItem("rejuvenating_aromatic", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.CLEANSE_SPELL, 1)},properties.stacksTo(4)));

    public static final DeferredHolder<Item, Item> WISP_JAR = registerItem("wisp_jar", (properties) ->
            new BottleSpellItem(new SpellDataRegistryHolder[]{
                    new SpellDataRegistryHolder(SpellRegistry.WISP_SPELL, 6)},properties.stacksTo(4)));

    /**
     * Armor
     */
    // Specialized Wizard Armor side-grades
    public static final DeferredHolder<Item, Item> HEDGE_WIZARD_HAT = registerItem("hedge_wizard_hat",
            (properties) -> new HedgeWizardArmorItem(ArmorItem.Type.HELMET, properties.stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(37)).component(ComponentRegistry.CLOTHING_VARIANT, "hat")));
    public static final DeferredHolder<Item, Item> HEDGE_WIZARD_CHESTPLATE = registerItem("hedge_wizard_chestplate",
            (properties) -> new HedgeWizardArmorItem(ArmorItem.Type.CHESTPLATE, properties.stacksTo(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> HEDGE_WIZARD_LEGGINGS = registerItem("hedge_wizard_leggings",
            (properties) -> new HedgeWizardArmorItem(ArmorItem.Type.LEGGINGS, properties.stacksTo(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> HEDGE_WIZARD_BOOTS = registerItem("hedge_wizard_boots",
            (properties) -> new HedgeWizardArmorItem(ArmorItem.Type.BOOTS, properties.stacksTo(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));
    public static final DeferredHolder<Item, Item> ACOLYTE_HAT = registerItem("acolyte_hat",
            (properties) -> new AcolyteArmorItem(ArmorItem.Type.HELMET, properties.stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredHolder<Item, Item> ACOLYTE_CHESTPLATE = registerItem("acolyte_chestplate",
            (properties) -> new AcolyteArmorItem(ArmorItem.Type.CHESTPLATE, properties.stacksTo(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> ACOLYTE_LEGGINGS = registerItem("acolyte_leggings",
            (properties) -> new AcolyteArmorItem(ArmorItem.Type.LEGGINGS, properties.stacksTo(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> ACOLYTE_BOOTS = registerItem("acolyte_boots",
            (properties) -> new AcolyteArmorItem(ArmorItem.Type.BOOTS, properties.stacksTo(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));

    // Hybrid Class Gear
    public static final DeferredHolder<Item, Item> CRONE_HELMET = registerItem("crone_helmet",
            (properties) -> new CroneArmorItem(ArmorItem.Type.HELMET, properties.stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredHolder<Item, Item> CRONE_CHESTPLATE = registerItem("crone_chestplate",
            (properties) -> new CroneArmorItem(ArmorItem.Type.CHESTPLATE, properties.stacksTo(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> CRONE_LEGGINGS = registerItem("crone_leggings",
            (properties) -> new CroneArmorItem(ArmorItem.Type.LEGGINGS, properties.stacksTo(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> CRONE_BOOTS = registerItem("crone_boots",
            (properties) -> new CroneArmorItem(ArmorItem.Type.BOOTS, properties.stacksTo(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));
    public static final DeferredHolder<Item, Item> APOTHECARY_HELMET = registerItem("apothecary_helmet",
            (properties) -> new ApothecaryArmorItem(ArmorItem.Type.HELMET, properties.stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredHolder<Item, Item> APOTHECARY_CHESTPLATE = registerItem("apothecary_chestplate",
            (properties) -> new ApothecaryArmorItem(ArmorItem.Type.CHESTPLATE, properties.stacksTo(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> APOTHECARY_LEGGINGS = registerItem("apothecary_leggings",
            (properties) -> new ApothecaryArmorItem(ArmorItem.Type.LEGGINGS, properties.stacksTo(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> APOTHECARY_BOOTS = registerItem("apothecary_boots",
            (properties) -> new ApothecaryArmorItem(ArmorItem.Type.BOOTS, properties.stacksTo(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));
    public static Collection<DeferredHolder<Item, ? extends Item>> getIronsItems() {
        return ITEMS.getEntries();
    }

    /**
     * Item Registration Abstraction. Helpful in general, but especially to prepare for 26.1.2 Registration Changes
     */
    private static <T extends Item> DeferredHolder<Item, T> registerItem(String name, Function<Item.Properties, T> itemFactory) {
        return ITEMS.register(name, () -> itemFactory.apply(new Item.Properties()));
    }
}
package com.silver.issoccultspells.item.armor;

import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.DyeableArmorRenderer;
import io.redspace.ironsspellbooks.entity.armor.GenericArmorModel;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.entity.armor.WanderingMagicianModel;
import io.redspace.ironsspellbooks.entity.armor.priest.PriestArmorModel;
import io.redspace.ironsspellbooks.entity.armor.priest.PriestArmorRenderer;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.Map;

public class ApothecaryArmorItem extends ImbuableChestplateArmorItem implements IDisableJacket {

    public ApothecaryArmorItem(Type type, Properties settings) {
        super(ArmorMaterialRegistry.SCHOOL, type, settings, new AttributeContainer[]{
                new AttributeContainer(AttributeRegistry.MAX_MANA, 75, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .015, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .015, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MANA_REGEN, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new DyeableArmorRenderer<>(new GenericArmorModel<AcolyteArmorItem>("wizard")
                .variants(Map.of("hat", IronsSpellbooks.id("geo/wizard_armor_hat.geo.json"))));
    }
}

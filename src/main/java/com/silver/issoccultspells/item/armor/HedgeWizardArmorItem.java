package com.silver.issoccultspells.item.armor;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.armor.DyeableArmorRenderer;
import io.redspace.ironsspellbooks.entity.armor.GenericArmorModel;
import io.redspace.ironsspellbooks.entity.armor.GenericCustomArmorRenderer;
import io.redspace.ironsspellbooks.entity.armor.WanderingMagicianModel;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import io.redspace.ironsspellbooks.item.armor.ImbuableChestplateArmorItem;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.registries.ArmorMaterialRegistry;
import io.redspace.ironsspellbooks.registries.ComponentRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.Map;

public class HedgeWizardArmorItem extends ImbuableChestplateArmorItem implements IDisableJacket {

    public HedgeWizardArmorItem(Type type, Properties settings) {
        super(ArmorMaterialRegistry.SCHOOL, type, settings, new AttributeContainer[]{
                new AttributeContainer(AttributeRegistry.MAX_MANA, 125, AttributeModifier.Operation.ADD_VALUE),
                new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, .025, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
        });
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new DyeableArmorRenderer<>(new GenericArmorModel<AcolyteArmorItem>("wizard")
                .variants(Map.of("hat", IronsSpellbooks.id("geo/wizard_armor_hat.geo.json"))));
    }
}
package com.silver.issoccultspells.item.weapon;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import io.redspace.ironsspellbooks.item.weapons.StaffTier;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASSchoolRegistry;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class OccultTeir implements IronsWeaponTier {

    public static StaffTier CRONE = new StaffTier(4, -3,
            new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ASAttributeRegistry.RITUAL_MAGIC_POWER, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(ASAttributeRegistry.MANA_STEAL, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );
    public static StaffTier CADUCEUS = new StaffTier(4, -3,
            new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, .2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.MANA_REGEN, .2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );
    public static StaffTier HEDGE_WIZARD = new StaffTier(2, -3,
            new AttributeContainer(AttributeRegistry.NATURE_SPELL_POWER, .1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.MANA_REGEN, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );
    public static StaffTier ACOLYTE = new StaffTier(2, -3,
            new AttributeContainer(AttributeRegistry.HOLY_SPELL_POWER, .1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.CAST_TIME_REDUCTION, .10, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );
    float damage;
    float speed;
    AttributeContainer[] attributes;

    public OccultTeir(float damage, float speed, AttributeContainer... attributes) {
        this.damage = damage;
        this.speed = speed;
        this.attributes = attributes;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    public AttributeContainer[] getAdditionalAttributes() {
        return this.attributes;
    }
}

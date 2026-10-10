package com.silver.issoccultspells.item.curios;

import net.acetheeldritchking.aces_spell_utils.items.curios.PassiveAbilitySpellbook;
import net.minecraft.world.item.Rarity;

public class OccultPassiveAbilitySpellbook extends PassiveAbilitySpellbook {
    public int COOLDOWN = 10 * 20;

    public OccultPassiveAbilitySpellbook(int slots, Properties properties, int cooldown)
    {
        super(slots, properties);
        setCooldownTicks(cooldown);
    }
    public OccultPassiveAbilitySpellbook(int slots, Properties properties)
    {
        this(slots,properties,10 * 20);
    }
    @Override
    protected int getCooldownTicks() {
        return COOLDOWN;
    }
    public void setCooldownTicks(int ticks) {
        COOLDOWN = ticks;
    }
}

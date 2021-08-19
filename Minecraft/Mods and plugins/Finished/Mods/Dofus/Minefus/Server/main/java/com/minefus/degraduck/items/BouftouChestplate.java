package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class BouftouChestplate extends ItemArmor
{
    public BouftouChestplate()
    {
        super(Minefus.bouftouArmor, 0, 1);
        setUnlocalizedName("bouftou_chestplate");
        GameRegistry.registerItem(this, "bouftou_chestplate");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }
}
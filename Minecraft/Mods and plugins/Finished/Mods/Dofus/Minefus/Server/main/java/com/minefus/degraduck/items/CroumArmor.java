package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class CroumArmor extends ItemArmor
{
    public CroumArmor()
    {
        super(Minefus.petArmor, 0, 3);
        setUnlocalizedName("croum");
        GameRegistry.registerItem(this, "croum");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return false;
    }
}
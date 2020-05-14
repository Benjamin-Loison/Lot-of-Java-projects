package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class MousseChestplate extends ItemArmor
{
    public MousseChestplate()
    {
        super(Minefus.mousseArmor, 0, 1);
        setUnlocalizedName("mousse_chestplate");
        GameRegistry.registerItem(this, "mousse_chestplate");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }
}
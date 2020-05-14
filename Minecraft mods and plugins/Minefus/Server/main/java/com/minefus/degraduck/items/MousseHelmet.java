package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class MousseHelmet extends ItemArmor
{
    public MousseHelmet()
    {
        super(Minefus.mousseArmor, 0, 0);
        setUnlocalizedName("mousse_helmet");
        GameRegistry.registerItem(this, "mousse_helmet");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }
}
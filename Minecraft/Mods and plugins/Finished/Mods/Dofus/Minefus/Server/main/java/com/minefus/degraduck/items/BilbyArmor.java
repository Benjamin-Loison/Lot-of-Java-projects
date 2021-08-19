package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class BilbyArmor extends ItemArmor
{
    private ModelBase value;

    public BilbyArmor()
    {
        super(Minefus.petArmor, 0, 3);
        setUnlocalizedName("bilby");
        GameRegistry.registerItem(this, "bilby");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return false;
    }
}
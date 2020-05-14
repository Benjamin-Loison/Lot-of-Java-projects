package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class RoyalBouftouChestplate extends ItemArmor
{
    public RoyalBouftouChestplate()
    {
        super(Minefus.royalBouftouArmor, 0, 1);
        setUnlocalizedName("royal_bouftou_chestplate");
        GameRegistry.registerItem(this, "royal_bouftou_chestplate");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }
}
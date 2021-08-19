package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class RoyalBouftouHelmet extends ItemArmor
{
    public RoyalBouftouHelmet()
    {
        super(Minefus.royalBouftouArmor, 0, 0);
        setUnlocalizedName("royal_bouftou_helmet");
        GameRegistry.registerItem(this, "royal_bouftou_helmet");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }
}
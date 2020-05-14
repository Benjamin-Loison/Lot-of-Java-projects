package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RoyalBouftouBoots extends Item
{
    public RoyalBouftouBoots()
    {
        setUnlocalizedName("royal_bouftou_boots");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "royal_bouftou_boots");
    }
}
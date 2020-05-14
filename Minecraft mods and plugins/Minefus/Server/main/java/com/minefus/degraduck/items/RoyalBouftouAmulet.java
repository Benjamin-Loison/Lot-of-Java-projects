package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RoyalBouftouAmulet extends Item
{
    public RoyalBouftouAmulet()
    {
        setUnlocalizedName("royal_bouftou_amulet");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "royal_bouftou_amulet");
    }
}
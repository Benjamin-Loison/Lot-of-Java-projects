package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RoyalBouftouRing extends Item
{
    public RoyalBouftouRing()
    {
        setUnlocalizedName("royal_bouftou_ring");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "royal_bouftou_ring");
    }
}
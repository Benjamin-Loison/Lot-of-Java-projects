package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RoyalBouftouBelt extends Item
{
    public RoyalBouftouBelt()
    {
        setUnlocalizedName("royal_bouftou_belt");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "royal_bouftou_belt");
    }
}
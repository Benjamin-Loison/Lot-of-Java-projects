package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BouftouRing extends Item
{
    public BouftouRing()
    {
        setUnlocalizedName("bouftou_ring");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "bouftou_ring");
    }
}
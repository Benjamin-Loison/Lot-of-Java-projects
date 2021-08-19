package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BouftouBoots extends Item
{
    public BouftouBoots()
    {
        setUnlocalizedName("bouftou_boots");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "bouftou_boots");
    }
}
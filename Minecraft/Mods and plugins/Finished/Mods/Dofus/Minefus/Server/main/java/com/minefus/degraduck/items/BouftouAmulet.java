package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BouftouAmulet extends Item
{
    public BouftouAmulet()
    {
        setUnlocalizedName("bouftou_amulet");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "bouftou_amulet");
    }
}
package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MousseBoots extends Item
{
    public MousseBoots()
    {
        setUnlocalizedName("mousse_boots");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "mousse_boots");
    }
}
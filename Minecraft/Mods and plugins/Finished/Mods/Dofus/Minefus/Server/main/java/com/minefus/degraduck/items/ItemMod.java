package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemMod extends Item
{
    public ItemMod(String unlocalizedName, String texture, int size)
    {
        setUnlocalizedName(unlocalizedName);
        setMaxStackSize(size);
        GameRegistry.registerItem(this, unlocalizedName);
    }
}
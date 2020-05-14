package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemMimibiote extends Item
{
    public ItemMimibiote()
    {
        setUnlocalizedName("mimibiote");
        GameRegistry.registerItem(this, "mimibiote");
    }
}
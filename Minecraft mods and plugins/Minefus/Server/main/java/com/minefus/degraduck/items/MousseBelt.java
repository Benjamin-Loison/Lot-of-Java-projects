package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class MousseBelt extends Item
{
    public MousseBelt()
    {
        setUnlocalizedName("mousse_belt");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "mousse_belt");
    }
}
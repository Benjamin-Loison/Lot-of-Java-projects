package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BouftouBelt extends Item
{
    public BouftouBelt()
    {
        setUnlocalizedName("bouftou_belt");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "bouftou_belt");
    }
}
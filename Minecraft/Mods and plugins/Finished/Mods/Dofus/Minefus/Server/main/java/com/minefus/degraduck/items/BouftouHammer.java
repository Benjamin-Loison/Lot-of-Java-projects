package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemSword;

public class BouftouHammer extends ItemSword
{
    public BouftouHammer()
    {
        super(Minefus.hammer);
        setUnlocalizedName("bouftou_hammer");
        GameRegistry.registerItem(this, "bouftou_hammer");
    }
}
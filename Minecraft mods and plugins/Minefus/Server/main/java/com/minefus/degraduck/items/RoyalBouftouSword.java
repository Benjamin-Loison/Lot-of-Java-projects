package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemSword;

public class RoyalBouftouSword extends ItemSword
{
    public RoyalBouftouSword()
    {
        super(Minefus.sword);
        setUnlocalizedName("royal_bouftou_sword");
        GameRegistry.registerItem(this, "royal_bouftou_sword");
    }
}
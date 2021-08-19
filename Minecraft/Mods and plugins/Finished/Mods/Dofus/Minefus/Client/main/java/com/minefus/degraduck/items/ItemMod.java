package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemMod extends Item
{
    public ItemMod(String unlocalizedName, String texture, int size)
    {
        setUnlocalizedName(unlocalizedName);
        setTextureName(Minefus.MODID + ":" + texture);
        setCreativeTab(Minefus.dofusTab);
        setMaxStackSize(size);
        GameRegistry.registerItem(this, unlocalizedName);
    }
}
package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MousseBoots extends Item
{
    public MousseBoots()
    {
        setUnlocalizedName("mousse_boots");
        setTextureName(Minefus.MODID + ":Mousse boots");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "mousse_boots");
    }
}
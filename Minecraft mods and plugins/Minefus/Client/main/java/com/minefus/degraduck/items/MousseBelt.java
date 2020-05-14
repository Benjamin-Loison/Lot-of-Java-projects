package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MousseBelt extends Item
{
    public MousseBelt()
    {
        setUnlocalizedName("mousse_belt");
        setTextureName(Minefus.MODID + ":Mousse belt");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "mousse_belt");
    }
}
package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class PotionMod extends ItemFood
{
    public PotionMod(String name, String texture)
    {
        super(0, 0, true);
        setUnlocalizedName(name);
        setTextureName(Minefus.MODID + ":" + texture);
        setCreativeTab(Minefus.dofusTab);
        setAlwaysEdible();
        GameRegistry.registerItem(this, name);
    }

    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.drink;
    }
}
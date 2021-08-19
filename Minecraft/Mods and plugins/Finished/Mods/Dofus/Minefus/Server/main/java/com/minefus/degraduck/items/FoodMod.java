package com.minefus.degraduck.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class FoodMod extends ItemFood
{
    public FoodMod(String name, String texture, int healAmount, float saturationModifier, boolean foodForDogs)
    {
        super(healAmount, saturationModifier, foodForDogs);
        setUnlocalizedName(name);
        setAlwaysEdible();
        GameRegistry.registerItem(this, name);
    }

    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.eat;
    }
}
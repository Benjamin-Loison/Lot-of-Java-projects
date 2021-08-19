package fr.altiscraft.benjaminloison.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Drink extends ItemFood
{
    public Drink(String unlocalizedName, int size, int healAmount, float saturationModifier)
    {
        super(healAmount, saturationModifier, true);
        setUnlocalizedName(unlocalizedName);
        setAlwaysEdible();
        setMaxStackSize(size);
    }

    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
    {
        super.onFoodEaten(stack, world, player);
    }

    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.drink;
    }
}
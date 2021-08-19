package fr.altiscraft.benjaminloison.items;

import net.minecraft.item.Item;

public class CustomItem extends Item
{
    public CustomItem(String name, int stackSize)
    {
        setUnlocalizedName(name);
        setMaxStackSize(stackSize);
    }

    public CustomItem(String name)
    {
        setUnlocalizedName(name);
        setMaxStackSize(1);
    }
}
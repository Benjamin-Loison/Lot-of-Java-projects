package fr.watchdogs.benjaminloison.common.guns;

import net.minecraft.item.ItemStack;

public class Paintjob
{
    public String iconName, textureName;
    public ItemStack[] dyesNeeded;

    public Paintjob(String iconName, String textureName, ItemStack[] dyesNeeded)
    {
        this.iconName = iconName;
        this.textureName = textureName;
        this.dyesNeeded = dyesNeeded;
    }
}
package fr.altiscraft.benjaminloison.items;

import net.minecraft.item.ItemArmor;

public class Armor extends ItemArmor
{
    public Armor(ArmorMaterial material, int type, String unlocalizedName)
    {
        super(material, 0, type);
        setUnlocalizedName(unlocalizedName);
    }
}
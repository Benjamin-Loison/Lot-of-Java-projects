package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.item.ItemArmor;

public class ItemModArmor extends ItemArmor
{
    public ItemModArmor(String unlocalizedName, ArmorMaterial material, int renderIndex, int armorType)
    {
        super(material, renderIndex, armorType);
        setUnlocalizedName(unlocalizedName);
    }
}
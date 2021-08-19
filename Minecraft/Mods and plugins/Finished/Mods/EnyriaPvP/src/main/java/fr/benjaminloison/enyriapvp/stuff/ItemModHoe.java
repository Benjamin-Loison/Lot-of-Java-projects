package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;

public class ItemModHoe extends ItemHoe
{
    public ItemModHoe(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(unlocalizedName);
    }
}
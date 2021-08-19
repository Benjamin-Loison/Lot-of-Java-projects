package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;

public class ItemModAxe extends ItemAxe
{
    public ItemModAxe(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(unlocalizedName);
    }
}
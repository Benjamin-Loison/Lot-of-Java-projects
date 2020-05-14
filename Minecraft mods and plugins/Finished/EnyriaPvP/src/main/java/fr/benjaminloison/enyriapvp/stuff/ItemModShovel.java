package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;

public class ItemModShovel extends ItemSpade
{
    public ItemModShovel(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(unlocalizedName);
    }
}
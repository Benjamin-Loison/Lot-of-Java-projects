package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword
{
    public ItemModSword(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(unlocalizedName);
    }
}
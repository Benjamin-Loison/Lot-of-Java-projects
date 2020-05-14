package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe
{
    public ItemModPickaxe(String unlocalizedName, ToolMaterial material)
    {
        super(material);
        setUnlocalizedName(unlocalizedName);
    }
}
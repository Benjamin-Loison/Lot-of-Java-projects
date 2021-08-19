package fr.watchdogs.benjaminloison.api;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemLoader
{
    public ItemLoader()
    {}

    private void registerItem(Item i)
    {
        GameRegistry.registerItem(i, i.getUnlocalizedName().replace("item.", ""));
    }
}
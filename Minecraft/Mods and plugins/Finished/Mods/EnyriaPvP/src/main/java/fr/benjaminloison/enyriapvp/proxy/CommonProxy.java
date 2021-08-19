package fr.benjaminloison.enyriapvp.proxy;

import fr.benjaminloison.enyriapvp.common.EnyriaPvP;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class CommonProxy
{
    protected void registerItemTexture(Item item, int metadata, String name)
    {}

    protected void registerItemTexture(Item item, String name)
    {}

    protected void registerBlockTexture(Block block, int metadata, String name)
    {}

    protected void registerBlockTexture(Block block, String name)
    {}
}
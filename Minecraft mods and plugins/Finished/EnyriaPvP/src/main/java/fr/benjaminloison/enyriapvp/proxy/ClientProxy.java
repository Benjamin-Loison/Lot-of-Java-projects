package fr.benjaminloison.enyriapvp.proxy;

import fr.benjaminloison.enyriapvp.common.EnyriaPvP;
import fr.benjaminloison.enyriapvp.stuff.Blockz;
import fr.benjaminloison.enyriapvp.stuff.Itemz;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerItemTexture(Item item, int metadata, String name)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, metadata, new ModelResourceLocation(EnyriaPvP.MODID + ":" + name, "inventory"));
    }

    @Override
    public void registerItemTexture(Item item, String name)
    {
        registerItemTexture(item, 0, name);
    }

    @Override
    public void registerBlockTexture(Block block, int metadata, String name)
    {
        registerItemTexture(Item.getItemFromBlock(block), metadata, name);
    }

    @Override
    public void registerBlockTexture(Block block, String name)
    {
        registerBlockTexture(block, 0, name);
    }
}
package fr.chequemod.benjaminloison.common;

import fr.chequemod.benjaminloison.gui.GuiCheque;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Cheque extends Item
{
    public Cheque()
    {
        setUnlocalizedName("cheque");
        setTextureName(ChequeMod.MODID + ":Cheque");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer entity)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiCheque());
        return item;
    }
}
package fr.phonegui.benjaminloison.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.phonegui.benjaminloison.common.PhoneGui;
import fr.phonegui.benjaminloison.gui.GuiPhone;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Phone extends Item
{
    public Phone()
    {
        setUnlocalizedName("phone");
        setTextureName(PhoneGui.MODID + ":Phone");
        setCreativeTab(CreativeTabs.tabMaterials);
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer entity)
    {
        Minecraft.getMinecraft().displayGuiScreen(new GuiPhone());
        return item;
    }
}
package fr.pnjmarket.benjaminloison.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Marteau extends Item
{
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        Entity entity = Minecraft.getMinecraft().objectMouseOver.entityHit;
        if(entity instanceof EntityMarketPNJ)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiPNJ((EntityMarketPNJ)Minecraft.getMinecraft().objectMouseOver.entityHit));
        }
        return itemstack;
    }
}
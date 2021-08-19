package fr.chequemod.benjaminloison.common;

import fr.chequemod.benjaminloison.entity.EntityBanker;
import fr.chequemod.benjaminloison.packets.PacketKillNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Hammer extends Item
{
    public Hammer()
    {
        setUnlocalizedName("hammer");
        setTextureName(ChequeMod.MODID + ":Hammer");
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        Entity target = Minecraft.getMinecraft().objectMouseOver.entityHit;
        if(target instanceof EntityBanker)
            ChequeMod.instance.network.sendToServer(new PacketKillNPC(target.getEntityId()));
        return itemstack;
    }
}
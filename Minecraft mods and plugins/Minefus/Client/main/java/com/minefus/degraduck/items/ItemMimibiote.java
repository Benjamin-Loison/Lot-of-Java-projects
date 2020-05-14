package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.packets.OpenGuiPacket;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMimibiote extends Item
{
    public ItemMimibiote()
    {
        setUnlocalizedName("mimibiote");
        setTextureName(Minefus.MODID + ":Mimibiote");
        setCreativeTab(Minefus.dofusTab);
        GameRegistry.registerItem(this, "mimibiote");
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        Minefus.PACKET_PIPELINE.sendToServer(new OpenGuiPacket(Minefus.GUI_MIMIBIOTE_INV));
        return stack;
    }
}
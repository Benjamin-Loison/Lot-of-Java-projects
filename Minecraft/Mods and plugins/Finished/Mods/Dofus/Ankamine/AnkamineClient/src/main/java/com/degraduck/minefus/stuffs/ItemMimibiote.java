package com.degraduck.minefus.stuffs;

import java.util.List;

import com.degraduck.minefus.common.Dofus;
import com.degraduck.minefus.common.OpenGuiPacketMimibiote;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMimibiote extends Item
{
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        Dofus.packetPipeline.sendToServer(new OpenGuiPacketMimibiote(Dofus.GUI_MIMIBIOTE_INV));
        return stack;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
    {
        super.addInformation(stack, player, par3List, par4);
    }
}

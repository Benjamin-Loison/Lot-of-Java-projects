package com.degraduck.minefus.items;

import com.degraduck.minefus.common.Dofus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemPetitCoffre
  extends Item
{
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
  {
    String item = null;
    if (!world.isRemote)
    {
      double random = Math.random() * 100.0D;
      if (random <= 40.0D)
      {
          player.inventory.addItemStackToInventory(new ItemStack(Dofus.StuffCoiffeBouftou, 1));
        item = "Coiffe Du Bouftou";
      }
      if ((random > 40.0D) && (random <= 60.0D))
      {
          player.inventory.addItemStackToInventory(new ItemStack(Dofus.StuffCoiffeBouftou, 1));
        item = "Coiffe Du Bouftou";
      }
      if ((random > 60.0D) && (random <= 80.0D))
      {
          player.inventory.addItemStackToInventory(new ItemStack(Dofus.StuffCoiffeBouftou, 1));
        item = "Coiffe Du Bouftou";
      }
      if ((random > 80.0D) && (random <= 90.0D))
      {
          player.inventory.addItemStackToInventory(new ItemStack(Dofus.StuffCoiffeBouftou, 1));
        item = "Coiffe Du Bouftou";
      }
      if ((random > 90.0D) && (random <= 100.0D))
      {
          player.inventory.addItemStackToInventory(new ItemStack(Dofus.StuffCoiffeBouftou, 1));
        item = "Coiffe Du Bouftou";
      }
      player.addChatComponentMessage(new ChatComponentText("Â§f[Â§4" + player.getDisplayName() + "Â§f]" + "Â§2Â§oÂ§l a obtenu Â§f[" + "Â§4" + item + "Â§f]Â§2Â§oÂ§l en ouvrant Â§f[" + "Â§4" + stack.getDisplayName() + "Â§f] Â§2Â§oÂ§l!"));
    }
    stack.stackSize -= 1;
    return stack;
  }
}

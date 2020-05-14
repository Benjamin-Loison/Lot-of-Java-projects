package com.degraduck.minefus.common;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Potionbonta
  extends Item
{
  public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
  {
    player.setPositionAndUpdate(588.0D, 25.0D, -617.0D);
    stack.stackSize -= 1;
    return stack;
  }
  
  public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
  {
    par2List.add("§4Teleporte a Bonta");
  }
}

package com.degraduck.minefus.common;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAmulette
  extends Slot
{
  public SlotAmulette(IInventory inventory, int slotIndex, int x, int y)
  {
    super(inventory, slotIndex, x, y);
  }
  
  public boolean isItemValid(ItemStack itemstack)
  {
    return (itemstack.getItem() == Dofus.StuffAmuletteBouftou) || (itemstack.getItem() == Dofus.StuffAmuletteBouftouRoyal);
  }
}

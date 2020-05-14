package com.degraduck.minefus.common;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCeinture
  extends Slot
{
  public SlotCeinture(IInventory inventory, int slotIndex, int x, int y)
  {
    super(inventory, slotIndex, x, y);
  }
  
  public boolean isItemValid(ItemStack itemstack)
  {
    return (itemstack.getItem() == Dofus.StuffCeintureBouftou) || (itemstack.getItem() == Dofus.StuffCeintureBouftouRoyal);
  }
}

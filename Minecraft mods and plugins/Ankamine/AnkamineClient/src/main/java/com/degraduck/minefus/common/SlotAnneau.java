package com.degraduck.minefus.common;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAnneau
  extends Slot
{
  public SlotAnneau(IInventory inventory, int slotIndex, int x, int y)
  {
    super(inventory, slotIndex, x, y);
  }
  
  public boolean isItemValid(ItemStack itemstack)
  {
    return (itemstack.getItem() == Dofus.StuffAnneauBouftou) || (itemstack.getItem() == Dofus.StuffAnneauBouftouRoyal);
  }
}

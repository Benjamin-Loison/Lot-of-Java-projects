package com.degraduck.minefus.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MinefusContainerPlayer
  extends Container
{
  private final EntityPlayer thePlayer;
  
  public MinefusContainerPlayer(EntityPlayer player, InventoryPlayer inventoryPlayer, MinefusInventoryPlayer inventoryMinefus)
  {
    thePlayer = player;
    bindMinefusInventory(inventoryMinefus);
    bindPlayerInventory(inventoryPlayer);
    bindArmorInventory(inventoryPlayer);
  }
  
  private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
  {
    for (int i = 0; i < 9; i++) {
      addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 194));
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++) {
        addSlotToContainer(new Slot(inventoryPlayer, j + (i + 1) * 9, 8 + j * 18, 136 + i * 18));
      }
    }
  }
  
  private void bindMinefusInventory(MinefusInventoryPlayer inventoryMinefus)
  {
    addSlotToContainer(new Slot(inventoryMinefus, 1, 8, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 2, 26, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 3, 44, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 4, 62, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 5, 80, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 6, 98, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 7, 116, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 8, 134, 119));
    addSlotToContainer(new Slot(inventoryMinefus, 9, 152, 119));
    
    addSlotToContainer(new SlotMinefus(inventoryMinefus, 10, 8, 8));
    addSlotToContainer(new SlotMinefus(inventoryMinefus, 11, 8, 26));
    addSlotToContainer(new SlotMinefus(inventoryMinefus, 12, 8, 44));
    addSlotToContainer(new SlotMinefus(inventoryMinefus, 13, 8, 62));
    addSlotToContainer(new SlotMinefus(inventoryMinefus, 14, 8, 80));
    addSlotToContainer(new SlotMinefus(inventoryMinefus, 15, 8, 98));
    
    addSlotToContainer(new SlotAnneau(inventoryMinefus, 16, 28, 43));
    addSlotToContainer(new SlotAnneau(inventoryMinefus, 17, 118, 43));
    addSlotToContainer(new SlotCeinture(inventoryMinefus, 18, 28, 61));
    addSlotToContainer(new SlotAmulette(inventoryMinefus, 19, 28, 7));
    addSlotToContainer(new SlotBotte(inventoryMinefus, 20, 28, 79));
  }
  
  private void bindArmorInventory(InventoryPlayer inventoryPlayer)
  {
    addSlotToContainer(new SlotArmor(thePlayer, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1, 118, 7, 0));
    addSlotToContainer(new SlotArmor(thePlayer, inventoryPlayer, inventoryPlayer.getSizeInventory() - 2, 118, 25, 1));
    addSlotToContainer(new SlotArmor(thePlayer, inventoryPlayer, inventoryPlayer.getSizeInventory() - 3, 28, 25, 2));
    addSlotToContainer(new SlotArmor(thePlayer, inventoryPlayer, inventoryPlayer.getSizeInventory() - 4, 118, 79, 3));
  }
  
  public boolean canInteractWith(EntityPlayer player)
  {
    return true;
  }
  
  public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
  {
    ItemStack itemstack = null;
    Slot slot = (Slot)inventorySlots.get(slotId);
    if ((slot != null) && (slot.getHasStack()))
    {
      ItemStack itemstack1 = slot.getStack();
      itemstack = itemstack1.copy();
      if (slotId < 0)
      {
        if (!mergeItemStack(itemstack1, 0, inventorySlots.size(), true)) {
          return null;
        }
      }
      else
      {
        if (!mergeItemStack(itemstack1, 0, 0, false)) {
          return null;
        }
        if ((slotId == 9) && (slotId != 0)) {
          if (!mergeItemStack(itemstack1, 0, 9, true)) {
            return null;
          }
        }
      }
      if (itemstack.stackSize == 0) {
        slot.putStack((ItemStack)null);
      } else {
        slot.onSlotChanged();
      }
    }
    return itemstack;
  }
}

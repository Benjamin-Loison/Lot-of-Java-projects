package com.degraduck.minefus.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MimibioteContainerPlayer extends Container
{
    private final EntityPlayer thePlayer;

    public MimibioteContainerPlayer(EntityPlayer player, InventoryPlayer inventoryPlayer, MinefusInventoryPlayer inventoryMinefus)
    {
        thePlayer = player;
        bindMinefusInventory(inventoryMinefus);

        bindPlayerInventory(inventoryPlayer);
    }

    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        for(int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 172));
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                addSlotToContainer(new Slot(inventoryPlayer, j + (i + 1) * 9, 8 + j * 18, 114 + i * 18));
            }
        }
    }

    private void bindMinefusInventory(MinefusInventoryPlayer inventoryMinefus)
    {
        addSlotToContainer(new Slot(inventoryMinefus, 1, 8, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 2, 26, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 3, 44, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 4, 62, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 5, 80, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 6, 98, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 7, 116, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 8, 134, 97));
        addSlotToContainer(new Slot(inventoryMinefus, 9, 152, 97));

        addSlotToContainer(new SlotMimiApa(inventoryMinefus, 30, 98, 43));
        addSlotToContainer(new SlotMimiStuff(inventoryMinefus, 31, 60, 43));
        addSlotToContainer(new SlotMimiResult(inventoryMinefus, 32, 137, 43));
    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotId);
        if((slot != null) && (slot.getHasStack()))
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(slotId < 0)
            {
                if(!mergeItemStack(itemstack1, 0, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else
            {
                if(!mergeItemStack(itemstack1, 0, 0, false))
                {
                    return null;
                }
                if((slotId == 9) && (slotId != 0))
                {
                    if(!mergeItemStack(itemstack1, 0, 9, true))
                    {
                        return null;
                    }
                }
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}

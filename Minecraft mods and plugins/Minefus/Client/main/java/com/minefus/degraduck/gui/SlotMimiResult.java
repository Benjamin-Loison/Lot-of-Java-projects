package com.minefus.degraduck.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMimiResult extends Slot
{
    public SlotMimiResult(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }
}
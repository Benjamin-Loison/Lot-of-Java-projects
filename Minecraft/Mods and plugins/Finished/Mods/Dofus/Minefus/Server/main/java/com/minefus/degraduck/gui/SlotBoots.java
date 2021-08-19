package com.minefus.degraduck.gui;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBoots extends Slot
{
    public SlotBoots(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return (itemstack.getItem() == Minefus.bouftouBoots) || (itemstack.getItem() == Minefus.royalBouftouBoots);
    }
}
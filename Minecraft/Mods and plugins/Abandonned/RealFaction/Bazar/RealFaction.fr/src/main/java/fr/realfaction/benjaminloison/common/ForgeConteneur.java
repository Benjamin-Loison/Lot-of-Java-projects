package fr.realfaction.benjaminloison.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ForgeConteneur extends Container {
	private TileEntityForge tileForge;
	
	public ForgeConteneur(TileEntityForge tile, InventoryPlayer inventory)

	{

        this.tileForge = tile;

        this.addSlotToContainer(new Slot(tile, 0, 49, 75));

        this.addSlotToContainer(new Slot(tile, 1, 89, 75));

        this.addSlotToContainer(new Slot(tile, 2, 129, 75));

        this.addSlotToContainer(new SlotResult(tile, 3, 89, 135));

        this.bindPlayerInventory(inventory);	

	}
	
	@Override

	public boolean canInteractWith(EntityPlayer player) {

		return this.tileForge.isUseableByPlayer(player);

	}

	

	private void bindPlayerInventory(InventoryPlayer inventory)

	{

		int i;

		for (i = 0; i < 3; ++i)

        {

            for (int j = 0; j < 9; ++j)

            {

                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 17 + j * 18, 171 + i * 18));

            }

        }



        for (i = 0; i < 9; ++i)

        {

            this.addSlotToContainer(new Slot(inventory, i, 17 + i * 18, 229));

        }

	}

	

	public ItemStack transferStackInSlot(EntityPlayer player, int quantity)

    {

        ItemStack itemstack = null;

        Slot slot = (Slot)this.inventorySlots.get(quantity);



        if (slot != null && slot.getHasStack())

        {

            ItemStack itemstack1 = slot.getStack();

            itemstack = itemstack1.copy();



            if (quantity < this.tileForge.getSizeInventory())

            {

                if (!this.mergeItemStack(itemstack1, this.tileForge.getSizeInventory(), this.inventorySlots.size(), true))

                {

                    return null;

                }

            }

            else if (!this.mergeItemStack(itemstack1, 0, this.tileForge.getSizeInventory(), false))

            {

                return null;

            }



            if (itemstack1.stackSize == 0)

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

	

	public void onContainerClosed(EntityPlayer player)

    {

        super.onContainerClosed(player);

        this.tileForge.closeInventory();

    }

}
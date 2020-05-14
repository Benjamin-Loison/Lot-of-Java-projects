package fr.watchdogs.benjaminloison.common.guns;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryGunModTable extends InventoryBasic
{
    public ItemStack lastGunStack;
    public GunType gunType;
    public int genericScroll;
    private boolean busy;

    public InventoryGunModTable()
    {
        super("fr.watchdogs.benjaminloison.common.guns.modification.table", true, 13);
    }

    @Override
    public void markDirty()
    {
        if(busy)
            return;
        ItemStack gunStack = getStackInSlot(0);
        if(gunStack == null || !(gunStack.getItem() instanceof ItemGun))
            return;
        gunType = ((ItemGun)gunStack.getItem()).type;
        if(gunStack != lastGunStack)
        {
            busy = true;
            NBTTagCompound attachmentTags = gunStack.stackTagCompound.getCompoundTag("attachments");
            setInventorySlotContents(1, ItemStack.loadItemStackFromNBT(attachmentTags.getCompoundTag("barrel")));
            setInventorySlotContents(2, ItemStack.loadItemStackFromNBT(attachmentTags.getCompoundTag("scope")));
            setInventorySlotContents(3, ItemStack.loadItemStackFromNBT(attachmentTags.getCompoundTag("stock")));
            setInventorySlotContents(4, ItemStack.loadItemStackFromNBT(attachmentTags.getCompoundTag("grip")));
            genericScroll = 0;
            for(int i = 0; i < Math.min(gunType.numGenericAttachmentSlots, 8); i++)
                setInventorySlotContents(5 + i, ItemStack.loadItemStackFromNBT(attachmentTags.getCompoundTag("generic_" + i)));
            busy = false;
        }
        else
        {
            NBTTagCompound gunTags = new NBTTagCompound();
            gunTags.setTag("ammo", getStackInSlot(0).stackTagCompound.getTag("ammo"));
            if(getStackInSlot(0).stackTagCompound.getTag("Paint") != null)
                gunTags.setTag("Paint", getStackInSlot(0).stackTagCompound.getTag("Paint"));
            NBTTagCompound attachmentTags = new NBTTagCompound();
            writeAttachmentTags(attachmentTags, getStackInSlot(1), "barrel");
            writeAttachmentTags(attachmentTags, getStackInSlot(2), "scope");
            writeAttachmentTags(attachmentTags, getStackInSlot(3), "stock");
            writeAttachmentTags(attachmentTags, getStackInSlot(4), "grip");
            for(int i = 0; i < gunType.numGenericAttachmentSlots; i++)
                if(i >= genericScroll * 4 && i < genericScroll * 4 + 8)
                    writeAttachmentTags(attachmentTags, getStackInSlot(i - genericScroll * 4 + 5), "generic_" + i);
                else
                    attachmentTags.setTag("generic_" + i, getStackInSlot(0).stackTagCompound.getTag("generic_" + i));
            gunTags.setTag("attachments", attachmentTags);
            gunStack.stackTagCompound = gunTags;
        }
        lastGunStack = gunStack;
    }

    public void writeAttachmentTags(NBTTagCompound attachmentTags, ItemStack attachmentStack, String attachmentName)
    {
        NBTTagCompound tags = new NBTTagCompound();
        if(attachmentStack != null)
            attachmentStack.writeToNBT(tags);
        attachmentTags.setTag(attachmentName, tags);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return false;
    }
}
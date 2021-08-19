package fr.realfaction.benjaminloison.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityForge extends TileEntity implements IInventory {
	private byte direction;

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.direction = compound.getByte("Direction");
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.contents = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;
			if (j >= 0 && j < this.contents.length) {
				this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		this.workingTime = compound.getShort("workingTime");
		this.workingTimeNeeded = compound.getShort("workingTimeNeeded");
	}

	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setByte("Direction", this.direction);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.contents.length; ++i) {
			if (this.contents[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.contents[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		compound.setTag("Items", nbttaglist);
		compound.setShort("workingTime", (short) this.workingTime);
		compound.setShort("workingTimeNeeded", (short) this.workingTimeNeeded);
	}

	public byte getDirection() {
		return this.direction;
	}

	public void setDirection(byte direction) {
		this.direction = direction;
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
		this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord,
				this.zCoord);
	}

	private ItemStack[] contents = new ItemStack[4];
	private int workingTime = 0;
	private int workingTimeNeeded = 200;

	@Override
	public int getSizeInventory() {
		return this.contents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return this.contents[slotIndex];
	}

	public boolean isBurning() {
		return this.workingTime > 0;
	}

	private boolean canSmelt() {
		if (this.contents[0] == null || this.contents[1] == null || this.contents[2] == null) {
			return false;
		} else {
			ItemStack itemstack = ForgeRecette.smelting()
					.getSmeltingResult(new ItemStack[] { this.contents[0], this.contents[1], this.contents[2] });
			if (itemstack == null)
				return false;
			if (this.contents[3] == null)
				return true;
			if (!this.contents[3].isItemEqual(itemstack))
				return false;
			int result = contents[3].stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.contents[3].getMaxStackSize();
		}
	}

	public void updateEntity() {
		if (this.isBurning() && this.canSmelt()) {
			++this.workingTime;
		}
		if (this.canSmelt() && !this.isBurning()) {
			this.workingTime = 1;
		}
		if (this.canSmelt() && this.workingTime == this.workingTimeNeeded) {
			this.smeltItem();
			this.workingTime = 0;
		}
		if (!this.canSmelt()) {
			this.workingTime = 0;
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = ForgeRecette.smelting()
					.getSmeltingResult(new ItemStack[] { this.contents[0], this.contents[1], this.contents[2] });
			if (this.contents[3] == null) {
				this.contents[3] = itemstack.copy();
			} else if (this.contents[3].getItem() == itemstack.getItem()) {
				this.contents[3].stackSize += itemstack.stackSize;
			}
			--this.contents[0].stackSize;
			--this.contents[1].stackSize;
			--this.contents[2].stackSize;
			if (this.contents[0].stackSize <= 0) {
				this.contents[0] = null;
			}
			if (this.contents[1].stackSize <= 0) {
				this.contents[1] = null;
			}
			if (this.contents[2].stackSize <= 0) {
				this.contents[2] = null;
			}
		}
	}

	@Override // Comme dit plus haut, c'est expliqué dans le tutoriel de robin

	public ItemStack decrStackSize(int slotIndex, int amount) {

		if (this.contents[slotIndex] != null)

		{

			ItemStack itemstack;

			if (this.contents[slotIndex].stackSize <= amount)

			{

				itemstack = this.contents[slotIndex];

				this.contents[slotIndex] = null;

				this.markDirty();

				return itemstack;

			}

			else

			{

				itemstack = this.contents[slotIndex].splitStack(amount);

				if (this.contents[slotIndex].stackSize == 0)

				{

					this.contents[slotIndex] = null;

				}

				this.markDirty();

				return itemstack;

			}

		}

		else

		{

			return null;

		}

	}

	@Override

	public ItemStack getStackInSlotOnClosing(int slotIndex) {

		if (this.contents[slotIndex] != null)

		{

			ItemStack itemstack = this.contents[slotIndex];

			this.contents[slotIndex] = null;

			return itemstack;

		}

		else

		{

			return null;

		}

	}

	@Override

	public void setInventorySlotContents(int slotIndex, ItemStack stack) {

		this.contents[slotIndex] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())

		{

			stack.stackSize = this.getInventoryStackLimit();

		}

		this.markDirty();

	}

	@Override

	public String getInventoryName() { // J'ai décider qu'on ne pouvait pas
										// mettre de nom custom

		return "tile.machineTuto";

	}

	@Override

	public boolean hasCustomInventoryName() {

		return false;

	}

	@Override

	public int getInventoryStackLimit() {

		return 64;

	}

	@Override

	public boolean isUseableByPlayer(EntityPlayer player) {

		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
						(double) this.zCoord + 0.5D) <= 64.0D;

	}

	@Override

	public void openInventory() {

	}

	@Override

	public void closeInventory() {

	}

	@Override

	public boolean isItemValidForSlot(int slot, ItemStack stack) {

		return slot == 3 ? false : true;

	}

	@SideOnly(Side.CLIENT)

	public int getCookProgress()

	{

		return this.workingTime * 41 / this.workingTimeNeeded;

	}

}
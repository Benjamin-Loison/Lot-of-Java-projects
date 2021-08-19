package fr.realfaction.benjaminloison.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.realfaction.benjaminloison.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlocForge extends BlockContainer {
	protected BlocForge() {
		super(Material.ground);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityForge();
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)

    {

        if (world.isRemote)

        {

            return true;

        }

        else

        {

        	player.openGui(ModRealFaction.instance, 0, world, x, y, z);

            return true;

        }

    }

	@SideOnly(Side.CLIENT)
	private boolean Forge() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.tesrRenderId;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		if (stack.getItemDamage() == 0) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if ((tile instanceof TileEntityForge)) {
				int direction = MathHelper.floor_double(living.rotationYaw * 4.0F / 360.0F + 2.5D) & 0x3;
				((TileEntityForge) tile).setDirection((byte) direction);
			}
		}
	}

	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
		if (((axis == ForgeDirection.UP) || (axis == ForgeDirection.DOWN)) && (!world.isRemote)
				&& (world.getBlockMetadata(x, y, z) == 0)) {
			TileEntity tile = world.getTileEntity(x, y, z);
			if ((tile instanceof TileEntityForge)) {
				TileEntityForge tileForge = (TileEntityForge) tile;
				byte direction = tileForge.getDirection();
				direction = (byte) (direction + 1);
				if (direction > 3) {
					direction = 0;
				}
				tileForge.setDirection(direction);
				return true;
			}
		}
		return false;
	}

	public ForgeDirection[] getValidRotations(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) == 0 ? new ForgeDirection[] { ForgeDirection.UP, ForgeDirection.DOWN }
				: ForgeDirection.VALID_DIRECTIONS;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileEntityForge) {
			int direction = ((TileEntityForge) tile).getDirection();
			if (direction == 0) {
				this.setBlockBounds(0.1F, 0.0F, 0.0F, 0.9F, 2.4F, 1.0F);
			}
			if (direction == 1) {
				this.setBlockBounds(0.0F, 0.0F, 0.1F, 1.0F, 2.4F, 0.9F);
			}
			if (direction == 2) {
				this.setBlockBounds(0.1F, 0.0F, 0.0F, 0.9F, 2.4F, 1.0F);
			}
			if (direction == 3) {
				this.setBlockBounds(0.0F, 0.0F, 0.1F, 1.0F, 2.4F, 0.9F);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityForge();
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof IInventory) {
			IInventory inv = (IInventory) tileentity;
			for (int i1 = 0; i1 < inv.getSizeInventory(); ++i1) {
				ItemStack itemstack = inv.getStackInSlot(i1);
				if (itemstack != null) {
					float f = world.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;
					for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world
							.spawnEntityInWorld(entityitem)) {
						int j1 = world.rand.nextInt(21) + 10;
						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}
						itemstack.stackSize -= j1;
						entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1),
								(double) ((float) z + f2),
								new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
									.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}
			world.func_147453_f(x, y, z, block);
		}
		super.breakBlock(world, x, y, z, block, metadata);
	}
}
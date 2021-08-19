package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.tileentity.TileEntityBin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Bin extends Block
{
    public Bin()
    {
        super(Material.ground);
        setBlockName("bin");
        setHardness(1);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityBin();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        player.openGui(AltisCraft.instance, 0, world, x, y, z);
        return true;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.getItemDamage() == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityBin))
            {
                ((TileEntityBin)tile).setDirection((byte)(MathHelper.floor_double(living.rotationYaw * 4 / 362.5D) & 0x3));
                if(stack.hasDisplayName())
                    ((TileEntityBin)tile).setCustomName(stack.getDisplayName());
            }
        }
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityBin))
            {
                TileEntityBin tileBin = (TileEntityBin)tile;
                byte direction = (byte)(tileBin.getDirection() + 1);
                if(direction > 3)
                    direction = 0;
                tileBin.setDirection(direction);
                return true;
            }
        }
        return false;
    }

    public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 0 ? new ForgeDirection[] {ForgeDirection.UP, ForgeDirection.DOWN} : ForgeDirection.VALID_DIRECTIONS;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityBin)
        {
            int direction = ((TileEntityBin)tile).getDirection();
            if(direction == 0)
                setBlockBounds(0, 0, 0, 1, 1, 0.5F);
            else if(direction == 1)
                setBlockBounds(0.5F, 0, 0, 1, 1, 1);
            else if(direction == 2)
                setBlockBounds(0, 0, 0.5F, 1, 1, 1);
            else if(direction == 3)
                setBlockBounds(0, 0, 0, 0.5F, 1, 1);
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block bloc, int metadata)
    {
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if(tileentity instanceof IInventory)
        {
            IInventory inv = (IInventory)tileentity;
            for(int i = 0; i < inv.getSizeInventory(); i++)
            {
                ItemStack itemstack = inv.getStackInSlot(i);
                if(itemstack != null)
                {
                    float f0 = world.rand.nextFloat() * 0.8F + 0.1F, f1 = world.rand.nextFloat() * 0.8F + 0.1F, f3 = 0.05F;
                    EntityItem entityitem;
                    for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = world.rand.nextInt(21) + 10;
                        if(j1 > itemstack.stackSize)
                            j1 = itemstack.stackSize;
                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f0), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);
                        if(itemstack.hasTagCompound())
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }
                }
            }
            world.func_147453_f(x, y, z, bloc);
        }
        super.breakBlock(world, x, y, z, bloc, metadata);
    }
}
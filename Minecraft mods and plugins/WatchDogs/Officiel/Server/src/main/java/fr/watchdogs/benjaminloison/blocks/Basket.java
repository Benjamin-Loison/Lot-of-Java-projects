package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.tileentity.TileEntityBasket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Basket extends Block
{
    public Basket()
    {
        super(Material.ground);
        setBlockName("basket");
        setHardness(1);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityBasket();
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

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.getItemDamage() == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityBasket))
                ((TileEntityBasket)tile).setDirection((byte)(MathHelper.floor_double(living.rotationYaw * 4 / 362.5D) & 0x3));
        }
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityBasket))
            {
                TileEntityBasket tileBasket = (TileEntityBasket)tile;
                byte direction = (byte)(tileBasket.getDirection() + 1);
                if(direction > 3)
                    direction = 0;
                tileBasket.setDirection(direction);
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
        if(tile instanceof TileEntityBasket)
        {
            int direction = ((TileEntityBasket)tile).getDirection();
            if(direction == 0)
                setBlockBounds(0.4F, 0, 0, 0.6F, 3, 0.2F);
            else if(direction == 1)
                setBlockBounds(0.8F, 0, 0.4F, 1, 3, 0.6F);
            else if(direction == 2)
                setBlockBounds(0.4F, 0, 0.8F, 0.6F, 3, 1);
            else if(direction == 3)
                setBlockBounds(0.2F, 0, 0.4F, 0, 3, 0.6F);
        }
    }
}
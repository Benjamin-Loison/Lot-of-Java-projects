package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.tileentity.TileEntityNoEntry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class NoEntry extends Block
{
    public NoEntry()
    {
        super(Material.ground);
        setBlockName("no_entry");
        setHardness(1);
        setBlockBounds(0.45F, 0, 0.45F, 0.55F, 3, 0.55F);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityNoEntry();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.getItemDamage() == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityNoEntry))
                ((TileEntityNoEntry)tile).setDirection((byte)(MathHelper.floor_double(living.rotationYaw * 4 / 362.5D) & 0x3));
        }
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityNoEntry))
            {
                TileEntityNoEntry tileNoEntry = (TileEntityNoEntry)tile;
                byte direction = (byte)(tileNoEntry.getDirection() + 1);
                if(direction > 3)
                    direction = 0;
                tileNoEntry.setDirection(direction);
                return true;
            }
        }
        return false;
    }

    public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 0 ? new ForgeDirection[] {ForgeDirection.UP, ForgeDirection.DOWN} : ForgeDirection.VALID_DIRECTIONS;
    }
}
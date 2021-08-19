package fr.altiscraft.benjaminloison.blocks;

import java.io.File;
import java.io.FileWriter;

import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.tileentity.TileEntityVATM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class VATM extends Block
{
    public VATM()
    {
        super(Material.ground);
        setBlockName("v_atm");
        setHardness(1);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityVATM();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int o)
    {
        new File(FileAPI.atm + x + "," + y + "," + z + ".txt").delete();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.getItemDamage() == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityVATM))
                ((TileEntityVATM)tile).setDirection((byte)(MathHelper.floor_double(living.rotationYaw * 4 / 360 + 2.5D) & 0x3));
        }
        try
        {
            FileWriter fw = new FileWriter(FileAPI.atm + x + "," + y + "," + z + ".txt");
            if(!living.isSneaking())
            {
                if(living.isInvisible())
                fw.write("0");
            }
            else
                fw.write("-1");
            fw.close();
        }
        catch(Exception e)
        {}
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityVATM))
            {
                TileEntityVATM tileVATM = (TileEntityVATM)tile;
                byte direction = (byte)(tileVATM.getDirection() + 1);
                if(direction > 3)
                    direction = 0;
                tileVATM.setDirection(direction);
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
        if(tile instanceof TileEntityVATM)
        {
            int direction = ((TileEntityVATM)tile).getDirection();
            if(direction == 0)
                setBlockBounds(-0.7F, 0, 0, 1.7F, 3, 0.5F);
            else if(direction == 1)
                setBlockBounds(0.5F, 0, -0.7F, 1, 3, 1.7F);
            else if(direction == 2)
                setBlockBounds(-0.7F, 0, 0.5F, 1.7F, 3, 1);
            else if(direction == 3)
                setBlockBounds(0, 0, -0.7F, 0.5F, 3, 1.7F);
        }
    }
}
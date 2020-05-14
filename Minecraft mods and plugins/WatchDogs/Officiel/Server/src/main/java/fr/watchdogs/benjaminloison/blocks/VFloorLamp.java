package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.tileentity.TileEntityVFloorLamp;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class VFloorLamp extends Block
{
    public VFloorLamp()
    {
        super(Material.ground);
        setLightLevel(1f);
        setBlockName("v_floor_lamp");
        setHardness(1);
        setBlockBounds(0.4F, 0, 0.4F, 0.6F, 4.5F, 0.6F);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityVFloorLamp();
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
            if((tile instanceof TileEntityVFloorLamp))
                ((TileEntityVFloorLamp)tile).setDirection((byte)(MathHelper.floor_double(living.rotationYaw * 4 / 362.5D) & 0x3));
        }
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        if((axis == ForgeDirection.UP || axis == ForgeDirection.DOWN) && world.getBlockMetadata(x, y, z) == 0)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if((tile instanceof TileEntityVFloorLamp))
            {
                TileEntityVFloorLamp tileVFloorLamp = (TileEntityVFloorLamp)tile;
                byte direction = (byte)(tileVFloorLamp.getDirection() + 1);
                if(direction > 3)
                    direction = 0;
                tileVFloorLamp.setDirection(direction);
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
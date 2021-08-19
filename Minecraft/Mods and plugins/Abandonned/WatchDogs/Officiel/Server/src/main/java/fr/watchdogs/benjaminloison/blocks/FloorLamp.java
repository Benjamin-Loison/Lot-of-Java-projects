package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.tileentity.TileEntityFloorLamp;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FloorLamp extends Block
{
    public FloorLamp()
    {
        super(Material.ground);
        setBlockName("floor_lamp");
        setHardness(1);
        setBlockBounds(0.45F, 0, 0.45F, 0.55F, 1, 0.55F);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityFloorLamp();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
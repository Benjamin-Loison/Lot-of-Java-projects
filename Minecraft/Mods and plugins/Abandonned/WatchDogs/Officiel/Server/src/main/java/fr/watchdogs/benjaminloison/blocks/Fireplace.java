package fr.altiscraft.benjaminloison.blocks;

import fr.watchdogs.benjaminloison.tileentity.TileEntityFireplace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Fireplace extends Block
{
    public Fireplace()
    {
        super(Material.ground);
        setBlockName("fireplace");
        setHardness(7);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityFireplace();
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
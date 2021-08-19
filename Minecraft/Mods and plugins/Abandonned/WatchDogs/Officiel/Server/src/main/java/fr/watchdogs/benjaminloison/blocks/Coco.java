package fr.altiscraft.benjaminloison.blocks;

import java.util.Random;

import fr.watchdogs.benjaminloison.tileentity.TileEntityCoco;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Coco extends Block
{
    public Coco()
    {
        super(Material.ground);
        setBlockName("coco");
        setHardness(4);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityCoco();
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int quantityDropped(Random rdm)
    {
        return 0;
    }
}
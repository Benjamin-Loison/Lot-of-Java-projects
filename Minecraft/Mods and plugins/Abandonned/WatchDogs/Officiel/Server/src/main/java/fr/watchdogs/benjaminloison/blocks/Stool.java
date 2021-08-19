package fr.altiscraft.benjaminloison.blocks;

import java.util.Random;

import fr.watchdogs.benjaminloison.tileentity.TileEntityStool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Stool extends Block
{
    public Stool()
    {
        super(Material.ground);
        setBlockName("stool");
        setHardness(1);
        setBlockBounds(0, 0, 0, 1, 1.4F, 1);
    }

    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityStool();
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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int o, float d, float t, float q)
    {
        player.setPosition(x + 0.5, y + 1.4, z + 0.5);
        return true;
    }
}
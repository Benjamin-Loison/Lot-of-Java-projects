package fr.altiscraft.benjaminloison.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInvisible extends Block
{
    public BlockInvisible()
    {
        super(Material.ground);
        setHardness(-1);
        setResistance(-1);
        setBlockName("invisible");
        setLightOpacity(0);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return false;
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
    {
        return null;
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
}
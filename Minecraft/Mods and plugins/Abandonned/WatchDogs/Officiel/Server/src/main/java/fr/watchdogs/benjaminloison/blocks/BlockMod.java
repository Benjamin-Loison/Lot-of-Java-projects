package fr.altiscraft.benjaminloison.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block
{
    public BlockMod(Material mat, String name, float hardness)
    {
        super(mat);
        setBlockName(name);
        setHardness(hardness);
    }
}
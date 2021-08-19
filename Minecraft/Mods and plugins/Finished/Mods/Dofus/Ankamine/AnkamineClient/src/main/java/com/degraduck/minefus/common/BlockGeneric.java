package com.degraduck.minefus.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGeneric extends Block
{
    public BlockGeneric(Material material)
    {
        super(material);
        setBlockUnbreakable();
        setResistance(600000);
        setCreativeTab(Dofus.tabDofusItems);
    }
}
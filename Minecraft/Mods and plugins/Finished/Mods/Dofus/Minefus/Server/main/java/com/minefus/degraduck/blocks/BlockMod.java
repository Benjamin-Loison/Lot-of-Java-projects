package com.minefus.degraduck.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block
{
    public BlockMod(Material material, String name, String texture)
    {
        super(material);
        setBlockUnbreakable();
        setResistance(600000);
        setBlockName(name);
        GameRegistry.registerBlock(this, name);
    }
}
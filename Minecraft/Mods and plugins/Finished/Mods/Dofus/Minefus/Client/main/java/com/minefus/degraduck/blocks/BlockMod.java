package com.minefus.degraduck.blocks;

import com.minefus.degraduck.common.Minefus;

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
        setCreativeTab(Minefus.dofusTab);
        setBlockName(name);
        setBlockTextureName(Minefus.MODID + ":" + texture);
        GameRegistry.registerBlock(this, name);
    }
}
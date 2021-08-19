package com.degraduck.minefus.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class M_BlockSlab
{
    public static Block DoubleSlabMStone, SingleSlabMStone;

    public static void init()
    {
        DoubleSlabMStone = new BlockSlabMStone(true, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("double_slab_stone");
        SingleSlabMStone = new BlockSlabMStone(false, Material.rock).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("single_slab_stone").setCreativeTab(Dofus.tabDofusItems);
        register();
    }

    public static void register()
    {
        GameRegistry.registerBlock(DoubleSlabMStone, ItemBlockMStoneSlab.class, "double_slab_stone");
        GameRegistry.registerBlock(SingleSlabMStone, ItemBlockMStoneSlab.class, "single_slab_stone");
    }
}
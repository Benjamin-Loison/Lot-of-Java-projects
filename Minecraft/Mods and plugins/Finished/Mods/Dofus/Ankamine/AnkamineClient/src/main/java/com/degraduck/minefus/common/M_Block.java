package com.degraduck.minefus.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class M_Block
{
    public static Block dirt, stone_0, stone_1, stone_slab, double_slab_stone, single_slab_stone, grass_full;

    public static void init()
    {
        dirt = new BlockGeneric(Material.grass).setBlockName("dirt").setBlockTextureName(Dofus.MODID + ":Dirt");
        stone_0 = new BlockGeneric(Material.rock).setBlockName("stone_0").setBlockTextureName(Dofus.MODID + ":Stone 0");
        stone_1 = new BlockGeneric(Material.rock).setBlockName("stone_1").setBlockTextureName(Dofus.MODID + ":Stone 1");
        grass_full = new BlockGeneric(Material.grass).setBlockName("grass_full").setBlockTextureName(Dofus.MODID + ":Grass Top");
        register();
    }

    public static void register()
    {
        GameRegistry.registerBlock(dirt, "dirt");
        GameRegistry.registerBlock(stone_0, "stone_0");
        GameRegistry.registerBlock(stone_1, "stone_1");
        GameRegistry.registerBlock(grass_full, "grass_full");
    }
}
package com.minefus.degraduck.blocks;

import java.util.List;
import java.util.Random;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSlabStone extends BlockSlab
{
    public static final String[] StepTypes = new String[] {"stone_0", "stone_1", "dirt"};

    public BlockSlabStone(boolean isDouble, Material material, float hardness, float resistance, String name)
    {
        super(isDouble, material);
        if(!field_150004_a)
            setLightOpacity(0);
        setHardness(hardness);
        setResistance(resistance);
        setBlockName(name);
        GameRegistry.registerBlock(this, name);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.stone_slab);
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(Minefus.singleSlabStone, 2, metadata & 7);
    }

    @Override
    public String func_150002_b(int metadata)
    {
        if(metadata < 0 || metadata >= StepTypes.length)
            metadata = 0;
        return super.getUnlocalizedName() + "." + StepTypes[metadata];
    }
}
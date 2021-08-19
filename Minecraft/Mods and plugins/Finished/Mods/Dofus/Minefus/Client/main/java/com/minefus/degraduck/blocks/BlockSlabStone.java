package com.minefus.degraduck.blocks;

import java.util.List;
import java.util.Random;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
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

    public BlockSlabStone(boolean isDouble, Material material, float hardness, float resistance, SoundType sound, String name)
    {
        super(isDouble, material);
        if(!field_150004_a)
            setLightOpacity(0);
        setHardness(hardness);
        setResistance(resistance);
        setStepSound(sound);
        setBlockName(name);
        setCreativeTab(Minefus.dofusTab);
        setBlockTextureName(Minefus.MODID + ":Dirt");
        GameRegistry.registerBlock(this, name);
    }

    public IIcon getIcon(int side, int metadata)
    {
        int k = metadata & 7;
        return k == 0 ? Minefus.stone_0.getBlockTextureFromSide(side) : k == 1 ? Minefus.stone_1.getBlockTextureFromSide(side) : k == 2 ? Minefus.dirt.getBlockTextureFromSide(side) : Blocks.iron_block.getBlockTextureFromSide(side);
    }

    private static boolean func_150003_a(Block block)
    {
        return block == Minefus.singleSlabStone;
    }

    public Item getItem(World world, int x, int y, int z)
    {
        return func_150003_a(this) ? Item.getItemFromBlock(Minefus.singleSlabStone) : Item.getItemFromBlock(Minefus.doubleSlabStone);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.stone_slab);
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(Minefus.singleSlabStone, 2, metadata & 7);
    }

    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        if(item != Item.getItemFromBlock(Minefus.doubleSlabStone))
            for(int i = 0; i < StepTypes.length; i++)
                list.add(new ItemStack(item, 1, i));
    }

    @Override
    public String func_150002_b(int metadata)
    {
        if(metadata < 0 || metadata >= StepTypes.length)
            metadata = 0;
        return super.getUnlocalizedName() + "." + StepTypes[metadata];
    }
}
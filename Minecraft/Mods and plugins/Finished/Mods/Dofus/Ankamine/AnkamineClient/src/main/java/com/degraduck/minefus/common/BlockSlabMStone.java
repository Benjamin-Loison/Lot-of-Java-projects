package com.degraduck.minefus.common;

import java.util.List;
import java.util.Random;

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

public class BlockSlabMStone extends BlockSlab
{
    public static final String[] StepTypes = new String[] {"stone_0", "stone_1", "dirt"};

    public BlockSlabMStone(boolean isdouble, Material material)
    {
        super(isdouble, material);
        if(!field_150004_a)
            setLightOpacity(0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        int k = metadata & 7;
        return k == 0 ? M_Block.stone_0.getBlockTextureFromSide(side) : k == 1 ? M_Block.stone_1.getBlockTextureFromSide(side) : k == 2 ? M_Block.dirt.getBlockTextureFromSide(side) : Blocks.iron_block.getBlockTextureFromSide(side);
    }

    @SideOnly(Side.CLIENT)
    private static boolean func_150003_a(Block block)
    {
        return block == M_Block.single_slab_stone;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return func_150003_a(this) ? Item.getItemFromBlock(M_Block.single_slab_stone) : Item.getItemFromBlock(M_Block.double_slab_stone);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune)
    {
        return Item.getItemFromBlock(Blocks.stone_slab);
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(M_Block.single_slab_stone, 2, metadata & 7);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        if(item != Item.getItemFromBlock(M_Block.double_slab_stone))
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
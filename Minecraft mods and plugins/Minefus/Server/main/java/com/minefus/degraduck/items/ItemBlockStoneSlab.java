package com.minefus.degraduck.items;

import com.minefus.degraduck.blocks.BlockSlabStone;
import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBlockStoneSlab extends ItemBlock
{
    private final boolean isFullBlock;
    private final Block theHalfSlab, doubleSlab;

    public ItemBlockStoneSlab(Block block)
    {
        super(block);
        theHalfSlab = Minefus.singleSlabStone;
        doubleSlab = Minefus.doubleSlabStone;
        if(block == Minefus.doubleSlabStone)
            isFullBlock = true;
        else
            isFullBlock = false;
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getMetadata(int metadata)
    {
        return metadata;
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        return ((BlockSlabStone)theHalfSlab).func_150002_b(stack.getItemDamage());
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if(isFullBlock)
            return super.onItemUse(stack, player, world, x, y, z, side, par8, par9, par10);
        else if(stack.stackSize == 0)
            return false;
        else if(!player.canPlayerEdit(x, y, z, side, stack))
            return false;
        else
        {
            Block i1 = world.getBlock(x, y, z);
            int j1 = world.getBlockMetadata(x, y, z), k1 = j1 & 7;
            boolean flag = (j1 & 8) != 0;
            if((side == 1 && !flag || side == 0 && flag) && i1 == theHalfSlab && k1 == stack.getItemDamage())
            {
                if(world.checkNoEntityCollision(doubleSlab.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, doubleSlab, k1, 3))
                {
                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), doubleSlab.stepSound.getStepResourcePath(), (doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, doubleSlab.stepSound.getPitch() * 0.8F);
                    --stack.stackSize;
                }
                return true;
            }
            else
                return placeDoubleSlabFromTop(stack, player, world, x, y, z, side) ? true : super.onItemUse(stack, player, world, x, y, z, side, par8, par9, par10);
        }
    }

    private boolean placeDoubleSlabFromTop(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side)
    {
        if(side == 0)
            --y;
        else if(side == 1)
            ++y;
        else if(side == 2)
            --z;
        else if(side == 3)
            ++z;
        else if(side == 4)
            --x;
        else if(side == 5)
            ++x;
        Block i1 = world.getBlock(x, y, z);
        int j1 = world.getBlockMetadata(x, y, z), k1 = j1 & 7;
        if(i1 == theHalfSlab && k1 == stack.getItemDamage())
        {
            if(world.checkNoEntityCollision(doubleSlab.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, doubleSlab, k1, 3))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), doubleSlab.stepSound.getStepResourcePath(), (doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, doubleSlab.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }
            return true;
        }
        else
            return false;
    }
}
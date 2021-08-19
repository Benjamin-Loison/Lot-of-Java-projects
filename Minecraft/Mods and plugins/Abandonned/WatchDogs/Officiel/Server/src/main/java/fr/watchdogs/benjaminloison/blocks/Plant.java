package fr.altiscraft.benjaminloison.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

public class Plant extends BlockBush implements IShearable
{
    public Plant(String name, float hardness)
    {
        super(Material.leaves);
        setBlockName(name);
        setHardness(hardness);
        setBlockBounds(0.1F, 0, 0.1F, 0.9F, 0.8F, 0.9F);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
    {
        return null;
    }

    public int quantityDropped(Random rdm)
    {
        return 0;
    }
}
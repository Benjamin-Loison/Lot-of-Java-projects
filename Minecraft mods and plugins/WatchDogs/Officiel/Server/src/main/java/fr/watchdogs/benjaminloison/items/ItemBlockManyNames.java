package fr.watchdogs.benjaminloison.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockManyNames extends ItemBlock
{
    public ItemBlockManyNames(Block b)
    {
        super(b);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "_" + stack.getItemDamage();
    }

    @Override
    public int getMetadata(int i)
    {
        return i;
    }
}
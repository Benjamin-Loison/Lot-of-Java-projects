package fr.realfaction.benjaminloison.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForgeRecette {
	private static final ForgeRecette smeltingBase = new ForgeRecette();
	private Map smeltingList = new HashMap();
	
	public ForgeRecette()
	{
		this.addRecipe(Items.apple, Items.apple, Items.arrow, new ItemStack(Blocks.diamond_block));
	}
	
	public void addRecipe(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4)
	{
		ItemStack[] stackList = new ItemStack[]{stack1, stack2, stack3};
		this.smeltingList.put(stackList, stack4);
	}

    public void addRecipe(Item item1, Item item2, Item item3, ItemStack stack)
	{
		this.addRecipe(new ItemStack(item1), new ItemStack(item2), new ItemStack(item3), stack);
	}

	public void addRecipe(Block block1, Item item2, Item item3, ItemStack stack)
	{
		this.addRecipe(Item.getItemFromBlock(block1), item2, item3, stack);
	}

	public void addRecipe(Block block1, Block block2, Item item3, ItemStack stack)
	{
		this.addRecipe(Item.getItemFromBlock(block1), Item.getItemFromBlock(block2), item3, stack);
	}
	
	public void addRecipe(Block block1, Block block2, Block block3, ItemStack stack)
	{
		this.addRecipe(Item.getItemFromBlock(block1), Item.getItemFromBlock(block2), Item.getItemFromBlock(block3), stack);
	}
	
	public ItemStack getSmeltingResult(ItemStack[] stack)
	 {
	     Iterator iterator = this.smeltingList.entrySet().iterator();

	     Entry entry;



	     do

	     {

	         if (!iterator.hasNext())

	         {

	             return null;

	         }

           entry = (Entry)iterator.next();

       }

       while (!this.isSameKey(stack, (ItemStack[])entry.getKey()));

	     

       return (ItemStack)entry.getValue();

	  }
	
	private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2)

	 {

		 boolean isSame = false;

		 for(int i=0; i<=2; i++)

		 {

			 if(stackList[i].getItem() == stackList2[i].getItem())

			 {

				 isSame = true;

			 }

			 else

			 {

				 return false;

			 }

		 }

		 return isSame;

	 }
	
	public Map getSmeltingList()

	 {

       return this.smeltingList;

    }



	public static ForgeRecette smelting()

	{

		return smeltingBase;

	}
}

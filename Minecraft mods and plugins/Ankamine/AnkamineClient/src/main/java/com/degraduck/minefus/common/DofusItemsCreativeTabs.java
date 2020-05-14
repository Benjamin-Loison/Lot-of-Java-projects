package com.degraduck.minefus.common;

import java.util.List;

import com.degraduck.minefus.common.Dofus;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DofusItemsCreativeTabs
  extends CreativeTabs
{
  private List list;
  
  public DofusItemsCreativeTabs(String lable)
  {
    super(lable);
  }
  
  private void addItem(Item item)
  {
    item.getSubItems(item, this, list);
  }
  
  private void addBlock(Block block)
  {
    block.getSubBlocks(Item.getItemFromBlock(block), this, list);
  }
  
  public Item getTabIconItem()
  {
    return Dofus.kamas;
  }
  
  public void displayAllReleventItems(List list)
  {
    this.list = list;
  }
  
  public String getTranslatedTabLabel()
  {
    return getTabLabel();
  }
}

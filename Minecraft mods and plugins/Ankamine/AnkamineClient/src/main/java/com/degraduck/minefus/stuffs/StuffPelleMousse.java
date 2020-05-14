package com.degraduck.minefus.stuffs;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class StuffPelleMousse
  extends ItemSword
{
  public StuffPelleMousse(ToolMaterial material)
  {
    super(material);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iconRegister)
  {
    itemIcon = iconRegister.registerIcon("moddofus:icone_pelle_mousse");
  }
  
  public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
  {
    super.addInformation(stack, player, par3List, par4);
  }
}

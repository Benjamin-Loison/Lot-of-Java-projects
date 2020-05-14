package com.degraduck.minefus.stuffs;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class StuffEpeeBouftouRoyal
  extends ItemSword
{
  public StuffEpeeBouftouRoyal(ToolMaterial material)
  {
    super(material);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iconRegister)
  {
    itemIcon = iconRegister.registerIcon("moddofus:icone_epee_bouftou_royal");
  }
  
  public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
  {
    par3List.add("§7Â§oCette Ã©pÃ©e ne sait pas trop");
    par3List.add("§7Â§osur quel sabot danser...");
    par3List.add("");
    par3List.add("§4  Bonus :");
    
    super.addInformation(stack, player, par3List, par4);
  }
}

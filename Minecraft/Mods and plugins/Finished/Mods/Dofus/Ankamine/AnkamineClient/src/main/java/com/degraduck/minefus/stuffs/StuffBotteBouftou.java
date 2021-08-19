package com.degraduck.minefus.stuffs;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StuffBotteBouftou
  extends Item
{
  public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
  {
    par3List.add("§7Â§oVous n'avez pas honte de mettre vos");
    par3List.add("§7Â§opieds dans une carcasse de Bouftou ?");
    
    par3List.add("");
    par3List.add("§6Panoplie Du Bouftou :");
    par3List.add("");
    if ((BonusPanoplie.bonusBouftouAff >= 0) && (BonusPanoplie.bonusBouftouAff < 2))
    {
      par3List.add("§4  Prochain Bonus : " + BonusPanoplie.bonusBouftouAff + "/2");
      par3List.add("");
      par3List.add("§8Â§o+1,0% de Vitesse");
    }
    if ((BonusPanoplie.bonusBouftouAff >= 2) && (BonusPanoplie.bonusBouftouAff < 4))
    {
      par3List.add("§4  Bonus : 2/2");
      par3List.add("");
      par3List.add("§9+1,0% de Vitesse");
      par3List.add("");
      par3List.add("§4  Prochain Bonus : " + BonusPanoplie.bonusBouftouAff + "/4");
      par3List.add("");
      par3List.add("§8Â§o+2,0% de Vitesse");
    }
    if ((BonusPanoplie.bonusBouftouAff >= 4) && (BonusPanoplie.bonusBouftouAff < 6))
    {
      par3List.add("§4  Bonus : 4/4");
      par3List.add("");
      par3List.add("§9+2,0% de Vitesse");
      par3List.add("");
      par3List.add("§4  Prochain Bonus : " + BonusPanoplie.bonusBouftouAff + "/6");
      par3List.add("");
      par3List.add("§8Â§o+2,0% de Vitesse");
      par3List.add("§8Â§o+1,0 Point de Vie");
    }
    if (BonusPanoplie.bonusBouftouAff == 6)
    {
      par3List.add("§4  Bonus : 6/6");
      par3List.add("");
      par3List.add("§9+2,0% de Vitesse");
      par3List.add("§9+1,0 Point de Vie");
    }
    super.addInformation(stack, player, par3List, par4);
  }
}

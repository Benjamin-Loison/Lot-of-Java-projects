package com.degraduck.minefus.stuffs;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StuffAnneauBouftouRoyal
  extends Item
{
  public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
  {
    par3List.add("§7Â§oCet anneau a l'odeur du Bouftou Royal");
    par3List.add("§7Â§oce qui augmente son charme.");
    par3List.add("");
    par3List.add("§6Panoplie Du Bouftou Royal:");
    par3List.add("");
    if ((BonusPanoplie.bonusBouftouRoyalAff >= 0) && (BonusPanoplie.bonusBouftouRoyalAff < 2))
    {
      par3List.add("§4  Prochain Bonus : " + BonusPanoplie.bonusBouftouRoyalAff + "/2");
      par3List.add("");
      par3List.add("§8Â§o+1,0% de Vitesse");
    }
    if ((BonusPanoplie.bonusBouftouRoyalAff >= 2) && (BonusPanoplie.bonusBouftouRoyalAff < 4))
    {
      par3List.add("§4  Bonus : 2/2");
      par3List.add("");
      par3List.add("§9+1,0% de Vitesse");
      par3List.add("");
      par3List.add("§4  Prochain Bonus : " + BonusPanoplie.bonusBouftouRoyalAff + "/4");
      par3List.add("");
      par3List.add("§8Â§o+2,0% de Vitesse");
      par3List.add("§8Â§o+5,0 de Regeneration");
    }
    if ((BonusPanoplie.bonusBouftouRoyalAff >= 4) && (BonusPanoplie.bonusBouftouRoyalAff < 6))
    {
      par3List.add("§4  Bonus : 4/4");
      par3List.add("");
      par3List.add("§9+2,0% de Vitesse");
      par3List.add("§9+5,0 de Regeneration");
      par3List.add("");
      par3List.add("§4  Prochain Bonus : " + BonusPanoplie.bonusBouftouRoyalAff + "/6");
      par3List.add("");
      par3List.add("§8Â§o+2,0% de Vitesse");
      par3List.add("§8Â§o+2,0 Point de Vie");
      par3List.add("§8Â§o+7,5 de Regeneration");
    }
    if (BonusPanoplie.bonusBouftouRoyalAff == 6)
    {
      par3List.add("§4  Bonus : 6/6");
      par3List.add("");
      par3List.add("§9+2,0% de Vitesse");
      par3List.add("§9+2,0 Point de Vie");
      par3List.add("§9+7,5 de Regeneration");
    }
    super.addInformation(stack, player, par3List, par4);
  }
}

package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RoyalBouftouBoots extends Item
{
    public RoyalBouftouBoots()
    {
        setUnlocalizedName("royal_bouftou_boots");
        setTextureName(Minefus.MODID + ":Royal bouftou boots");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "royal_bouftou_boots");
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        list.add("§7Â§oElles brillent, elles respirent la");
        list.add("§7Â§opuissance, ces bottes sont en tous");
        list.add("§7Â§opoints royales.");
        list.add("");
        list.add("§6Panoplie Du Bouftou Royal:");
        list.add("");
        if(PanoplyBonus.royalBouftouBonus < 2)
        {
            list.add("§4  Prochain Bonus : " + PanoplyBonus.royalBouftouBonus + "/2");
            list.add("");
            list.add("§8Â§o+1,0% de Vitesse");
        }
        else if(PanoplyBonus.royalBouftouBonus < 4)
        {
            list.add("§4  Bonus : 2/2");
            list.add("");
            list.add("§9+1,0% de Vitesse");
            list.add("");
            list.add("§4  Prochain Bonus : " + PanoplyBonus.royalBouftouBonus + "/4");
            list.add("");
            list.add("§8Â§o+2,0% de Vitesse");
            list.add("§8Â§o+5,0 de Regeneration");
        }
        else if(PanoplyBonus.royalBouftouBonus < 6)
        {
            list.add("§4  Bonus : 4/4");
            list.add("");
            list.add("§9+2,0% de Vitesse");
            list.add("§9+5,0 de Regeneration");
            list.add("");
            list.add("§4  Prochain Bonus : " + PanoplyBonus.royalBouftouBonus + "/6");
            list.add("");
            list.add("§8Â§o+2,0% de Vitesse");
            list.add("§8Â§o+2,0 Point de Vie");
            list.add("§8Â§o+7,5 de Regeneration");
        }
        else if(PanoplyBonus.royalBouftouBonus == 6)
        {
            list.add("§4  Bonus : 6/6");
            list.add("");
            list.add("§9+2,0% de Vitesse");
            list.add("§9+2,0 Point de Vie");
            list.add("§9+7,5 de Regeneration");
        }
        super.addInformation(stack, player, list, b);
    }
}
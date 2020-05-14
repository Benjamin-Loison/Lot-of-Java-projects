package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BouftouBelt extends Item
{
    public BouftouBelt()
    {
        setUnlocalizedName("bouftou_belt");
        setTextureName(Minefus.MODID + ":Bouftou belt");
        setMaxStackSize(1);
        GameRegistry.registerItem(this, "bouftou_belt");
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 18)
            return Minefus.MODID + ":textures/models/armor/Bouftou belt.png";
        return Minefus.MODID + ":textures/models/armor/Empty.png";
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        list.add("§7Â§oCette ceinture est ridiculement");
        list.add("§7Â§oridicule...");
        list.add("");
        list.add("§6Panoplie Du Bouftou :");
        list.add("");
        if(PanoplyBonus.bouftouBonus < 2)
        {
            list.add("§4  Prochain Bonus : " + PanoplyBonus.bouftouBonus + "/2");
            list.add("");
            list.add("§8Â§o+1,0% de Vitesse");
        }
        else if(PanoplyBonus.bouftouBonus < 4)
        {
            list.add("§4  Bonus : 2/2");
            list.add("");
            list.add("§9+1,0% de Vitesse");
            list.add("");
            list.add("§4  Prochain Bonus : " + PanoplyBonus.bouftouBonus + "/4");
            list.add("");
            list.add("§8Â§o+2,0% de Vitesse");
        }
        else if(PanoplyBonus.bouftouBonus < 6)
        {
            list.add("§4  Bonus : 4/4");
            list.add("");
            list.add("§9+2,0% de Vitesse");
            list.add("");
            list.add("§4  Prochain Bonus : " + PanoplyBonus.bouftouBonus + "/6");
            list.add("");
            list.add("§8Â§o+2,0% de Vitesse");
            list.add("§8Â§o+1,0 Point de Vie");
        }
        else if(PanoplyBonus.bouftouBonus == 6)
        {
            list.add("§4  Bonus : 6/6");
            list.add("");
            list.add("§9+2,0% de Vitesse");
            list.add("§9+1,0 Point de Vie");
        }
        super.addInformation(stack, player, list, b);
    }
}
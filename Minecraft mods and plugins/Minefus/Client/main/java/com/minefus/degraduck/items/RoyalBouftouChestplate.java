package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.models.items.ModelRoyalbouftouChestplate;
import com.minefus.degraduck.models.mobs.ModelChiefOfWarBouftou;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class RoyalBouftouChestplate extends ItemArmor
{
    private ModelBase value;

    public RoyalBouftouChestplate()
    {
        super(Minefus.royalBouftouArmor, 0, 1);
        setUnlocalizedName("royal_bouftou_chestplate");
        setTextureName(Minefus.MODID + ":Royal bouftou chestplate");
        GameRegistry.registerItem(this, "royal_bouftou_chestplate");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 1)
            return Minefus.MODID + ":textures/models/armor/Royal bouftou chestplate.png";
        return Minefus.MODID + ":textures/models/armor/Empty.png";
    }

    public ModelBiped getArmorModel(EntityLivingBase entityliving, ItemStack stack, int armor)
    {
        if(stack.getItem() == Minefus.royalBouftouChestplate)
            return new ModelRoyalbouftouChestplate(armor);
        return null;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        list.add("§7Â§oCette cape portée par le grand Bouftou");
        list.add("§7Â§oRoyal confère style fabuleux.");
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

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre pre)
    {
        value = ((ModelBase)ObfuscationReflectionHelper.getPrivateValue(RendererLivingEntity.class, pre.renderer, new String[] {"mainModel", "field_77045_g"}));
        ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, pre.renderer, new ModelChiefOfWarBouftou(), new String[] {"mainModel", "field_77045_g"});
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post post)
    {
        ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, post.renderer, value, new String[] {"mainModel", "field_77045_g"});
    }
}
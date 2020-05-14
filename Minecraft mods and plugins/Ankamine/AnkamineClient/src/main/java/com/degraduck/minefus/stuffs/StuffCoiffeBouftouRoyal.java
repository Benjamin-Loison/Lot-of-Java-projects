package com.degraduck.minefus.stuffs;

import java.util.List;

import com.degraduck.minefus.common.Dofus;
import com.degraduck.minefus.models.items.ModelCoiffeRoyal;
import com.degraduck.minefus.models.mobs.ModelChefDeGuerre;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class StuffCoiffeBouftouRoyal
  extends ItemArmor
{
  private ModelBase value;
  
  public StuffCoiffeBouftouRoyal(ItemArmor.ArmorMaterial material, int type)
  {
    super(material, 0, type);
  }
  
  public boolean getIsRepairable(ItemStack input, ItemStack repair)
  {
    return true;
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    if (slot == 0) {
      return "moddofus:textures/models/armor/stuff_coiffe_bouftou_royal.png";
    }
    return "moddofus:textures/models/armor/stuff_empty.png";
  }
  
  @SideOnly(Side.CLIENT)
  public ModelBiped getArmorModel(EntityLivingBase entityliving, ItemStack stack, int armor)
  {
    if (stack.getItem() == Dofus.StuffCoiffeBouftouRoyal) {
      return new ModelCoiffeRoyal(armor);
    }
    return null;
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {}
  
  public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
  {
    par3List.add("§7Â§oLa Boufcoiffe Royale est un objet");
    par3List.add("§7Â§oindÃ©modable.");
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
  
  @SubscribeEvent
  public void onRenderPlayerPre(RenderPlayerEvent.Pre pre)
  {
    value = ((ModelBase)ObfuscationReflectionHelper.getPrivateValue(RendererLivingEntity.class, pre.renderer, new String[] { "mainModel", "field_77045_g" }));
    
    ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, pre.renderer, new ModelChefDeGuerre(), new String[] { "mainModel", "field_77045_g" });
  }
  
  @SubscribeEvent
  public void onRenderPlayerPost(RenderPlayerEvent.Post post)
  {
    ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, post.renderer, value, new String[] { "mainModel", "field_77045_g" });
  }
}

package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobPetitChefDeGuerre;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobPetitChefDeGuerre
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_chef_de_guerre_bouftou.png");
  
  public RenderMobPetitChefDeGuerre(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobPetitChefDeGuerre entitymobPetitChefDeGuerre)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobPetitChefDeGuerre)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobPetitChefDeGuerre((EntityMobPetitChefDeGuerre)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobPetitChefDeGuerre(EntityMobPetitChefDeGuerre par1EntityMobPetitChefDeGuerre, float par2)
  {
    switch (par1EntityMobPetitChefDeGuerre.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(1.55F, 1.55F, 1.55F);
      break;
    case 1: 
      GL11.glScalef(1.575F, 1.575F, 1.575F);
      break;
    case 2: 
      GL11.glScalef(1.6F, 1.6F, 1.6F);
      break;
    case 3: 
      GL11.glScalef(1.625F, 1.672F, 1.625F);
      break;
    case 4: 
      GL11.glScalef(1.65F, 1.65F, 1.65F);
      break;
    default: 
      GL11.glScalef(1.6F, 1.6F, 1.6F);
    }
  }
}

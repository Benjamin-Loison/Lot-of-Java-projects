package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobPioussinRose;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobPioussinRose
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_piou_rose.png");
  
  public RenderMobPioussinRose(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobPioussinRose entitymobpioussinrose)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobPioussinRose)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobPioussinRose((EntityMobPioussinRose)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobPioussinRose(EntityMobPioussinRose par1EntityMobentitymobpioussinRose, float par2)
  {
    switch (par1EntityMobentitymobpioussinRose.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.55F, 0.55F, 0.55F);
      break;
    case 1: 
      GL11.glScalef(0.575F, 0.575F, 0.575F);
      break;
    case 2: 
      GL11.glScalef(0.6F, 0.6F, 0.6F);
      break;
    case 3: 
      GL11.glScalef(0.625F, 0.625F, 0.625F);
      break;
    case 4: 
      GL11.glScalef(0.65F, 0.65F, 0.65F);
      break;
    default: 
      GL11.glScalef(0.6F, 0.6F, 0.6F);
    }
  }
}

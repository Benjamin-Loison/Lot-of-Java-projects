package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobMoskitoPeureux;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobMoskitoPeureux
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_moskito.png");
  
  public RenderMobMoskitoPeureux(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobMoskitoPeureux entitymobmoskitopeureux)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobMoskitoPeureux)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobMoskitoPeureux((EntityMobMoskitoPeureux)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobMoskitoPeureux(EntityMobMoskitoPeureux par1EntityMobMoskitoPeureux, float par2)
  {
    switch (par1EntityMobMoskitoPeureux.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.9F, 0.9F, 0.9F);
      break;
    case 1: 
      GL11.glScalef(0.925F, 0.925F, 0.925F);
      break;
    case 2: 
      GL11.glScalef(0.95F, 0.95F, 0.95F);
      break;
    case 3: 
      GL11.glScalef(0.975F, 0.975F, 0.975F);
      break;
    case 4: 
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      break;
    default: 
      GL11.glScalef(0.95F, 0.95F, 0.95F);
    }
  }
}

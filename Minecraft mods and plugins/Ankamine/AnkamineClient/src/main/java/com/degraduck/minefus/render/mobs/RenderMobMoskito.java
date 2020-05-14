package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobMoskito;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobMoskito
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_moskito.png");
  
  public RenderMobMoskito(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobMoskito entitymobmoskito)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobMoskito)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobMoskito((EntityMobMoskito)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobMoskito(EntityMobMoskito par1EntityMobMoskito, float par2)
  {
    switch (par1EntityMobMoskito.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.95F, 0.95F, 0.95F);
      break;
    case 1: 
      GL11.glScalef(0.975F, 0.975F, 0.975F);
      break;
    case 2: 
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      break;
    case 3: 
      GL11.glScalef(1.25F, 1.25F, 1.25F);
      break;
    case 4: 
      GL11.glScalef(1.5F, 1.5F, 1.5F);
      break;
    default: 
      GL11.glScalef(1.0F, 1.0F, 1.0F);
    }
  }
}

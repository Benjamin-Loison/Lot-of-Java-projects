package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobTofu;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobTofu
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_tofu.png");
  
  public RenderMobTofu(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobTofu entitymobtofu)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobTofu)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobTofu((EntityMobTofu)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobTofu(EntityMobTofu par1EntityMobTofu, float par2)
  {
    switch (par1EntityMobTofu.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.7F, 0.7F, 0.7F);
      break;
    case 1: 
      GL11.glScalef(0.725F, 0.725F, 0.725F);
      break;
    case 2: 
      GL11.glScalef(0.75F, 0.75F, 0.75F);
      break;
    case 3: 
      GL11.glScalef(0.775F, 0.775F, 0.775F);
      break;
    case 4: 
      GL11.glScalef(0.8F, 0.8F, 0.8F);
      break;
    default: 
      GL11.glScalef(0.75F, 0.75F, 0.75F);
    }
  }
}

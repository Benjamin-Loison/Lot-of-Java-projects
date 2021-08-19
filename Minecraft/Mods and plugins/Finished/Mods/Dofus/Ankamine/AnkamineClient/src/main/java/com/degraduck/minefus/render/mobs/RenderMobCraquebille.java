package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobCraquebille;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobCraquebille
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_craquebille.png");
  
  public RenderMobCraquebille(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobCraquebille entitymobcraquebille)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobCraquebille)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobCraquebille((EntityMobCraquebille)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobCraquebille(EntityMobCraquebille par1EntityMobCraquebille, float par2)
  {
    switch (par1EntityMobCraquebille.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.8F, 0.8F, 0.8F);
      break;
    case 1: 
      GL11.glScalef(0.825F, 0.825F, 0.825F);
      break;
    case 2: 
      GL11.glScalef(0.85F, 0.85F, 0.85F);
      break;
    case 3: 
      GL11.glScalef(0.875F, 0.875F, 0.875F);
      break;
    case 4: 
      GL11.glScalef(0.9F, 0.9F, 0.9F);
      break;
    default: 
      GL11.glScalef(0.85F, 0.85F, 0.85F);
    }
  }
}

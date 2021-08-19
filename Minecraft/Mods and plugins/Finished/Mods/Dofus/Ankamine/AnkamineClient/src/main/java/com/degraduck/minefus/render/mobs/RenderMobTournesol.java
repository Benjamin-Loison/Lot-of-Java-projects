package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobTournesolSauvage;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobTournesol
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_tournesol.png");
  
  public RenderMobTournesol(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobTournesolSauvage entitymobtournesol)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobTournesolSauvage)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobTournesol((EntityMobTournesolSauvage)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobTournesol(EntityMobTournesolSauvage par1EntityMobTournesolSauvage, float par2)
  {
    switch (par1EntityMobTournesolSauvage.mobSizeGen)
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

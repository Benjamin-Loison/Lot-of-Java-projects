package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobPetitTournesolSauvage;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobPetitTournesol
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_tournesol.png");
  
  public RenderMobPetitTournesol(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobPetitTournesolSauvage entitymobpetittournesol)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobPetitTournesolSauvage)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobPetitTournesol((EntityMobPetitTournesolSauvage)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobPetitTournesol(EntityMobPetitTournesolSauvage par1EntityMobPetitTournesolSauvage, float par2)
  {
    switch (par1EntityMobPetitTournesolSauvage.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.65F, 0.65F, 0.65F);
      break;
    case 1: 
      GL11.glScalef(0.675F, 0.675F, 0.675F);
      break;
    case 2: 
      GL11.glScalef(0.7F, 0.7F, 0.7F);
      break;
    case 3: 
      GL11.glScalef(0.725F, 0.725F, 0.725F);
      break;
    case 4: 
      GL11.glScalef(0.75F, 0.75F, 0.75F);
      break;
    default: 
      GL11.glScalef(0.7F, 0.7F, 0.7F);
    }
  }
}

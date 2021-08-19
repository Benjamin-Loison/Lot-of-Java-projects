package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobArakneMalade;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobArakneMalade
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_arakne_malade.png");
  
  public RenderMobArakneMalade(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobArakneMalade entitymobaraknemalade)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobArakneMalade)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobArakneMalade((EntityMobArakneMalade)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobArakneMalade(EntityMobArakneMalade par1EntityMobArakneMalade, float par2)
  {
    switch (par1EntityMobArakneMalade.mobSizeGen)
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

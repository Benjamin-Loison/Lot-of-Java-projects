package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobBouftonBlanc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobBouftonBlanc
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_boufton_blanc.png");
  
  public RenderMobBouftonBlanc(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobBouftonBlanc entitymobbouftonblanc)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobBouftonBlanc)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobBouftonBlanc((EntityMobBouftonBlanc)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobBouftonBlanc(EntityMobBouftonBlanc par1EntityMobBouftonBlanc, float par2)
  {
    switch (par1EntityMobBouftonBlanc.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(1.2F, 1.2F, 1.2F);
      break;
    case 1: 
      GL11.glScalef(1.225F, 1.225F, 1.225F);
      break;
    case 2: 
      GL11.glScalef(1.25F, 1.25F, 1.25F);
      break;
    case 3: 
      GL11.glScalef(1.275F, 1.275F, 1.275F);
      break;
    case 4: 
      GL11.glScalef(1.3F, 1.3F, 1.3F);
      break;
    default: 
      GL11.glScalef(1.25F, 1.25F, 1.25F);
    }
  }
}

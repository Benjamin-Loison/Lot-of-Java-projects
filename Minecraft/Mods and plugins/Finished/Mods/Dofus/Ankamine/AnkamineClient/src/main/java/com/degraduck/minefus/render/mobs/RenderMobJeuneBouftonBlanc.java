package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobJeuneBouftonBlanc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobJeuneBouftonBlanc
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_boufton_blanc.png");
  
  public RenderMobJeuneBouftonBlanc(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobJeuneBouftonBlanc entitymobjeunebouftonblanc)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobJeuneBouftonBlanc)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobJeuneBouftonBlanc((EntityMobJeuneBouftonBlanc)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobJeuneBouftonBlanc(EntityMobJeuneBouftonBlanc par1EntityMobJeuneBouftonBlanc, float par2)
  {
    switch (par1EntityMobJeuneBouftonBlanc.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(1.15F, 1.15F, 1.15F);
      break;
    case 1: 
      GL11.glScalef(1.175F, 1.175F, 1.175F);
      break;
    case 2: 
      GL11.glScalef(1.2F, 1.2F, 1.2F);
      break;
    case 3: 
      GL11.glScalef(1.225F, 1.225F, 1.225F);
      break;
    case 4: 
      GL11.glScalef(1.25F, 1.25F, 1.25F);
      break;
    default: 
      GL11.glScalef(1.2F, 1.2F, 1.2F);
    }
  }
}

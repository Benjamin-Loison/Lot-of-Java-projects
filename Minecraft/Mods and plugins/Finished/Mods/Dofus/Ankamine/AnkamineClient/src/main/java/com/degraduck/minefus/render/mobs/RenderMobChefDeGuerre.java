package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobChefDeGuerre;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobChefDeGuerre
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_chef_de_guerre_bouftou.png");
  
  public RenderMobChefDeGuerre(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobChefDeGuerre entitymobChefDeGuerre)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobChefDeGuerre)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobChefDeGuerre((EntityMobChefDeGuerre)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobChefDeGuerre(EntityMobChefDeGuerre par1EntityMobChefDeGuerre, float par2)
  {
    switch (par1EntityMobChefDeGuerre.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(1.6F, 1.6F, 1.6F);
      break;
    case 1: 
      GL11.glScalef(1.625F, 1.625F, 1.625F);
      break;
    case 2: 
      GL11.glScalef(1.65F, 1.65F, 1.65F);
      break;
    case 3: 
      GL11.glScalef(1.675F, 1.675F, 1.675F);
      break;
    case 4: 
      GL11.glScalef(1.7F, 1.7F, 1.7F);
      break;
    default: 
      GL11.glScalef(1.65F, 1.65F, 1.65F);
    }
  }
}

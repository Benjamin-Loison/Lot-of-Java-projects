package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobBouftou;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobBouftou
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_bouftou.png");
  
  public RenderMobBouftou(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobBouftou entitymobbouftou)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobBouftou)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobBouftou((EntityMobBouftou)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobBouftou(EntityMobBouftou par1EntityMobBouftou, float par2)
  {
    switch (par1EntityMobBouftou.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(1.45F, 1.45F, 1.45F);
      break;
    case 1: 
      GL11.glScalef(1.475F, 1.475F, 1.475F);
      break;
    case 2: 
      GL11.glScalef(1.5F, 1.5F, 1.5F);
      break;
    case 3: 
      GL11.glScalef(1.525F, 1.525F, 1.525F);
      break;
    case 4: 
      GL11.glScalef(1.55F, 1.55F, 1.55F);
      break;
    default: 
      GL11.glScalef(1.5F, 1.5F, 1.5F);
    }
  }
}

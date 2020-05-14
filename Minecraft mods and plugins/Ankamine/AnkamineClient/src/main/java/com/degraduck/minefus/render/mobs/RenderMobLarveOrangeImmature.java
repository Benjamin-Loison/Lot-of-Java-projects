package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobLarveOrangeImmature;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobLarveOrangeImmature
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_larve_orange.png");
  
  public RenderMobLarveOrangeImmature(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobLarveOrangeImmature entitymoblarveorangeimmature)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobLarveOrangeImmature)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobLarveOrangeImmature((EntityMobLarveOrangeImmature)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobLarveOrangeImmature(EntityMobLarveOrangeImmature par1EntityMobLarveOrangeImmature, float par2)
  {
    switch (par1EntityMobLarveOrangeImmature.mobSizeGen)
    {
    case 0: 
      GL11.glScalef(0.75F, 0.75F, 0.75F);
      break;
    case 1: 
      GL11.glScalef(0.775F, 0.775F, 0.775F);
      break;
    case 2: 
      GL11.glScalef(0.8F, 0.8F, 0.8F);
      break;
    case 3: 
      GL11.glScalef(0.925F, 0.925F, 0.925F);
      break;
    case 4: 
      GL11.glScalef(0.95F, 0.95F, 0.95F);
      break;
    default: 
      GL11.glScalef(0.8F, 0.8F, 0.8F);
    }
  }
}

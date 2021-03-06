package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobLarveBleuImmature;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobLarveBleuImmature
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_larve_bleu.png");
  
  public RenderMobLarveBleuImmature(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobLarveBleuImmature entitymoblarvebleuimmature)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobLarveBleuImmature)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobLarveBleuImmature((EntityMobLarveBleuImmature)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobLarveBleuImmature(EntityMobLarveBleuImmature par1EntityMobLarveBleuImmature, float par2)
  {
    switch (par1EntityMobLarveBleuImmature.mobSizeGen)
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

package com.degraduck.minefus.render.mobs;

import org.lwjgl.opengl.GL11;

import com.degraduck.minefus.entity.EntityMobBouftouRoyale;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMobBouftouRoyal
  extends RenderLiving
{
  private static final ResourceLocation texture = new ResourceLocation("moddofus:textures/entity/mob_bouftou_royal.png");
  
  public RenderMobBouftouRoyal(ModelBase par1ModelBase, float par2)
  {
    super(par1ModelBase, par2);
  }
  
  protected ResourceLocation getEntityTexture(EntityMobBouftouRoyale entitymobbouftouroyal)
  {
    return texture;
  }
  
  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return getEntityTexture((EntityMobBouftouRoyale)par1Entity);
  }
  
  protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
  {
    scaleMobBouftouRoyal((EntityMobBouftouRoyale)par1EntityLivingBase, par2);
  }
  
  protected void scaleMobBouftouRoyal(EntityMobBouftouRoyale par1EntityMobBouftouRoyale, float par2)
  {
    GL11.glScalef(1.95F, 1.95F, 1.95F);
  }
}

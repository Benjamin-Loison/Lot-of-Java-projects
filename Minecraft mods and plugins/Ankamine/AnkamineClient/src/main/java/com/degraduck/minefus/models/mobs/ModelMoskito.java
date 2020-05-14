package com.degraduck.minefus.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMoskito
  extends ModelBase
{
  ModelRenderer head;
  ModelRenderer eyrs;
  ModelRenderer naze;
  ModelRenderer body;
  ModelRenderer leg1;
  ModelRenderer leg2;
  ModelRenderer leg3;
  ModelRenderer leg4;
  ModelRenderer wing1;
  ModelRenderer wing2;
  ModelRenderer wing3;
  ModelRenderer wing4;
  
  public ModelMoskito()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    head = new ModelRenderer(this, 0, 17);
    head.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4);
    head.setRotationPoint(0.0F, 20.5F, -2.0F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    eyrs = new ModelRenderer(this, 0, 26);
    eyrs.addBox(-2.5F, -1.5F, -4.5F, 5, 2, 1);
    eyrs.setRotationPoint(0.0F, 20.5F, -2.0F);
    eyrs.setTextureSize(64, 32);
    eyrs.mirror = true;
    setRotation(eyrs, 0.0F, 0.0F, 0.0F);
    naze = new ModelRenderer(this, 15, 27);
    naze.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4);
    naze.setRotationPoint(0.0F, 21.0F, -6.0F);
    naze.setTextureSize(64, 32);
    naze.mirror = true;
    setRotation(naze, 0.2230717F, 0.0F, 0.0F);
    body = new ModelRenderer(this, 0, 3);
    body.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 10);
    body.setRotationPoint(0.0F, 20.5F, -2.0F);
    body.setTextureSize(64, 32);
    body.mirror = true;
    setRotation(body, 0.1396263F, 0.0F, 0.0F);
    leg1 = new ModelRenderer(this, 0, 0);
    leg1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
    leg1.setRotationPoint(2.0F, 23.0F, -2.5F);
    leg1.setTextureSize(64, 32);
    leg1.mirror = true;
    setRotation(leg1, 0.0F, 0.1745329F, 0.0F);
    leg2 = new ModelRenderer(this, 0, 0);
    leg2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
    leg2.setRotationPoint(2.0F, 23.0F, 1.0F);
    leg2.setTextureSize(64, 32);
    leg2.mirror = true;
    setRotation(leg2, 0.0F, -0.1745329F, 0.0F);
    leg3 = new ModelRenderer(this, 0, 0);
    leg3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
    leg3.setRotationPoint(-2.0F, 23.0F, -2.5F);
    leg3.setTextureSize(64, 32);
    leg3.mirror = true;
    setRotation(leg3, 0.0F, -0.1745329F, 0.0F);
    leg4 = new ModelRenderer(this, 0, 0);
    leg4.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
    leg4.setRotationPoint(-2.0F, 23.0F, 1.0F);
    leg4.setTextureSize(64, 32);
    leg4.mirror = true;
    setRotation(leg4, 0.0F, 0.1745329F, 0.0F);
    wing1 = new ModelRenderer(this, 0, 29);
    wing1.addBox(-0.5F, 0.0F, -1.0F, 5, 1, 2);
    wing1.setRotationPoint(2.0F, 19.0F, -3.5F);
    wing1.setTextureSize(64, 32);
    wing1.mirror = true;
    setRotation(wing1, 0.2617994F, 0.0F, -0.3490659F);
    wing2 = new ModelRenderer(this, 0, 29);
    wing2.addBox(-0.5F, 0.0F, -1.0F, 5, 1, 2);
    wing2.setRotationPoint(2.0F, 19.5F, -3.5F);
    wing2.setTextureSize(64, 32);
    wing2.mirror = true;
    setRotation(wing2, 0.3490659F, 0.0F, 0.1745329F);
    wing3 = new ModelRenderer(this, 0, 29);
    wing3.addBox(-4.5F, 0.0F, -1.0F, 5, 1, 2);
    wing3.setRotationPoint(-2.0F, 19.0F, -3.5F);
    wing3.setTextureSize(64, 32);
    wing3.mirror = true;
    setRotation(wing3, 0.2617994F, 0.0F, 0.3490659F);
    wing4 = new ModelRenderer(this, 0, 29);
    wing4.addBox(-4.5F, 0.0F, -1.0F, 5, 1, 2);
    wing4.setRotationPoint(-2.0F, 19.5F, -3.5F);
    wing4.setTextureSize(64, 32);
    wing4.mirror = true;
    setRotation(wing4, 0.3490659F, 0.0F, -0.1745329F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    eyrs.render(f5);
    naze.render(f5);
    body.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    wing1.render(f5);
    wing2.render(f5);
    wing3.render(f5);
    wing4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    wing1.rotateAngleZ = (MathHelper.cos(f * 3.5F + 3.1415927F) * 2.2F * f1 * 0.6F);
    wing2.rotateAngleZ = (MathHelper.cos(f * 3.5F + 3.1415927F) * -2.2F * f1 * 0.6F);
    
    wing3.rotateAngleZ = (MathHelper.cos(f * 3.5F + 3.1415927F) * 2.2F * f1 * 0.6F);
    wing4.rotateAngleZ = (MathHelper.cos(f * 3.5F + 3.1415927F) * -2.2F * f1 * 0.6F);
    
    leg1.offsetZ = (MathHelper.cos(f * 0.8F + 3.1415927F) * 0.1F * f1 * 0.5F);
    leg2.offsetZ = (MathHelper.cos(f * 0.8F + 3.1415927F) * -0.1F * f1 * 0.5F);
    leg3.offsetZ = (MathHelper.cos(f * 0.8F + 3.1415927F) * -0.1F * f1 * 0.5F);
    leg4.offsetZ = (MathHelper.cos(f * 0.8F + 3.1415927F) * 0.1F * f1 * 0.5F);
  }
}

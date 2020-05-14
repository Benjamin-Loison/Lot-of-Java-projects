package com.degraduck.minefus.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLarveVerte
  extends ModelBase
{
  ModelRenderer head;
  ModelRenderer body1;
  ModelRenderer body2;
  ModelRenderer body3;
  ModelRenderer body14;
  ModelRenderer eye1;
  ModelRenderer eye2;
  ModelRenderer tooth1;
  ModelRenderer tooth2;
  ModelRenderer tooth3;
  
  public ModelLarveVerte()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    head = new ModelRenderer(this, 20, 0);
    head.addBox(-2.0F, -2.0F, -3.0F, 4, 4, 3);
    head.setRotationPoint(0.0F, 22.0F, -3.0F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    body1 = new ModelRenderer(this, 0, 0);
    body1.addBox(-2.5F, -3.0F, 0.0F, 5, 5, 3);
    body1.setRotationPoint(0.0F, 22.03333F, -3.0F);
    body1.setTextureSize(64, 32);
    body1.mirror = true;
    setRotation(body1, 0.0F, 0.0F, 0.0F);
    body2 = new ModelRenderer(this, 0, 8);
    body2.addBox(-3.0F, -3.5F, 3.0F, 6, 6, 3);
    body2.setRotationPoint(0.0F, 22.0F, -3.0F);
    body2.setTextureSize(64, 32);
    body2.mirror = true;
    setRotation(body2, 0.0F, 0.0F, 0.0F);
    body3 = new ModelRenderer(this, 0, 18);
    body3.addBox(-2.5F, -3.0F, 6.0F, 5, 5, 3);
    body3.setRotationPoint(0.0F, 22.0F, -3.0F);
    body3.setTextureSize(64, 32);
    body3.mirror = true;
    setRotation(body3, 0.0F, 0.0F, 0.0F);
    body14 = new ModelRenderer(this, 17, 19);
    body14.addBox(-2.0F, -2.0F, 9.0F, 4, 4, 1);
    body14.setRotationPoint(0.0F, 22.0F, -3.0F);
    body14.setTextureSize(64, 32);
    body14.mirror = true;
    setRotation(body14, 0.0F, 0.0F, 0.0F);
    eye1 = new ModelRenderer(this, 20, 7);
    eye1.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
    eye1.setRotationPoint(-1.5F, 21.5F, -6.0F);
    eye1.setTextureSize(64, 32);
    eye1.mirror = true;
    setRotation(eye1, 0.0F, 0.0F, 0.0F);
    eye2 = new ModelRenderer(this, 20, 12);
    eye2.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
    eye2.setRotationPoint(1.5F, 21.5F, -6.0F);
    eye2.setTextureSize(64, 32);
    eye2.mirror = true;
    setRotation(eye2, 0.0F, 0.0F, 0.0F);
    tooth1 = new ModelRenderer(this, 30, 7);
    tooth1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
    tooth1.setRotationPoint(-2.1F, 23.0F, -6.5F);
    tooth1.setTextureSize(64, 32);
    tooth1.mirror = true;
    setRotation(tooth1, 0.0F, 0.0F, 0.0F);
    tooth2 = new ModelRenderer(this, 30, 7);
    tooth2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
    tooth2.setRotationPoint(1.2F, 23.0F, -6.5F);
    tooth2.setTextureSize(64, 32);
    tooth2.mirror = true;
    setRotation(tooth2, 0.0F, 0.0F, 0.0F);
    tooth3 = new ModelRenderer(this, 30, 7);
    tooth3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
    tooth3.setRotationPoint(-0.5F, 23.0F, -6.5F);
    tooth3.setTextureSize(64, 32);
    tooth3.mirror = true;
    setRotation(tooth3, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body1.render(f5);
    body2.render(f5);
    body3.render(f5);
    body14.render(f5);
    eye1.render(f5);
    eye2.render(f5);
    tooth1.render(f5);
    tooth2.render(f5);
    tooth3.render(f5);
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
  }
}

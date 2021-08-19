package com.degraduck.minefus.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoiffeMousse
  extends ModelBiped
{
  ModelRenderer Shape2;
  ModelRenderer Shape4;
  ModelRenderer Shape1;
  ModelRenderer Shape5;
  ModelRenderer Shape3;
  
  public ModelCoiffeMousse(float f)
  {
    super(f, 0.0F, 64, 64);
    textureWidth = 64;
    textureHeight = 64;
    
    Shape2 = new ModelRenderer(this, 20, 52);
    Shape2.addBox(-3.0F, -3.0F, -5.1F, 6, 3, 1);
    Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape2.setTextureSize(64, 64);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 14, 52);
    Shape4.addBox(3.0F, -10.0F, -5.1F, 2, 10, 1);
    Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape4.setTextureSize(64, 64);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape1 = new ModelRenderer(this, 0, 32);
    Shape1.addBox(-5.0F, -10.0F, -4.1F, 10, 10, 9);
    Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape1.setTextureSize(64, 64);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 35, 52);
    Shape5.addBox(-5.0F, -10.0F, -5.1F, 2, 10, 1);
    Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape5.setTextureSize(64, 64);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 0, 52);
    Shape3.addBox(-3.0F, -10.0F, -5.1F, 6, 5, 1);
    Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape3.setTextureSize(64, 64);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape2.render(f5);
    Shape4.render(f5);
    Shape1.render(f5);
    Shape5.render(f5);
    Shape3.render(f5);
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
    
    Shape1.rotateAngleY = (f3 / 57.295776F);
    Shape1.rotateAngleX = (f4 / 57.295776F);
    Shape2.rotateAngleY = (f3 / 57.295776F);
    Shape2.rotateAngleX = (f4 / 57.295776F);
    Shape3.rotateAngleY = (f3 / 57.295776F);
    Shape3.rotateAngleX = (f4 / 57.295776F);
    Shape4.rotateAngleY = (f3 / 57.295776F);
    Shape4.rotateAngleX = (f4 / 57.295776F);
    Shape5.rotateAngleY = (f3 / 57.295776F);
    Shape5.rotateAngleX = (f4 / 57.295776F);
  }
}

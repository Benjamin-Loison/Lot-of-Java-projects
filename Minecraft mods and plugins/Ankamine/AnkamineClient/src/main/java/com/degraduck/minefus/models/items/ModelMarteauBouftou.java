package com.degraduck.minefus.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMarteauBouftou
  extends ModelBase
{
  ModelRenderer Shape4;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer Shape5;
  ModelRenderer Shape6;
  ModelRenderer Shape7;
  ModelRenderer Shape8;
  ModelRenderer Shape9;
  ModelRenderer Shape10;
  ModelRenderer Shape11;
  ModelRenderer Shape12;
  
  public ModelMarteauBouftou()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    Shape4 = new ModelRenderer(this, 45, 10);
    Shape4.addBox(-2.5F, -12.0F, -2.0F, 5, 3, 4);
    Shape4.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape4.setTextureSize(64, 32);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 16, 0);
    Shape2.addBox(-1.0F, -13.0F, -1.0F, 2, 17, 2);
    Shape2.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape2.setTextureSize(64, 32);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 0, 0);
    Shape3.addBox(-1.5F, -3.5F, -1.5F, 3, 7, 3);
    Shape3.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape3.setTextureSize(64, 32);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 28, 9);
    Shape5.addBox(-2.5F, -14.0F, 2.0F, 5, 5, 3);
    Shape5.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape5.setTextureSize(64, 32);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
    Shape6 = new ModelRenderer(this, 28, 0);
    Shape6.addBox(-2.5F, -14.0F, -5.0F, 5, 5, 3);
    Shape6.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape6.setTextureSize(64, 32);
    Shape6.mirror = true;
    setRotation(Shape6, 0.0F, 0.0F, 0.0F);
    Shape7 = new ModelRenderer(this, 49, 0);
    Shape7.addBox(-3.0F, -14.5F, -6.0F, 6, 6, 1);
    Shape7.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape7.setTextureSize(64, 32);
    Shape7.mirror = true;
    setRotation(Shape7, 0.0F, 0.0F, 0.0F);
    Shape8 = new ModelRenderer(this, 49, 0);
    Shape8.addBox(-3.0F, -14.5F, 5.0F, 6, 6, 1);
    Shape8.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape8.setTextureSize(64, 32);
    Shape8.mirror = true;
    setRotation(Shape8, 0.0F, 0.0F, 0.0F);
    Shape9 = new ModelRenderer(this, 57, 18);
    Shape9.addBox(-1.0F, -16.0F, -4.0F, 2, 2, 1);
    Shape9.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape9.setTextureSize(64, 32);
    Shape9.mirror = true;
    setRotation(Shape9, 0.0F, 0.0F, 0.0F);
    Shape10 = new ModelRenderer(this, 50, 18);
    Shape10.addBox(-1.0F, -16.0F, -3.0F, 2, 1, 1);
    Shape10.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape10.setTextureSize(64, 32);
    Shape10.mirror = true;
    setRotation(Shape10, 0.0F, 0.0F, 0.0F);
    Shape11 = new ModelRenderer(this, 57, 18);
    Shape11.addBox(-1.0F, -16.0F, 3.0F, 2, 2, 1);
    Shape11.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape11.setTextureSize(64, 32);
    Shape11.mirror = true;
    setRotation(Shape11, 0.0F, 0.0F, 0.0F);
    Shape12 = new ModelRenderer(this, 50, 18);
    Shape12.addBox(-1.0F, -16.0F, 2.0F, 2, 1, 1);
    Shape12.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape12.setTextureSize(64, 32);
    Shape12.mirror = true;
    setRotation(Shape12, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape4.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
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

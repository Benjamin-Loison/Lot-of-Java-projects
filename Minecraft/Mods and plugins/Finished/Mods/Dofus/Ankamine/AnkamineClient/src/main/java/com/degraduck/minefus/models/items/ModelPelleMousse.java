package com.degraduck.minefus.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPelleMousse
  extends ModelBase
{
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer Shape4;
  ModelRenderer Shape5;
  ModelRenderer Shape6;
  ModelRenderer Shape7;
  ModelRenderer Shape8;
  ModelRenderer Shape9;
  ModelRenderer Shape10;
  ModelRenderer Shape11;
  ModelRenderer Shape12;
  ModelRenderer Shape13;
  
  public ModelPelleMousse()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    Shape1 = new ModelRenderer(this, 0, 12);
    Shape1.addBox(-1.0F, -12.0F, -1.0F, 2, 16, 2);
    Shape1.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape1.setTextureSize(64, 32);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 0, 0);
    Shape2.addBox(-1.5F, -13.0F, -1.5F, 3, 1, 3);
    Shape2.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape2.setTextureSize(64, 32);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 0, 0);
    Shape3.addBox(-2.5F, -14.0F, -1.5F, 5, 1, 3);
    Shape3.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape3.setTextureSize(64, 32);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 0, 0);
    Shape4.addBox(-3.5F, -16.0F, -1.5F, 7, 2, 3);
    Shape4.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape4.setTextureSize(64, 32);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 0, 0);
    Shape5.addBox(-4.5F, -17.0F, -1.5F, 9, 1, 3);
    Shape5.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape5.setTextureSize(64, 32);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
    Shape6 = new ModelRenderer(this, 0, 0);
    Shape6.addBox(-3.5F, -27.0F, -1.5F, 7, 2, 3);
    Shape6.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape6.setTextureSize(64, 32);
    Shape6.mirror = true;
    setRotation(Shape6, 0.0F, 0.0F, 0.0F);
    Shape7 = new ModelRenderer(this, 0, 0);
    Shape7.addBox(-4.5F, -26.0F, -1.5F, 1, 9, 3);
    Shape7.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape7.setTextureSize(64, 32);
    Shape7.mirror = true;
    setRotation(Shape7, 0.0F, 0.0F, 0.0F);
    Shape8 = new ModelRenderer(this, 0, 0);
    Shape8.addBox(3.5F, -26.0F, -1.5F, 1, 9, 3);
    Shape8.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape8.setTextureSize(64, 32);
    Shape8.mirror = true;
    setRotation(Shape8, 0.0F, 0.0F, 0.0F);
    Shape9 = new ModelRenderer(this, 0, 0);
    Shape9.addBox(-1.0F, -25.0F, -1.5F, 2, 8, 3);
    Shape9.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape9.setTextureSize(64, 32);
    Shape9.mirror = true;
    setRotation(Shape9, 0.0F, 0.0F, 0.0F);
    Shape10 = new ModelRenderer(this, 0, 0);
    Shape10.addBox(-3.7F, -20.0F, -1.5F, 3, 1, 3);
    Shape10.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape10.setTextureSize(64, 32);
    Shape10.mirror = true;
    setRotation(Shape10, 0.0F, 0.0F, 0.0F);
    Shape11 = new ModelRenderer(this, 0, 0);
    Shape11.addBox(0.8F, -23.0F, -1.5F, 3, 1, 3);
    Shape11.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape11.setTextureSize(64, 32);
    Shape11.mirror = true;
    setRotation(Shape11, 0.0F, 0.0F, 0.0F);
    Shape12 = new ModelRenderer(this, 0, 0);
    Shape12.addBox(-3.7F, -23.0F, -1.5F, 3, 1, 3);
    Shape12.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape12.setTextureSize(64, 32);
    Shape12.mirror = true;
    setRotation(Shape12, 0.0F, 0.0F, 0.0F);
    Shape13 = new ModelRenderer(this, 0, 0);
    Shape13.addBox(0.8F, -20.0F, -1.5F, 3, 1, 3);
    Shape13.setRotationPoint(0.0F, 20.0F, 0.0F);
    Shape13.setTextureSize(64, 32);
    Shape13.mirror = true;
    setRotation(Shape13, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {      model.rotateAngleX = x;
  model.rotateAngleY = y;
  model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}

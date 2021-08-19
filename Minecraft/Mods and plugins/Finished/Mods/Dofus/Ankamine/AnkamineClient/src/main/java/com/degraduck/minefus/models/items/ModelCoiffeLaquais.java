package com.degraduck.minefus.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoiffeLaquais
  extends ModelBiped
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
  
  public ModelCoiffeLaquais(float f)
  {
    super(f, 0.0F, 64, 64);
    textureWidth = 64;
    textureHeight = 64;
    
    Shape1 = new ModelRenderer(this, 16, 32);
    Shape1.addBox(-4.5F, -11.1F, -4.5F, 9, 6, 9);
    Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape1.setTextureSize(64, 64);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 0, 39);
    Shape2.addBox(4.0F, -11.0F, -3.5F, 1, 6, 7);
    Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape2.setTextureSize(64, 64);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 0, 32);
    Shape3.addBox(-3.5F, -11.0F, 4.0F, 7, 6, 1);
    Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape3.setTextureSize(64, 64);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 0, 39);
    Shape4.addBox(-5.0F, -11.0F, -3.5F, 1, 6, 7);
    Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape4.setTextureSize(64, 64);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 46, 32);
    Shape5.addBox(-1.0F, -6.1F, -5.5F, 2, 1, 1);
    Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape5.setTextureSize(64, 64);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
    Shape6 = new ModelRenderer(this, 0, 32);
    Shape6.addBox(-3.5F, -11.0F, -5.0F, 7, 6, 1);
    Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape6.setTextureSize(64, 64);
    Shape6.mirror = true;
    setRotation(Shape6, 0.0F, 0.0F, 0.0F);
    Shape7 = new ModelRenderer(this, 46, 32);
    Shape7.addBox(-1.5F, -8.6F, -5.5F, 3, 3, 1);
    Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape7.setTextureSize(64, 64);
    Shape7.mirror = true;
    setRotation(Shape7, 0.0F, 0.0F, 0.0F);
    Shape8 = new ModelRenderer(this, 46, 32);
    Shape8.addBox(1.0F, -8.1F, -5.5F, 1, 2, 1);
    Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape8.setTextureSize(64, 64);
    Shape8.mirror = true;
    setRotation(Shape8, 0.0F, 0.0F, 0.0F);
    Shape9 = new ModelRenderer(this, 46, 32);
    Shape9.addBox(-1.0F, -9.1F, -5.5F, 2, 1, 1);
    Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape9.setTextureSize(64, 64);
    Shape9.mirror = true;
    setRotation(Shape9, 0.0F, 0.0F, 0.0F);
    Shape10 = new ModelRenderer(this, 46, 32);
    Shape10.addBox(-2.0F, -8.1F, -5.5F, 1, 2, 1);
    Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape10.setTextureSize(64, 64);
    Shape10.mirror = true;
    setRotation(Shape10, 0.0F, 0.0F, 0.0F);
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

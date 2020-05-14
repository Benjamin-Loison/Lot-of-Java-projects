package com.degraduck.minefus.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCapeBouftou
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
  ModelRenderer Shape11;
  ModelRenderer Shape12;
  ModelRenderer Shape13;
  ModelRenderer Shape14;
  ModelRenderer Shape15;
  ModelRenderer Shape16;
  
  public ModelCapeBouftou(float f)
  {
    super(f, 0.0F, 64, 64);
    
    textureWidth = 64;
    textureHeight = 64;
    
    Shape1 = new ModelRenderer(this, 0, 50);
    Shape1.addBox(-6.5F, 0.5F, -4.0F, 13, 12, 2);
    Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape1.setTextureSize(64, 64);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 3.141593F, 0.0F);
    Shape2 = new ModelRenderer(this, 54, 58);
    Shape2.addBox(1.0F, 1.0F, -4.5F, 4, 5, 1);
    Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape2.setTextureSize(64, 64);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 3.141593F, 0.0F);
    Shape3 = new ModelRenderer(this, 54, 58);
    Shape3.addBox(-5.0F, 1.0F, -4.5F, 4, 5, 1);
    Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape3.setTextureSize(64, 64);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 3.141593F, 0.0F);
    Shape4 = new ModelRenderer(this, 40, 50);
    Shape4.addBox(-5.5F, 6.0F, -5.0F, 11, 7, 1);
    Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape4.setTextureSize(64, 64);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 3.141593F, 0.0F);
    Shape5 = new ModelRenderer(this, 44, 44);
    Shape5.addBox(-4.5F, 9.0F, -5.5F, 9, 5, 1);
    Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape5.setTextureSize(64, 64);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 3.141593F, 0.0F);
    Shape6 = new ModelRenderer(this, 27, 58);
    Shape6.addBox(-6.5F, 18.0F, -6.0F, 13, 4, 2);
    Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape6.setTextureSize(64, 64);
    Shape6.mirror = true;
    setRotation(Shape6, 0.0F, 3.141593F, 0.0F);
    Shape7 = new ModelRenderer(this, 3, 43);
    Shape7.addBox(-6.0F, 18.5F, -7.0F, 12, 3, 1);
    Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape7.setTextureSize(64, 64);
    Shape7.mirror = true;
    setRotation(Shape7, 0.0F, 3.141593F, 0.0F);
    Shape8 = new ModelRenderer(this, 0, 43);
    Shape8.addBox(-7.0F, 12.5F, -4.0F, 14, 10, 2);
    Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape8.setTextureSize(64, 64);
    Shape8.mirror = true;
    setRotation(Shape8, 0.0F, 3.141593F, 0.0F);
    Shape9 = new ModelRenderer(this, 38, 38);
    Shape9.addBox(-6.0F, 13.0F, -5.0F, 12, 5, 1);
    Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape9.setTextureSize(64, 64);
    Shape9.mirror = true;
    setRotation(Shape9, 0.0F, 3.141593F, 0.0F);
    Shape10 = new ModelRenderer(this, 42, 39);
    Shape10.addBox(-5.0F, 14.0F, -6.0F, 10, 4, 1);
    Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape10.setTextureSize(64, 64);
    Shape10.mirror = true;
    setRotation(Shape10, 0.0F, 3.141593F, 0.0F);
    Shape11 = new ModelRenderer(this, 12, 60);
    Shape11.addBox(-5.0F, 19.3F, -7.5F, 10, 2, 1);
    Shape11.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape11.setTextureSize(64, 64);
    Shape11.mirror = true;
    setRotation(Shape11, 0.0F, 3.141593F, 0.0F);
    Shape12 = new ModelRenderer(this, 28, 50);
    Shape12.addBox(-1.5F, 16.0F, -9.0F, 3, 3, 3);
    Shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape12.setTextureSize(64, 64);
    Shape12.mirror = true;
    setRotation(Shape12, 0.0F, 3.141593F, 0.0F);
    Shape13 = new ModelRenderer(this, 12, 39);
    Shape13.addBox(-8.0F, 20.1F, -6.5F, 3, 2, 2);
    Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape13.setTextureSize(64, 64);
    Shape13.mirror = true;
    setRotation(Shape13, 0.0F, 3.141593F, 0.0F);
    Shape14 = new ModelRenderer(this, 0, 39);
    Shape14.addBox(5.0F, 20.1F, -6.5F, 3, 2, 2);
    Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape14.setTextureSize(64, 64);
    Shape14.mirror = true;
    setRotation(Shape14, 0.0F, 3.141593F, 0.0F);
    Shape15 = new ModelRenderer(this, 0, 0);
    Shape15.addBox(3.0F, -0.5F, -3.0F, 1, 1, 1);
    Shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape15.setTextureSize(64, 64);
    Shape15.mirror = true;
    setRotation(Shape15, 0.0F, 3.141593F, 0.0F);
    Shape16 = new ModelRenderer(this, 0, 0);
    Shape16.addBox(-4.0F, -0.5F, -3.0F, 1, 1, 1);
    Shape16.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape16.setTextureSize(64, 64);
    Shape16.mirror = true;
    setRotation(Shape16, 0.0F, 3.141593F, 0.0F);
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
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
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
    Shape1.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape2.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape3.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape4.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape5.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape6.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape7.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape8.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape9.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape10.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape11.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape12.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape13.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape14.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape15.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
    Shape16.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F);
  }
}

package com.degraduck.minefus.models.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoiffeQuete
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
  
  public ModelCoiffeQuete(float f)
  {
    super(f, 0.0F, 64, 64);
    textureWidth = 64;
    textureHeight = 64;
    
    Shape1 = new ModelRenderer(this, 0, 32);
    Shape1.addBox(-1.0F, -12.0F, 2.0F, 2, 2, 2);
    Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape1.setTextureSize(64, 64);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 0, 32);
    Shape2.addBox(-1.0F, -20.0F, 2.0F, 2, 7, 2);
    Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape2.setTextureSize(64, 64);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 8, 32);
    Shape3.addBox(3.5F, -8.5F, -4.0F, 1, 2, 8);
    Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape3.setTextureSize(64, 64);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 8, 32);
    Shape4.addBox(-4.5F, -8.5F, -4.0F, 1, 2, 8);
    Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape4.setTextureSize(64, 64);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 8, 32);
    Shape5.addBox(-4.0F, -8.5F, 3.5F, 8, 2, 1);
    Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape5.setTextureSize(64, 64);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
    Shape6 = new ModelRenderer(this, 8, 32);
    Shape6.addBox(-4.0F, -8.5F, -4.5F, 8, 2, 1);
    Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape6.setTextureSize(64, 64);
    Shape6.mirror = true;
    setRotation(Shape6, 0.0F, 0.0F, 0.0F);
    Shape7 = new ModelRenderer(this, 0, 42);
    Shape7.addBox(-0.5F, -9.5F, 4.0F, 1, 2, 1);
    Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape7.setTextureSize(64, 64);
    Shape7.mirror = true;
    setRotation(Shape7, 0.0F, 0.0F, 0.0F);
    Shape8 = new ModelRenderer(this, 0, 42);
    Shape8.addBox(-0.5F, -13.5F, 3.5F, 1, 2, 1);
    Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape8.setTextureSize(64, 64);
    Shape8.mirror = true;
    setRotation(Shape8, 0.0F, 0.0F, 0.0F);
    Shape9 = new ModelRenderer(this, 0, 42);
    Shape9.addBox(-0.5F, -11.0F, 3.5F, 1, 2, 1);
    Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape9.setTextureSize(64, 64);
    Shape9.mirror = true;
    setRotation(Shape9, 0.0F, 0.0F, 0.0F);
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
    
    GL11.glPushMatrix();
    GL11.glEnable(2977);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(0.75F, 0.75F, 0.75F, 0.75F);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
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
    Shape6.rotateAngleY = (f3 / 57.295776F);
    Shape6.rotateAngleX = (f4 / 57.295776F);
    Shape7.rotateAngleY = (f3 / 57.295776F);
    Shape7.rotateAngleX = (f4 / 57.295776F);
    Shape8.rotateAngleY = (f3 / 57.295776F);
    Shape8.rotateAngleX = (f4 / 57.295776F);
    Shape9.rotateAngleY = (f3 / 57.295776F);
    Shape9.rotateAngleX = (f4 / 57.295776F);
  }
}

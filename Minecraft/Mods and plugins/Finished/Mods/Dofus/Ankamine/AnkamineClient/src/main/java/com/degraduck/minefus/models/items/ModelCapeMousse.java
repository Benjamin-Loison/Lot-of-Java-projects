package com.degraduck.minefus.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCapeMousse
  extends ModelBiped
{
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  
  public ModelCapeMousse(float f)
  {
    super(f, 0.0F, 64, 64);
    textureWidth = 64;
    textureHeight = 64;
    
    Shape1 = new ModelRenderer(this, 0, 33);
    Shape1.addBox(-6.5F, 0.5F, 2.0F, 13, 12, 1);
    Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape1.setTextureSize(64, 64);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 0, 47);
    Shape2.addBox(-7.0F, 12.5F, 2.0F, 14, 10, 1);
    Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
    Shape2.setTextureSize(64, 64);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
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
    Shape1.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F + f1 / 1.4F + 0.1F);
    Shape2.rotateAngleX = (MathHelper.cos(f2 * 0.1F) / 25.0F + f1 / 1.4F + 0.1F);
  }
}

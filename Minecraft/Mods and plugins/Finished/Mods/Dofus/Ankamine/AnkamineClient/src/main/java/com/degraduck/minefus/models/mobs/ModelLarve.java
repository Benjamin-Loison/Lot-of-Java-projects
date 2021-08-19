package com.degraduck.minefus.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelLarve
  extends ModelBase
{
  ModelRenderer body1;
  ModelRenderer body2;
  ModelRenderer body3;
  ModelRenderer body14;
  ModelRenderer Piece1;
  
  public ModelLarve()
  {
    textureWidth = 64;
    textureHeight = 32;
    setTextureOffset("Piece1.head", 20, 0);
    setTextureOffset("Piece1.eye1", 20, 7);
    setTextureOffset("Piece1.eye2", 20, 12);
    setTextureOffset("Piece1.tooth1", 30, 7);
    setTextureOffset("Piece1.tooth3", 30, 7);
    setTextureOffset("Piece1.tooth2", 30, 7);
    
    body1 = new ModelRenderer(this, 0, 0);
    body1.addBox(-2.5F, -3.0F, -1.5F, 5, 5, 3);
    body1.setRotationPoint(0.0F, 22.03333F, -2.5F);
    body1.setTextureSize(64, 32);
    body1.mirror = true;
    setRotation(body1, 0.0F, 0.0F, 0.0F);
    body2 = new ModelRenderer(this, 0, 8);
    body2.addBox(-3.0F, -3.5F, -1.5F, 6, 6, 3);
    body2.setRotationPoint(0.0F, 22.0F, 0.0F);
    body2.setTextureSize(64, 32);
    body2.mirror = true;
    setRotation(body2, 0.0F, 0.0F, 0.0F);
    body3 = new ModelRenderer(this, 0, 18);
    body3.addBox(-2.5F, -3.0F, -1.5F, 5, 5, 3);
    body3.setRotationPoint(0.0F, 22.0F, 2.5F);
    body3.setTextureSize(64, 32);
    body3.mirror = true;
    setRotation(body3, 0.0F, 0.0F, 0.0F);
    body14 = new ModelRenderer(this, 17, 19);
    body14.addBox(-2.0F, -2.0F, -1.0F, 4, 4, 2);
    body14.setRotationPoint(0.0F, 22.0F, 4.0F);
    body14.setTextureSize(64, 32);
    body14.mirror = true;
    setRotation(body14, 0.0F, 0.0F, 0.0F);
    Piece1 = new ModelRenderer(this, "Piece1");
    Piece1.setRotationPoint(0.0F, 22.0F, -3.0F);
    setRotation(Piece1, 0.0F, 0.0F, 0.0F);
    Piece1.mirror = true;
    Piece1.addBox("head", -2.0F, -2.0F, -3.0F, 4, 4, 3);
    Piece1.addBox("eye1", -2.5F, -1.5F, -4.0F, 2, 2, 2);
    Piece1.addBox("eye2", 0.5F, -1.5F, -4.0F, 2, 2, 2);
    Piece1.addBox("tooth1", -2.1F, 1.0F, -3.5F, 1, 1, 1);
    Piece1.addBox("tooth3", -0.5F, 1.0F, -3.5F, 1, 1, 1);
    Piece1.addBox("tooth2", 1.1F, 1.0F, -3.5F, 1, 1, 1);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body1.render(f5);
    body2.render(f5);
    body3.render(f5);
    body14.render(f5);
    Piece1.render(f5);
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
    Piece1.offsetZ = (MathHelper.cos(f * 1.15F + 3.1415927F) * -0.2F * f1 * 0.6F);
    body1.offsetZ = (MathHelper.cos(f * 1.15F + 3.1415927F) * -0.2F * f1 * 0.6F);
    body3.offsetZ = (MathHelper.cos(f * 1.15F + 3.1415927F) * 0.2F * f1 * 0.6F);
    body14.offsetZ = (MathHelper.cos(f * 1.15F + 3.1415927F) * 0.2F * f1 * 0.6F);
  }
}

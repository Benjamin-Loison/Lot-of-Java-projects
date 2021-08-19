package com.degraduck.minefus.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBouftonBlanc
  extends ModelBase
{
  ModelRenderer body;
  ModelRenderer head;
  ModelRenderer leg1;
  ModelRenderer leg2;
  ModelRenderer leg3;
  ModelRenderer leg4;
  ModelRenderer horn1;
  ModelRenderer horn2;
  ModelRenderer horn3;
  ModelRenderer horn4;
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer Shape4;
  ModelRenderer Shape5;
  ModelRenderer Piece1;
  
  public ModelBouftonBlanc()
  {
    textureWidth = 64;
    textureHeight = 32;
    setTextureOffset("Piece1.tail1", 0, 12);
    setTextureOffset("Piece1.Shape6", 0, 8);
    setTextureOffset("Piece1.Shape8", 13, 0);
    setTextureOffset("Piece1.Shape7", 15, 4);
    
    body = new ModelRenderer(this, 0, 19);
    body.addBox(-3.0F, -2.5F, 0.0F, 6, 5, 8);
    body.setRotationPoint(0.0F, 20.0F, -4.0F);
    body.setTextureSize(64, 32);
    body.mirror = true;
    setRotation(body, 0.0F, 0.0F, 0.0F);
    head = new ModelRenderer(this, 28, 27);
    head.addBox(-2.5F, -2.0F, -2.5F, 5, 4, 1);
    head.setRotationPoint(0.0F, 20.0F, -2.0F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    leg1 = new ModelRenderer(this, 30, 22);
    leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
    leg1.setRotationPoint(1.5F, 22.0F, -1.5F);
    leg1.setTextureSize(64, 32);
    leg1.mirror = true;
    setRotation(leg1, 0.0F, 0.0F, 0.0F);
    leg2 = new ModelRenderer(this, 30, 22);
    leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
    leg2.setRotationPoint(1.5F, 22.0F, 1.8F);
    leg2.setTextureSize(64, 32);
    leg2.mirror = true;
    setRotation(leg2, 0.0F, 0.0F, 0.0F);
    leg3 = new ModelRenderer(this, 30, 22);
    leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
    leg3.setRotationPoint(-1.5F, 22.0F, -1.5F);
    leg3.setTextureSize(64, 32);
    leg3.mirror = true;
    setRotation(leg3, 0.0F, 0.0F, 0.0F);
    leg4 = new ModelRenderer(this, 30, 22);
    leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
    leg4.setRotationPoint(-1.5F, 22.0F, 1.8F);
    leg4.setTextureSize(64, 32);
    leg4.mirror = true;
    setRotation(leg4, 0.0F, 0.0F, 0.0F);
    horn1 = new ModelRenderer(this, 13, 14);
    horn1.addBox(0.0F, -1.0F, -1.0F, 2, 2, 2);
    horn1.setRotationPoint(3.0F, 18.6F, -1.0F);
    horn1.setTextureSize(64, 32);
    horn1.mirror = true;
    setRotation(horn1, 0.0F, 0.0F, 0.0F);
    horn2 = new ModelRenderer(this, 22, 15);
    horn2.addBox(0.0F, -1.5F, -0.5F, 1, 2, 1);
    horn2.setRotationPoint(4.5F, 18.6F, -1.0F);
    horn2.setTextureSize(64, 32);
    horn2.mirror = true;
    setRotation(horn2, 0.0F, 0.0F, 0.0F);
    horn3 = new ModelRenderer(this, 13, 10);
    horn3.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2);
    horn3.setRotationPoint(-3.0F, 18.6F, -1.0F);
    horn3.setTextureSize(64, 32);
    horn3.mirror = true;
    setRotation(horn3, 0.0F, 0.0F, 0.0F);
    horn4 = new ModelRenderer(this, 22, 15);
    horn4.addBox(-1.0F, -1.5F, -0.5F, 1, 2, 1);
    horn4.setRotationPoint(-4.5F, 18.6F, -1.0F);
    horn4.setTextureSize(64, 32);
    horn4.mirror = true;
    setRotation(horn4, 0.0F, 0.0F, 0.0F);
    Shape1 = new ModelRenderer(this, 29, 17);
    Shape1.addBox(-2.0F, -1.5F, -3.0F, 4, 3, 1);
    Shape1.setRotationPoint(0.0F, 20.0F, -2.0F);
    Shape1.setTextureSize(64, 32);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 42, 3);
    Shape2.addBox(-2.5F, -3.0F, 1.5F, 5, 1, 6);
    Shape2.setRotationPoint(0.0F, 20.0F, -4.0F);
    Shape2.setTextureSize(64, 32);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 25, 0);
    Shape3.addBox(2.5F, -2.0F, 1.5F, 1, 4, 6);
    Shape3.setRotationPoint(0.0F, 20.0F, -4.0F);
    Shape3.setTextureSize(64, 32);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 25, 0);
    Shape4.addBox(-3.5F, -2.0F, 1.5F, 1, 4, 6);
    Shape4.setRotationPoint(0.0F, 20.0F, -4.0F);
    Shape4.setTextureSize(64, 32);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 0, 0);
    Shape5.addBox(-2.5F, -2.0F, 7.5F, 5, 4, 1);
    Shape5.setRotationPoint(0.0F, 20.0F, -4.0F);
    Shape5.setTextureSize(64, 32);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
    Piece1 = new ModelRenderer(this, "Piece1");
    Piece1.setRotationPoint(0.0F, 20.0F, 3.5F);
    setRotation(Piece1, 0.0F, 0.0F, 0.0F);
    Piece1.mirror = true;
    Piece1.addBox("tail1", -1.5F, -1.5F, 1.0F, 3, 3, 2);
    Piece1.addBox("Shape6", -1.0F, -1.0F, 2.5F, 2, 2, 1);
    Piece1.addBox("Shape8", -2.0F, -1.0F, 1.5F, 4, 2, 1);
    Piece1.addBox("Shape7", -1.0F, -2.0F, 1.5F, 2, 4, 1);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body.render(f5);
    head.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    horn1.render(f5);
    horn2.render(f5);
    horn3.render(f5);
    horn4.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
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
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    leg1.rotateAngleX = (MathHelper.cos(f * 1.1F + 3.1415927F) * 2.0F * f1 * 0.6F);
    leg2.rotateAngleX = (MathHelper.cos(f * 1.1F + 3.1415927F) * -2.0F * f1 * 0.6F);
    leg3.rotateAngleX = (MathHelper.cos(f * 1.1F + 3.1415927F) * 2.0F * f1 * 0.6F);
    leg4.rotateAngleX = (MathHelper.cos(f * 1.2F + 3.1415927F) * -2.0F * f1 * 0.6F);
    
    Piece1.rotateAngleY = (MathHelper.cos(f * 0.9F + 3.1415927F) * 1.0F * f1 * 0.6F);
  }
}

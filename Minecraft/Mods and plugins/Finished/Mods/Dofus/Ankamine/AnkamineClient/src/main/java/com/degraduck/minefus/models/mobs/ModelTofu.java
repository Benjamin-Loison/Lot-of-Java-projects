package com.degraduck.minefus.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTofu
  extends ModelBase
{
  ModelRenderer head;
  ModelRenderer foot1;
  ModelRenderer foot2;
  ModelRenderer bec;
  ModelRenderer wing1;
  ModelRenderer wing2;
  ModelRenderer chev1;
  ModelRenderer chev2;
  ModelRenderer chev3;
  ModelRenderer Shape1;
  ModelRenderer Shape2;
  ModelRenderer Shape3;
  ModelRenderer Shape4;
  ModelRenderer Shape5;
  
  public ModelTofu()
  {
    textureWidth = 64;
    textureHeight = 32;
    
    head = new ModelRenderer(this, 0, 18);
    head.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
    head.setRotationPoint(0.0F, 18.5F, 0.0F);
    head.setTextureSize(64, 32);
    head.mirror = true;
    setRotation(head, 0.0F, 0.0F, 0.0F);
    foot1 = new ModelRenderer(this, 29, 24);
    foot1.addBox(-1.5F, 0.0F, -5.0F, 3, 2, 5);
    foot1.setRotationPoint(-2.0F, 22.0F, 0.0F);
    foot1.setTextureSize(64, 32);
    foot1.mirror = true;
    setRotation(foot1, 0.0F, 0.3323355F, 0.0F);
    foot2 = new ModelRenderer(this, 29, 24);
    foot2.addBox(-1.5F, 0.0F, -5.0F, 3, 2, 5);
    foot2.setRotationPoint(2.0F, 22.0F, 0.0F);
    foot2.setTextureSize(64, 32);
    foot2.mirror = true;
    setRotation(foot2, 0.0F, -0.4658687F, 0.0F);
    bec = new ModelRenderer(this, 0, 12);
    bec.addBox(-2.0F, -0.5F, -2.0F, 4, 1, 4);
    bec.setRotationPoint(0.0F, 20.5F, -3.2F);
    bec.setTextureSize(64, 32);
    bec.mirror = true;
    setRotation(bec, 0.2094395F, 0.7853982F, 0.2094395F);
    wing1 = new ModelRenderer(this, 53, 22);
    wing1.addBox(0.0F, -0.5F, -2.0F, 1, 4, 4);
    wing1.setRotationPoint(4.0F, 17.5F, 0.0F);
    wing1.setTextureSize(64, 32);
    wing1.mirror = true;
    setRotation(wing1, 0.0F, 0.0F, 0.0F);
    wing2 = new ModelRenderer(this, 53, 13);
    wing2.addBox(-1.0F, -0.5F, -2.0F, 1, 4, 4);
    wing2.setRotationPoint(-4.0F, 17.5F, 0.0F);
    wing2.setTextureSize(64, 32);
    wing2.mirror = true;
    setRotation(wing2, 0.0F, 0.0F, 0.0F);
    chev1 = new ModelRenderer(this, 18, 0);
    chev1.addBox(-0.5F, -5.5F, -0.5F, 1, 4, 1);
    chev1.setRotationPoint(0.0F, 16.0F, 0.0F);
    chev1.setTextureSize(64, 32);
    chev1.mirror = true;
    setRotation(chev1, 0.2974289F, -0.669215F, 0.0F);
    chev2 = new ModelRenderer(this, 18, 0);
    chev2.addBox(-0.5F, -7.0F, -0.5F, 1, 6, 1);
    chev2.setRotationPoint(0.0F, 16.0F, 0.0F);
    chev2.setTextureSize(64, 32);
    chev2.mirror = true;
    setRotation(chev2, 0.0F, 0.0F, -0.2230717F);
    chev3 = new ModelRenderer(this, 18, 0);
    chev3.addBox(-0.5F, -6.5F, -0.5F, 1, 5, 1);
    chev3.setRotationPoint(0.0F, 16.0F, 0.0F);
    chev3.setTextureSize(64, 32);
    chev3.mirror = true;
    setRotation(chev3, 0.0F, -0.9294653F, 0.2974289F);
    Shape1 = new ModelRenderer(this, 49, 0);
    Shape1.addBox(3.0F, -4.0F, -3.0F, 1, 6, 6);
    Shape1.setRotationPoint(0.0F, 19.5F, 0.0F);
    Shape1.setTextureSize(64, 32);
    Shape1.mirror = true;
    setRotation(Shape1, 0.0F, 0.0F, 0.0F);
    Shape2 = new ModelRenderer(this, 49, 0);
    Shape2.addBox(-4.0F, -4.0F, -3.0F, 1, 6, 6);
    Shape2.setRotationPoint(0.0F, 19.5F, 0.0F);
    Shape2.setTextureSize(64, 32);
    Shape2.mirror = true;
    setRotation(Shape2, 0.0F, 0.0F, 0.0F);
    Shape3 = new ModelRenderer(this, 33, 0);
    Shape3.addBox(-3.0F, -4.0F, 3.0F, 6, 6, 1);
    Shape3.setRotationPoint(0.0F, 19.5F, 0.0F);
    Shape3.setTextureSize(64, 32);
    Shape3.mirror = true;
    setRotation(Shape3, 0.0F, 0.0F, 0.0F);
    Shape4 = new ModelRenderer(this, 33, 0);
    Shape4.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 1);
    Shape4.setRotationPoint(0.0F, 19.5F, 0.0F);
    Shape4.setTextureSize(64, 32);
    Shape4.mirror = true;
    setRotation(Shape4, 0.0F, 0.0F, 0.0F);
    Shape5 = new ModelRenderer(this, 23, 8);
    Shape5.addBox(-3.0F, -5.0F, -3.0F, 6, 1, 6);
    Shape5.setRotationPoint(0.0F, 19.5F, 0.0F);
    Shape5.setTextureSize(64, 32);
    Shape5.mirror = true;
    setRotation(Shape5, 0.0F, 0.0F, 0.0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    foot1.render(f5);
    foot2.render(f5);
    bec.render(f5);
    wing1.render(f5);
    wing2.render(f5);
    chev1.render(f5);
    chev2.render(f5);
    chev3.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
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
    foot1.offsetZ = (MathHelper.cos(f * 1.5F + 3.1415927F) * 0.25F * f1 * 0.4F);
    foot2.offsetZ = (MathHelper.cos(f * 1.5F + 3.1415927F) * -0.25F * f1 * 0.4F);
    
    wing1.rotateAngleZ = (MathHelper.cos(f * 1.5F + 3.1415927F) * 0.5F * f1 * 0.6F);
    wing2.rotateAngleZ = (MathHelper.cos(f * 1.5F + 3.1415927F) * -0.5F * f1 * 0.6F);
  }
}

package com.degraduck.minefus.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelChampchamp
  extends ModelBase
{
  ModelRenderer body1;
  ModelRenderer body2;
  ModelRenderer leg1;
  ModelRenderer leg2;
  ModelRenderer leg3;
  ModelRenderer leg4;
  ModelRenderer Piece1;
  
  public ModelChampchamp()
  {
    textureWidth = 128;
    textureHeight = 256;
    setTextureOffset("Piece1.head1", 0, 55);
    setTextureOffset("Piece1.head3", 0, 82);
    setTextureOffset("Piece1.head2", 0, 36);
    setTextureOffset("Piece1.head4", 0, 104);
    setTextureOffset("Piece1.head5", 0, 124);
    setTextureOffset("Piece1.head6", 6, 126);
    setTextureOffset("Piece1.thorn1", 0, 76);
    setTextureOffset("Piece1.head7", 0, 143);
    setTextureOffset("Piece1.thorn2", 0, 76);
    setTextureOffset("Piece1.head8", 0, 0);
    setTextureOffset("Piece1.thorn3", 0, 76);
    setTextureOffset("Piece1.head9", 66, 36);
    setTextureOffset("Piece1.thorn4", 0, 76);
    setTextureOffset("Piece1.thorn7", 0, 76);
    setTextureOffset("Piece1.thorn5", 0, 76);
    setTextureOffset("Piece1.thorn6", 0, 76);
    
    body1 = new ModelRenderer(this, 0, 22);
    body1.addBox(-4.5F, 2.5F, -4.5F, 9, 4, 9);
    body1.setRotationPoint(0.0F, 17.0F, 0.0F);
    body1.setTextureSize(128, 256);
    body1.mirror = true;
    setRotation(body1, 0.0F, 0.0F, 0.0F);
    body2 = new ModelRenderer(this, 36, 22);
    body2.addBox(-3.0F, -4.0F, -3.0F, 6, 6, 6);
    body2.setRotationPoint(0.0F, 18.0F, 0.0F);
    body2.setTextureSize(128, 256);
    body2.mirror = true;
    setRotation(body2, 0.0F, 0.0F, 0.0F);
    leg1 = new ModelRenderer(this, 74, 0);
    leg1.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
    leg1.setRotationPoint(4.5F, 21.0F, -4.5F);
    leg1.setTextureSize(128, 256);
    leg1.mirror = true;
    setRotation(leg1, 0.0F, 0.0F, 0.0F);
    leg2 = new ModelRenderer(this, 74, 0);
    leg2.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
    leg2.setRotationPoint(-4.5F, 21.0F, -4.5F);
    leg2.setTextureSize(128, 256);
    leg2.mirror = true;
    setRotation(leg2, 0.0F, 0.0F, 0.0F);
    leg3 = new ModelRenderer(this, 74, 0);
    leg3.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
    leg3.setRotationPoint(4.5F, 21.0F, 4.5F);
    leg3.setTextureSize(128, 256);
    leg3.mirror = true;
    setRotation(leg3, 0.0F, 0.0F, 0.0F);
    leg4 = new ModelRenderer(this, 74, 0);
    leg4.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
    leg4.setRotationPoint(-4.5F, 21.0F, 4.5F);
    leg4.setTextureSize(128, 256);
    leg4.mirror = true;
    setRotation(leg4, 0.0F, 0.0F, 0.0F);
    Piece1 = new ModelRenderer(this, "Piece1");
    Piece1.setRotationPoint(0.0F, 13.0F, 0.0F);
    setRotation(Piece1, 0.0F, 0.0F, 0.0F);
    Piece1.mirror = true;
    Piece1.addBox("head1", -9.0F, -2.0F, -9.0F, 18, 2, 18);
    Piece1.addBox("head3", -8.5F, -4.0F, -8.5F, 17, 2, 17);
    Piece1.addBox("head2", -8.0F, 0.0F, -8.0F, 16, 1, 16);
    Piece1.addBox("head4", -8.0F, -5.0F, -8.0F, 16, 1, 16);
    Piece1.addBox("head5", -7.0F, -6.0F, -7.0F, 14, 1, 14);
    Piece1.addBox("head6", -6.0F, -7.0F, -6.0F, 12, 1, 12);
    Piece1.addBox("thorn1", 3.5F, -10.0F, 2.5F, 2, 3, 2);
    Piece1.addBox("head7", -5.0F, -8.0F, -5.0F, 10, 1, 10);
    Piece1.addBox("thorn2", -8.5F, -7.0F, -6.0F, 2, 3, 2);
    Piece1.addBox("head8", -3.5F, -9.0F, -3.5F, 7, 1, 7);
    Piece1.addBox("thorn3", -3.0F, -9.0F, -6.5F, 2, 3, 2);
    Piece1.addBox("head9", -6.5F, 0.5F, -6.5F, 13, 1, 13);
    Piece1.addBox("thorn4", 6.5F, -7.0F, -1.0F, 2, 3, 2);
    Piece1.addBox("thorn7", -1.0F, -8.0F, 6.0F, 2, 3, 2);
    Piece1.addBox("thorn5", -7.0F, -9.0F, 2.0F, 2, 3, 2);
    Piece1.addBox("thorn6", 5.5F, -8.0F, -7.5F, 2, 3, 2);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body1.render(f5);
    body2.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
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
    leg1.rotateAngleX = (MathHelper.cos(f * 3.4F + 3.1415927F) * 2.5F * f1 * 0.6F);
    leg2.rotateAngleX = (MathHelper.cos(f * 3.4F + 3.1415927F) * -2.5F * f1 * 0.6F);
    leg3.rotateAngleX = (MathHelper.cos(f * 3.4F + 3.1415927F) * -2.5F * f1 * 0.6F);
    leg4.rotateAngleX = (MathHelper.cos(f * 3.4F + 3.1415927F) * 2.5F * f1 * 0.6F);
    
    Piece1.rotateAngleX = (MathHelper.cos(f * 0.2F + 3.1415927F) * 0.3F * f1 * 0.6F);
    Piece1.rotateAngleY = (MathHelper.cos(f * 0.2F + 3.1415927F) * 0.3F * f1 * 0.6F);
    Piece1.rotateAngleZ = (MathHelper.cos(f * 0.2F + 3.1415927F) * -0.3F * f1 * 0.6F);
  }
}

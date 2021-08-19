package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelLarva extends ModelBase
{
    ModelRenderer one, two, three, four, five;

    public ModelLarva()
    {
        textureWidth = 64;
        textureHeight = 32;
        setTextureOffset("Piece1.head", 20, 0);
        setTextureOffset("Piece1.eye1", 20, 7);
        setTextureOffset("Piece1.eye2", 20, 12);
        setTextureOffset("Piece1.tooth1", 30, 7);
        setTextureOffset("Piece1.tooth3", 30, 7);
        setTextureOffset("Piece1.tooth2", 30, 7);
        one = new ModelRenderer(this, 0, 0);
        one.addBox(-2.5F, -3.0F, -1.5F, 5, 5, 3);
        one.setRotationPoint(0.0F, 22.03333F, -2.5F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 8);
        two.addBox(-3.0F, -3.5F, -1.5F, 6, 6, 3);
        two.setRotationPoint(0.0F, 22.0F, 0.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 18);
        three.addBox(-2.5F, -3.0F, -1.5F, 5, 5, 3);
        three.setRotationPoint(0.0F, 22.0F, 2.5F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 17, 19);
        four.addBox(-2.0F, -2.0F, -1.0F, 4, 4, 2);
        four.setRotationPoint(0.0F, 22.0F, 4.0F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, "Piece1");
        five.setRotationPoint(0.0F, 22.0F, -3.0F);
        setRotation(five, 0.0F, 0.0F, 0.0F);
        five.mirror = true;
        five.addBox("head", -2.0F, -2.0F, -3.0F, 4, 4, 3);
        five.addBox("eye1", -2.5F, -1.5F, -4.0F, 2, 2, 2);
        five.addBox("eye2", 0.5F, -1.5F, -4.0F, 2, 2, 2);
        five.addBox("tooth1", -2.1F, 1.0F, -3.5F, 1, 1, 1);
        five.addBox("tooth3", -0.5F, 1.0F, -3.5F, 1, 1, 1);
        five.addBox("tooth2", 1.1F, 1.0F, -3.5F, 1, 1, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        one.render(f5);
        two.render(f5);
        three.render(f5);
        four.render(f5);
        five.render(f5);
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
        five.offsetZ = MathHelper.cos(f * 1.15F + 3.1415927F) * -0.2F * f1 * 0.6F;
        one.offsetZ = MathHelper.cos(f * 1.15F + 3.1415927F) * -0.2F * f1 * 0.6F;
        three.offsetZ = MathHelper.cos(f * 1.15F + 3.1415927F) * 0.2F * f1 * 0.6F;
        four.offsetZ = MathHelper.cos(f * 1.15F + 3.1415927F) * 0.2F * f1 * 0.6F;
    }
}
package com.minefus.degraduck.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMousseHelmet extends ModelBiped
{
    ModelRenderer one, two, three, four, five;

    public ModelMousseHelmet(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        one = new ModelRenderer(this, 20, 52);
        one.addBox(-3.0F, -3.0F, -5.1F, 6, 3, 1);
        one.setRotationPoint(0.0F, 0.0F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 14, 52);
        two.addBox(3.0F, -10.0F, -5.1F, 2, 10, 1);
        two.setRotationPoint(0.0F, 0.0F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 32);
        three.addBox(-5.0F, -10.0F, -4.1F, 10, 10, 9);
        three.setRotationPoint(0.0F, 0.0F, 0.0F);
        three.setTextureSize(64, 64);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 35, 52);
        four.addBox(-5.0F, -10.0F, -5.1F, 2, 10, 1);
        four.setRotationPoint(0.0F, 0.0F, 0.0F);
        four.setTextureSize(64, 64);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 0, 52);
        five.addBox(-3.0F, -10.0F, -5.1F, 6, 5, 1);
        five.setRotationPoint(0.0F, 0.0F, 0.0F);
        five.setTextureSize(64, 64);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
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
        three.rotateAngleY = f3 / 57.295776F;
        three.rotateAngleX = f4 / 57.295776F;
        one.rotateAngleY = f3 / 57.295776F;
        one.rotateAngleX = f4 / 57.295776F;
        five.rotateAngleY = f3 / 57.295776F;
        five.rotateAngleX = f4 / 57.295776F;
        two.rotateAngleY = f3 / 57.295776F;
        two.rotateAngleX = f4 / 57.295776F;
        four.rotateAngleY = f3 / 57.295776F;
        four.rotateAngleX = f4 / 57.295776F;
    }
}
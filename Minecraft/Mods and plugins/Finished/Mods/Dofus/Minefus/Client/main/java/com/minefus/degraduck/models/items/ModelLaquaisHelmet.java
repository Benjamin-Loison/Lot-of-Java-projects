package com.minefus.degraduck.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLaquaisHelmet extends ModelBiped
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten;

    public ModelLaquaisHelmet(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        one = new ModelRenderer(this, 16, 32);
        one.addBox(-4.5F, -11.1F, -4.5F, 9, 6, 9);
        one.setRotationPoint(0.0F, 0.0F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 39);
        two.addBox(4.0F, -11.0F, -3.5F, 1, 6, 7);
        two.setRotationPoint(0.0F, 0.0F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 32);
        three.addBox(-3.5F, -11.0F, 4.0F, 7, 6, 1);
        three.setRotationPoint(0.0F, 0.0F, 0.0F);
        three.setTextureSize(64, 64);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 0, 39);
        four.addBox(-5.0F, -11.0F, -3.5F, 1, 6, 7);
        four.setRotationPoint(0.0F, 0.0F, 0.0F);
        four.setTextureSize(64, 64);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 46, 32);
        five.addBox(-1.0F, -6.1F, -5.5F, 2, 1, 1);
        five.setRotationPoint(0.0F, 0.0F, 0.0F);
        five.setTextureSize(64, 64);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 0, 32);
        six.addBox(-3.5F, -11.0F, -5.0F, 7, 6, 1);
        six.setRotationPoint(0.0F, 0.0F, 0.0F);
        six.setTextureSize(64, 64);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 46, 32);
        seven.addBox(-1.5F, -8.6F, -5.5F, 3, 3, 1);
        seven.setRotationPoint(0.0F, 0.0F, 0.0F);
        seven.setTextureSize(64, 64);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 46, 32);
        eight.addBox(1.0F, -8.1F, -5.5F, 1, 2, 1);
        eight.setRotationPoint(0.0F, 0.0F, 0.0F);
        eight.setTextureSize(64, 64);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 46, 32);
        nine.addBox(-1.0F, -9.1F, -5.5F, 2, 1, 1);
        nine.setRotationPoint(0.0F, 0.0F, 0.0F);
        nine.setTextureSize(64, 64);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 46, 32);
        ten.addBox(-2.0F, -8.1F, -5.5F, 1, 2, 1);
        ten.setRotationPoint(0.0F, 0.0F, 0.0F);
        ten.setTextureSize(64, 64);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
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
        six.render(f5);
        seven.render(f5);
        eight.render(f5);
        nine.render(f5);
        ten.render(f5);
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
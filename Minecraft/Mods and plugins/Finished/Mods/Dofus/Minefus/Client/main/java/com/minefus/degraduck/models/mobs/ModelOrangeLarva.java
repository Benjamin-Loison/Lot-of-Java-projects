package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOrangeLarva extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten;

    public ModelOrangeLarva()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 20, 0);
        one.addBox(-2.0F, -2.0F, -3.0F, 4, 4, 3);
        one.setRotationPoint(0.0F, 22.0F, -3.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 0);
        two.addBox(-2.5F, -3.0F, 0.0F, 5, 5, 3);
        two.setRotationPoint(0.0F, 22.03333F, -3.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 8);
        three.addBox(-3.0F, -3.5F, 3.0F, 6, 6, 3);
        three.setRotationPoint(0.0F, 22.0F, -3.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 0, 18);
        four.addBox(-2.5F, -3.0F, 6.0F, 5, 5, 3);
        four.setRotationPoint(0.0F, 22.0F, -3.0F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 17, 19);
        five.addBox(-2.0F, -2.0F, 9.0F, 4, 4, 1);
        five.setRotationPoint(0.0F, 22.0F, -3.0F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 20, 7);
        six.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        six.setRotationPoint(-1.5F, 21.5F, -6.0F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 20, 12);
        seven.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2);
        seven.setRotationPoint(1.5F, 21.5F, -6.0F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 30, 7);
        eight.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        eight.setRotationPoint(-2.1F, 23.0F, -6.5F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 30, 7);
        nine.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        nine.setRotationPoint(1.2F, 23.0F, -6.5F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 30, 7);
        ten.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        ten.setRotationPoint(-0.5F, 23.0F, -6.5F);
        ten.setTextureSize(64, 32);
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
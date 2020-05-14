package com.minefus.degraduck.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBouftouHammer extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, twelve;

    public ModelBouftouHammer()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 45, 10);
        one.addBox(-2.5F, -12.0F, -2.0F, 5, 3, 4);
        one.setRotationPoint(0.0F, 20.0F, 0.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 16, 0);
        two.addBox(-1.0F, -13.0F, -1.0F, 2, 17, 2);
        two.setRotationPoint(0.0F, 20.0F, 0.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 0);
        three.addBox(-1.5F, -3.5F, -1.5F, 3, 7, 3);
        three.setRotationPoint(0.0F, 20.0F, 0.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 28, 9);
        four.addBox(-2.5F, -14.0F, 2.0F, 5, 5, 3);
        four.setRotationPoint(0.0F, 20.0F, 0.0F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 28, 0);
        five.addBox(-2.5F, -14.0F, -5.0F, 5, 5, 3);
        five.setRotationPoint(0.0F, 20.0F, 0.0F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 49, 0);
        six.addBox(-3.0F, -14.5F, -6.0F, 6, 6, 1);
        six.setRotationPoint(0.0F, 20.0F, 0.0F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 49, 0);
        seven.addBox(-3.0F, -14.5F, 5.0F, 6, 6, 1);
        seven.setRotationPoint(0.0F, 20.0F, 0.0F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 57, 18);
        eight.addBox(-1.0F, -16.0F, -4.0F, 2, 2, 1);
        eight.setRotationPoint(0.0F, 20.0F, 0.0F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 50, 18);
        nine.addBox(-1.0F, -16.0F, -3.0F, 2, 1, 1);
        nine.setRotationPoint(0.0F, 20.0F, 0.0F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 57, 18);
        ten.addBox(-1.0F, -16.0F, 3.0F, 2, 2, 1);
        ten.setRotationPoint(0.0F, 20.0F, 0.0F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 50, 18);
        twelve.addBox(-1.0F, -16.0F, 2.0F, 2, 1, 1);
        twelve.setRotationPoint(0.0F, 20.0F, 0.0F);
        twelve.setTextureSize(64, 32);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
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
        twelve.render(f5);
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
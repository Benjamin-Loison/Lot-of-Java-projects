package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMoskito extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve;

    public ModelMoskito()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 0, 17);
        one.addBox(-2.0F, -2.0F, -4.0F, 4, 4, 4);
        one.setRotationPoint(0.0F, 20.5F, -2.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 26);
        two.addBox(-2.5F, -1.5F, -4.5F, 5, 2, 1);
        two.setRotationPoint(0.0F, 20.5F, -2.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 15, 27);
        three.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4);
        three.setRotationPoint(0.0F, 21.0F, -6.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.2230717F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 0, 3);
        four.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 10);
        four.setRotationPoint(0.0F, 20.5F, -2.0F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.1396263F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 0, 0);
        five.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
        five.setRotationPoint(2.0F, 23.0F, -2.5F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.1745329F, 0.0F);
        six = new ModelRenderer(this, 0, 0);
        six.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
        six.setRotationPoint(2.0F, 23.0F, 1.0F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, -0.1745329F, 0.0F);
        seven = new ModelRenderer(this, 0, 0);
        seven.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
        seven.setRotationPoint(-2.0F, 23.0F, -2.5F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, -0.1745329F, 0.0F);
        eight = new ModelRenderer(this, 0, 0);
        eight.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1);
        eight.setRotationPoint(-2.0F, 23.0F, 1.0F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.1745329F, 0.0F);
        nine = new ModelRenderer(this, 0, 29);
        nine.addBox(-0.5F, 0.0F, -1.0F, 5, 1, 2);
        nine.setRotationPoint(2.0F, 19.0F, -3.5F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.2617994F, 0.0F, -0.3490659F);
        ten = new ModelRenderer(this, 0, 29);
        ten.addBox(-0.5F, 0.0F, -1.0F, 5, 1, 2);
        ten.setRotationPoint(2.0F, 19.5F, -3.5F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.3490659F, 0.0F, 0.1745329F);
        eleven = new ModelRenderer(this, 0, 29);
        eleven.addBox(-4.5F, 0.0F, -1.0F, 5, 1, 2);
        eleven.setRotationPoint(-2.0F, 19.0F, -3.5F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.2617994F, 0.0F, 0.3490659F);
        twelve = new ModelRenderer(this, 0, 29);
        twelve.addBox(-4.5F, 0.0F, -1.0F, 5, 1, 2);
        twelve.setRotationPoint(-2.0F, 19.5F, -3.5F);
        twelve.setTextureSize(64, 32);
        twelve.mirror = true;
        setRotation(twelve, 0.3490659F, 0.0F, -0.1745329F);
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
        eleven.render(f5);
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
        nine.rotateAngleZ = MathHelper.cos(f * 3.5F + 3.1415927F) * 2.2F * f1 * 0.6F;
        ten.rotateAngleZ = MathHelper.cos(f * 3.5F + 3.1415927F) * -2.2F * f1 * 0.6F;
        eleven.rotateAngleZ = MathHelper.cos(f * 3.5F + 3.1415927F) * 2.2F * f1 * 0.6F;
        twelve.rotateAngleZ = MathHelper.cos(f * 3.5F + 3.1415927F) * -2.2F * f1 * 0.6F;
        five.offsetZ = MathHelper.cos(f * 1.0F + 3.1415927F) * 0.2F * f1 * 0.6F;
        six.offsetZ = MathHelper.cos(f * 0.8F + 3.1415927F) * -0.1F * f1 * 0.5F;
        seven.offsetZ = MathHelper.cos(f * 0.8F + 3.1415927F) * -0.1F * f1 * 0.5F;
        eight.offsetZ = MathHelper.cos(f * 1.0F + 3.1415927F) * 0.2F * f1 * 0.6F;
    }
}
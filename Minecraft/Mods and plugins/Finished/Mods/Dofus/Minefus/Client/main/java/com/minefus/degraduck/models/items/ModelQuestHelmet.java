package com.minefus.degraduck.models.items;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQuestHelmet extends ModelBiped
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine;

    public ModelQuestHelmet(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        one = new ModelRenderer(this, 0, 32);
        one.addBox(-1.0F, -12.0F, 2.0F, 2, 2, 2);
        one.setRotationPoint(0.0F, 0.0F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 32);
        two.addBox(-1.0F, -20.0F, 2.0F, 2, 7, 2);
        two.setRotationPoint(0.0F, 0.0F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 8, 32);
        three.addBox(3.5F, -8.5F, -4.0F, 1, 2, 8);
        three.setRotationPoint(0.0F, 0.0F, 0.0F);
        three.setTextureSize(64, 64);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 8, 32);
        four.addBox(-4.5F, -8.5F, -4.0F, 1, 2, 8);
        four.setRotationPoint(0.0F, 0.0F, 0.0F);
        four.setTextureSize(64, 64);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 8, 32);
        five.addBox(-4.0F, -8.5F, 3.5F, 8, 2, 1);
        five.setRotationPoint(0.0F, 0.0F, 0.0F);
        five.setTextureSize(64, 64);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 8, 32);
        six.addBox(-4.0F, -8.5F, -4.5F, 8, 2, 1);
        six.setRotationPoint(0.0F, 0.0F, 0.0F);
        six.setTextureSize(64, 64);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 0, 42);
        seven.addBox(-0.5F, -9.5F, 4.0F, 1, 2, 1);
        seven.setRotationPoint(0.0F, 0.0F, 0.0F);
        seven.setTextureSize(64, 64);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 0, 42);
        eight.addBox(-0.5F, -13.5F, 3.5F, 1, 2, 1);
        eight.setRotationPoint(0.0F, 0.0F, 0.0F);
        eight.setTextureSize(64, 64);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 0, 42);
        nine.addBox(-0.5F, -11.0F, 3.5F, 1, 2, 1);
        nine.setRotationPoint(0.0F, 0.0F, 0.0F);
        nine.setTextureSize(64, 64);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
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
        GL11.glPushMatrix();
        GL11.glEnable(2977);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(0.75F, 0.75F, 0.75F, 0.75F);
        seven.render(f5);
        eight.render(f5);
        nine.render(f5);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
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
        one.rotateAngleY = (f3 / 57.295776F);
        one.rotateAngleX = (f4 / 57.295776F);
        two.rotateAngleY = (f3 / 57.295776F);
        two.rotateAngleX = (f4 / 57.295776F);
        three.rotateAngleY = (f3 / 57.295776F);
        three.rotateAngleX = (f4 / 57.295776F);
        four.rotateAngleY = (f3 / 57.295776F);
        four.rotateAngleX = (f4 / 57.295776F);
        five.rotateAngleY = (f3 / 57.295776F);
        five.rotateAngleX = (f4 / 57.295776F);
        six.rotateAngleY = (f3 / 57.295776F);
        six.rotateAngleX = (f4 / 57.295776F);
        seven.rotateAngleY = (f3 / 57.295776F);
        seven.rotateAngleX = (f4 / 57.295776F);
        eight.rotateAngleY = (f3 / 57.295776F);
        eight.rotateAngleX = (f4 / 57.295776F);
        nine.rotateAngleY = (f3 / 57.295776F);
        nine.rotateAngleX = (f4 / 57.295776F);
    }
}
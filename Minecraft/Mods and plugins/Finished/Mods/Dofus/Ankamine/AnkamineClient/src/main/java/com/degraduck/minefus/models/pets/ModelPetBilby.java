package com.degraduck.minefus.models.pets;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPetBilby extends ModelBiped
{
    ModelRenderer one, two;

    public ModelPetBilby(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        one = new ModelRenderer(this, 0, 32);
        one.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
        one.setRotationPoint(10.0F, 20.5F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 21, 32);
        two.addBox(-0.5F, 0.5F, -3.5F, 4, 1, 4);
        two.setRotationPoint(10.0F, 20.5F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0872665F, 0.7853982F, 0.0872665F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glEnable(2977);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(0.75F, 0.75F, 0.75F, 0.85F);
        one.render(f5);
        two.render(f5);
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
        one.offsetY = (MathHelper.cos(f * 1.0F + 3.1415927F) * -0.2F * f1 * 0.6F);
        one.offsetZ = (MathHelper.cos(f * 1.0F + 3.1415927F) * -0.2F * f1 * 0.6F);
        two.offsetY = (MathHelper.cos(f * 1.0F + 3.1415927F) * -0.2F * f1 * 0.6F);
        two.offsetZ = (MathHelper.cos(f * 1.0F + 3.1415927F) * -0.2F * f1 * 0.6F);
    }
}
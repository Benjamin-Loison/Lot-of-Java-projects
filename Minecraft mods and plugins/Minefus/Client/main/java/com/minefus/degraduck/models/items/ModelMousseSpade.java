package com.minefus.degraduck.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMousseSpade extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, twelve, eleven, thirteen;

    public ModelMousseSpade()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 0, 12);
        one.addBox(-1.0F, -12.0F, -1.0F, 2, 16, 2);
        one.setRotationPoint(0.0F, 20.0F, 0.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 0);
        two.addBox(-1.5F, -13.0F, -1.5F, 3, 1, 3);
        two.setRotationPoint(0.0F, 20.0F, 0.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 0);
        three.addBox(-2.5F, -14.0F, -1.5F, 5, 1, 3);
        three.setRotationPoint(0.0F, 20.0F, 0.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 0, 0);
        four.addBox(-3.5F, -16.0F, -1.5F, 7, 2, 3);
        four.setRotationPoint(0.0F, 20.0F, 0.0F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 0, 0);
        five.addBox(-4.5F, -17.0F, -1.5F, 9, 1, 3);
        five.setRotationPoint(0.0F, 20.0F, 0.0F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 0, 0);
        six.addBox(-3.5F, -27.0F, -1.5F, 7, 2, 3);
        six.setRotationPoint(0.0F, 20.0F, 0.0F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 0, 0);
        seven.addBox(-4.5F, -26.0F, -1.5F, 1, 9, 3);
        seven.setRotationPoint(0.0F, 20.0F, 0.0F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 0, 0);
        eight.addBox(3.5F, -26.0F, -1.5F, 1, 9, 3);
        eight.setRotationPoint(0.0F, 20.0F, 0.0F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 0, 0);
        nine.addBox(-1.0F, -25.0F, -1.5F, 2, 8, 3);
        nine.setRotationPoint(0.0F, 20.0F, 0.0F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 0, 0);
        ten.addBox(-3.7F, -20.0F, -1.5F, 3, 1, 3);
        ten.setRotationPoint(0.0F, 20.0F, 0.0F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 0, 0);
        twelve.addBox(0.8F, -23.0F, -1.5F, 3, 1, 3);
        twelve.setRotationPoint(0.0F, 20.0F, 0.0F);
        twelve.setTextureSize(64, 32);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 0, 0);
        eleven.addBox(-3.7F, -23.0F, -1.5F, 3, 1, 3);
        eleven.setRotationPoint(0.0F, 20.0F, 0.0F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        thirteen = new ModelRenderer(this, 0, 0);
        thirteen.addBox(0.8F, -20.0F, -1.5F, 3, 1, 3);
        thirteen.setRotationPoint(0.0F, 20.0F, 0.0F);
        thirteen.setTextureSize(64, 32);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 0.0F, 0.0F);
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
        eleven.render(f5);
        thirteen.render(f5);
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
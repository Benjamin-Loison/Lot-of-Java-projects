package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelArakne extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen;

    public ModelArakne()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 0, 0);
        one.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
        one.setRotationPoint(0.0F, 17.0F, 0.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 14);
        two.addBox(0.0F, 0.0F, -1.0F, 3, 2, 2);
        two.setRotationPoint(2.0F, 22.0F, -2.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.4089647F, 0.0F);
        three = new ModelRenderer(this, 0, 14);
        three.addBox(0.0F, 0.0F, -1.0F, 3, 2, 2);
        three.setRotationPoint(2.0F, 22.0F, 2.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, -0.5948578F, 0.0F);
        four = new ModelRenderer(this, 0, 18);
        four.addBox(-3.0F, 0.0F, -1.0F, 3, 2, 2);
        four.setRotationPoint(-2.0F, 22.0F, 2.0F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.3346075F, 0.0F);
        five = new ModelRenderer(this, 0, 18);
        five.addBox(-3.0F, 0.0F, -1.0F, 3, 2, 2);
        five.setRotationPoint(-2.0F, 22.0F, -2.0F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, -0.5402259F, 0.0F);
        six = new ModelRenderer(this, 10, 14);
        six.addBox(-2.5F, -2.5F, -5.0F, 5, 3, 1);
        six.setRotationPoint(0.0F, 17.0F, 0.0F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 22, 14);
        seven.addBox(-2.5F, 1.0F, -4.5F, 1, 2, 1);
        seven.setRotationPoint(0.0F, 17.0F, 0.0F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.1047198F);
        eight = new ModelRenderer(this, 22, 14);
        eight.addBox(1.5F, 1.0F, -4.5F, 1, 2, 1);
        eight.setRotationPoint(0.0F, 17.0F, 0.0F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, -0.1047198F);
        nine = new ModelRenderer(this, 22, 14);
        nine.addBox(-0.5F, 1.0F, -4.5F, 1, 2, 1);
        nine.setRotationPoint(0.0F, 17.0F, 0.0F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 39, 22);
        ten.addBox(-3.0F, -4.0F, -3.0F, 6, 1, 6);
        ten.setRotationPoint(0.0F, 17.0F, 0.0F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 49, 0);
        eleven.addBox(-3.0F, -3.0F, 3.0F, 6, 6, 1);
        eleven.setRotationPoint(0.0F, 17.0F, 0.0F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 34, 0);
        twelve.addBox(3.0F, -3.0F, -3.0F, 1, 6, 6);
        twelve.setRotationPoint(0.0F, 17.0F, 0.0F);
        twelve.setTextureSize(64, 32);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
        thirteen = new ModelRenderer(this, 34, 0);
        thirteen.addBox(-4.0F, -3.0F, -3.0F, 1, 6, 6);
        thirteen.setRotationPoint(0.0F, 17.0F, 0.0F);
        thirteen.setTextureSize(64, 32);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 0.0F, 0.0F);
        fourteen = new ModelRenderer(this, 39, 13);
        fourteen.addBox(-3.0F, 3.0F, -3.0F, 6, 1, 6);
        fourteen.setRotationPoint(0.0F, 17.0F, 0.0F);
        fourteen.setTextureSize(64, 32);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 0.0F, 0.0F);
        fiveteen = new ModelRenderer(this, 49, 0);
        fiveteen.addBox(-3.0F, -3.0F, -4.0F, 6, 6, 1);
        fiveteen.setRotationPoint(0.0F, 17.0F, 0.0F);
        fiveteen.setTextureSize(64, 32);
        fiveteen.mirror = true;
        setRotation(fiveteen, 0.0F, 0.0F, 0.0F);
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
        thirteen.render(f5);
        fourteen.render(f5);
        fiveteen.render(f5);
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
        two.offsetZ = MathHelper.cos(f * 1.9F + 3.1415927F) * 0.5F * f1 * 0.4F;
        three.offsetZ = MathHelper.cos(f * 1.9F + 3.1415927F) * -0.5F * f1 * 0.4F;
        four.offsetZ = MathHelper.cos(f * 1.9F + 3.1415927F) * -0.5F * f1 * 0.4F;
        five.offsetZ = MathHelper.cos(f * 1.9F + 3.1415927F) * 0.5F * f1 * 0.4F;
    }
}
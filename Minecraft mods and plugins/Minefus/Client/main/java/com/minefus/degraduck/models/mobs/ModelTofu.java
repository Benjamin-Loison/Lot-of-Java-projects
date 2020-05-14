package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTofu extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, fourteen, fiveteen;

    public ModelTofu()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 0, 18);
        one.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
        one.setRotationPoint(0.0F, 18.5F, 0.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 29, 24);
        two.addBox(-1.5F, 0.0F, -5.0F, 3, 2, 5);
        two.setRotationPoint(-2.0F, 22.0F, 0.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.3323355F, 0.0F);
        three = new ModelRenderer(this, 29, 24);
        three.addBox(-1.5F, 0.0F, -5.0F, 3, 2, 5);
        three.setRotationPoint(2.0F, 22.0F, 0.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, -0.4658687F, 0.0F);
        four = new ModelRenderer(this, 0, 12);
        four.addBox(-2.0F, -0.5F, -2.0F, 4, 1, 4);
        four.setRotationPoint(0.0F, 20.5F, -3.2F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.2094395F, 0.7853982F, 0.2094395F);
        five = new ModelRenderer(this, 53, 22);
        five.addBox(0.0F, -0.5F, -2.0F, 1, 4, 4);
        five.setRotationPoint(4.0F, 17.5F, 0.0F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 53, 13);
        six.addBox(-1.0F, -0.5F, -2.0F, 1, 4, 4);
        six.setRotationPoint(-4.0F, 17.5F, 0.0F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 18, 0);
        seven.addBox(-0.5F, -5.5F, -0.5F, 1, 4, 1);
        seven.setRotationPoint(0.0F, 16.0F, 0.0F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.2974289F, -0.669215F, 0.0F);
        eight = new ModelRenderer(this, 18, 0);
        eight.addBox(-0.5F, -7.0F, -0.5F, 1, 6, 1);
        eight.setRotationPoint(0.0F, 16.0F, 0.0F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, -0.2230717F);
        nine = new ModelRenderer(this, 18, 0);
        nine.addBox(-0.5F, -6.5F, -0.5F, 1, 5, 1);
        nine.setRotationPoint(0.0F, 16.0F, 0.0F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, -0.9294653F, 0.2974289F);
        ten = new ModelRenderer(this, 49, 0);
        ten.addBox(3.0F, -4.0F, -3.0F, 1, 6, 6);
        ten.setRotationPoint(0.0F, 19.5F, 0.0F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 49, 0);
        eleven.addBox(-4.0F, -4.0F, -3.0F, 1, 6, 6);
        eleven.setRotationPoint(0.0F, 19.5F, 0.0F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 33, 0);
        twelve.addBox(-3.0F, -4.0F, 3.0F, 6, 6, 1);
        twelve.setRotationPoint(0.0F, 19.5F, 0.0F);
        twelve.setTextureSize(64, 32);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
        fourteen = new ModelRenderer(this, 33, 0);
        fourteen.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 1);
        fourteen.setRotationPoint(0.0F, 19.5F, 0.0F);
        fourteen.setTextureSize(64, 32);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 0.0F, 0.0F);
        fiveteen = new ModelRenderer(this, 23, 8);
        fiveteen.addBox(-3.0F, -5.0F, -3.0F, 6, 1, 6);
        fiveteen.setRotationPoint(0.0F, 19.5F, 0.0F);
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
        two.offsetZ = MathHelper.cos(f * 1.5F + 3.1415927F) * 0.25F * f1 * 0.4F;
        three.offsetZ = MathHelper.cos(f * 1.5F + 3.1415927F) * -0.25F * f1 * 0.4F;
        five.rotateAngleZ = MathHelper.cos(f * 1.5F + 3.1415927F) * 0.5F * f1 * 0.6F;
        six.rotateAngleZ = MathHelper.cos(f * 1.5F + 3.1415927F) * -0.5F * f1 * 0.6F;
    }
}
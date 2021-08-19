package com.minefus.degraduck.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBouftouChestplate extends ModelBiped
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, twelve, eleven, thirteen, fourteen, fiveteen, sixteen;

    public ModelBouftouChestplate(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        one = new ModelRenderer(this, 0, 50);
        one.addBox(-6.5F, 0.5F, -4.0F, 13, 12, 2);
        one.setRotationPoint(0.0F, 0.0F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 3.141593F, 0.0F);
        two = new ModelRenderer(this, 54, 58);
        two.addBox(1.0F, 1.0F, -4.5F, 4, 5, 1);
        two.setRotationPoint(0.0F, 0.0F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0F, 3.141593F, 0.0F);
        three = new ModelRenderer(this, 54, 58);
        three.addBox(-5.0F, 1.0F, -4.5F, 4, 5, 1);
        three.setRotationPoint(0.0F, 0.0F, 0.0F);
        three.setTextureSize(64, 64);
        three.mirror = true;
        setRotation(three, 0.0F, 3.141593F, 0.0F);
        four = new ModelRenderer(this, 40, 50);
        four.addBox(-5.5F, 6.0F, -5.0F, 11, 7, 1);
        four.setRotationPoint(0.0F, 0.0F, 0.0F);
        four.setTextureSize(64, 64);
        four.mirror = true;
        setRotation(four, 0.0F, 3.141593F, 0.0F);
        five = new ModelRenderer(this, 44, 44);
        five.addBox(-4.5F, 9.0F, -5.5F, 9, 5, 1);
        five.setRotationPoint(0.0F, 0.0F, 0.0F);
        five.setTextureSize(64, 64);
        five.mirror = true;
        setRotation(five, 0.0F, 3.141593F, 0.0F);
        six = new ModelRenderer(this, 27, 58);
        six.addBox(-6.5F, 18.0F, -6.0F, 13, 4, 2);
        six.setRotationPoint(0.0F, 0.0F, 0.0F);
        six.setTextureSize(64, 64);
        six.mirror = true;
        setRotation(six, 0.0F, 3.141593F, 0.0F);
        seven = new ModelRenderer(this, 3, 43);
        seven.addBox(-6.0F, 18.5F, -7.0F, 12, 3, 1);
        seven.setRotationPoint(0.0F, 0.0F, 0.0F);
        seven.setTextureSize(64, 64);
        seven.mirror = true;
        setRotation(seven, 0.0F, 3.141593F, 0.0F);
        eight = new ModelRenderer(this, 0, 43);
        eight.addBox(-7.0F, 12.5F, -4.0F, 14, 10, 2);
        eight.setRotationPoint(0.0F, 0.0F, 0.0F);
        eight.setTextureSize(64, 64);
        eight.mirror = true;
        setRotation(eight, 0.0F, 3.141593F, 0.0F);
        nine = new ModelRenderer(this, 38, 38);
        nine.addBox(-6.0F, 13.0F, -5.0F, 12, 5, 1);
        nine.setRotationPoint(0.0F, 0.0F, 0.0F);
        nine.setTextureSize(64, 64);
        nine.mirror = true;
        setRotation(nine, 0.0F, 3.141593F, 0.0F);
        ten = new ModelRenderer(this, 42, 39);
        ten.addBox(-5.0F, 14.0F, -6.0F, 10, 4, 1);
        ten.setRotationPoint(0.0F, 0.0F, 0.0F);
        ten.setTextureSize(64, 64);
        ten.mirror = true;
        setRotation(ten, 0.0F, 3.141593F, 0.0F);
        twelve = new ModelRenderer(this, 12, 60);
        twelve.addBox(-5.0F, 19.3F, -7.5F, 10, 2, 1);
        twelve.setRotationPoint(0.0F, 0.0F, 0.0F);
        twelve.setTextureSize(64, 64);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 3.141593F, 0.0F);
        eleven = new ModelRenderer(this, 28, 50);
        eleven.addBox(-1.5F, 16.0F, -9.0F, 3, 3, 3);
        eleven.setRotationPoint(0.0F, 0.0F, 0.0F);
        eleven.setTextureSize(64, 64);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 3.141593F, 0.0F);
        thirteen = new ModelRenderer(this, 12, 39);
        thirteen.addBox(-8.0F, 20.1F, -6.5F, 3, 2, 2);
        thirteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        thirteen.setTextureSize(64, 64);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 3.141593F, 0.0F);
        fourteen = new ModelRenderer(this, 0, 39);
        fourteen.addBox(5.0F, 20.1F, -6.5F, 3, 2, 2);
        fourteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        fourteen.setTextureSize(64, 64);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 3.141593F, 0.0F);
        fiveteen = new ModelRenderer(this, 0, 0);
        fiveteen.addBox(3.0F, -0.5F, -3.0F, 1, 1, 1);
        fiveteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        fiveteen.setTextureSize(64, 64);
        fiveteen.mirror = true;
        setRotation(fiveteen, 0.0F, 3.141593F, 0.0F);
        sixteen = new ModelRenderer(this, 0, 0);
        sixteen.addBox(-4.0F, -0.5F, -3.0F, 1, 1, 1);
        sixteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        sixteen.setTextureSize(64, 64);
        sixteen.mirror = true;
        setRotation(sixteen, 0.0F, 3.141593F, 0.0F);
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
        fourteen.render(f5);
        fiveteen.render(f5);
        sixteen.render(f5);
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
        one.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        two.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        three.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        four.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        five.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        six.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        seven.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        eight.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        nine.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        ten.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        twelve.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        eleven.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        thirteen.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        fourteen.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        fiveteen.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
        sixteen.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 25.0F - f1 / 2.0F - 0.1F;
    }
}
package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelChiefOfWarBouftou extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen, seventeen, eighteen, nineteen, twenty, twentyone, twentytwo, twentythree, twentyfour, twentyfive, twentysix, twentyseven, twentyeight, twentynine, thirty;

    public ModelChiefOfWarBouftou()
    {
        textureWidth = 64;
        textureHeight = 32;
        one = new ModelRenderer(this, 0, 17);
        one.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 9);
        one.setRotationPoint(0.0F, 19.0F, -4.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 50, 26);
        two.addBox(-3.0F, -2.5F, -2.5F, 6, 5, 1);
        two.setRotationPoint(0.0F, 19.0F, -2.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 38, 20);
        three.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2);
        three.setRotationPoint(1.5F, 22.0F, -1.0F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 38, 20);
        four.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2);
        four.setRotationPoint(1.5F, 22.0F, 2.4F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 38, 20);
        five.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2);
        five.setRotationPoint(-1.5F, 22.0F, -1.0F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 38, 20);
        six.addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2);
        six.setRotationPoint(-1.5F, 22.0F, 2.4F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 13, 9);
        seven.addBox(0.0F, -1.0F, -1.0F, 3, 3, 3);
        seven.setRotationPoint(3.5F, 18.0F, -0.5F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 37, 26);
        eight.addBox(1.0F, -1.5F, -2.0F, 3, 3, 3);
        eight.setRotationPoint(3.5F, 18.0F, -0.5F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 0, 9);
        nine.addBox(-2.0F, -1.0F, -1.0F, 3, 3, 3);
        nine.setRotationPoint(-4.5F, 18.0F, -0.5F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 37, 26);
        ten.addBox(-3.0F, -1.5F, -2.0F, 3, 3, 3);
        ten.setRotationPoint(-4.5F, 18.0F, -0.5F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 35, 0);
        eleven.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 1);
        eleven.setRotationPoint(0.0F, 19.0F, -2.0F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 48, 0);
        eleven.addBox(3.0F, -2.5F, 2.0F, 1, 5, 6);
        eleven.setRotationPoint(0.0F, 19.0F, -4.0F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        thirteen = new ModelRenderer(this, 48, 0);
        thirteen.addBox(-4.0F, -2.5F, 2.0F, 1, 5, 6);
        thirteen.setRotationPoint(0.0F, 19.0F, -4.0F);
        thirteen.setTextureSize(64, 32);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 0.0F, 0.0F);
        fourteen = new ModelRenderer(this, 0, 0);
        fourteen.addBox(-3.0F, -3.5F, 1.5F, 6, 1, 7);
        fourteen.setRotationPoint(0.0F, 19.0F, -4.0F);
        fourteen.setTextureSize(64, 32);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 0.0F, 0.0F);
        fiveteen = new ModelRenderer(this, 50, 26);
        fiveteen.addBox(-3.0F, -2.5F, 4.5F, 6, 5, 1);
        fiveteen.setRotationPoint(0.0F, 19.0F, 0.0F);
        fiveteen.setTextureSize(64, 32);
        fiveteen.mirror = true;
        setRotation(fiveteen, 0.0F, 0.0F, 0.0F);
        sixteen = new ModelRenderer(this, 27, 0);
        sixteen.addBox(-1.0F, -1.0F, 5.0F, 2, 2, 1);
        sixteen.setRotationPoint(0.0F, 18.0F, 5.0F);
        sixteen.setTextureSize(64, 32);
        sixteen.mirror = true;
        setRotation(sixteen, -0.5061455F, 0.0F, 0.0F);
        seventeen = new ModelRenderer(this, 38, 11);
        seventeen.addBox(-1.0F, -1.5F, 3.5F, 2, 3, 2);
        seventeen.setRotationPoint(0.0F, 18.0F, 5.0F);
        seventeen.setTextureSize(64, 32);
        seventeen.mirror = true;
        setRotation(seventeen, -0.5061455F, 0.0F, 0.0F);
        eighteen = new ModelRenderer(this, 34, 6);
        eighteen.addBox(-1.5F, -1.0F, 3.5F, 3, 2, 2);
        eighteen.setRotationPoint(0.0F, 18.0F, 5.0F);
        eighteen.setTextureSize(64, 32);
        eighteen.mirror = true;
        setRotation(eighteen, -0.5061455F, 0.0F, 0.0F);
        nineteen = new ModelRenderer(this, 56, 23);
        nineteen.addBox(-1.5F, 1.0F, -4.0F, 3, 1, 1);
        nineteen.setRotationPoint(0.0F, 19.0F, -2.0F);
        nineteen.setTextureSize(64, 32);
        nineteen.mirror = true;
        setRotation(nineteen, 0.3490659F, 0.0F, 0.0F);
        twenty = new ModelRenderer(this, 59, 0);
        twenty.addBox(-0.5F, 1.3F, -4.8F, 1, 1, 1);
        twenty.setRotationPoint(0.0F, 19.0F, -2.0F);
        twenty.setTextureSize(64, 32);
        twenty.mirror = true;
        setRotation(twenty, 0.3490659F, 0.0F, 0.0F);
        twentyone = new ModelRenderer(this, 53, 12);
        twentyone.addBox(1.5F, -2.0F, -3.0F, 2, 2, 3);
        twentyone.setRotationPoint(3.5F, 18.0F, -0.5F);
        twentyone.setTextureSize(64, 32);
        twentyone.mirror = true;
        setRotation(twentyone, 0.0F, 0.0F, 0.0F);
        twentytwo = new ModelRenderer(this, 26, 10);
        twentytwo.addBox(2.0F, -2.5F, -4.0F, 1, 2, 3);
        twentytwo.setRotationPoint(3.5F, 18.0F, -0.5F);
        twentytwo.setTextureSize(64, 32);
        twentytwo.mirror = true;
        setRotation(twentytwo, 0.0F, 0.0F, 0.0F);
        twentythree = new ModelRenderer(this, 20, 0);
        twentythree.addBox(2.0F, -3.5F, -4.0F, 1, 1, 1);
        twentythree.setRotationPoint(3.5F, 18.0F, -0.5F);
        twentythree.setTextureSize(64, 32);
        twentythree.mirror = true;
        setRotation(twentythree, 0.0F, 0.0F, 0.0F);
        twentyfour = new ModelRenderer(this, 53, 12);
        twentyfour.addBox(-2.5F, -2.0F, -3.0F, 2, 2, 3);
        twentyfour.setRotationPoint(-4.5F, 18.0F, -0.5F);
        twentyfour.setTextureSize(64, 32);
        twentyfour.mirror = true;
        setRotation(twentyfour, 0.0F, 0.0F, 0.0F);
        twentyfive = new ModelRenderer(this, 26, 10);
        twentyfive.addBox(-2.0F, -2.5F, -4.0F, 1, 2, 3);
        twentyfive.setRotationPoint(-4.5F, 18.0F, -0.5F);
        twentyfive.setTextureSize(64, 32);
        twentyfive.mirror = true;
        setRotation(twentyfive, 0.0F, 0.0F, 0.0F);
        twentysix = new ModelRenderer(this, 20, 0);
        twentysix.addBox(-2.0F, -3.5F, -4.0F, 1, 1, 1);
        twentysix.setRotationPoint(-4.5F, 18.0F, -0.5F);
        twentysix.setTextureSize(64, 32);
        twentysix.mirror = true;
        setRotation(twentysix, 0.0F, 0.0F, 0.0F);
        twentyseven = new ModelRenderer(this, 48, 0);
        twentyseven.addBox(-0.5F, -3.8F, 0.5F, 1, 2, 1);
        twentyseven.setRotationPoint(0.0F, 19.0F, -4.0F);
        twentyseven.setTextureSize(64, 32);
        twentyseven.mirror = true;
        setRotation(twentyseven, 0.2094395F, 0.0F, 0.0F);
        twentyeight = new ModelRenderer(this, 48, 0);
        twentyeight.addBox(-0.5F, -4.3F, 1.5F, 1, 2, 1);
        twentyeight.setRotationPoint(0.0F, 19.0F, -4.0F);
        twentyeight.setTextureSize(64, 32);
        twentyeight.mirror = true;
        setRotation(twentyeight, 0.122173F, 0.0F, 0.0F);
        twentynine = new ModelRenderer(this, 49, 22);
        twentynine.addBox(-1.0F, -1.0F, 3.0F, 2, 2, 1);
        twentynine.setRotationPoint(0.0F, 18.0F, 5.0F);
        twentynine.setTextureSize(64, 32);
        twentynine.mirror = true;
        setRotation(twentynine, -0.5061455F, 0.0F, 0.0F);
        thirty = new ModelRenderer(this, 26, 18);
        thirty.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 4);
        thirty.setRotationPoint(0.0F, 18.0F, 5.0F);
        thirty.setTextureSize(64, 32);
        thirty.mirror = true;
        setRotation(thirty, -0.5061455F, 0.0F, 0.0F);
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
        eleven.render(f5);
        thirteen.render(f5);
        fourteen.render(f5);
        fiveteen.render(f5);
        sixteen.render(f5);
        seventeen.render(f5);
        eighteen.render(f5);
        nineteen.render(f5);
        twenty.render(f5);
        twentyone.render(f5);
        twentytwo.render(f5);
        twentythree.render(f5);
        twentyfour.render(f5);
        twentyfive.render(f5);
        twentysix.render(f5);
        twentyseven.render(f5);
        twentyeight.render(f5);
        twentynine.render(f5);
        thirty.render(f5);
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
        three.rotateAngleX = MathHelper.cos(f * 1.2F + 3.1415927F) * 2.0F * f1 * 0.6F;
        four.rotateAngleX = MathHelper.cos(f * 1.2F + 3.1415927F) * -2.0F * f1 * 0.6F;
        five.rotateAngleX = MathHelper.cos(f * 1.2F + 3.1415927F) * 2.0F * f1 * 0.6F;
        six.rotateAngleX = MathHelper.cos(f * 1.2F + 3.1415927F) * -2.0F * f1 * 0.6F;
        sixteen.rotateAngleY = MathHelper.cos(f * 1.0F + 3.1415927F) * 1.0F * f1 * 0.6F;
        seventeen.rotateAngleY = MathHelper.cos(f * 1.0F + 3.1415927F) * 1.0F * f1 * 0.6F;
        eighteen.rotateAngleY = MathHelper.cos(f * 1.0F + 3.1415927F) * 1.0F * f1 * 0.6F;
        twentynine.rotateAngleY = MathHelper.cos(f * 1.0F + 3.1415927F) * 1.0F * f1 * 0.6F;
        thirty.rotateAngleY = MathHelper.cos(f * 1.0F + 3.1415927F) * 1.0F * f1 * 0.6F;
    }
}
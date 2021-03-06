package com.minefus.degraduck.models.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBouftouHelmet extends ModelBiped
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, twelve, eleven, thirteen, fourteen, fiveteen, sixteen, seventeen;
    
    public ModelBouftouHelmet(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        one = new ModelRenderer(this, 20, 42);
        one.addBox(-4.5F, -8.0F, -4.5F, 9, 3, 1);
        one.setRotationPoint(0.0F, 0.0F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 0, 32);
        two.addBox(-4.0F, -9.0F, -4.0F, 8, 1, 8);
        two.setRotationPoint(0.0F, 0.0F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 48);
        three.addBox(-5.0F, -8.0F, -4.0F, 1, 5, 8);
        three.setRotationPoint(0.0F, 0.0F, 0.0F);
        three.setTextureSize(64, 64);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 26, 32);
        four.addBox(-5.0F, -3.0F, -2.5F, 1, 2, 4);
        four.setRotationPoint(0.0F, 0.0F, 0.0F);
        four.setTextureSize(64, 64);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 35, 55);
        five.addBox(-4.0F, -8.0F, 4.0F, 8, 5, 1);
        five.setRotationPoint(0.0F, 0.0F, 0.0F);
        five.setTextureSize(64, 64);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 0, 48);
        six.addBox(4.0F, -8.0F, -4.0F, 1, 5, 8);
        six.setRotationPoint(0.0F, 0.0F, 0.0F);
        six.setTextureSize(64, 64);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 26, 32);
        seven.addBox(4.0F, -3.0F, -2.5F, 1, 2, 4);
        seven.setRotationPoint(0.0F, 0.0F, 0.0F);
        seven.setTextureSize(64, 64);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 0, 41);
        eight.addBox(-3.0F, -9.5F, -3.0F, 6, 1, 6);
        eight.setRotationPoint(0.0F, 0.0F, 0.0F);
        eight.setTextureSize(64, 64);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 28, 47);
        nine.addBox(-4.0F, -7.5F, -5.0F, 8, 2, 1);
        nine.setRotationPoint(0.0F, 0.0F, 0.0F);
        nine.setTextureSize(64, 64);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 37, 37);
        ten.addBox(-3.0F, -7.5F, 4.5F, 6, 3, 1);
        ten.setRotationPoint(0.0F, 0.0F, 0.0F);
        ten.setTextureSize(64, 64);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 18, 50);
        twelve.addBox(-5.5F, -7.5F, -3.5F, 1, 4, 7);
        twelve.setRotationPoint(0.0F, 0.0F, 0.0F);
        twelve.setTextureSize(64, 64);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 18, 50);
        eleven.addBox(4.5F, -7.5F, -3.5F, 1, 4, 7);
        eleven.setRotationPoint(0.0F, 0.0F, 0.0F);
        eleven.setTextureSize(64, 64);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        thirteen = new ModelRenderer(this, 14, 51);
        thirteen.addBox(-1.0F, -5.5F, -4.7F, 2, 2, 1);
        thirteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        thirteen.setTextureSize(64, 64);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 0.0F, 0.0F);
        fourteen = new ModelRenderer(this, 47, 47);
        fourteen.addBox(-8.0F, -8.5F, -0.5F, 4, 2, 2);
        fourteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        fourteen.setTextureSize(64, 64);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 0.0F, 0.0F);
        fiveteen = new ModelRenderer(this, 55, 52);
        fiveteen.addBox(-8.5F, -9.3F, 0.0F, 1, 2, 1);
        fiveteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        fiveteen.setTextureSize(64, 64);
        fiveteen.mirror = true;
        setRotation(fiveteen, 0.0F, 0.0F, 0.0F);
        sixteen = new ModelRenderer(this, 42, 42);
        sixteen.addBox(4.0F, -8.5F, -0.5F, 4, 2, 2);
        sixteen.setRotationPoint(0.0F, 0.0F, 0.0F);
        sixteen.setTextureSize(64, 64);
        sixteen.mirror = true;
        setRotation(sixteen, 0.0F, 0.0F, 0.0F);
        seventeen = new ModelRenderer(this, 55, 42);
        seventeen.addBox(7.5F, -9.3F, 0.0F, 1, 2, 1);
        seventeen.setRotationPoint(0.0F, 0.0F, 0.0F);
        seventeen.setTextureSize(64, 64);
        seventeen.mirror = true;
        setRotation(seventeen, 0.0F, 0.0F, 0.0F);
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
        seventeen.render(f5);
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

        one.rotateAngleY = f3 / 57.295776F;
        one.rotateAngleX = f4 / 57.295776F;
        two.rotateAngleY = f3 / 57.295776F;
        two.rotateAngleX = f4 / 57.295776F;
        three.rotateAngleY = f3 / 57.295776F;
        three.rotateAngleX = f4 / 57.295776F;
        four.rotateAngleY = f3 / 57.295776F;
        four.rotateAngleX = f4 / 57.295776F;
        five.rotateAngleY = f3 / 57.295776F;
        five.rotateAngleX = f4 / 57.295776F;
        six.rotateAngleY = f3 / 57.295776F;
        six.rotateAngleX = f4 / 57.295776F;
        seven.rotateAngleY = f3 / 57.295776F;
        seven.rotateAngleX = f4 / 57.295776F;
        eight.rotateAngleY = f3 / 57.295776F;
        eight.rotateAngleX = f4 / 57.295776F;
        nine.rotateAngleY = f3 / 57.295776F;
        nine.rotateAngleX = f4 / 57.295776F;
        ten.rotateAngleY = f3 / 57.295776F;
        ten.rotateAngleX = f4 / 57.295776F;
        twelve.rotateAngleY = f3 / 57.295776F;
        twelve.rotateAngleX = f4 / 57.295776F;
        eleven.rotateAngleY = f3 / 57.295776F;
        eleven.rotateAngleX = f4 / 57.295776F;
        thirteen.rotateAngleY = f3 / 57.295776F;
        thirteen.rotateAngleX = f4 / 57.295776F;
        fourteen.rotateAngleY = f3 / 57.295776F;
        fourteen.rotateAngleX = f4 / 57.295776F;
        fiveteen.rotateAngleY = f3 / 57.295776F;
        fiveteen.rotateAngleX = f4 / 57.295776F;
        sixteen.rotateAngleY = f3 / 57.295776F;
        sixteen.rotateAngleX = f4 / 57.295776F;
        seventeen.rotateAngleY = f3 / 57.295776F;
        seventeen.rotateAngleX = f4 / 57.295776F;
    }
}

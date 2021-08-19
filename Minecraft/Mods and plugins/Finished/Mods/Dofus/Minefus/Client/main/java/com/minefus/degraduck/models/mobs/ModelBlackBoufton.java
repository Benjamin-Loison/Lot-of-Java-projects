package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBlackBoufton extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen;

    public ModelBlackBoufton()
    {
        textureWidth = 64;
        textureHeight = 32;
        setTextureOffset("Piece1.tail1", 0, 12);
        setTextureOffset("Piece1.Shape6", 0, 8);
        setTextureOffset("Piece1.Shape8", 13, 0);
        setTextureOffset("Piece1.Shape7", 15, 4);
        one = new ModelRenderer(this, 0, 19);
        one.addBox(-3.0F, -2.5F, 0.0F, 6, 5, 8);
        one.setRotationPoint(0.0F, 20.0F, -4.0F);
        one.setTextureSize(64, 32);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 28, 27);
        two.addBox(-2.5F, -2.0F, -2.5F, 5, 4, 1);
        two.setRotationPoint(0.0F, 20.0F, -2.0F);
        two.setTextureSize(64, 32);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 30, 22);
        three.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
        three.setRotationPoint(1.5F, 22.0F, -1.5F);
        three.setTextureSize(64, 32);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 30, 22);
        four.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
        four.setRotationPoint(1.5F, 22.0F, 1.8F);
        four.setTextureSize(64, 32);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 30, 22);
        five.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
        five.setRotationPoint(-1.5F, 22.0F, -1.5F);
        five.setTextureSize(64, 32);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 30, 22);
        six.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
        six.setRotationPoint(-1.5F, 22.0F, 1.8F);
        six.setTextureSize(64, 32);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 13, 14);
        seven.addBox(0.0F, -1.0F, -1.0F, 2, 2, 2);
        seven.setRotationPoint(3.0F, 18.6F, -1.0F);
        seven.setTextureSize(64, 32);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 22, 15);
        eight.addBox(0.0F, -1.5F, -0.5F, 1, 2, 1);
        eight.setRotationPoint(4.5F, 18.6F, -1.0F);
        eight.setTextureSize(64, 32);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 13, 10);
        nine.addBox(-2.0F, -1.0F, -1.0F, 2, 2, 2);
        nine.setRotationPoint(-3.0F, 18.6F, -1.0F);
        nine.setTextureSize(64, 32);
        nine.mirror = true;
        setRotation(nine, 0.0F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 22, 15);
        ten.addBox(-1.0F, -1.5F, -0.5F, 1, 2, 1);
        ten.setRotationPoint(-4.5F, 18.6F, -1.0F);
        ten.setTextureSize(64, 32);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 29, 17);
        eleven.addBox(-2.0F, -1.5F, -3.0F, 4, 3, 1);
        eleven.setRotationPoint(0.0F, 20.0F, -2.0F);
        eleven.setTextureSize(64, 32);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 42, 3);
        twelve.addBox(-2.5F, -3.0F, 1.5F, 5, 1, 6);
        twelve.setRotationPoint(0.0F, 20.0F, -4.0F);
        twelve.setTextureSize(64, 32);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
        thirteen = new ModelRenderer(this, 25, 0);
        thirteen.addBox(2.5F, -2.0F, 1.5F, 1, 4, 6);
        thirteen.setRotationPoint(0.0F, 20.0F, -4.0F);
        thirteen.setTextureSize(64, 32);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 0.0F, 0.0F);
        fourteen = new ModelRenderer(this, 25, 0);
        fourteen.addBox(-3.5F, -2.0F, 1.5F, 1, 4, 6);
        fourteen.setRotationPoint(0.0F, 20.0F, -4.0F);
        fourteen.setTextureSize(64, 32);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 0.0F, 0.0F);
        fiveteen = new ModelRenderer(this, 0, 0);
        fiveteen.addBox(-2.5F, -2.0F, 7.5F, 5, 4, 1);
        fiveteen.setRotationPoint(0.0F, 20.0F, -4.0F);
        fiveteen.setTextureSize(64, 32);
        fiveteen.mirror = true;
        setRotation(fiveteen, 0.0F, 0.0F, 0.0F);
        sixteen = new ModelRenderer(this, "Piece1");
        sixteen.setRotationPoint(0.0F, 20.0F, 3.5F);
        setRotation(sixteen, 0.0F, 0.0F, 0.0F);
        sixteen.mirror = true;
        sixteen.addBox("tail1", -1.5F, -1.5F, 1.0F, 3, 3, 2);
        sixteen.addBox("Shape6", -1.0F, -1.0F, 2.5F, 2, 2, 1);
        sixteen.addBox("Shape8", -2.0F, -1.0F, 1.5F, 4, 2, 1);
        sixteen.addBox("Shape7", -1.0F, -2.0F, 1.5F, 2, 4, 1);
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
        three.rotateAngleX = MathHelper.cos(f * 1.1F + 3.1415927F) * 2.0F * f1 * 0.6F;
        four.rotateAngleX = MathHelper.cos(f * 1.1F + 3.1415927F) * -2.0F * f1 * 0.6F;
        five.rotateAngleX = MathHelper.cos(f * 1.1F + 3.1415927F) * 2.0F * f1 * 0.6F;
        six.rotateAngleX = MathHelper.cos(f * 1.2F + 3.1415927F) * -2.0F * f1 * 0.6F;
        sixteen.rotateAngleY = MathHelper.cos(f * 0.9F + 3.1415927F) * 1.0F * f1 * 0.6F;
    }
}
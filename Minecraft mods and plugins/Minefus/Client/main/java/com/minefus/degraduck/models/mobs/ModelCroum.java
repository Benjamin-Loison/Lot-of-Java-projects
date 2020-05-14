package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCroum extends ModelBiped
{
    ModelRenderer one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fiveteen, sixteen;

    public ModelCroum(float f)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;
        setTextureOffset("BrasEpee.Shape12", 30, 32);
        setTextureOffset("BrasEpee.Shape16", 36, 32);
        setTextureOffset("BrasEpee.Shape17", 30, 32);
        setTextureOffset("BrasEpee.Shape18", 56, 32);
        setTextureOffset("BrasEpee.Shape19", 56, 32);
        setTextureOffset("BrasEpee.Shape20", 56, 32);
        setTextureOffset("BrasEpee.Shape21", 56, 32);
        setTextureOffset("BrasBouclier.Shape11", 56, 32);
        setTextureOffset("BrasBouclier.Shape14", 56, 32);
        setTextureOffset("BrasBouclier.Shape15", 56, 32);
        setTextureOffset("BrasBouclier.Shape27", 28, 45);
        setTextureOffset("BrasBouclier.Shape29", 36, 57);
        setTextureOffset("BrasBouclier.Shape26", 24, 49);
        setTextureOffset("BrasBouclier.Shape28", 36, 51);
        setTextureOffset("BrasBouclier.Shape25", 36, 44);
        setTextureOffset("BrasBouclier.Shape23", 36, 61);
        setTextureOffset("BrasBouclier.Shape24", 22, 55);
        setTextureOffset("BrasBouclier.Shape13", 56, 32);
        one = new ModelRenderer(this, 14, 40);
        one.addBox(1.5F, -2.5F, -1.5F, 1, 3, 3);
        one.setRotationPoint(12.0F, 19.5F, 0.0F);
        one.setTextureSize(64, 64);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 22, 40);
        two.addBox(-1.5F, -3.5F, -1.5F, 3, 1, 3);
        two.setRotationPoint(12.0F, 19.5F, 0.0F);
        two.setTextureSize(64, 64);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 0, 32);
        three.addBox(-0.5F, -3.5F, -2.5F, 1, 5, 1);
        three.setRotationPoint(12.0F, 19.5F, 0.0F);
        three.setTextureSize(64, 64);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 4, 32);
        four.addBox(-0.5F, -4.0F, -2.0F, 1, 1, 4);
        four.setRotationPoint(12.0F, 19.5F, 0.0F);
        four.setTextureSize(64, 64);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 0, 38);
        five.addBox(-0.5F, -3.5F, 1.5F, 1, 2, 1);
        five.setRotationPoint(12.0F, 19.5F, 0.0F);
        five.setTextureSize(64, 64);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 14, 32);
        six.addBox(-2.0F, -3.0F, -2.0F, 4, 4, 4);
        six.setRotationPoint(12.0F, 19.5F, 0.0F);
        six.setTextureSize(64, 64);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, 14, 40);
        seven.addBox(-2.5F, -2.5F, -1.5F, 1, 3, 3);
        seven.setRotationPoint(12.0F, 19.5F, 0.0F);
        seven.setTextureSize(64, 64);
        seven.mirror = true;
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        eight = new ModelRenderer(this, 0, 41);
        eight.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);
        eight.setRotationPoint(12.0F, 19.5F, 0.0F);
        eight.setTextureSize(64, 64);
        eight.mirror = true;
        setRotation(eight, 0.0F, 0.0F, 0.0F);
        nine = new ModelRenderer(this, 4, 37);
        nine.addBox(-1.0F, -2.5F, 1.5F, 2, 2, 2);
        nine.setRotationPoint(12.0F, 19.5F, 0.0F);
        nine.setTextureSize(64, 64);
        nine.mirror = true;
        setRotation(nine, -0.1919862F, 0.0F, 0.0F);
        ten = new ModelRenderer(this, 0, 45);
        ten.addBox(-1.5F, 1.7F, -1.5F, 3, 1, 3);
        ten.setRotationPoint(12.0F, 19.5F, 0.0F);
        ten.setTextureSize(64, 64);
        ten.mirror = true;
        setRotation(ten, 0.0F, 0.0F, 0.0F);
        eleven = new ModelRenderer(this, 49, 32);
        eleven.addBox(-0.5F, 2.5F, -1.8F, 1, 1, 1);
        eleven.setRotationPoint(12.0F, 19.5F, 0.0F);
        eleven.setTextureSize(64, 64);
        eleven.mirror = true;
        setRotation(eleven, 0.0F, 0.0F, 0.0F);
        twelve = new ModelRenderer(this, 54, 32);
        twelve.addBox(-0.5F, 1.8F, 1.2F, 1, 1, 4);
        twelve.setRotationPoint(12.0F, 19.5F, 0.0F);
        twelve.setTextureSize(64, 64);
        twelve.mirror = true;
        setRotation(twelve, 0.0F, 0.0F, 0.0F);
        thirteen = new ModelRenderer(this, 58, 32);
        thirteen.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        thirteen.setRotationPoint(12.7F, 22.0F, 0.0F);
        thirteen.setTextureSize(64, 64);
        thirteen.mirror = true;
        setRotation(thirteen, 0.0F, 0.0F, 0.0F);
        fourteen = new ModelRenderer(this, 58, 32);
        fourteen.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        fourteen.setRotationPoint(11.3F, 22.0F, 0.0F);
        fourteen.setTextureSize(64, 64);
        fourteen.mirror = true;
        setRotation(fourteen, 0.0F, 0.0F, 0.0F);
        fiveteen = new ModelRenderer(this, "BrasEpee");
        fiveteen.setRotationPoint(12.0F, 19.0F, 0.0F);
        setRotation(fiveteen, 0.0F, 0.0F, 0.0F);
        fiveteen.mirror = true;
        fiveteen.addBox("Shape12", 3.5F, -5.5F, -1.0F, 1, 1, 1);
        fiveteen.addBox("Shape16", 3.0F, -0.5F, -2.5F, 2, 1, 4);
        fiveteen.addBox("Shape17", 3.5F, -4.5F, -1.5F, 1, 4, 2);
        fiveteen.addBox("Shape18", 3.5F, 1.0F, -2.0F, 1, 1, 3);
        fiveteen.addBox("Shape19", 2.5F, 1.0F, -1.0F, 3, 1, 1);
        fiveteen.addBox("Shape20", 3.0F, 0.5F, -1.5F, 2, 2, 2);
        fiveteen.addBox("Shape21", 3.5F, 2.0F, -1.0F, 1, 1, 1);
        sixteen = new ModelRenderer(this, "BrasBouclier");
        sixteen.setRotationPoint(12.0F, 19.0F, 0.0F);
        setRotation(sixteen, 0.0F, 0.0F, 0.0F);
        sixteen.mirror = true;
        sixteen.addBox("twelve", -4.5F, 0.0F, -1.0F, 1, 3, 1);
        sixteen.addBox("Shape14", -5.0F, 0.5F, -1.5F, 2, 2, 2);
        sixteen.addBox("Shape15", -4.5F, 1.0F, -2.0F, 1, 1, 3);
        sixteen.addBox("Shape27", -5.0F, 3.0F, -1.5F, 1, 1, 3);
        sixteen.addBox("Shape29", -4.5F, -4.5F, -1.5F, 1, 1, 3);
        sixteen.addBox("Shape26", -5.5F, 2.5F, -2.5F, 1, 1, 5);
        sixteen.addBox("Shape28", -5.0F, -3.5F, -2.0F, 1, 2, 4);
        sixteen.addBox("Shape25", -5.5F, -1.5F, -2.5F, 1, 2, 5);
        sixteen.addBox("Shape23", -4.0F, -5.5F, -1.0F, 1, 1, 2);
        sixteen.addBox("Shape24", -6.0F, 0.0F, -3.0F, 1, 3, 6);
        sixteen.addBox("Shape13", -3.5F, 1.0F, -1.0F, 1, 1, 1);
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
        one.rotateAngleY = f3 / 85.943665F;
        one.rotateAngleX = f4 / 85.943665F;
        two.rotateAngleY = f3 / 85.943665F;
        two.rotateAngleX = f4 / 85.943665F;
        three.rotateAngleY = f3 / 85.943665F;
        three.rotateAngleX = f4 / 85.943665F;
        four.rotateAngleY = f3 / 85.943665F;
        four.rotateAngleX = f4 / 85.943665F;
        five.rotateAngleY = f3 / 85.943665F;
        five.rotateAngleX = f4 / 85.943665F;
        six.rotateAngleY = f3 / 85.943665F;
        six.rotateAngleX = f4 / 85.943665F;
        seven.rotateAngleY = f3 / 85.943665F;
        seven.rotateAngleX = f4 / 85.943665F;
        eight.rotateAngleY = f3 / 85.943665F;
        eight.rotateAngleX = f4 / 85.943665F;
        nine.rotateAngleY = f3 / 85.943665F;
        nine.rotateAngleX = f4 / 85.943665F;
        ten.rotateAngleY = f3 / 85.943665F;
        ten.rotateAngleX = f4 / 85.943665F;
        eleven.rotateAngleY = f3 / 85.943665F;
        eleven.rotateAngleX = f4 / 85.943665F;
        fiveteen.offsetZ = MathHelper.cos(f * 0.6F + 3.1415927F) * -0.2F * f1 * 0.4F;
        fiveteen.offsetY = MathHelper.cos(f * 0.6F + 3.1415927F) * -0.1F * f1 * 0.1F;
        sixteen.offsetZ = MathHelper.cos(f * 0.6F + 3.1415927F) * 0.2F * f1 * 0.4F;
        sixteen.offsetY = MathHelper.cos(f * 0.6F + 3.1415927F) * 0.1F * f1 * 0.1F;
        twelve.rotateAngleX = MathHelper.cos(f2 * 0.1F) / 20.0F + f1 / 1.4F - 0.4F;
        thirteen.rotateAngleX = MathHelper.cos(f * 0.9F + 3.1415927F) * 2.0F * f1 * 0.6F;
        fourteen.rotateAngleX = MathHelper.cos(f * 0.9F + 3.1415927F) * -2.0F * f1 * 0.6F;
    }
}
package com.minefus.degraduck.models.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelChampChamp extends ModelBase
{
    ModelRenderer one, two, three, four, five, six, seven;

    public ModelChampChamp()
    {
        textureWidth = 128;
        textureHeight = 256;
        setTextureOffset("Piece1.head1", 0, 55);
        setTextureOffset("Piece1.head3", 0, 82);
        setTextureOffset("Piece1.head2", 0, 36);
        setTextureOffset("Piece1.head4", 0, 104);
        setTextureOffset("Piece1.head5", 0, 124);
        setTextureOffset("Piece1.head6", 6, 126);
        setTextureOffset("Piece1.thorn1", 0, 76);
        setTextureOffset("Piece1.head7", 0, 143);
        setTextureOffset("Piece1.thorn2", 0, 76);
        setTextureOffset("Piece1.head8", 0, 0);
        setTextureOffset("Piece1.thorn3", 0, 76);
        setTextureOffset("Piece1.head9", 66, 36);
        setTextureOffset("Piece1.thorn4", 0, 76);
        setTextureOffset("Piece1.thorn7", 0, 76);
        setTextureOffset("Piece1.thorn5", 0, 76);
        setTextureOffset("Piece1.thorn6", 0, 76);
        one = new ModelRenderer(this, 0, 22);
        one.addBox(-4.5F, 2.5F, -4.5F, 9, 4, 9);
        one.setRotationPoint(0.0F, 17.0F, 0.0F);
        one.setTextureSize(128, 256);
        one.mirror = true;
        setRotation(one, 0.0F, 0.0F, 0.0F);
        two = new ModelRenderer(this, 36, 22);
        two.addBox(-3.0F, -4.0F, -3.0F, 6, 6, 6);
        two.setRotationPoint(0.0F, 18.0F, 0.0F);
        two.setTextureSize(128, 256);
        two.mirror = true;
        setRotation(two, 0.0F, 0.0F, 0.0F);
        three = new ModelRenderer(this, 74, 0);
        three.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
        three.setRotationPoint(4.5F, 21.0F, -4.5F);
        three.setTextureSize(128, 256);
        three.mirror = true;
        setRotation(three, 0.0F, 0.0F, 0.0F);
        four = new ModelRenderer(this, 74, 0);
        four.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
        four.setRotationPoint(-4.5F, 21.0F, -4.5F);
        four.setTextureSize(128, 256);
        four.mirror = true;
        setRotation(four, 0.0F, 0.0F, 0.0F);
        five = new ModelRenderer(this, 74, 0);
        five.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
        five.setRotationPoint(4.5F, 21.0F, 4.5F);
        five.setTextureSize(128, 256);
        five.mirror = true;
        setRotation(five, 0.0F, 0.0F, 0.0F);
        six = new ModelRenderer(this, 74, 0);
        six.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
        six.setRotationPoint(-4.5F, 21.0F, 4.5F);
        six.setTextureSize(128, 256);
        six.mirror = true;
        setRotation(six, 0.0F, 0.0F, 0.0F);
        seven = new ModelRenderer(this, "Piece1");
        seven.setRotationPoint(0.0F, 13.0F, 0.0F);
        setRotation(seven, 0.0F, 0.0F, 0.0F);
        seven.mirror = true;
        seven.addBox("head1", -9.0F, -2.0F, -9.0F, 18, 2, 18);
        seven.addBox("head3", -8.5F, -4.0F, -8.5F, 17, 2, 17);
        seven.addBox("head2", -8.0F, 0.0F, -8.0F, 16, 1, 16);
        seven.addBox("head4", -8.0F, -5.0F, -8.0F, 16, 1, 16);
        seven.addBox("head5", -7.0F, -6.0F, -7.0F, 14, 1, 14);
        seven.addBox("head6", -6.0F, -7.0F, -6.0F, 12, 1, 12);
        seven.addBox("thorn1", 3.5F, -10.0F, 2.5F, 2, 3, 2);
        seven.addBox("head7", -5.0F, -8.0F, -5.0F, 10, 1, 10);
        seven.addBox("thorn2", -8.5F, -7.0F, -6.0F, 2, 3, 2);
        seven.addBox("head8", -3.5F, -9.0F, -3.5F, 7, 1, 7);
        seven.addBox("thorn3", -3.0F, -9.0F, -6.5F, 2, 3, 2);
        seven.addBox("head9", -6.5F, 0.5F, -6.5F, 13, 1, 13);
        seven.addBox("thorn4", 6.5F, -7.0F, -1.0F, 2, 3, 2);
        seven.addBox("thorn7", -1.0F, -8.0F, 6.0F, 2, 3, 2);
        seven.addBox("thorn5", -7.0F, -9.0F, 2.0F, 2, 3, 2);
        seven.addBox("thorn6", 5.5F, -8.0F, -7.5F, 2, 3, 2);
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
        three.rotateAngleX = MathHelper.cos(f * 3.4F + 3.1415927F) * 2.5F * f1 * 0.6F;
        four.rotateAngleX = MathHelper.cos(f * 3.4F + 3.1415927F) * -2.5F * f1 * 0.6F;
        five.rotateAngleX = MathHelper.cos(f * 3.4F + 3.1415927F) * -2.5F * f1 * 0.6F;
        six.rotateAngleX = MathHelper.cos(f * 3.4F + 3.1415927F) * 2.5F * f1 * 0.6F;
        seven.rotateAngleX = MathHelper.cos(f * 0.2F + 3.1415927F) * 0.3F * f1 * 0.6F;
        seven.rotateAngleY = MathHelper.cos(f * 0.2F + 3.1415927F) * 0.3F * f1 * 0.6F;
        seven.rotateAngleZ = MathHelper.cos(f * 0.2F + 3.1415927F) * -0.3F * f1 * 0.6F;
    }
}
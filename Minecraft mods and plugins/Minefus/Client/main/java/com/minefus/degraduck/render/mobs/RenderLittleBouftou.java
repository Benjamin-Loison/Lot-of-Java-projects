package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityLittleBouftou;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLittleBouftou extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Bouftou.png");

    public RenderLittleBouftou(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityLittleBouftou entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityLittleBouftou)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobPetitBouftou((EntityLittleBouftou)entity, f);
    }

    protected void scaleMobPetitBouftou(EntityLittleBouftou entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(1.4F, 1.4F, 1.4F);
                break;
            case 1:
                GL11.glScalef(1.425F, 1.425F, 1.425F);
                break;
            case 2:
                GL11.glScalef(1.45F, 1.45F, 1.45F);
                break;
            case 3:
                GL11.glScalef(1.475F, 1.475F, 1.475F);
                break;
            case 4:
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                break;
            default:
                GL11.glScalef(1.45F, 1.45F, 1.45F);
        }
    }
}
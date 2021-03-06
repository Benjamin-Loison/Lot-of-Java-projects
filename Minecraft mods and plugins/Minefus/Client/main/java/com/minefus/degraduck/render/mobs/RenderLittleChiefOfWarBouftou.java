package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityLittleChiefOfWarBouftou;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLittleChiefOfWarBouftou extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Chief of war bouftou.png");

    public RenderLittleChiefOfWarBouftou(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityLittleChiefOfWarBouftou entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityLittleChiefOfWarBouftou)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobPetitChefDeGuerre((EntityLittleChiefOfWarBouftou)entity, f);
    }

    protected void scaleMobPetitChefDeGuerre(EntityLittleChiefOfWarBouftou entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(1.55F, 1.55F, 1.55F);
                break;
            case 1:
                GL11.glScalef(1.575F, 1.575F, 1.575F);
                break;
            case 2:
                GL11.glScalef(1.6F, 1.6F, 1.6F);
                break;
            case 3:
                GL11.glScalef(1.625F, 1.672F, 1.625F);
                break;
            case 4:
                GL11.glScalef(1.65F, 1.65F, 1.65F);
                break;
            default:
                GL11.glScalef(1.6F, 1.6F, 1.6F);
        }
    }
}
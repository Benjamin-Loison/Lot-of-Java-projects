package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityFairfulMoskito;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFairfulMoskito extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Moskito.png");

    public RenderFairfulMoskito(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityFairfulMoskito entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityFairfulMoskito)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobMoskitoPeureux((EntityFairfulMoskito)entity, f);
    }

    protected void scaleMobMoskitoPeureux(EntityFairfulMoskito entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(0.9F, 0.9F, 0.9F);
                break;
            case 1:
                GL11.glScalef(0.925F, 0.925F, 0.925F);
                break;
            case 2:
                GL11.glScalef(0.95F, 0.95F, 0.95F);
                break;
            case 3:
                GL11.glScalef(0.975F, 0.975F, 0.975F);
                break;
            case 4:
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                break;
            default:
                GL11.glScalef(0.95F, 0.95F, 0.95F);
        }
    }
}
package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityMoskito;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderMoskito extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Moskito.png");

    public RenderMoskito(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityMoskito entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityMoskito)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobMoskito((EntityMoskito)entity, f);
    }

    protected void scaleMobMoskito(EntityMoskito entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(0.95F, 0.95F, 0.95F);
                break;
            case 1:
                GL11.glScalef(0.975F, 0.975F, 0.975F);
                break;
            case 2:
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                break;
            case 3:
                GL11.glScalef(1.25F, 1.25F, 1.25F);
                break;
            case 4:
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                break;
            default:
                GL11.glScalef(1.0F, 1.0F, 1.0F);
        }
    }
}
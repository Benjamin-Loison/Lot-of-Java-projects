package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityWhiteBoufton;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderWhiteBoufton extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/White boufton.png");

    public RenderWhiteBoufton(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityWhiteBoufton entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityWhiteBoufton)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobBouftonBlanc((EntityWhiteBoufton)entity, f);
    }

    protected void scaleMobBouftonBlanc(EntityWhiteBoufton entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(1.2F, 1.2F, 1.2F);
                break;
            case 1:
                GL11.glScalef(1.225F, 1.225F, 1.225F);
                break;
            case 2:
                GL11.glScalef(1.25F, 1.25F, 1.25F);
                break;
            case 3:
                GL11.glScalef(1.275F, 1.275F, 1.275F);
                break;
            case 4:
                GL11.glScalef(1.3F, 1.3F, 1.3F);
                break;
            default:
                GL11.glScalef(1.25F, 1.25F, 1.25F);
        }
    }
}
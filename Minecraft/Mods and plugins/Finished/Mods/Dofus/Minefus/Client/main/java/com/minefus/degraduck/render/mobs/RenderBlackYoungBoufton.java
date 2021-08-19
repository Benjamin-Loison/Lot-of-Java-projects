package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityBlackYoungBoufton;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBlackYoungBoufton extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Black boufton.png");

    public RenderBlackYoungBoufton(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityBlackYoungBoufton entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityBlackYoungBoufton)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobJeuneBouftonNoir((EntityBlackYoungBoufton)entity, f);
    }

    protected void scaleMobJeuneBouftonNoir(EntityBlackYoungBoufton entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(1.15F, 1.15F, 1.15F);
                break;
            case 1:
                GL11.glScalef(1.175F, 1.175F, 1.175F);
                break;
            case 2:
                GL11.glScalef(1.2F, 1.2F, 1.2F);
                break;
            case 3:
                GL11.glScalef(1.225F, 1.225F, 1.225F);
                break;
            case 4:
                GL11.glScalef(1.25F, 1.25F, 1.25F);
                break;
            default:
                GL11.glScalef(1.2F, 1.2F, 1.2F);
        }
    }
}
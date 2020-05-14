package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityPurplePiou;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderPurplePiou extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Purple piou.png");

    public RenderPurplePiou(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityPurplePiou entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityPurplePiou)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobPiouViolet((EntityPurplePiou)entity, f);
    }

    protected void scaleMobPiouViolet(EntityPurplePiou entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(0.6F, 0.6F, 0.6F);
                break;
            case 1:
                GL11.glScalef(0.625F, 0.625F, 0.625F);
                break;
            case 2:
                GL11.glScalef(0.65F, 0.65F, 0.65F);
                break;
            case 3:
                GL11.glScalef(0.675F, 0.675F, 0.675F);
                break;
            case 4:
                GL11.glScalef(0.7F, 0.7F, 0.7F);
                break;
            default:
                GL11.glScalef(0.65F, 0.65F, 0.65F);
        }
    }
}
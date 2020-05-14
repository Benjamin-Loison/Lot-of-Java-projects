package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityBlueUnripeLarva;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBlueUnripeLarva extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Blue larva.png");

    public RenderBlueUnripeLarva(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityBlueUnripeLarva entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityBlueUnripeLarva)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobLarveBleuImmature((EntityBlueUnripeLarva)entity, f);
    }

    protected void scaleMobLarveBleuImmature(EntityBlueUnripeLarva entity, float f)
    {
        switch(entity.mobSizeGen)
        {
            case 0:
                GL11.glScalef(0.75F, 0.75F, 0.75F);
                break;
            case 1:
                GL11.glScalef(0.775F, 0.775F, 0.775F);
                break;
            case 2:
                GL11.glScalef(0.8F, 0.8F, 0.8F);
                break;
            case 3:
                GL11.glScalef(0.925F, 0.925F, 0.925F);
                break;
            case 4:
                GL11.glScalef(0.95F, 0.95F, 0.95F);
                break;
            default:
                GL11.glScalef(0.8F, 0.8F, 0.8F);
        }
    }
}
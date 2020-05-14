package com.minefus.degraduck.render.mobs;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.entity.EntityRoyalBouftou;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRoyalBouftou extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/entity/Royal bouftou.png");

    public RenderRoyalBouftou(ModelBase model, float f)
    {
        super(model, f);
    }

    protected ResourceLocation getEntityTexture(EntityRoyalBouftou entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return getEntityTexture((EntityRoyalBouftou)entity);
    }

    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        scaleMobBouftouRoyal((EntityRoyalBouftou)entity, f);
    }

    protected void scaleMobBouftouRoyal(EntityRoyalBouftou entity, float f)
    {
        GL11.glScalef(1.95F, 1.95F, 1.95F);
    }
}
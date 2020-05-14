package com.minefus.degraduck.render.items;

import org.lwjgl.opengl.GL11;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.models.items.ModelRoyalBouftouSword;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class RenderRoyalBouftouSword implements IItemRenderer
{
    protected ModelRoyalBouftouSword model;
    protected static final ResourceLocation texture = new ResourceLocation(Minefus.MODID + ":textures/items/Royal bouftou sword.png");

    public RenderRoyalBouftouSword()
    {
        model = new ModelRoyalBouftouSword();
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
    {
        switch(type)
        {
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            default:
                break;
        }
        return false;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
    {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case EQUIPPED:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(145.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -1.035F, -0.7F);
                model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();
                Minecraft.getMinecraft().renderEngine.bindTexture(texture);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(145.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-2.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.2F, -1.0F, -0.75F);
                model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            default:
                break;
        }
    }
}
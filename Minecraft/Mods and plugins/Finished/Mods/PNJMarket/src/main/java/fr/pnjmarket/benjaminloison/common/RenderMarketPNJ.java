package fr.pnjmarket.benjaminloison.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMarketPNJ extends RenderBiped
{
    public ResourceLocation locationSkin;
    public ThreadDownloadImageData downloadImageSkin = null;
    public String textureChanged;

    public RenderMarketPNJ(ModelBiped model, float shadow)
    {
        super(model, shadow);
    }

    protected ResourceLocation getEntityTexture(EntityLiving living)
    {
        EEPPNJ prop = EEPPNJ.get(living);
        DataWatcher dw = living.getDataWatcher();
        String texture = dw.getWatchableObjectString(20);
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(prop.getLocationHat(texture));
        if(prop.texture != texture)
        {
            prop.downloadImageHat = null;
        }
        if(prop.downloadImageHat == null)
        {
            prop.downloadImageHat = prop.getDownloadImageHat(prop.getLocationHat(texture), texture);
        }
        prop.texture = texture;
        return prop.getLocationHat(texture);
    }

    private ResourceLocation getDefaultPNJTexture()
    {
        return new ResourceLocation(ModPNJMarket.MODID, "textures/entity/steve.png");
    }
}

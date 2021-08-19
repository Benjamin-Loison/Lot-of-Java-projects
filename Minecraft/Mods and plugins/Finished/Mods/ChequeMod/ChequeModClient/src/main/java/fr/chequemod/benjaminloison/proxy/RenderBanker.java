package fr.chequemod.benjaminloison.proxy;

import java.awt.image.BufferedImage;
import java.io.File;

import fr.chequemod.benjaminloison.common.ChequeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class RenderBanker extends RenderBiped
{
    public ResourceLocation locationSkin;
    public ThreadDownloadImageData downloadImageSkin = null;
    public String textureChanged;
    public ResourceLocation locationHat;
    public String texture;
    public ThreadDownloadImageData downloadImageHat;

    public RenderBanker(ModelBiped model, float shadow)
    {
        super(model, shadow);
    }

    protected ResourceLocation getEntityTexture(EntityLiving living)
    {
        String texture = I18n.format("texture.file.name");
        downloadImageHat = getDownloadImageHat(getLocationHat(texture), texture);
        return getLocationHat(texture);
    }

    private ResourceLocation getDefaultPNJTexture()
    {
        return new ResourceLocation(ChequeMod.MODID, "textures/entity/Steve.png");
    }

    public ThreadDownloadImageData getTextureHat()
    {
        return downloadImageHat;
    }

    public ThreadDownloadImageData getDownloadImageHat(ResourceLocation resourceLocation, String uuid)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(resourceLocation);
        if(object == null)
        {
            object = new ThreadDownloadImageData((File)null, String.format(I18n.format("banker.skin.url") + ".png", StringUtils.stripControlCodes(uuid)), null, new ImageBufferDownload()
            {
                @Override
                public BufferedImage parseUserSkin(BufferedImage image)
                {
                    return image;
                }
            });
            texturemanager.loadTexture(resourceLocation, (ITextureObject)object);
        }
        return (ThreadDownloadImageData)object;
    }

    public String getHatInfoUrl(String uuid)
    {
        return String.format(I18n.format("banker.skin.url") + ".png", new Object[] {StringUtils.stripControlCodes(uuid)});
    }

    public ResourceLocation getLocationHat(String uuid)
    {
        return new ResourceLocation(ChequeMod.MODID, "texture/entity/skins/" + StringUtils.stripControlCodes(uuid));
    }
}
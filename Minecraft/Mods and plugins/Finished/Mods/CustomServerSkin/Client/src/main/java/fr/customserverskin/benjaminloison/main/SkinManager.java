package fr.customserverskin.benjaminloison.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class SkinManager
{
    public static final Map<String, String> eep = new HashMap<String, String>();

    public static ThreadDownloadImageData getDownloadImageHat(ResourceLocation resourceLocation, String name)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(resourceLocation);
        if(object == null)
        {
            object = new ThreadDownloadImageData((File)null, String.format(I18n.format("url.skin.database") + "%s.png", StringUtils.stripControlCodes(name)), null, new ImageBufferDownload()
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

    public static ResourceLocation getLocationHat(String name)
    {
        return new ResourceLocation(CustomServerSkin.MODID, "texture/skins/" + StringUtils.stripControlCodes(name));
    }
}

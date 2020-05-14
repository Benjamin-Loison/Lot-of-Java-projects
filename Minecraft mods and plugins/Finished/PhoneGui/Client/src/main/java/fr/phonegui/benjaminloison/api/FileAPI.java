package fr.phonegui.benjaminloison.api;

import net.minecraft.client.resources.I18n;

public class FileAPI
{
    public static int configNumberInt(String config)
    {
        return Integer.parseInt(I18n.format(config));
    }
}
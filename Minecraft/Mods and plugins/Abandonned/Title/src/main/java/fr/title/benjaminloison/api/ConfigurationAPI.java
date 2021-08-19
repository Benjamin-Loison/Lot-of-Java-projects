package fr.title.benjaminloison.api;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.resources.I18n;

public class ConfigurationAPI
{
    public static int getConfiguration(String configurationParameter)
    {
        String configuration = I18n.format(configurationParameter);
        if(StringUtils.isNumeric(configuration))
            return Integer.parseInt(configuration);
        else
            return 0;
    }
}
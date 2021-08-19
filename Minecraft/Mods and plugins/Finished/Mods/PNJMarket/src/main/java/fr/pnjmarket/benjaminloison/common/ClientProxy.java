package fr.pnjmarket.benjaminloison.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
    public ClientProxy()
    {
        MinecraftForge.EVENT_BUS.register(this);
        RenderingRegistry.registerEntityRenderingHandler(EntityMarketPNJ.class, new RenderMarketPNJ(new ModelBiped(), 0.5F));
    }

    public void registerRender()
    {
        FMLCommonHandler.instance().bus().register(this);
        System.out.println("Méthode côté client");
    }
}
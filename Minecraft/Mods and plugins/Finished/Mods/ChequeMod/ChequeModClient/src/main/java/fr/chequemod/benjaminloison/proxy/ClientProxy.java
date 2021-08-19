package fr.chequemod.benjaminloison.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.chequemod.benjaminloison.entity.EntityBanker;
import net.minecraft.client.model.ModelBiped;

public class ClientProxy
{
    public ClientProxy()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBanker.class, new RenderBanker(new ModelBiped(), 0.5F));
    }
}
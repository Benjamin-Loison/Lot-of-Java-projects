package fr.title.benjaminloison.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.title.benjaminloison.events.ForgeEventHandler;
import fr.title.benjaminloison.events.RenderTickHandler;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Title.MODID, version = "Universal", name = Title.NAME)
public class Title
{
    public static final String MODID = "title", NAME = "Title";
    @Instance(MODID)
    public static Title instance;
    public SimpleNetworkWrapper network;
    public static ForgeEventHandler forgeEventHandler;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        print("Initializing !");
        instance = this;
        forgeEventHandler = new ForgeEventHandler();
        MinecraftForge.EVENT_BUS.register(forgeEventHandler);
        FMLCommonHandler.instance().bus().register(new RenderTickHandler());
        print(I18n.format("initialized"));
    }

    public static void print(Object object)
    {
        System.out.println("[" + NAME + "] " + object);
    }
}
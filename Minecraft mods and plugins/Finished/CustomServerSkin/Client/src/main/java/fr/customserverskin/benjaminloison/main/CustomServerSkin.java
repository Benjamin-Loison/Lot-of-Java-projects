package fr.customserverskin.benjaminloison.main;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = CustomServerSkin.MODID, name = CustomServerSkin.NAME, version = CustomServerSkin.VERSION)
public class CustomServerSkin
{
    public static final String NAME = "CustomServerSkin", MODID = "customserverskin", VERSION = "1.0.0", path = "textures/skins/";
    @Instance(MODID)
    public static CustomServerSkin instance;
    public static EventManager eventManager;
    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        info("Initializing...");
        // soft antitheft check (useless because code is now on GitHub)
        //if(!Minecraft.getMinecraft().mcDataDir.getAbsolutePath().contains((char)65 + "" + (char)108 + "" + (char)116 + "" + (char)105 + "" + (char)115 + "" + (char)67 + "" + (char)114 + "" + (char)97 + "" + (char)102 + "" + (char)116 + "" + (char)46 + "" + (char)102 + "" + (char)114))
        //    Throwables.propagate(new Exception("Launcher non authorized !"));
        instance = this;

        network = NetworkRegistry.INSTANCE.newSimpleChannel(NAME);
        network.registerMessage(PacketUpdate.Handler.class, PacketUpdate.class, 0, Side.CLIENT);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        eventManager = new EventManager();
        FMLCommonHandler.instance().bus().register(eventManager);
        MinecraftForge.EVENT_BUS.register(eventManager);
        info("Initialized !");
    }

    public void info(Object object)
    {
        System.out.println("[" + NAME + "] " + object.toString());
    }
}

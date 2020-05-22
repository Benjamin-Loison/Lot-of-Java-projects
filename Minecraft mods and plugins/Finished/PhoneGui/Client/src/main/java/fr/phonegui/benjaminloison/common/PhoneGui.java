package fr.phonegui.benjaminloison.common;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.phonegui.benjaminloison.items.Phone;
import fr.phonegui.benjaminloison.items.TalkieWalkie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = PhoneGui.MODID, name = PhoneGui.NAME, version = PhoneGui.VERSION)
public class PhoneGui
{
    public static final String NAME = "PhoneGui", MODID = "phonegui", VERSION = "InDev";

    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent event)
    {
        // antitheft check (useless because code is now on GitHub)
        //if(!Minecraft.getMinecraft().mcDataDir.getAbsolutePath().contains((char)65 + "" + (char)108 + "" + (char)116 + "" + (char)105 + "" + (char)115 + "" + (char)67 + "" + (char)114 + "" + (char)97 + "" + (char)102 + "" + (char)116 + "" + (char)46 + "" + (char)102 + "" + (char)114 + "" + (char)32 + "" + (char)45 + "" + (char)32 + "" + (char)68 + "" + (char)97 + "" + (char)114 + "" + (char)107 + "" + (char)82 + "" + (char)80))
        //    Throwables.propagate(new Exception("Launcher non authorized !"));
        GameRegistry.registerItem(new Phone(), "phone");
        int number = 0;
        while(!I18n.format("item.talkiewalkie" + number + ".name").equals("item.talkiewalkie" + number + ".name"))
        {
            GameRegistry.registerItem(new TalkieWalkie(number), "talkiewalkie" + number);
            number++;
        }
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
        // antitheft check (useless because code is now on GitHub)
        //if(!event.manager.getSocketAddress().toString().contains((char)97 + "" + (char)108 + "" + (char)116 + "" + (char)105 + "" + (char)115 + "" + (char)99 + "" + (char)114 + "" + (char)97 + "" + (char)102 + "" + (char)116 + "" + (char)46 + "" + (char)102 + "" + (char)114 + "" + (char)47))
        //    Throwables.propagate(new Exception("Server non authorized !"));
    }
}

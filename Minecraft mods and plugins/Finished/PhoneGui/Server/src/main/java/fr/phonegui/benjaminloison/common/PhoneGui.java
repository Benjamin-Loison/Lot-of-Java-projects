package fr.phonegui.benjaminloison.common;

import java.io.File;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.phonegui.benjaminloison.items.Phone;
import fr.phonegui.benjaminloison.items.TalkieWalkie;

@Mod(modid = PhoneGui.MODID, name = PhoneGui.NAME, version = PhoneGui.VERSION)
public class PhoneGui
{
    public static final String NAME = "PhoneGui", MODID = "phonegui", VERSION = "InDev";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // antitheft check (useless because code is now on GitHub)
        //if(!new File("").getAbsolutePath().contains((char)68 + "" + (char)97 + "" + (char)114 + "" + (char)107 + "" + (char)82 + "" + (char)80))
        //    Throwables.propagate(new Exception("Server non authorized !"));
        GameRegistry.registerItem(new Phone(), "phone");
        for(int i = 0; i < Integer.parseInt("TalkieWalkie:4".replace("TalkieWalkie:", "")); i++)
            GameRegistry.registerItem(new TalkieWalkie(i), "talkiewalkie" + i);

    }
}

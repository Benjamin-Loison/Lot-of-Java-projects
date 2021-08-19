package fr.taltisconverter.benjaminloison.common;

import java.io.File;

import com.sk89q.worldedit.WorldEdit;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = AltisConverterMod.MODID, name = AltisConverterMod.NAME, version = AltisConverterMod.VERSION)
public class AltisConverterMod
{
    public static final String NAME = "AltisConverter", MODID = "altisconverter", VERSION = "InDev";

    // World Edit mod jar is init as a lib and a mod likewise
    static WorldEdit weIns;
    EventHandler eventHandler;
    
    static String modFolderStr = "mods" + File.separator + NAME + File.separator;
    static File modFolder = new File(modFolderStr);
    
    static String mapFolderStr;
    static File mapFolder;
    
    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent event)
    {
        modFolder.mkdirs();
        // FMLCommonHandler.instance().bus().register(this);
        eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        weIns = getWorldEdit();
    }

    public WorldEdit getWorldEdit()
    {
        return WorldEdit.getInstance();
    }
}
package fr.taltisconverter.benjaminloison.common;

import java.io.File;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

public class EventHandler
{
    String cmd = "ca";

    @SubscribeEvent
    public void onServerChat(final ServerChatEvent event)
    {
        /*Thread th = new Thread()
        {
            public void run()
            {*/
                // Minecraft mc = Minecraft.getMinecraft();
                EntityPlayer player = event.player/* mc.thePlayer */;

                if(player != null)
                {
                    // Minecraft.getMinecraft().theWorld.setBlock(1234, 12, 1234, Blocks.acacia_stairs);
                    String msg = event.message/* .getUnformattedText() */;
                    if(msg.contains(cmd))
                    {
                        // player.addChatMessage(new ChatComponentText(player.worldObj.getBlock(0, 0, 0).getLocalizedName() + ""));
                        String[] parts = msg.split(" ");
                        String realCmd = "";
                        int partsLength = parts.length;
                        for(int partIndex = 0; partIndex < partsLength; partIndex++)
                        {
                            if(parts[partIndex].equals(cmd))
                            {
                                for(int newPartIndex = partIndex + 1; newPartIndex < partsLength; newPartIndex++)
                                {
                                    realCmd += parts[newPartIndex];
                                    if(newPartIndex != partsLength - 1)
                                        realCmd += " ";
                                }
                                break;
                            }
                        }
                        String[] args = realCmd.split(" ");
                        if(args.length < 1)
                            return;
                        String tmpStr = AltisConverterMod.modFolderStr + args[0] + File.separator;
                        File tmpFile = new File(tmpStr);
                        if(!tmpFile.exists())
                            return;
                        AltisConverterMod.mapFolderStr = tmpStr;
                        AltisConverterMod.mapFolder = tmpFile;
                        player.addChatMessage(new ChatComponentText(String.valueOf(MapUtil.minHeight())));
                        player.addChatMessage(new ChatComponentText(String.valueOf(MapUtil.maxHeight())));
                        player.addChatMessage(new ChatComponentText(String.valueOf(MapUtil.seaHeight())));
                        MapUtil.biomeTileSize = MapUtil.value("biomeTileSize");
                        MapUtil.subChunkGroundSize = MapUtil.value("subChunkGroundSize");
                        MapUtil.MC_WATER = MapUtil.mcHeight(MapUtil.seaHeight());
                        MapUtil.quotient = MapUtil.MC_MAX / (MapUtil.maxHeight - MapUtil.minHeight());
                        MapUtil.importMap();
                        player.addChatMessage(new ChatComponentText("Import finished !"));
                        // event.setCanceled(true);
                    }
                }
           /* }
        };
        th.start();*/
    }
}

/*
 * Fine
 * System.out.println(String.valueOf(MapUtil.mcHeight(-100)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(-99.5)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(-98)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(-95)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(0)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(50)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(250)));
 * System.out.println(String.valueOf(MapUtil.mcHeight(340)));
 */
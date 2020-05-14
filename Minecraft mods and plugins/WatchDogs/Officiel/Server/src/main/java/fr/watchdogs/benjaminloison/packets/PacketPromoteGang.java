package fr.altiscraft.benjaminloison.packets;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class PacketPromoteGang implements IMessage
{
    static String name;
    static boolean promote, join;

    public PacketPromoteGang()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
        promote = buf.readBoolean();
        join = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketPromoteGang, IMessage>
    {
        @Override
        public IMessage onMessage(PacketPromoteGang message, MessageContext ctx)
        {
            try
            {
                EntityPlayerMP client = ctx.getServerHandler().playerEntity;
                EEP props = EEP.get(client);
                String clientName = client.getDisplayName();
                List<EntityPlayerMP> playerList = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
                SimpleNetworkWrapper network = AltisCraft.instance.network;
                if(join)
                    if(promote)
                    {
                        network.sendTo(new PacketMessage("you.have.joined.the.gang", props.gang), client);
                        client.addChatMessage(new ChatComponentText("!GANG!"));
                        for(EntityPlayerMP player : playerList)
                            if(EEP.get(player).gang.equals(props.gangInviting))
                            {
                                network.sendTo(new PacketMessage("player.has.joined.your.gang", clientName), player);
                                player.addChatMessage(new ChatComponentText("!FRIEND! " + client.getUniqueID() + " false"));
                            }
                        props.set("gang", props.gangInviting);
                        props.set("gangInviting", "");
                        FileAPI.addLign(name, new File(FileAPI.gang + props.gang + ".txt"));
                    }
                    else
                    {
                        EntityPlayerMP playerToAdd = (EntityPlayerMP)client.worldObj.getPlayerEntityByName(name);
                        if(playerToAdd != null)
                        {
                            EEP propsa = EEP.get(playerToAdd);
                            propsa.set("gangInviting", props.gang);
                            network.sendTo(new PacketMessage("you.have.been.invited.in.the.gang", props.gang), playerToAdd);
                            network.sendTo(new PacketMessage("you.have.invited.player.in.your.gang", playerToAdd.getDisplayName()), client);
                        }
                        else
                            client.addChatMessage(new ChatComponentTranslation("player.not.found"));
                    }
                else if(promote)
                {
                    File gang = new File(FileAPI.gang + props.gang + ".txt"), tempGang = new File(FileAPI.gang + props.gang + ".temp");
                    gang.renameTo(tempGang);
                    Scanner scan = new Scanner(tempGang);
                    FileWriter fw = new FileWriter(gang);
                    EntityPlayerMP chief = (EntityPlayerMP)client.worldObj.getPlayerEntityByName(name);
                    fw.write(name + "\n");
                    scan.nextLine();
                    while(scan.hasNextLine())
                    {
                        String stream = scan.nextLine();
                        if(!stream.equals(name))
                            fw.write(stream);
                        if(scan.hasNextLine())
                            fw.write("\n");
                        else
                            fw.write(clientName);
                        if(scan.hasNextLine())
                            fw.write("\n");
                    }
                    fw.close();
                    scan.close();
                    tempGang.delete();
                    network.sendTo(new PacketMessage("you.have.promoted", name), client);
                    network.sendTo(new PacketMessage("you.have.been.promoted.chief.of.the.gang.by", clientName), chief);
                    for(EntityPlayerMP player : playerList)
                        if(EEP.get(player).gang.equals(props.gang) && player != client && player != chief)
                            network.sendTo(new PacketMessage("player.has.promoted.player.chief.of.your.gang", clientName, chief.getDisplayName()), player);
                }
                else
                {
                    FileAPI.removeLign(name, new File(FileAPI.gang + props.gang + ".txt"));
                    EntityPlayerMP leaver = (EntityPlayerMP)client.worldObj.getPlayerEntityByName(name);
                    if(leaver != null)
                    {
                        EEP leaverProps = EEP.get(leaver);
                        network.sendTo(new PacketMessage("you.have.been.fired.of.the.gang", leaverProps.gang), leaver);
                        leaverProps.set("gang", "");
                        network.sendTo(new PacketMessage("you.have.fired", leaver.getDisplayName()), client);
                        client.addChatMessage(new ChatComponentText("!FRIEND! " + leaver.getUniqueID() + " true"));
                        for(EntityPlayerMP player : playerList)
                            if(EEP.get(player).gang.equals(props.gang) && player != client)
                            {
                                leaver.addChatMessage(new ChatComponentText("!FRIEND! " + player.getUniqueID() + " true"));
                                player.addChatMessage(new ChatComponentText("!FRIEND! " + leaver.getUniqueID() + " true"));
                                network.sendTo(new PacketMessage("player.has.fired.player", clientName, leaver.getDisplayName()), player);
                            }
                    }
                    else
                        client.addChatMessage(new ChatComponentTranslation("player.not.found"));
                }
            }
            catch(Exception e)
            {}
            return null;
        }
    }
}
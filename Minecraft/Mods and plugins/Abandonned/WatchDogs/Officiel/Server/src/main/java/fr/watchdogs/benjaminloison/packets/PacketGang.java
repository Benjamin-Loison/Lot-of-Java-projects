package fr.watchdogs.benjaminloison.packets;

import java.io.File;
import java.util.Scanner;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketGang implements IMessage
{
    public PacketGang()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketGang, IMessage>
    {
        @Override
        public IMessage onMessage(PacketGang message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            String chief = "", players[] = {"", "", "", "", "", "", "", "", "", ""}, playersUUIDS[] = {"", "", "", "", "", "", "", "", "", ""};
            long money = 0;
            try
            {
                Scanner sc = new Scanner(new File(FileAPI.gang + EEP.get(player).gang + ".txt"));
                chief = sc.nextLine();
                money = Long.parseLong(sc.nextLine());
                int i = 0;
                while(sc.hasNextLine())
                {
                    players[i] = sc.nextLine();
                    playersUUIDS[i] = player.worldObj.getPlayerEntityByName(players[i]).getUniqueID().toString();
                    i++;
                }
                sc.close();
            }
            catch(Exception e)
            {}
            WatchDogs.instance.network.sendTo(new PacketRenderGang(chief, money, players, playersUUIDS), player);
            return null;
        }
    }
}
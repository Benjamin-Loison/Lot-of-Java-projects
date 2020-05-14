package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketRenderGang implements IMessage
{
    String chief, players[], playersUUIDS[];
    long money;

    public PacketRenderGang(String chief, long money, String[] players, String[] playersUUIDS)
    {
        this.chief = chief;
        this.money = money;
        this.players = players;
        this.playersUUIDS = playersUUIDS;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, chief);
        buf.writeLong(money);
        buf.writeInt(players.length);
        for(int i = 0; i < players.length; i++)
        {
            ByteBufUtils.writeUTF8String(buf, players[i]);
            ByteBufUtils.writeUTF8String(buf, playersUUIDS[i]);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketRenderGang, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRenderGang message, MessageContext ctx)
        {
            return null;
        }
    }
}
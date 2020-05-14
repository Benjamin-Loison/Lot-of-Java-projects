package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.ClientStats;
import fr.watchdogs.benjaminloison.api.ThirstLogic;
import io.netty.buffer.ByteBuf;

public class PacketUpdateClient0 implements IMessage
{
    int level;
    float saturation;
    boolean poisoned;

    public PacketUpdateClient0()
    {}

    public PacketUpdateClient0(ThirstLogic stats)
    {
        this.level = stats.thirstLevel;
        this.saturation = stats.thirstSaturation;
        this.poisoned = stats.poisonLogic.isPlayerPoisoned();
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        level = buffer.readInt();
        saturation = buffer.readFloat();
        poisoned = buffer.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeInt(level);
        buffer.writeFloat(saturation);
        buffer.writeBoolean(poisoned);
    }

    public void handleClientSide()
    {
        ClientStats.getInstance().level = level;
        ClientStats.getInstance().saturation = saturation;
        ClientStats.getInstance().isPoisoned = poisoned;
    }

    public static class Handler implements IMessageHandler<PacketUpdateClient0, IMessage>
    {
        @Override
        public IMessage onMessage(PacketUpdateClient0 message, MessageContext ctx)
        {
            message.handleClientSide();
            return null;
        }
    }
}
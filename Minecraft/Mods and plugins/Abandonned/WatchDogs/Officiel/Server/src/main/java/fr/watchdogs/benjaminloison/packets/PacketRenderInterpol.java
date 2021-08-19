package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketRenderInterpol implements IMessage
{
    int numberResearches, numberAmountResearches, amountResearches[], numberReasonResearches;
    String[] researches, reasonResearches;

    public PacketRenderInterpol(String[] researches, int[] amountResearches, String[] reasonResearches)
    {
        numberResearches = researches.length;
        this.researches = researches;
        numberAmountResearches = amountResearches.length;
        this.amountResearches = amountResearches;
        numberReasonResearches = reasonResearches.length;
        this.reasonResearches = reasonResearches;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(numberResearches);
        for(int i = 0; i < researches.length; i++)
            ByteBufUtils.writeUTF8String(buf, researches[i]);
        buf.writeInt(numberAmountResearches);
        for(int i = 0; i < amountResearches.length; i++)
            buf.writeInt(amountResearches[i]);
        buf.writeInt(numberReasonResearches);
        for(int i = 0; i < reasonResearches.length; i++)
            ByteBufUtils.writeUTF8String(buf, reasonResearches[i]);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketRenderInterpol, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRenderInterpol message, MessageContext ctx)
        {
            return null;
        }
    }
}
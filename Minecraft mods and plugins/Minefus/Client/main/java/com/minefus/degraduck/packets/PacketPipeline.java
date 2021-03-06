package com.minefus.degraduck.packets;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

@ChannelHandler.Sharable
public class PacketPipeline extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket>
{
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList();
    private boolean isPostInitialised = false;

    public boolean registerPacket(Class<? extends AbstractPacket> clazz)
    {
        if(packets.size() > 256)
            return false;
        if(packets.contains(clazz))
            return false;
        if(isPostInitialised)
            return false;
        packets.add(clazz);
        return true;
    }

    protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception
    {
        ByteBuf buffer = Unpooled.buffer();
        if(!packets.contains(msg.getClass()))
            throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
        buffer.writeByte((byte)packets.indexOf(msg.getClass()));
        msg.encodeInto(ctx, buffer);
        out.add(new FMLProxyPacket(buffer.copy(), (String)ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get()));
    }

    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception
    {
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();
        Class<? extends AbstractPacket> clazz = (Class)packets.get(discriminator);
        if(clazz == null)
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        AbstractPacket pkt = (AbstractPacket)clazz.newInstance();
        pkt.decodeInto(ctx, payload.slice());
        pkt.handleClientSide(getClientPlayer());
        out.add(pkt);
    }

    public void initialise()
    {
        channels = NetworkRegistry.INSTANCE.newChannel("MinefusInventory", new ChannelHandler[] {this});
        channels = NetworkRegistry.INSTANCE.newChannel("MimibioteInventory", new ChannelHandler[] {this});
        registerPackets();
    }

    public void registerPackets()
    {
        registerPacket(OpenGuiPacket.class);
        registerPacket(SyncPlayerPropsPacket.class);
    }

    public void postInitialise()
    {
        if(isPostInitialised)
            return;
        isPostInitialised = true;
        Collections.sort(packets, new Comparator()
        {
            public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2)
            {
                int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
                if(com == 0)
                    com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
                return com;
            }

            @Override
            public int compare(Object arg0, Object arg1)
            {
                return 0;
            }
        });
    }

    private EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    public void sendToServer(AbstractPacket message)
    {
        ((FMLEmbeddedChannel)channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        ((FMLEmbeddedChannel)channels.get(Side.CLIENT)).writeAndFlush(message);
    }
}
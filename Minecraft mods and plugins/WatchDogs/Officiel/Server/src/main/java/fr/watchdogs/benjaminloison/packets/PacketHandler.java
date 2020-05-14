package fr.watchdogs.benjaminloison.packets;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;

@ChannelHandler.Sharable
public class PacketHandler extends MessageToMessageCodec<FMLProxyPacket, PacketBase>
{
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Class<? extends PacketBase>> packets = new LinkedList<Class<? extends PacketBase>>();
    private boolean modInitialised = false;

    public boolean registerPacket(Class<? extends PacketBase> cl)
    {
        if(packets.size() > 256)
            return false;
        if(packets.contains(cl))
            return false;
        if(modInitialised)
            return false;
        packets.add(cl);
        return true;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, PacketBase msg, List<Object> out) throws Exception
    {
        ByteBuf encodedData = Unpooled.buffer();
        Class<? extends PacketBase> cl = msg.getClass();
        if(!packets.contains(cl))
            throw new NullPointerException("Packet not registered : " + cl.getCanonicalName());
        encodedData.writeByte((byte)packets.indexOf(cl));
        msg.encodeInto(ctx, encodedData);
        out.add(new FMLProxyPacket(encodedData.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get()));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception
    {
        ByteBuf encodedData = msg.payload();
        byte discriminator = encodedData.readByte();
        Class<? extends PacketBase> cl = packets.get(discriminator);
        if(cl == null)
            throw new NullPointerException("Packet not registered for discriminator : " + discriminator);
        PacketBase packet = cl.newInstance();
        packet.decodeInto(ctx, encodedData.slice());
        switch(FMLCommonHandler.instance().getEffectiveSide())
        {
            case CLIENT:
            {
                break;
            }
            case SERVER:
            {
                packet.handleServerSide(((NetHandlerPlayServer)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get()).playerEntity);
                break;
            }
        }
    }

    public void initialise()
    {
        channels = NetworkRegistry.INSTANCE.newChannel("FlansMod", this);
        registerPacket(PacketAAGunAngles.class);
        registerPacket(PacketBreakSound.class);
        registerPacket(PacketCraftDriveable.class);
        registerPacket(PacketDriveableControl.class);
        registerPacket(PacketDriveableDamage.class);
        registerPacket(PacketDriveableGUI.class);
        registerPacket(PacketDriveableKey.class);
        registerPacket(PacketDriveableKeyHeld.class);
        registerPacket(PacketFlak.class);
        registerPacket(PacketGunFire.class);
        registerPacket(PacketGunPaint.class);
        registerPacket(PacketMechaControl.class);
        registerPacket(PacketMGFire.class);
        registerPacket(PacketMGMount.class);
        registerPacket(PacketOffHandGunInfo.class);
        registerPacket(PacketPlaneControl.class);
        registerPacket(PacketPlaySound.class);
        registerPacket(PacketReload.class);
        registerPacket(PacketRepairDriveable.class);
        registerPacket(PacketSeatUpdates.class);
        registerPacket(PacketSelectOffHandGun.class);
        registerPacket(PacketVehicleControl.class);
    }

    public void postInitialise()
    {
        if(modInitialised)
            return;

        modInitialised = true;
        Collections.sort(packets, new Comparator<Class<? extends PacketBase>>()
        {
            @Override
            public int compare(Class<? extends PacketBase> c1, Class<? extends PacketBase> c2)
            {
                int com = String.CASE_INSENSITIVE_ORDER.compare(c1.getCanonicalName(), c2.getCanonicalName());
                if(com == 0)
                    com = c1.getCanonicalName().compareTo(c2.getCanonicalName());
                return com;
            }
        });
    }

    private EntityPlayer getClientPlayer()
    {
        return null;
    }

    public void sendToAll(PacketBase packet)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public void sendTo(PacketBase packet, EntityPlayerMP player)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public void sendToAllAround(PacketBase packet, NetworkRegistry.TargetPoint point)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public void sendToDimension(PacketBase packet, int dimensionID)
    {
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionID);
        channels.get(Side.SERVER).writeAndFlush(packet);
    }

    public void sendToServer(PacketBase packet)
    {
        channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        channels.get(Side.CLIENT).writeAndFlush(packet);
    }

    public void sendToAll(Packet packet)
    {
        MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
    }

    public void sendTo(Packet packet, EntityPlayerMP player)
    {
        player.playerNetServerHandler.sendPacket(packet);
    }

    public void sendToAllAround(Packet packet, NetworkRegistry.TargetPoint point)
    {
        MinecraftServer.getServer().getConfigurationManager().sendToAllNear(point.x, point.y, point.z, point.range, point.dimension, packet);
    }

    public void sendToDimension(Packet packet, int dimensionID)
    {
        MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayersInDimension(packet, dimensionID);
    }

    public void sendToAllAround(PacketBase packet, double x, double y, double z, float range, int dimension)
    {
        sendToAllAround(packet, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }
}
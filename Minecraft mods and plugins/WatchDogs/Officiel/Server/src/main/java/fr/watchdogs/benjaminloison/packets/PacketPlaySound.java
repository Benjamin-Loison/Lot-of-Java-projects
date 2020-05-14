package fr.watchdogs.benjaminloison.packets;

import java.util.Random;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketPlaySound extends PacketBase
{
    public static Random rand = new Random();
    public float posX, posY, posZ;
    public String sound;
    public boolean distort, silenced;

    public PacketPlaySound()
    {}

    public static void sendSoundPacket(double x, double y, double z, double range, int dimension, String s, boolean distort)
    {
        sendSoundPacket(x, y, z, range, dimension, s, distort, false);
    }

    public static void sendSoundPacket(double x, double y, double z, double range, int dimension, String s, boolean distort, boolean silenced)
    {
        WatchDogs.getPacketHandler().sendToAllAround(new PacketPlaySound(x, y, z, s, distort, silenced), x, y, z, (float)range, dimension);
    }

    public PacketPlaySound(double x, double y, double z, String s)
    {
        this(x, y, z, s, false);
    }

    public PacketPlaySound(double x, double y, double z, String s, boolean distort)
    {
        this(x, y, z, s, distort, false);
    }

    public PacketPlaySound(double x, double y, double z, String s, boolean distort, boolean silenced)
    {
        posX = (float)x;
        posY = (float)y;
        posZ = (float)z;
        sound = s;
        this.distort = distort;
        this.silenced = silenced;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeFloat(posX);
        data.writeFloat(posY);
        data.writeFloat(posZ);
        writeUTF(data, sound);
        data.writeBoolean(distort);
        data.writeBoolean(silenced);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        posX = data.readFloat();
        posY = data.readFloat();
        posZ = data.readFloat();
        sound = readUTF(data);
        distort = data.readBoolean();
        silenced = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}
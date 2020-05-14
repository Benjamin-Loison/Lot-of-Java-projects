package fr.watchdogs.benjaminloison.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketFlak extends PacketBase
{
    public double x, y, z;
    public int numParticles;
    public String particleType;

    public PacketFlak()
    {}

    public PacketFlak(double x1, double y1, double z1, int n, String s)
    {
        x = x1;
        y = y1;
        z = z1;
        numParticles = n;
        particleType = s;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeDouble(x);
        data.writeDouble(y);
        data.writeDouble(z);
        data.writeInt(numParticles);
        writeUTF(data, particleType);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        x = data.readDouble();
        y = data.readDouble();
        z = data.readDouble();
        numParticles = data.readInt();
        particleType = readUTF(data);
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}

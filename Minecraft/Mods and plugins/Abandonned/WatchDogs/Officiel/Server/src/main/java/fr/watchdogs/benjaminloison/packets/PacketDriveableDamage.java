package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.driveables.DriveablePart;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EnumDriveablePart;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDriveableDamage extends PacketBase
{
    public int entityId;
    public short[] health;
    public boolean[] onFire;

    public PacketDriveableDamage()
    {
        health = new short[EnumDriveablePart.values().length];
        onFire = new boolean[EnumDriveablePart.values().length];
    }

    public PacketDriveableDamage(EntityDriveable driveable)
    {
        entityId = driveable.getEntityId();
        health = new short[EnumDriveablePart.values().length];
        onFire = new boolean[EnumDriveablePart.values().length];
        for(int i = 0; i < EnumDriveablePart.values().length; i++)
        {
            DriveablePart part = driveable.getDriveableData().parts.get(EnumDriveablePart.values()[i]);
            health[i] = (short)part.health;
            onFire[i] = part.onFire;
        }
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(entityId);
        for(int i = 0; i < EnumDriveablePart.values().length; i++)
        {
            data.writeShort(health[i]);
            data.writeBoolean(onFire[i]);
        }
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        entityId = data.readInt();
        for(int i = 0; i < EnumDriveablePart.values().length; i++)
        {
            health[i] = data.readShort();
            onFire[i] = data.readBoolean();
        }
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}
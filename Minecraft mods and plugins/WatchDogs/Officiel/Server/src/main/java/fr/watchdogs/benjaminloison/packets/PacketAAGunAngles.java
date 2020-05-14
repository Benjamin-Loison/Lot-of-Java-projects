package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.guns.EntityAAGun;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketAAGunAngles extends PacketBase
{
    public int entityID;
    public float gunYaw, gunPitch;

    public PacketAAGunAngles()
    {}

    public PacketAAGunAngles(EntityAAGun entity)
    {
        entityID = entity.getEntityId();
        gunYaw = entity.gunYaw;
        gunPitch = entity.gunPitch;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(entityID);
        data.writeFloat(gunYaw);
        data.writeFloat(gunPitch);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        entityID = data.readInt();
        gunYaw = data.readFloat();
        gunPitch = data.readFloat();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}
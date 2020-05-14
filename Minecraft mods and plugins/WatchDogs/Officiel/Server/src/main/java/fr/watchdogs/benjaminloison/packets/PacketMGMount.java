package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.guns.EntityMG;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketMGMount extends PacketBase
{
    public int playerEntityId, mgEntityId;
    public boolean mounting;

    public PacketMGMount()
    {}

    public PacketMGMount(EntityPlayer player, EntityMG mg, boolean mounting)
    {
        playerEntityId = player.getEntityId();
        mgEntityId = mg.getEntityId();
        this.mounting = mounting;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(playerEntityId);
        data.writeInt(mgEntityId);
        data.writeBoolean(mounting);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        playerEntityId = data.readInt();
        mgEntityId = data.readInt();
        mounting = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}
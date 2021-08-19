package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.guns.EntityAAGun;
import fr.watchdogs.benjaminloison.common.guns.EntityMG;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketMGFire extends PacketBase
{
    public boolean held;

    public PacketMGFire()
    {}

    public PacketMGFire(boolean h)
    {
        held = h;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeBoolean(held);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        held = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        EntityMG mg = PlayerHandler.getPlayerData(playerEntity).mountingGun;
        if(mg != null)
            mg.mouseHeld(held);
        else if(playerEntity.ridingEntity instanceof EntityAAGun)
            ((EntityAAGun)playerEntity.ridingEntity).setMouseHeld(held);
    }
}
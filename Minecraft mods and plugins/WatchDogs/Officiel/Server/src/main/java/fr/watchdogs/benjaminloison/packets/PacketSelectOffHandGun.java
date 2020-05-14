package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.relauncher.Side;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketSelectOffHandGun extends PacketBase
{
    public int slot, entityID;

    public PacketSelectOffHandGun()
    {}

    public PacketSelectOffHandGun(int i)
    {
        slot = i;
    }

    public PacketSelectOffHandGun(EntityPlayer player, int i)
    {
        entityID = player.getEntityId();
        slot = i;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeByte(slot);
        data.writeInt(entityID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        slot = data.readByte();
        entityID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        PlayerHandler.getPlayerData(playerEntity, Side.SERVER).offHandGunSlot = slot;
        WatchDogs.getPacketHandler().sendToAllAround(new PacketOffHandGunInfo(playerEntity, slot), playerEntity.posX, playerEntity.posY, playerEntity.posZ, 50F, playerEntity.dimension);
    }
}
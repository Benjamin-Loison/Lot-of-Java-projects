package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDriveableGUI extends PacketBase
{
    public int guiID;

    public PacketDriveableGUI()
    {}

    public PacketDriveableGUI(int i)
    {
        guiID = i;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(guiID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        guiID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        if(playerEntity.ridingEntity != null && playerEntity.ridingEntity instanceof EntitySeat)
        {
            EntityDriveable d = ((EntitySeat)playerEntity.ridingEntity).driveable;
            switch(guiID)
            {
                case 0:
                    playerEntity.openGui(WatchDogs.instance, 6, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                case 1:
                    playerEntity.openGui(WatchDogs.instance, 7, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                case 2:
                    playerEntity.openGui(WatchDogs.instance, 8, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                case 3:
                    playerEntity.openGui(WatchDogs.instance, 9, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                case 4:
                    playerEntity.openGui(WatchDogs.instance, 10, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                case 5:
                    playerEntity.openGui(WatchDogs.instance, 12, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
            }
        }
    }
}
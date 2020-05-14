package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntityPlane;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketPlaneControl extends PacketDriveableControl
{
    public boolean gear, doors, wings, rideable;
    public String players[] = {"", "", "", "", "", "", ""};

    public PacketPlaneControl()
    {}

    public PacketPlaneControl(EntityDriveable driveable)
    {
        super(driveable);
        EntityPlane plane = (EntityPlane)driveable;
        gear = plane.varGear;
        doors = plane.varDoor;
        wings = plane.varWing;
        rideable = driveable.rideable;
        players[0] = driveable.owners[0];
        players[1] = driveable.owners[1];
        players[2] = driveable.owners[2];
        players[3] = driveable.owners[3];
        players[4] = driveable.owners[4];
        players[5] = driveable.owners[5];
        players[6] = driveable.owners[6];
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        super.encodeInto(ctx, data);
        data.writeBoolean(gear);
        data.writeBoolean(doors);
        data.writeBoolean(wings);
        data.writeBoolean(rideable);
        ByteBufUtils.writeUTF8String(data, players[0]);
        ByteBufUtils.writeUTF8String(data, players[1]);
        ByteBufUtils.writeUTF8String(data, players[2]);
        ByteBufUtils.writeUTF8String(data, players[3]);
        ByteBufUtils.writeUTF8String(data, players[4]);
        ByteBufUtils.writeUTF8String(data, players[5]);
        ByteBufUtils.writeUTF8String(data, players[6]);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        super.decodeInto(ctx, data);
        gear = data.readBoolean();
        doors = data.readBoolean();
        wings = data.readBoolean();
        rideable = data.readBoolean();
        players[0] = ByteBufUtils.readUTF8String(data);
        players[1] = ByteBufUtils.readUTF8String(data);
        players[2] = ByteBufUtils.readUTF8String(data);
        players[3] = ByteBufUtils.readUTF8String(data);
        players[4] = ByteBufUtils.readUTF8String(data);
        players[5] = ByteBufUtils.readUTF8String(data);
        players[6] = ByteBufUtils.readUTF8String(data);
    }

    @Override
    protected void updateDriveable(EntityDriveable driveable, boolean clientSide)
    {
        super.updateDriveable(driveable, clientSide);
        EntityPlane plane = (EntityPlane)driveable;
        plane.varGear = gear;
        plane.varDoor = doors;
        plane.varWing = wings;
        driveable.rideable = rideable;
        driveable.owners[0] = players[0];
        driveable.owners[1] = players[1];
        driveable.owners[2] = players[2];
        driveable.owners[3] = players[3];
        driveable.owners[4] = players[4];
        driveable.owners[5] = players[5];
        driveable.owners[6] = players[6];
    }
}
package fr.watchdogs.benjaminloison.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketBreakSound extends PacketBase
{
    public int x, y, z, blockID;

    public PacketBreakSound()
    {}

    public PacketBreakSound(int x, int y, int z, Block block)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        blockID = Block.getIdFromBlock(block);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeInt(blockID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        blockID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}
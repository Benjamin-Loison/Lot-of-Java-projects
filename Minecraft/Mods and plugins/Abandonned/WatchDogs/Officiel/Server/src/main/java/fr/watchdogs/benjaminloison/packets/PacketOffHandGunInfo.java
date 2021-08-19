package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PacketOffHandGunInfo extends PacketBase
{
    public int entityID;
    public ItemStack gunStack;

    public PacketOffHandGunInfo()
    {}

    public PacketOffHandGunInfo(EntityPlayerMP playerEntity, int slot)
    {
        entityID = playerEntity.getEntityId();
        if(slot == 0)
            gunStack = null;
        else
            gunStack = playerEntity.inventory.getStackInSlot(slot - 1);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeInt(entityID);
        ByteBufUtils.writeItemStack(data, gunStack);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        entityID = data.readInt();
        gunStack = ByteBufUtils.readItemStack(data);
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {}
}
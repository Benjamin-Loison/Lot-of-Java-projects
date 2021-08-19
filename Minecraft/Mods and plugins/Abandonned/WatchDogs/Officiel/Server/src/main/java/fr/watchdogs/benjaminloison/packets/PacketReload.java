package fr.watchdogs.benjaminloison.packets;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.ItemGun;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PacketReload extends PacketBase
{
    public boolean left;

    public PacketReload()
    {}

    public PacketReload(boolean l)
    {
        left = l;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        data.writeBoolean(left);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        left = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity)
    {
        PlayerData data = PlayerHandler.getPlayerData(playerEntity);
        ItemStack stack = playerEntity.getCurrentEquippedItem();
        if(left && data.offHandGunSlot != 0)
            stack = playerEntity.inventory.getStackInSlot(data.offHandGunSlot - 1);
        if(data != null && stack != null && stack.getItem() instanceof ItemGun)
        {
            GunType type = ((ItemGun)stack.getItem()).type;
            if(((ItemGun)stack.getItem()).reload(stack, type, playerEntity.worldObj, playerEntity, true, left))
            {
                data.shootTimeRight = data.shootTimeLeft = type.reloadTime;
                if(left)
                    data.reloadingLeft = true;
                else
                    data.reloadingRight = true;
                WatchDogs.getPacketHandler().sendTo(new PacketReload(left), playerEntity);
                if(type.reloadSound != null)
                    PacketPlaySound.sendSoundPacket(playerEntity.posX, playerEntity.posY, playerEntity.posZ, WatchDogs.soundRange, playerEntity.dimension, type.reloadSound, false);
            }
        }
    }
}
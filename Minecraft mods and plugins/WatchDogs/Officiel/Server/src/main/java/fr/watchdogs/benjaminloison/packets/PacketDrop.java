package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketDrop implements IMessage
{
    static int item, amount;
    static boolean isArmor;

    public PacketDrop()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        item = buf.readInt();
        amount = buf.readInt();
        isArmor = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketDrop, IMessage>
    {
        @Override
        public IMessage onMessage(PacketDrop message, MessageContext ctx)
        {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack itemStack = player.inventory.mainInventory[item];
            EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY + 2, player.posZ);
            if(!isArmor)
                if(amount != 0)
                    if(itemStack.stackSize - amount != 0)
                    {
                        drop.dropItem(itemStack.getItem(), amount);
                        player.inventory.mainInventory[item].splitStack(amount);
                    }
                    else
                    {
                        drop.dropItem(itemStack.getItem(), amount);
                        player.inventory.mainInventory[item] = null;
                    }
                else
                {
                    drop.dropItem(itemStack.getItem(), itemStack.stackSize);
                    player.inventory.mainInventory[item] = null;
                }
            else
            {
                player.dropItem(player.inventory.armorInventory[item - 9].getItem(), 1);
                player.inventory.armorInventory[item - 9] = null;
            }
            return null;
        }
    }
}
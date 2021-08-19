package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketDeleteItemStack implements IMessage
{
    static int item, amount;
    static boolean isArmor;

    public PacketDeleteItemStack()
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

    public static class Handler implements IMessageHandler<PacketDeleteItemStack, IMessage>
    {
        @Override
        public IMessage onMessage(PacketDeleteItemStack message, MessageContext ctx)
        {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            ItemStack itemStack = player.inventory.mainInventory[item];
            if(!isArmor)
                if(amount != 0)
                    if(itemStack.stackSize - amount != 0)
                        itemStack.splitStack(amount);
                    else
                        itemStack = null;
                else
                    itemStack = null;
            else
                player.inventory.armorInventory[item - 9] = null;
            return null;
        }
    }
}
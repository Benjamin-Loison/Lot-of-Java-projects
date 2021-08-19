package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class PacketGiveItemStack implements IMessage
{
    static int item, amount;
    static String name = "";
    static boolean isArmor;

    public PacketGiveItemStack()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        item = buf.readInt();
        amount = buf.readInt();
        name = ByteBufUtils.readUTF8String(buf);
        isArmor = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketGiveItemStack, IMessage>
    {
        @Override
        public IMessage onMessage(PacketGiveItemStack message, MessageContext ctx)
        {
            // TODO: Don ItemStack pas que quand null (cas ou give oeuf 1 et receveur full oeuf mais dernier stack 15, on doit pouvoir donner)
            EntityPlayer giver = ctx.getServerHandler().playerEntity;
            if(giver.worldObj.getPlayerEntityByName(name) instanceof EntityPlayer)
            {
                EntityPlayer receiver = (EntityPlayer)giver.worldObj.getPlayerEntityByName(name);
                if(receiver.getDistanceToEntity(giver) < 25)
                {
                    boolean isEmpty = false;
                    if(!isArmor)
                    {
                        for(int x = 0; x < 9; x++)
                            if(receiver.inventory.mainInventory[x] == null)
                                isEmpty = true;
                        if(isEmpty)
                        {
                            if(amount != 0)
                                if(giver.inventory.mainInventory[item].stackSize - amount != 0)
                                {
                                    giver.inventory.mainInventory[item].splitStack(amount);
                                    receiver.addChatMessage(new ChatComponentText(new ChatComponentTranslation("you.have.received") + "" + amount + new ChatComponentTranslation("number.amount") + giver.inventory.mainInventory[item].getDisplayName() + new ChatComponentTranslation("from") + giver.getDisplayName()));
                                    receiver.inventory.addItemStackToInventory(new ItemStack(giver.inventory.mainInventory[item].getItem(), amount));
                                }
                                else
                                {
                                    receiver.inventory.addItemStackToInventory(giver.inventory.mainInventory[item]);
                                    receiver.addChatMessage(new ChatComponentText(new ChatComponentTranslation("you.have.received") + "" + giver.inventory.mainInventory[item].stackSize + new ChatComponentTranslation("number.amount") + giver.inventory.mainInventory[item].getDisplayName() + new ChatComponentTranslation("from") + giver.getDisplayName()));
                                    giver.inventory.mainInventory[item] = null;
                                }
                            else
                            {
                                receiver.inventory.addItemStackToInventory(giver.inventory.mainInventory[item]);
                                receiver.addChatMessage(new ChatComponentText(new ChatComponentTranslation("you.have.received") + "" + giver.inventory.mainInventory[item].stackSize + new ChatComponentTranslation("number.amount") + giver.inventory.mainInventory[item].getDisplayName() + new ChatComponentTranslation("from") + giver.getDisplayName()));
                                giver.inventory.mainInventory[item] = null;
                            }
                        }
                        else
                            giver.addChatMessage(new ChatComponentTranslation("the.player.can.not.take.this"));
                    }
                    else
                    {
                        if(receiver.inventory.armorInventory[item - 9] == null)
                        {
                            receiver.inventory.armorInventory[item - 9] = giver.inventory.armorInventory[item - 9];
                            receiver.addChatMessage(new ChatComponentText(new ChatComponentTranslation("you.have.received") + giver.inventory.armorInventory[item - 9].getDisplayName() + new ChatComponentTranslation("from") + giver.getDisplayName()));
                            giver.inventory.armorInventory[item - 9] = null;
                        }
                        else
                            giver.addChatMessage(new ChatComponentTranslation("the.player.can.not.take.this"));
                    }
                }
                else
                    giver.addChatMessage(new ChatComponentTranslation("too.far"));
            }
            else
                giver.addChatMessage(new ChatComponentTranslation("name.invalid"));
            return null;
        }
    }
}
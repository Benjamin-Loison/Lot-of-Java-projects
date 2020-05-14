package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketTransfer implements IMessage
{
    static String name;
    static long amount;
    static boolean cash, antiCheat, isDeposit;

    public PacketTransfer()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        amount = buf.readLong();
        name = ByteBufUtils.readUTF8String(buf);
        cash = buf.readBoolean();
        antiCheat = buf.readBoolean();
        isDeposit = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketTransfer, IMessage>
    {
        @Override
        public IMessage onMessage(PacketTransfer message, MessageContext ctx)
        {
            EntityPlayerMP sender = ctx.getServerHandler().playerEntity, receiver = (EntityPlayerMP)sender.worldObj.getPlayerEntityByName(name);
            if(amount <= 0)
                return null;
            EEP pSend = EEP.get(sender), pRecei = null;
            if(receiver != null)
                pRecei = EEP.get(receiver);
            if(antiCheat)
                if(isDeposit)
                {
                    pSend.delCashAddMoney(amount);
                    AltisCraft.instance.network.sendTo(new PacketMessage("you.have.deposited.money", FileAPI.number(amount)), sender);
                }
                else
                {
                    pSend.delMoneyAddCash(amount);
                    AltisCraft.instance.network.sendTo(new PacketMessage("you.have.taken.money", FileAPI.number(amount)), sender);
                }
            else
            {
                if(receiver == null)
                    return null;
                if(cash)
                {
                    if(!pSend.sufficientCash(amount))
                        return null;
                    pSend.delCash(amount);
                    pRecei.addCash(amount);
                }
                else
                {
                    if(!pSend.sufficientMoney(amount))
                        return null;
                    pSend.delMoney(amount);
                    pRecei.addMoney(amount);
                }
                AltisCraft.instance.network.sendTo(new PacketMessage("money.transfered"), sender);
                AltisCraft.instance.network.sendTo(new PacketMessage("money.transfering"), receiver);
            }
            return null;
        }
    }
}
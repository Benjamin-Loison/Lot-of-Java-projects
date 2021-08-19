package fr.altiscraft.benjaminloison.packets;

import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PacketInterpol implements IMessage
{
    static String name, reason;

    public PacketInterpol()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
        reason = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketInterpol, IMessage>
    {
        @Override
        public IMessage onMessage(PacketInterpol message, MessageContext ctx)
        {
            EntityPlayerMP thePlayer = ctx.getServerHandler().playerEntity;
            EntityPlayer namePlayer = thePlayer.worldObj.getPlayerEntityByName(name);
            if(reason.equals(""))
                if(!name.equals("") && namePlayer instanceof EntityPlayer)
                    EEP.get(((EntityPlayer)namePlayer)).resetInterpol();
                else
                {
                    int research = 0, amount = 0, researchAmounts[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    String reason = "", researchesReasons[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
                            researches[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
                    for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                    {
                        EEP props = EEP.get(player);
                        if(props.moneyInterpol[0] != 0)
                        {
                            researches[research] = player.getDisplayName();
                            for(int i = 0; i < props.moneyInterpol.length; i++)
                                amount += props.moneyInterpol[i];
                            researchAmounts[research] = amount;
                            for(int i = 0; i < props.reasonInterpol.length; i++)
                                if(i == 0 && !props.reasonInterpol[i].equals(""))
                                    reason = reason + props.reasonInterpol[i];
                                else if(!props.reasonInterpol[i].equals(""))
                                    reason = reason + " " + props.reasonInterpol[i];
                            researchesReasons[research] = reason;
                            research++;
                        }
                    }
                    if(research == 0)
                        researches[0] = FileAPI.config("nobody");
                    AltisCraft.instance.network.sendTo(new PacketRenderInterpol(researches, researchAmounts, researchesReasons), thePlayer);
                }
            else
            {
                EEP props = EEP.get(thePlayer);
                long amount = props.allMoneyInterpol();
                props.delCash(amount);
                EEP.get(namePlayer).addCash(amount);
                props.resetInterpol();
                AltisCraft.instance.network.sendToServer(new PacketSendMessage("police", "message.police.fine.paid", thePlayer.getDisplayName(), amount, name, reason));
            }
            return null;
        }
    }
}

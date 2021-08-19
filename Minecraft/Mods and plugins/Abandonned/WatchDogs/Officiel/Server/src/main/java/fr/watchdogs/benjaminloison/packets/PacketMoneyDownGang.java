package fr.altiscraft.benjaminloison.packets;

import java.io.File;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;

public class PacketMoneyDownGang implements IMessage
{
    static int x, y, z;
    static String gang;
    static long amount;

    public PacketMoneyDownGang()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        amount = buf.readLong();
        gang = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketMoneyDownGang, IMessage>
    {
        @Override
        public IMessage onMessage(PacketMoneyDownGang message, MessageContext ctx)
        {
            try
            {
                EntityPlayerMP player = ctx.getServerHandler().playerEntity;
                EEP props = EEP.get(player);
                if(!props.sufficientCash(amount) || player.getDistance(x, y, z) > FileAPI.configNumber("range.security"))
                    return null;
                File gang = new File(FileAPI.gang + message.gang + ".txt"), tempGang = new File(FileAPI.gang + gang + ".temp"), atm = new File(FileAPI.atm + x + "," + y + "," + z + ".txt");
                String actualGangMoney = FileAPI.read(gang, 2);
                if(!StringUtils.isNumeric(actualGangMoney))
                {
                    player.addChatMessage(new ChatComponentTranslation("error.second.lign.is.not.numeric.report.this"));
                    return null;
                }
                Scanner scan = new Scanner(gang);
                String moneyToPut = "" + Long.parseLong(actualGangMoney) + amount;
                FileAPI.create(scan.nextLine() + "\n", tempGang);
                scan.nextLine();
                if(scan.hasNextLine())
                    FileAPI.addLign(moneyToPut + "\n", tempGang);
                else
                    FileAPI.addLign(moneyToPut, tempGang);
                while(scan.hasNextLine())
                    FileAPI.addLign(scan.nextLine(), tempGang);
                scan.close();
                gang.delete();
                tempGang.renameTo(gang);
                long atmMoney = Long.parseLong(FileAPI.read(atm));
                if(atmMoney < 0)
                {
                    player.addChatMessage(new ChatComponentTranslation("it.is.not.possible.to.depose.money.here"));
                    return null;
                }
                FileAPI.create(atmMoney + amount, atm);
                props.delMoney(amount);
                player.addChatMessage(new ChatComponentTranslation("money.transfered"));
            }
            catch(Exception e)
            {}
            return null;
        }
    }
}
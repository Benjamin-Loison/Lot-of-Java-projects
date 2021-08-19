package fr.watchdogs.benjaminloison.packets;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketCreateGang implements IMessage
{
    static String name;
    static boolean create, alone;

    public PacketCreateGang()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
        create = buf.readBoolean();
        alone = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketCreateGang, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCreateGang message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            EEP props = EEP.get(player);
            if(create)
            {
                File testFile = new File(FileAPI.gang + name + ".txt");
                if(!testFile.exists())
                {
                    FileAPI.create(player.getDisplayName() + "\n" + 0, testFile);
                    props.set("gang", name);
                    props.delMoney(FileAPI.configNumber("money.to.create.a.gang"));
                    WatchDogs.instance.network.sendTo(new PacketMessage("you.have.lost", FileAPI.number(FileAPI.configNumber("money.to.create.a.gang"))), player);
                }
                else
                    WatchDogs.instance.network.sendTo(new PacketMessage("this.gang.already.exist"), player);
            }
            else if(alone)
                try
                {
                    File gang = new File(FileAPI.gang + props.gang + ".txt"), tempGang = new File(FileAPI.gang + props.gang + ".temp");
                    gang.renameTo(tempGang);
                    Scanner scan = new Scanner(tempGang), tempScan = new Scanner(tempGang);
                    FileWriter fw = new FileWriter(gang);
                    String streama = tempScan.nextLine();
                    while(scan.hasNextLine())
                    {
                        String stream = scan.nextLine();
                        if(tempScan.hasNextLine())
                            streama = tempScan.nextLine();
                        if(streama.equals(player.getDisplayName()))
                            streama = "NOTHING";
                        else
                            streama = "NOTHING";
                        if(!stream.equals(player.getDisplayName()))
                            fw.write(stream);
                        if(!streama.equals("NOTHING"))
                            fw.write("\n");
                    }
                    fw.close();
                    scan.close();
                    tempScan.close();
                    tempGang.delete();
                    props.set("gang", name);
                    WatchDogs.instance.network.sendTo(new PacketMessage("you.have.left.your.gang"), player);
                }
                catch(Exception e)
                {}
            else
            {
                new File(FileAPI.gang + name + ".txt").delete();
                props.set("gang", name);
                WatchDogs.instance.network.sendTo(new PacketMessage("you.have.deleted.your.gang"), player);
            }
            return null;
        }
    }
}
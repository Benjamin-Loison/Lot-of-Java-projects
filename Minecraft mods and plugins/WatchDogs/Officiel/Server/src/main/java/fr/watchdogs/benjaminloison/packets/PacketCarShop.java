package fr.watchdogs.benjaminloison.packets;

import java.io.File;
import java.util.Scanner;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.CarShopAPI;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.VehicleAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketCarShop implements IMessage
{
    static String name;
    static int x, y, z, direction;

    public PacketCarShop()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        direction = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketCarShop, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCarShop message, MessageContext ctx)
        {
            try
            {
                EntityPlayerMP player = ctx.getServerHandler().playerEntity;
                File file = new File(FileAPI.carShop + name + ".txt");
                if(file.exists())
                {
                    VehicleAPI[] vehicles = new VehicleAPI[100];
                    int line = 0;
                    Scanner scan = new Scanner(file);
                    while(scan.hasNextLine())
                    {
                        String[] parts = scan.nextLine().split(" ");
                        if(parts.length >= 1)
                        {
                            vehicles[line] = new VehicleAPI(parts[0], Integer.parseInt(parts[1]));
                            line++;
                        }
                    }
                    scan.close();
                    WatchDogs.instance.network.sendTo(new PacketRenderCarShop(new CarShopAPI(vehicles, name, x, y, z, direction)), player);
                }
                else
                    WatchDogs.instance.network.sendTo(new PacketMessage("file.txt.car.shop.not.found", FileAPI.carShop + name + ".txt"), player);
            }
            catch(Exception e)
            {}
            return null;
        }
    }
}
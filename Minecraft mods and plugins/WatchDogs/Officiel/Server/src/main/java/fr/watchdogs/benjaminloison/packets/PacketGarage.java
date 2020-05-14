package fr.watchdogs.benjaminloison.packets;

import java.io.File;
import java.util.Scanner;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.CarShopAPI;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.VehicleAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketGarage implements IMessage
{
    static int x, y, z, direction;
    static boolean air;

    public PacketGarage()
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
        direction = buf.readInt();
        air = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketGarage, IMessage>
    {
        @Override
        public IMessage onMessage(PacketGarage message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            File file;
            if(air)
                file = new File(FileAPI.airGarage + player.getDisplayName() + ".txt");
            else
                file = new File(FileAPI.landGarage + player.getDisplayName() + ".txt");
            if(file.exists())
            {
                VehicleAPI[] vehicles = new VehicleAPI[48];
                int line = 0;
                try
                {
                    Scanner scan = new Scanner(file);
                    while(scan.hasNextLine())
                    {
                        vehicles[line] = new VehicleAPI(scan.nextLine());
                        line++;
                    }
                    scan.close();
                }
                catch(Exception e)
                {}
                if(line != 0)
                    if(air)
                        WatchDogs.instance.network.sendTo(new PacketRenderCarShop(new CarShopAPI(vehicles, x, y, z, direction, true)), player);
                    else
                        WatchDogs.instance.network.sendTo(new PacketRenderCarShop(new CarShopAPI(vehicles, x, y, z, direction)), player);
                else if(air)
                    WatchDogs.instance.network.sendTo(new PacketMessage("your.air.garage.is.empty"), player);
                else
                    WatchDogs.instance.network.sendTo(new PacketMessage("your.land.garage.is.empty"), player);
            }
            else
                try
                {
                    FileAPI.create(file);
                    if(air)
                        WatchDogs.instance.network.sendTo(new PacketMessage("your.air.garage.is.empty"), player);
                    else
                        WatchDogs.instance.network.sendTo(new PacketMessage("your.land.garage.is.empty"), player);
                }
                catch(Exception e)
                {}
            return null;
        }
    }
}
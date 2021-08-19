package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.CarShopAPI;
import fr.watchdogs.benjaminloison.api.VehicleAPI;
import io.netty.buffer.ByteBuf;

public class PacketRenderCarShop implements IMessage
{
    CarShopAPI conces;
    String name, nameVehicles[];
    int x, y, z, direction, numberVehicles, priceVehicles[];
    VehicleAPI[] vehicles;
    boolean air, isGarage;

    public PacketRenderCarShop(CarShopAPI carShop)
    {
        isGarage = carShop.isGarage();
        air = carShop.getAir();
        conces = carShop;
        name = carShop.getName();
        x = carShop.getX();
        y = carShop.getY();
        z = carShop.getZ();
        direction = carShop.getDirection();
        numberVehicles = carShop.getNumberVehicles();
        vehicles = new VehicleAPI[carShop.getNumberVehicles()];
        vehicles = carShop.getVehicles();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(isGarage);
        buf.writeBoolean(air);
        nameVehicles = new String[numberVehicles];
        priceVehicles = new int[numberVehicles];
        for(int i = 0; i < numberVehicles; i++)
        {
            nameVehicles[i] = vehicles[i].getName();
            priceVehicles[i] = vehicles[i].getPrice();
        }
        buf.writeInt(numberVehicles);
        for(int i = 0; i < numberVehicles; i++)
            ByteBufUtils.writeUTF8String(buf, nameVehicles[i]);
        for(int i = 0; i < numberVehicles; i++)
            buf.writeInt(priceVehicles[i]);
        ByteBufUtils.writeUTF8String(buf, name);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(direction);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketRenderCarShop, IMessage>
    {
        @Override
        public IMessage onMessage(PacketRenderCarShop message, MessageContext ctx)
        {
            return null;
        }
    }
}
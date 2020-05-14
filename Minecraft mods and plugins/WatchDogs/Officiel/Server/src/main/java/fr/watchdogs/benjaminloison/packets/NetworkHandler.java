package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler
{
    public static SimpleNetworkWrapper networkWrapper;
    private int registerID = -1;

    public NetworkHandler()
    {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("thirstmod");
        registerPacket(PacketUpdateClient0.class, PacketUpdateClient0.Handler.class, Side.CLIENT);
        registerPacket(PacketMovement.class, PacketMovement.Handler.class, Side.SERVER);
    }

    private void registerPacket(Class<? extends IMessage> c1, Class c2, Side side)
    {
        registerID++;
        networkWrapper.registerMessage(c2, c1, registerID, side);
    }
}

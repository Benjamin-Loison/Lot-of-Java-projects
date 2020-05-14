package fr.watchdogs.benjaminloison.api;

import java.util.List;

import fr.watchdogs.benjaminloison.entity.EEP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class PresenceAPI
{
    public static boolean main(long number, String grade)
    {
        long amount = 0;
        List<EntityPlayerMP> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
        for(EntityPlayerMP player : players)
            if(EEP.get(player).get(grade))
                amount++;
        if(amount >= number)
            return true;
        return false;
    }

}

package fr.watchdogs.benjaminloison.api;

import java.util.List;
import java.util.UUID;

import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class DebugEntityAPI
{
    public static boolean isEntity(Object object)
    {
        if(object instanceof EntitySeat || object instanceof EntityDriveable)
            return true;
        return false;
    }

    public static EntityDriveable getDriveable(Object object)
    {
        if(object instanceof EntitySeat)
            return ((EntitySeat)object).driveable;
        else if(object instanceof EntityDriveable)
            return (EntityDriveable)object;
        else
            return null;
    }

    public static boolean isPlayer(String uuid)
    {
        for(EntityPlayer playerF : (List<EntityPlayer>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
            if(playerF.getUniqueID().equals(UUID.fromString(uuid)))
                return true;
        return false;
    }

    public static EntityPlayer getPlayer(String uuid)
    {
        for(EntityPlayer player : (List<EntityPlayer>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
            if(player.getUniqueID().equals(UUID.fromString(uuid)))
                return player;
        return null;
    }
}
package fr.altiscraft.benjaminloison.packets;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.watchdogs.benjaminloison.api.DebugEntityAPI;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.entity.EntityCustomizableNPC;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class PacketPound implements IMessage
{
    static int id;
    static boolean isNpc;

    public PacketPound()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        id = buf.readInt();
        isNpc = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<PacketPound, IMessage>
    {
        @Override
        public IMessage onMessage(PacketPound message, MessageContext ctx)
        {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            World world = player.worldObj;
            EEP props = EEP.get(player);
            if(!isNpc)
            {
                if(!(props.staff || props.police || props.repairman))
                    return null;
                EntityDriveable driveable;
                if(DebugEntityAPI.isEntity(world.getEntityByID(id)))
                    driveable = DebugEntityAPI.getDriveable(world.getEntityByID(id));
                else
                    return null;
                setInGarage(driveable);
                if(StringUtils.isNumeric(new ChatComponentTranslation("money.for.pound").getUnformattedText()))
                {
                    EEP.get(player).addCash(Integer.parseInt(new ChatComponentTranslation("money.for.pound").getUnformattedText()));
                    player.addChatMessage(new ChatComponentTranslation("you.received.money.for.pound"));
                }
            }
            else
            {
                Entity npc = world.getEntityByID(id);
                String action;
                if(npc instanceof EntityCustomizableNPC)
                    action = ((EntityCustomizableNPC)npc).getDataWatcher().getWatchableObjectString(21);
                else
                    return null;
                boolean found = false;
                for(Entity entity : (List<Entity>)world.loadedEntityList)
                {
                    if(found)
                        break;
                    if(entity instanceof EntityDriveable)
                        if(entity.getDistanceToEntity(player) < FileAPI.configNumber("range.for.pound"))
                        {
                            EntityDriveable driveable = (EntityDriveable)entity;
                            for(int i = 0; i < driveable.owners.length; i++)
                                if(driveable.owners[i].equals(player.getDisplayName()))
                                {
                                    if((driveable.landVehicle() && action.equals(FileAPI.config("garage.air")) || (!driveable.landVehicle() && action.equals(FileAPI.config("garage.land")))))
                                    {
                                        player.addChatMessage(new ChatComponentTranslation("it.is.not.the.right.garage"));
                                        return null;
                                    }
                                    found = true;
                                    setInGarage(driveable);
                                    player.addChatMessage(new ChatComponentTranslation("vehicle.set.in.garage"));
                                    break;
                                }
                        }
                }
                if(!found)
                    player.addChatMessage(new ChatComponentTranslation("vehicle.not.found"));
            }
            return null;
        }

        void setInGarage(EntityDriveable driveable)
        {
            if(driveable.landVehicle())
                FileAPI.addLign(driveable.getDriveableType().shortName, new File(FileAPI.landGarage + driveable.owners[0] + ".txt"));
            else
                FileAPI.addLign(driveable.getDriveableType().shortName, new File(FileAPI.airGarage + driveable.owners[0] + ".txt"));
            driveable.setDead();
        }
    }
}
package fr.altiscraft.benjaminloison.packets;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.driveables.DriveableData;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntityPlane;
import fr.watchdogs.benjaminloison.driveables.EntityVehicle;
import fr.watchdogs.benjaminloison.driveables.EnumDriveablePart;
import fr.watchdogs.benjaminloison.driveables.EnumType;
import fr.watchdogs.benjaminloison.driveables.PartType;
import fr.watchdogs.benjaminloison.driveables.PlaneType;
import fr.watchdogs.benjaminloison.driveables.VehicleType;
import fr.watchdogs.benjaminloison.entity.EEP;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class PacketVehicle implements IMessage
{
    static int x, y, z, direction, number;
    static String vehicleName, nameGarage;
    static boolean isGarage, isAir;

    public PacketVehicle()
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
        number = buf.readInt();
        vehicleName = ByteBufUtils.readUTF8String(buf);
        isGarage = buf.readBoolean();
        isAir = buf.readBoolean();
        nameGarage = ByteBufUtils.readUTF8String(buf);
    }

    private static NBTTagCompound v(ItemStack itemStack, World world)
    {
        if(itemStack.stackTagCompound == null)
            itemStack.stackTagCompound = tagVehicle(itemStack, world);
        if(itemStack.stackTagCompound == null)
            itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setString("Type", VehicleType.getVehicle(vehicleName).shortName);
        itemStack.stackTagCompound.setString("Engine", PartType.defaultEngines.get(EnumType.vehicle).shortName);
        return itemStack.stackTagCompound;
    }

    private static NBTTagCompound tagVehicle(ItemStack itemStack, World world)
    {
        try
        {
            FileInputStream file = new FileInputStream(world.getSaveHandler().getMapFileFromName("vehicle_" + itemStack.getItemDamage()));
            NBTTagCompound tagCompound = CompressedStreamTools.readCompressed(file).getCompoundTag("data");
            for(EnumDriveablePart part : EnumDriveablePart.values())
            {
                tagCompound.setInteger(part.getShortName() + "_Health", VehicleType.getVehicle(vehicleName).health.get(part) == null ? 0 : VehicleType.getVehicle(vehicleName).health.get(part).health);
                tagCompound.setBoolean(part.getShortName() + "_Fire", false);
            }
            file.close();
            return tagCompound;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private static NBTTagCompound p(ItemStack itemStack, World world)
    {
        if(itemStack.stackTagCompound == null)
        {
            if(itemStack.getItemDamage() != 0)
                itemStack.stackTagCompound = tagPlane(itemStack, world);
            if(itemStack.stackTagCompound == null)
            {
                itemStack.stackTagCompound = new NBTTagCompound();
                itemStack.stackTagCompound.setString("Type", PlaneType.getPlane(vehicleName).shortName);
                itemStack.stackTagCompound.setString("Engine", PartType.defaultEngines.get(EnumType.plane).shortName);
            }
        }
        return itemStack.stackTagCompound;
    }

    private static NBTTagCompound tagPlane(ItemStack itemStack, World world)
    {
        try
        {
            File file = world.getSaveHandler().getMapFileFromName("plane_" + itemStack.getItemDamage());
            if(file != null && file.exists())
            {
                FileInputStream inputStream = new FileInputStream(file);
                NBTTagCompound tagCompound = CompressedStreamTools.readCompressed(inputStream).getCompoundTag("data");
                for(EnumDriveablePart part : EnumDriveablePart.values())
                {
                    tagCompound.setInteger(part.getShortName() + "_Health", PlaneType.getPlane(vehicleName).health.get(part) == null ? 0 : PlaneType.getPlane(vehicleName).health.get(part).health);
                    tagCompound.setBoolean(part.getShortName() + "_Fire", false);
                }
                inputStream.close();
                return tagCompound;
            }
        }
        catch(Exception e)
        {}
        return null;
    }

    public static DriveableData getDataVehicle(ItemStack itemStack, World world)
    {
        return new DriveableData(v(itemStack, world));
    }

    public static DriveableData getDataPlane(ItemStack itemStack, World world)
    {
        return new DriveableData(p(itemStack, world));
    }

    public static class Handler implements IMessageHandler<PacketVehicle, IMessage>
    {
        @Override
        public IMessage onMessage(PacketVehicle msg, MessageContext ctx)
        {
            World world = ctx.getServerHandler().playerEntity.worldObj;
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            EEP props = EEP.get(player);
            List<Entity> allEntities = world.loadedEntityList;
            boolean vehicleInArea = false;
            String[] informations = FileAPI.read(new File(FileAPI.carShop + nameGarage + ".txt"), number).split(" ");
            int price = 0;
            if(informations.length == 2)
                price = Integer.parseInt(informations[1]);
            if(VehicleType.getVehicle(vehicleName) != null)
            {
                EntityVehicle vehicle = new EntityVehicle(world, x + 0.5F, y + 2.5F, z + 0.5F, direction, VehicleType.getVehicle(vehicleName), getDataVehicle(GameRegistry.findItemStack(AltisCraft.MODID, vehicleName, 1), world), player.getDisplayName());
                for(Entity entity : allEntities)
                    if(entity instanceof EntityDriveable)
                        if(entity.getDistanceToEntity(vehicle) < 10)
                            vehicleInArea = true;
                if(vehicleInArea)
                {
                    player.addChatMessage(new ChatComponentTranslation("there.is.already.a.vehicle.in.the.area"));
                    return null;
                }
                if(props.cash >= price)
                {
                    props.delCashAddMoney(price);
                    world.spawnEntityInWorld(vehicle);
                }
                else
                    player.addChatMessage(new ChatComponentTranslation("you.have.not.enough.money"));
            }
            else
            {
                EntityPlane plane = new EntityPlane(world, x + 0.5F, y + 2.5F, z + 0.5F, direction, PlaneType.getPlane(vehicleName), getDataPlane(GameRegistry.findItemStack(AltisCraft.MODID, vehicleName, 1), world), player.getDisplayName());
                for(Entity entity : allEntities)
                    if(entity instanceof EntityDriveable)
                        if(entity.getDistanceToEntity(plane) < 10)
                            vehicleInArea = true;
                if(vehicleInArea)
                {
                    player.addChatMessage(new ChatComponentTranslation("there.is.already.a.vehicle.in.the.area"));
                    return null;
                }
                if(props.cash >= price)
                {
                    props.delCashAddMoney(price);
                    world.spawnEntityInWorld(plane);
                }
                else
                    player.addChatMessage(new ChatComponentTranslation("you.have.not.enough.money"));
            }
            if(isGarage)
                if(isAir)
                    FileAPI.removeLign(vehicleName, new File(FileAPI.airGarage + player.getDisplayName() + ".txt"));
                else
                    FileAPI.removeLign(vehicleName, new File(FileAPI.landGarage + player.getDisplayName() + ".txt"));
            return null;
        }
    }
}
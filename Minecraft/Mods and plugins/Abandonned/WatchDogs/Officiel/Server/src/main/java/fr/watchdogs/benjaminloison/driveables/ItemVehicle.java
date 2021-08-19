package fr.watchdogs.benjaminloison.driveables;

import java.io.FileInputStream;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemVehicle extends ItemMapBase implements IFlanItem
{
    public VehicleType type;

    public ItemVehicle(VehicleType type1)
    {
        maxStackSize = 1;
        type = type1;
        type.item = this;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    private NBTTagCompound getTagCompound(ItemStack stack, World world)
    {
        if(stack.stackTagCompound == null)
        {
            stack.stackTagCompound = getOldTagCompound(stack, world);
            if(stack.stackTagCompound == null)
            {
                stack.stackTagCompound = new NBTTagCompound();
                stack.stackTagCompound.setString("Type", type.shortName);
                stack.stackTagCompound.setString("Engine", PartType.defaultEngines.get(EnumType.vehicle).shortName);
            }
        }
        return stack.stackTagCompound;
    }

    private NBTTagCompound getOldTagCompound(ItemStack stack, World world)
    {
        try
        {
            FileInputStream fileinputstream = new FileInputStream(world.getSaveHandler().getMapFileFromName("vehicle_" + stack.getItemDamage()));
            NBTTagCompound tags = CompressedStreamTools.readCompressed(fileinputstream).getCompoundTag("data");
            for(EnumDriveablePart part : EnumDriveablePart.values())
            {
                tags.setInteger(part.getShortName() + "_Health", type.health.get(part) == null ? 0 : type.health.get(part).health);
                tags.setBoolean(part.getShortName() + "_Fire", false);
            }
            fileinputstream.close();
            return tags;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        float cosYaw = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F - (float)Math.PI), sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F - (float)Math.PI), cosPitch = -MathHelper.cos(-entityplayer.rotationPitch * 0.01745329F), sinPitch = MathHelper.sin(-entityplayer.rotationPitch * 0.01745329F);
        double length = 5;
        Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62 - entityplayer.yOffset, entityplayer.posZ), lookVec = posVec.addVector(sinYaw * cosPitch * length, sinPitch * length, cosYaw * cosPitch * length);
        MovingObjectPosition movingobjectposition = world.rayTraceBlocks(posVec, lookVec, type.placeableOnWater);
        if(movingobjectposition == null)
            return itemstack;
        if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK && type.placeableOnLand || world.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) instanceof BlockLiquid && !entityplayer.capabilities.isCreativeMode)
            itemstack.stackSize--;
        return itemstack;
    }

    public Entity spawnVehicle(World world, double x, double y, double z, ItemStack stack)
    {
        Entity entity = new EntityVehicle(world, x, y, z, type, getData(stack, world));
        world.spawnEntityInWorld(entity);
        return entity;
    }

    public DriveableData getData(ItemStack itemstack, World world)
    {
        return new DriveableData(getTagCompound(itemstack, world));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        ItemStack planeStack = new ItemStack(item, 1, 0);
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("Type", type.shortName);
        if(PartType.defaultEngines.containsKey(EnumType.vehicle))
            tags.setString("Engine", PartType.defaultEngines.get(EnumType.vehicle).shortName);
        for(EnumDriveablePart part : EnumDriveablePart.values())
        {
            tags.setInteger(part.getShortName() + "_Health", type.health.get(part) == null ? 0 : type.health.get(part).health);
            tags.setBoolean(part.getShortName() + "_Fire", false);
        }
        planeStack.stackTagCompound = tags;
        list.add(planeStack);
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}
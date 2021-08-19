package fr.watchdogs.benjaminloison.driveables;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemPlane extends Item implements IFlanItem
{
    public PlaneType type;

    public ItemPlane(PlaneType type1)
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
            if(stack.getItemDamage() != 0)
                stack.stackTagCompound = getOldTagCompound(stack, world);
            if(stack.stackTagCompound == null)
            {
                stack.stackTagCompound = new NBTTagCompound();
                stack.stackTagCompound.setString("Type", type.shortName);
                stack.stackTagCompound.setString("Engine", PartType.defaultEngines.get(EnumType.plane).shortName);
            }
        }
        return stack.stackTagCompound;
    }

    private NBTTagCompound getOldTagCompound(ItemStack stack, World world)
    {
        try
        {
            File file = world.getSaveHandler().getMapFileFromName("plane_" + stack.getItemDamage());
            if(file != null && file.exists())
            {
                FileInputStream fileinputstream = new FileInputStream(file);
                NBTTagCompound tags = CompressedStreamTools.readCompressed(fileinputstream).getCompoundTag("data");
                for(EnumDriveablePart part : EnumDriveablePart.values())
                {
                    tags.setInteger(part.getShortName() + "_Health", type.health.get(part) == null ? 0 : type.health.get(part).health);
                    tags.setBoolean(part.getShortName() + "_Fire", false);
                }
                fileinputstream.close();
                return tags;
            }
        }
        catch(IOException e)
        {
            WatchDogs.print("Failed to read old vehicle file");
            e.printStackTrace();
        }
        return null;
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
        if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
        {
            int i = movingobjectposition.blockX, j = movingobjectposition.blockY, k = movingobjectposition.blockZ;
            Block block = world.getBlock(i, j, k);
            if(type.placeableOnLand || block instanceof BlockLiquid)
            {
                DriveableData data = getPlaneData(itemstack, world);
                //if(data != null)
                  //  world.spawnEntityInWorld(new EntityPlane(world, (double)i + 0.5, (double)j + 2.5, (double)k + 0.5, entityplayer, type, data, entityplayer.getDisplayName()));
                if(!entityplayer.capabilities.isCreativeMode)
                    itemstack.stackSize--;
            }
        }
        return itemstack;
    }

    public Entity spawnPlane(World world, double x, double y, double z, ItemStack stack)
    {
        DriveableData data = getPlaneData(stack, world);
        if(data != null)
        {
           // Entity fr.watchdogs.benjaminloison.entity = new EntityPlane(world, x, y, z, type, data);
            //world.spawnEntityInWorld(fr.watchdogs.benjaminloison.entity);
           // return fr.watchdogs.benjaminloison.entity;
        }
        return null;
    }

    public DriveableData getPlaneData(ItemStack itemstack, World world)
    {
        return new DriveableData(getTagCompound(itemstack, world));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        ItemStack planeStack = new ItemStack(item, 1, 0);
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("Type", type.shortName);
        if(PartType.defaultEngines.containsKey(EnumType.plane))
            tags.setString("Engine", PartType.defaultEngines.get(EnumType.plane).shortName);
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
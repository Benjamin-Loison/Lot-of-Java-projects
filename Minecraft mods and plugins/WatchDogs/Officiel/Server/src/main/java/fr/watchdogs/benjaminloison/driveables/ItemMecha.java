package fr.watchdogs.benjaminloison.driveables;

import java.util.Collections;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemMecha extends Item implements IFlanItem
{
    public MechaType type;

    public ItemMecha(MechaType type1)
    {
        maxStackSize = 1;
        type = type1;
        type.item = this;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b)
    {
        if(type.description != null)
            Collections.addAll(lines, type.description.split("_"));
        NBTTagCompound tags = getTagCompound(stack, player.worldObj);
        String engineName = tags.getString("Engine");
        PartType part = PartType.getPart(engineName);
        if(part != null)
            lines.add(part.name);
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
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setString("Type", type.shortName);
            stack.stackTagCompound.setString("Engine", PartType.defaultEngines.get(EnumType.mecha).shortName);
        }
        return stack.stackTagCompound;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        float cosYaw = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F - (float)Math.PI), sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F - (float)Math.PI), cosPitch = -MathHelper.cos(-entityplayer.rotationPitch * 0.01745329F), sinPitch = MathHelper.sin(-entityplayer.rotationPitch * 0.01745329F);
        double length = 5;
        Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62 - entityplayer.yOffset, entityplayer.posZ), lookVec = posVec.addVector(sinYaw * cosPitch * length, sinPitch * length, cosYaw * cosPitch * length);
        MovingObjectPosition movingobjectposition = world.rayTraceBlocks(posVec, lookVec, true);
        if(movingobjectposition == null)
            return itemstack;
        if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
        {
            world.spawnEntityInWorld(new EntityMecha(world, (double)movingobjectposition.blockX + 0.5, (double)movingobjectposition.blockY + 1.5 + type.yOffset, (double)movingobjectposition.blockZ + 0.5, entityplayer, type, getData(itemstack, world), getTagCompound(itemstack, world)));
            if(!entityplayer.capabilities.isCreativeMode)
                itemstack.stackSize--;
        }
        return itemstack;
    }

    public DriveableData getData(ItemStack itemstack, World world)
    {
        return new DriveableData(getTagCompound(itemstack, world));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        ItemStack mechaStack = new ItemStack(item, 1, 0);
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("Type", type.shortName);
        if(PartType.defaultEngines.containsKey(EnumType.mecha))
            tags.setString("Engine", PartType.defaultEngines.get(EnumType.mecha).shortName);
        for(EnumDriveablePart part : EnumDriveablePart.values())
        {
            tags.setInteger(part.getShortName() + "_Health", type.health.get(part) == null ? 0 : type.health.get(part).health);
            tags.setBoolean(part.getShortName() + "_Fire", false);
        }
        mechaStack.stackTagCompound = tags;
        list.add(mechaStack);
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}
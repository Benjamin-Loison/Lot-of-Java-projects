package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.IFlanItem;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemAAGun extends Item implements IFlanItem
{
    public static final ArrayList<String> names = new ArrayList<String>();
    public AAGunType type;

    public ItemAAGun(AAGunType type1)
    {
        maxStackSize = 1;
        type = type1;
        type.item = this;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
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
            int i = movingobjectposition.blockX, j = movingobjectposition.blockY, k = movingobjectposition.blockZ;
            if(world.isSideSolid(i, j, k, ForgeDirection.UP))
                world.spawnEntityInWorld(new EntityAAGun(world, type, (double)i + 0.5, (double)j + 1, (double)k + 0.5, entityplayer));
            if(!entityplayer.capabilities.isCreativeMode)
                itemstack.stackSize--;
        }
        return itemstack;
    }

    public Entity spawnAAGun(World world, double x, double y, double z, ItemStack stack)
    {
        Entity entity = new EntityAAGun(world, type, x, y, z, null);
        world.spawnEntityInWorld(entity);
        return entity;
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}
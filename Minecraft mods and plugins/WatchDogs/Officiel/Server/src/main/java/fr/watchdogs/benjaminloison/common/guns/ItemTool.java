package fr.watchdogs.benjaminloison.common.guns;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.driveables.DriveablePart;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.packets.PacketFlak;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemTool extends ItemFood
{
    public ToolType type;

    public ItemTool(ToolType t)
    {
        super(t.foodness, false);
        maxStackSize = 1;
        type = t;
        type.item = this;
        setMaxDamage(type.toolLife);
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(type.foodness > 0)
            super.onItemRightClick(itemstack, world, entityplayer);
        else if(type.parachute)
        {
            EntityParachute parachute = new EntityParachute(world, type, entityplayer);
            world.spawnEntityInWorld(parachute);
            entityplayer.mountEntity(parachute);
            if(!entityplayer.capabilities.isCreativeMode && type.toolLife > 0)
                itemstack.setItemDamage(itemstack.getItemDamage() + 1);
            if(type.toolLife > 0 && type.destroyOnEmpty && itemstack.getItemDamage() == itemstack.getMaxDamage())
                itemstack.stackSize--;
            return itemstack;
        }
        else if(type.remote)
        {
            PlayerData data = PlayerHandler.getPlayerData(entityplayer, Side.SERVER);
            if(data.remoteExplosives.size() > 0)
            {
                data.remoteExplosives.get(0).detonate();
                if(data.remoteExplosives.get(0).detonated)
                    data.remoteExplosives.remove(0);
                if(!entityplayer.capabilities.isCreativeMode && type.toolLife > 0)
                    itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                if(type.toolLife > 0 && type.destroyOnEmpty && itemstack.getItemDamage() == itemstack.getMaxDamage())
                    itemstack.stackSize--;
                return itemstack;
            }
        }
        else
        {
            float cosYaw = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F), sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F), cosPitch = -MathHelper.cos(entityplayer.rotationPitch * 0.01745329F), sinPitch = MathHelper.sin(entityplayer.rotationPitch * 0.01745329F);
            double length = -5;
            Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62 - entityplayer.yOffset, entityplayer.posZ), lookVec = posVec.addVector(sinYaw * cosPitch * length, sinPitch * length, cosYaw * cosPitch * length);
            if(type.healDriveables)
                for(int i = 0; i < world.loadedEntityList.size(); i++)
                {
                    Object obj = world.loadedEntityList.get(i);
                    if(obj instanceof EntityDriveable)
                    {
                        EntityDriveable driveable = (EntityDriveable)obj;
                        DriveablePart part = driveable.raytraceParts(new Vector3f(posVec), Vector3f.sub(new Vector3f(lookVec), new Vector3f(posVec), null));
                        if(part != null && part.maxHealth > 0)
                        {
                            if(part.health < part.maxHealth && (type.toolLife == 0 || itemstack.getItemDamage() < itemstack.getMaxDamage()))
                            {
                                part.health += type.healAmount;
                                if(part.health > part.maxHealth)
                                    part.health = part.maxHealth;
                                if(!entityplayer.capabilities.isCreativeMode && type.toolLife > 0)
                                    itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                                if(type.toolLife > 0 && type.destroyOnEmpty && itemstack.getItemDamage() == itemstack.getMaxDamage())
                                    itemstack.stackSize--;
                                return itemstack;
                            }
                        }
                    }
                }
            if(type.healPlayers)
            {
                EntityLivingBase hitLiving = entityplayer;
                for(Object aList : world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(Math.min(posVec.xCoord, lookVec.xCoord), Math.min(posVec.yCoord, lookVec.yCoord), Math.min(posVec.zCoord, lookVec.zCoord), Math.max(posVec.xCoord, lookVec.xCoord), Math.max(posVec.yCoord, lookVec.yCoord), Math.max(posVec.zCoord, lookVec.zCoord))))
                {
                    if(!(aList instanceof EntityLivingBase))
                        continue;
                    EntityLivingBase checkEntity = (EntityLivingBase)aList;
                    if(checkEntity == entityplayer)
                        continue;
                    MovingObjectPosition hit = checkEntity.boundingBox.calculateIntercept(posVec, lookVec);
                    if(hit != null)
                        hitLiving = checkEntity;
                }
                if(hitLiving != null)
                {
                    if(itemstack.getItemDamage() >= itemstack.getMaxDamage() && type.toolLife > 0)
                        return itemstack;
                    hitLiving.heal(type.healAmount);
                    WatchDogs.getPacketHandler().sendToAllAround(new PacketFlak(hitLiving.posX, hitLiving.posY, hitLiving.posZ, 5, "heart"), new NetworkRegistry.TargetPoint(hitLiving.dimension, hitLiving.posX, hitLiving.posY, hitLiving.posZ, 50F));
                    if(!entityplayer.capabilities.isCreativeMode && type.toolLife > 0)
                        itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                    if(type.toolLife > 0 && type.destroyOnEmpty && itemstack.getItemDamage() >= itemstack.getMaxDamage())
                        itemstack.stackSize--;
                }
            }
        }
        return itemstack;
    }

    @Override
    public String toString()
    {
        return type == null ? getUnlocalizedName() : type.name;
    }
}
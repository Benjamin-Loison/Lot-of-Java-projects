package fr.watchdogs.benjaminloison.entity;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCustomizableNPC extends EntityLiving
{
    public EntityCustomizableNPC(World world)
    {
        super(world);
    }

    public boolean isMovementCeased()
    {
        return true;
    }

    @Override
    public void onLivingUpdate()
    {}

    @Override
    public boolean isEntityInvulnerable()
    {
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound n)
    {
        super.writeEntityToNBT(n);
        DataWatcher d = getDataWatcher();
        n.setString(WatchDogs.MODID + "Texture", d.getWatchableObjectString(20));
        n.setString(WatchDogs.MODID + "Action", d.getWatchableObjectString(21));
        n.setString(WatchDogs.MODID + "Permission", d.getWatchableObjectString(22));
        n.setString(WatchDogs.MODID + "X", d.getWatchableObjectString(23));
        n.setString(WatchDogs.MODID + "Y", d.getWatchableObjectString(24));
        n.setString(WatchDogs.MODID + "Z", d.getWatchableObjectString(25));
        n.setString(WatchDogs.MODID + "Direction", d.getWatchableObjectString(26));
        n.setString(WatchDogs.MODID + "Name", d.getWatchableObjectString(27));
    }

    public void readEntityFromNBT(NBTTagCompound n)
    {
        super.readEntityFromNBT(n);
        DataWatcher d = getDataWatcher();
        d.updateObject(20, n.getString(WatchDogs.MODID + "Texture"));
        d.updateObject(21, n.getString(WatchDogs.MODID + "Action"));
        d.updateObject(22, n.getString(WatchDogs.MODID + "Permission"));
        d.updateObject(23, n.getString(WatchDogs.MODID + "X"));
        d.updateObject(24, n.getString(WatchDogs.MODID + "Y"));
        d.updateObject(25, n.getString(WatchDogs.MODID + "Z"));
        d.updateObject(26, n.getString(WatchDogs.MODID + "Direction"));
        d.updateObject(27, n.getString(WatchDogs.MODID + "Name"));
    }
}
package fr.chequemod.benjaminloison.entity;

import fr.chequemod.benjaminloison.common.ChequeMod;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBanker extends EntityLiving
{
    public EntityBanker(World world)
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

    public boolean interact(EntityPlayer p)
    {
        return true;
    }

    @Override
    public boolean isEntityInvulnerable()
    {
        return true;
    }
}
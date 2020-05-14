package com.minefus.degraduck.entity;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGreenPioussin extends EntityMob
{
    public int mobSizeGen;

    public EntityGreenPioussin(World world)
    {
        super(world);
        mobSizeGen = ((int)Math.round(Math.random() * 5));
        setCustomNameTag(I18n.format("entity.green_pioussin.name"));
        setSize(0.6F, 0.6F);
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24D);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
    }

    public Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 1);
        return (entityplayer != null) && (canEntityBeSeen(entityplayer)) ? entityplayer : null;
    }

    public String getHurtSound()
    {
        return Minefus.MODID + ":Piou hurt";
    }

    public String getDeathSound()
    {
        return Minefus.MODID + ":Piou death";
    }

    public String getLivingSound()
    {
        return Minefus.MODID + ":Piou living";
    }

    public boolean attackEntityFrom(DamageSource damage, float f)
    {
        if(isEntityInvulnerable())
            return false;
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.28D);
        return super.attackEntityFrom(damage, f);
    }
}
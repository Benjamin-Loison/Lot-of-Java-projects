package com.minefus.degraduck.entity;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityWildTournesol extends EntityMob
{
    public int mobSizeGen;

    public EntityWildTournesol(World world)
    {
        super(world);
        mobSizeGen = ((int)Math.round(Math.random() * 5.0D));
        setCustomNameTag(I18n.format("entity.wild_tournesol.name"));
        setSize(0.7F, 0.7F);
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(28.0D);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22D);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
    }

    public Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 1.0D);
        return (entityplayer != null) && (canEntityBeSeen(entityplayer)) ? entityplayer : null;
    }

    public String getHurt()
    {
        return Minefus.MODID + ":Wild tournesol hurt";
    }

    public String getDeath()
    {
        return Minefus.MODID + ":Wild tournesol death";
    }

    public String getLiving()
    {
        return Minefus.MODID + ":Wild tournesol living";
    }

    public boolean attackEntityFrom(DamageSource damage, float f)
    {
        if(isEntityInvulnerable())
            return false;
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.22D);
        return super.attackEntityFrom(damage, f);
    }
}
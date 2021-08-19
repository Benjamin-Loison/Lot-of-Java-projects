package com.minefus.degraduck.entity;

import com.minefus.degraduck.common.Minefus;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySickedTofu extends EntityMob
{
    public int mobSizeGen;

    public EntitySickedTofu(World world)
    {
        super(world);
        mobSizeGen = ((int)Math.round(Math.random() * 5));
        setCustomNameTag(I18n.format("entity.sicked_tofu.name"));
        setSize(0.6F, 0.6F);
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.55D);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
    }

    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 1);
        return (entityplayer != null) && (canEntityBeSeen(entityplayer)) ? entityplayer : null;
    }

    public String getHurt()
    {
        return Minefus.MODID + ":Tofu hurt";
    }

    public String getDeath()
    {
        return Minefus.MODID + ":Tofu death";
    }

    public String getLiving()
    {
        return Minefus.MODID + ":Tofu living";
    }

    public boolean attackEntityFrom(DamageSource damage, float f)
    {
        if(isEntityInvulnerable())
            return false;
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.87D);
        return super.attackEntityFrom(damage, f);
    }
}
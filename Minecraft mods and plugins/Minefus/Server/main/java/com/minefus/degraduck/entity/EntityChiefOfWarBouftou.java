package com.minefus.degraduck.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityChiefOfWarBouftou extends EntityMob
{
    public EntityChiefOfWarBouftou(World world)
    {
        super(world);
        setSize(0.8F, 0.8F);
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10);
        getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2);
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.58D);
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
    }

    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 1);
        return (entityplayer != null) && (canEntityBeSeen(entityplayer)) ? entityplayer : null;
    }

    public boolean attackEntityFrom(DamageSource damage, float f)
    {
        if(isEntityInvulnerable())
            return false;
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.9D);
        return super.attackEntityFrom(damage, f);
    }
}
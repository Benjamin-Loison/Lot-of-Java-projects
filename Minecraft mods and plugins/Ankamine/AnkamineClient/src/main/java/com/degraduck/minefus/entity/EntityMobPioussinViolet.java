package com.degraduck.minefus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMobPioussinViolet
  extends EntityMob
{
  public int mobSizeGen;
  
  public EntityMobPioussinViolet(World world)
  {
    super(world);
    mobSizeGen = ((int)Math.round(Math.random() * 5.0D));
    setCustomNameTag("Pioussin Violet");
    setSize(0.6F, 0.6F);
  }
  
  public void applyEntityAttributes()
  {
    super.applyEntityAttributes();
    
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24D);
    getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
  }
  
  public Entity findPlayerToAttack()
  {
    EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 1.0D);
    return (entityplayer != null) && (canEntityBeSeen(entityplayer)) ? entityplayer : null;
  }
  
  public String getHurtSound()
  {
    return "moddofusmobs:piouHurt";
  }
  
  public String getDeathSound()
  {
    return "moddofusmobs:piouDead";
  }
  
  public String getLivingSound()
  {
    return "moddofusmobs:piouLiving";
  }
  
  public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
  {
    if (isEntityInvulnerable()) {
      return false;
    }
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.28D);
    return super.attackEntityFrom(p_70097_1_, p_70097_2_);
  }
}

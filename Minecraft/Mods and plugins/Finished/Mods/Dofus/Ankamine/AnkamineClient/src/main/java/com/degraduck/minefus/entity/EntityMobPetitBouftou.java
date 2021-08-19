package com.degraduck.minefus.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMobPetitBouftou
  extends EntityMob
{
  public int mobSizeGen;
  
  public EntityMobPetitBouftou(World world)
  {
    super(world);
    mobSizeGen = ((int)Math.round(Math.random() * 5.0D));
    setCustomNameTag("Petit Bouftou");
    setSize(0.7F, 0.7F);
  }
  
  public void applyEntityAttributes()
  {
    super.applyEntityAttributes();
    
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(22.0D);
    getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24D);
    getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
  }
  
  protected Entity findPlayerToAttack()
  {
    EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 1.0D);
    return (entityplayer != null) && (canEntityBeSeen(entityplayer)) ? entityplayer : null;
  }
  
  public String getHurtSound()
  {
    return "moddofusmobs:bouftouHurt";
  }
  
  public String getDeathSound()
  {
    return "moddofusmobs:bouftouDead";
  }
  
  public String getLivingSound()
  {
    return "moddofusmobs:bouftouLiving";
  }
  
  public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
  {
    if (isEntityInvulnerable()) {
      return false;
    }
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.24D);
    return super.attackEntityFrom(p_70097_1_, p_70097_2_);
  }
}

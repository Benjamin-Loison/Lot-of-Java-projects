package fr.watchdogs.benjaminloison.common.guns;

import java.util.List;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.ItemTeamArmour;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.packets.PacketFlak;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityGrenade extends EntityShootable implements IEntityAdditionalSpawnData
{
    public GrenadeType type;
    public EntityLivingBase thrower;
    public RotatedAxes axes = new RotatedAxes();
    public Vector3f angularVelocity = new Vector3f(0, 0, 0);
    public float prevRotationRoll;
    public boolean smoking, detonated, stuck;
    public int smokeTime, numUsesRemaining, stuckToX, stuckToY, stuckToZ;

    public EntityGrenade(World w)
    {
        super(w);
    }

    public EntityGrenade(World w, GrenadeType g, EntityLivingBase t)
    {
        this(w);
        setPosition(t.posX, t.posY + t.getEyeHeight(), t.posZ);
        type = g;
        numUsesRemaining = type.numUses;
        thrower = t;
        setSize(g.hitBoxSize, g.hitBoxSize);
        axes.setAngles(t.rotationYaw + 90, g.spinWhenThrown ? t.rotationPitch : 0, 0);
        rotationYaw = prevRotationYaw = g.spinWhenThrown ? t.rotationYaw + 90 : 0;
        rotationPitch = prevRotationPitch = t.rotationPitch;
        float speed = 0.5F * type.throwSpeed;
        motionX = axes.getXAxis().x * speed;
        motionY = axes.getXAxis().y * speed;
        motionZ = axes.getXAxis().z * speed;
        if(type.spinWhenThrown)
            angularVelocity = new Vector3f(0, 0, 10);
        if(type.throwSound != null)
            PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.throwSound, true);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if(type.despawnTime > 0 && ticksExisted > type.despawnTime)
        {
            detonated = true;
            setDead();
            return;
        }
        if(smoking)
        {
            WatchDogs.getPacketHandler().sendToAllAround(new PacketFlak(posX, posY, posZ, 50, type.smokeParticleType), posX, posY, posZ, 30, dimension);
            List list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox.expand(type.smokeRadius, type.smokeRadius, type.smokeRadius));
            for(Object obj : list)
            {
                EntityLivingBase entity = ((EntityLivingBase)obj);
                if(entity.getDistanceToEntity(this) < type.smokeRadius)
                {
                    boolean smokeThem = true;
                    for(int i = 0; i < 5; i++)
                    {
                        ItemStack stack = entity.getEquipmentInSlot(i);
                        if(stack != null && stack.getItem() instanceof ItemTeamArmour)
                            if(((ItemTeamArmour)stack.getItem()).type.smokeProtection)
                                smokeThem = false;
                    }
                    if(smokeThem)
                        for(PotionEffect effect : type.smokeEffects)
                            entity.addPotionEffect(new PotionEffect(effect));
                }
            }
            smokeTime--;
            if(smokeTime == 0)
                setDead();
        }
        if(ticksExisted > type.fuse && type.fuse > 0)
            detonate();
        if(type.livingProximityTrigger > 0 || type.driveableProximityTrigger > 0)
        {
            float checkRadius = Math.max(type.livingProximityTrigger, type.driveableProximityTrigger);
            for(Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(checkRadius, checkRadius, checkRadius)))
            {
                if(obj == thrower && ticksExisted < 10)
                    continue;
                if(obj instanceof EntityLivingBase && getDistanceToEntity((Entity)obj) < type.livingProximityTrigger)
                {
                    if(type.damageToTriggerer > 0)
                        ((EntityLivingBase)obj).attackEntityFrom(getGrenadeDamage(), type.damageToTriggerer);
                    detonate();
                    break;
                }
                if(obj instanceof EntityDriveable && getDistanceToEntity((Entity)obj) < type.driveableProximityTrigger)
                {
                    if(type.damageToTriggerer > 0)
                        ((EntityDriveable)obj).attackEntityFrom(getGrenadeDamage(), type.damageToTriggerer);
                    detonate();
                    break;
                }
            }
        }
        if(stuck && worldObj.isAirBlock(stuckToX, stuckToY, stuckToZ))
            stuck = false;
        if(!stuck && !type.stickToThrower)
        {
            prevRotationYaw = axes.getYaw();
            prevRotationPitch = axes.getPitch();
            prevRotationRoll = axes.getRoll();
            if(angularVelocity.lengthSquared() > 0.00000001)
                axes.rotateLocal(angularVelocity.length(), angularVelocity.normalise(null));
            Vector3f posVec = new Vector3f(posX, posY, posZ), motVec = new Vector3f(motionX, motionY, motionZ), nextPosVec = Vector3f.add(posVec, motVec, null);
            MovingObjectPosition hit = worldObj.rayTraceBlocks(posVec.toVec3(), nextPosVec.toVec3());
            if(hit != null && hit.typeOfHit == MovingObjectType.BLOCK)
            {
                Block block = worldObj.getBlock(hit.blockX, hit.blockY, hit.blockZ);
                Material mat = block.getMaterial();
                if(type.explodeOnImpact)
                    detonate();
                else if(type.breaksGlass && mat == Material.glass && FileAPI.configBoolean("weapons.break.glass"))
                {
                    worldObj.setBlockToAir(hit.blockX, hit.blockY, hit.blockZ);
                    WatchDogs.proxy.playBlockBreakSound(hit.blockX, hit.blockY, hit.blockZ, block);
                }
                else if(!type.penetratesBlocks)
                {
                    Vector3f hitVec = new Vector3f(hit.hitVec), preHitMotVec = Vector3f.sub(hitVec, posVec, null), postHitMotVec = Vector3f.sub(motVec, preHitMotVec, null);
                    int sideHit = hit.sideHit;
                    switch(sideHit)
                    {
                        case 1:
                            postHitMotVec.setY(-postHitMotVec.getY());
                            break;
                        case 5:
                            postHitMotVec.setX(-postHitMotVec.getX());
                            break;
                        case 3:
                            postHitMotVec.setZ(-postHitMotVec.getZ());
                            break;
                    }
                    float lambda = Math.abs(motVec.lengthSquared()) < 0.00000001F ? 1F : postHitMotVec.length() / motVec.length();
                    postHitMotVec.scale(type.bounciness / 2);
                    posX += preHitMotVec.x + postHitMotVec.x;
                    posY += preHitMotVec.y + postHitMotVec.y;
                    posZ += preHitMotVec.z + postHitMotVec.z;
                    motionX = postHitMotVec.x / lambda;
                    motionY = postHitMotVec.y / lambda;
                    motionZ = postHitMotVec.z / lambda;
                    motVec = new Vector3f(motionX, motionY, motionZ);
                    float randomSpinner = 90;
                    Vector3f.add(angularVelocity, new Vector3f(rand.nextGaussian() * randomSpinner, rand.nextGaussian() * randomSpinner, rand.nextGaussian() * randomSpinner), angularVelocity);
                    angularVelocity.scale(motVec.lengthSquared());
                    if(motVec.lengthSquared() > 0.01)
                        playSound(type.bounceSound, 1, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
                    if(type.sticky)
                    {
                        posX = hitVec.x;
                        posY = hitVec.y;
                        posZ = hitVec.z;
                        motionX = motionY = motionZ = 0;
                        angularVelocity.set(0, 0, 0);
                        float yaw = axes.getYaw();
                        switch(hit.sideHit)
                        {
                            case 0:
                                axes.setAngles(yaw, 180, 0);
                                break;
                            case 1:
                                axes.setAngles(yaw, 0, 0);
                                break;
                            case 2:
                                axes.setAngles(270, 90, 0);
                                axes.rotateLocalYaw(yaw);
                                break;
                            case 3:
                                axes.setAngles(90, 90, 0);
                                axes.rotateLocalYaw(yaw);
                                break;
                            case 4:
                                axes.setAngles(180, 90, 0);
                                axes.rotateLocalYaw(yaw);
                                break;
                            case 5:
                                axes.setAngles(0, 90, 0);
                                axes.rotateLocalYaw(yaw);
                                break;
                        }
                        stuck = true;
                        stuckToX = hit.blockX;
                        stuckToY = hit.blockY;
                        stuckToZ = hit.blockZ;
                    }
                }
            }
            else
            {
                posX += motionX;
                posY += motionY;
                posZ += motionZ;
            }
            setPosition(posX, posY, posZ);
        }
        if(type.stickToThrower)
        {
            if(thrower == null || thrower.isDead)
                setDead();
            else
                setPosition(thrower.posX, thrower.posY, thrower.posZ);
        }
        if(type.damageVsLiving > 0 && !stuck)
        {
            Vector3f motVec = new Vector3f(motionX, motionY, motionZ);
            for(Object obj : worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox))
            {
                if(obj == thrower && ticksExisted < 10 || motVec.lengthSquared() < 0.01D)
                    continue;
                if(obj instanceof EntityLivingBase)
                    ((EntityLivingBase)obj).attackEntityFrom(getGrenadeDamage(), type.damageVsLiving * motVec.lengthSquared() * 3);
            }
        }
        motionY -= 9.81 / 400 * type.fallSpeed;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float f)
    {
        if(type.detonateWhenShot)
            detonate();
        return type.detonateWhenShot;
    }

    public void detonate()
    {
        if(ticksExisted < type.primeDelay)
            return;
        if(detonated)
            return;
        detonated = true;
        PacketPlaySound.sendSoundPacket(posX, posY, posZ, WatchDogs.soundRange, dimension, type.detonateSound, true);
        if(type.explosionRadius > 0.1)
            if((thrower instanceof EntityPlayer))
                new FlansModExplosion(worldObj, this, (EntityPlayer)thrower, type, posX, posY, posZ, type.explosionRadius, FileAPI.configBoolean("weapons.break.glass") && type.explosionBreaksBlocks);
            else
                worldObj.createExplosion(this, posX, posY, posZ, type.explosionRadius, FileAPI.configBoolean("weapons.break.glass") && type.explosionBreaksBlocks);
        if(type.fireRadius > 0.1)
            for(float i = -type.fireRadius; i < type.fireRadius; i++)
                for(float j = -type.fireRadius; j < type.fireRadius; j++)
                    for(float k = -type.fireRadius; k < type.fireRadius; k++)
                    {
                        int x = MathHelper.floor_double(i + posX), y = MathHelper.floor_double(j + posY), z = MathHelper.floor_double(k + posZ);
                        if(i * i + j * j + k * k <= type.fireRadius * type.fireRadius && worldObj.getBlock(x, y, z) == Blocks.air && rand.nextBoolean())
                            worldObj.setBlock(x, y, z, Blocks.fire, 0, 3);
                    }
        for(int i = 0; i < type.explodeParticles; i++)
            worldObj.spawnParticle(type.explodeParticleType, posX, posY, posZ, rand.nextGaussian(), rand.nextGaussian(), rand.nextGaussian());
        if(type.dropItemOnDetonate != null)
        {
            String itemName = type.dropItemOnDetonate;
            int damage = 0;
            if(itemName.contains("."))
            {
                damage = Integer.parseInt(itemName.split("\\.")[1]);
                itemName = itemName.split("\\.")[0];
            }
            ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);
            entityDropItem(dropStack, 1);
        }
        if(type.smokeTime > 0)
        {
            smoking = true;
            smokeTime = type.smokeTime;
        }
        else
            setDead();
    }

    @Override
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int i)
    {}

    private DamageSource getGrenadeDamage()
    {
        if(thrower instanceof EntityPlayer)
            return (new EntityDamageSourceGun(type.shortName, this, (EntityPlayer)thrower, type, false)).setProjectile();
        else
            return (new EntityDamageSourceIndirect(type.shortName, this, thrower)).setProjectile();
    }

    @Override
    protected void entityInit()
    {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tags)
    {
        type = GrenadeType.getGrenade(tags.getString("Type"));
        thrower = worldObj.getPlayerEntityByName(tags.getString("Thrower"));
        rotationYaw = tags.getFloat("RotationYaw");
        rotationPitch = tags.getFloat("RotationPitch");
        axes.setAngles(rotationYaw, rotationPitch, 0);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tags)
    {
        if(type == null)
            setDead();
        else
        {
            tags.setString("Type", type.shortName);
            if(thrower != null)
                tags.setString("Thrower", thrower.getCommandSenderName());
            tags.setFloat("RotationYaw", axes.getYaw());
            tags.setFloat("RotationPitch", axes.getPitch());
        }
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        ByteBufUtils.writeUTF8String(data, type.shortName);
        data.writeInt(thrower == null ? 0 : thrower.getEntityId());
        data.writeFloat(axes.getYaw());
        data.writeFloat(axes.getPitch());
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        type = GrenadeType.getGrenade(ByteBufUtils.readUTF8String(data));
        thrower = (EntityLivingBase)worldObj.getEntityByID(data.readInt());
        setRotation(data.readFloat(), data.readFloat());
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
        axes.setAngles(rotationYaw, rotationPitch, 0);
        if(type.spinWhenThrown)
            angularVelocity = new Vector3f(0, 0, 10);
    }

    @Override
    public boolean isBurning()
    {
        return false;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return !isDead && type.isDeployableBag;
    }

    @Override
    public boolean interactFirst(EntityPlayer player)
    {
        if(type.isDeployableBag)
        {
            boolean used = false;
            if(type.healAmount > 0 && player.getHealth() < player.getMaxHealth())
            {
                player.heal(type.healAmount);
                WatchDogs.getPacketHandler().sendToAllAround(new PacketFlak(player.posX, player.posY, player.posZ, 5, "heart"), new NetworkRegistry.TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 50));
                used = true;
            }
            for(PotionEffect effect : type.potionEffects)
            {
                player.addPotionEffect(new PotionEffect(effect));
                used = true;
            }
            if(type.numClips > 0 && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemGun)
            {
                GunType gun = ((ItemGun)player.getCurrentEquippedItem().getItem()).type;
                if(gun.ammo.size() > 0)
                {
                    ShootableType bulletToGive = gun.ammo.get(0);
                    if(player.inventory.addItemStackToInventory(new ItemStack(bulletToGive.item, Math.min(bulletToGive.maxStackSize, type.numClips * gun.numAmmoItemsInGun))))
                        used = true;
                }
            }
            if(used)
            {
                numUsesRemaining--;
                if(numUsesRemaining <= 0)
                    setDead();
            }
        }
        return true;
    }
}
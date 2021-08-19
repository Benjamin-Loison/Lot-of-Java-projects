package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntityMecha;
import fr.watchdogs.benjaminloison.driveables.EntityPlane;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import fr.watchdogs.benjaminloison.driveables.EntityVehicle;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.packets.PacketFlak;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBullet extends EntityShootable implements IEntityAdditionalSpawnData
{
    private static int bulletLife = 600;
    public Entity owner, lockedOnTo;
    private int ticksInAir, pingOfShooter = 0;
    public BulletType type;
    public InfoType firedFrom;
    public float damage, penetratingPower;
    public boolean shotgun = false;
    public static Random bulletRandom = new Random();

    public EntityBullet(World world)
    {
        super(world);
        ticksInAir = 0;
        setSize(0.5F, 0.5F);
    }

    private EntityBullet(World world, EntityLivingBase shooter, float gunDamage, BulletType bulletType, InfoType shotFrom)
    {
        this(world);
        owner = shooter;
        if(shooter instanceof EntityPlayerMP)
            pingOfShooter = ((EntityPlayerMP)shooter).ping;
        type = bulletType;
        firedFrom = shotFrom;
        damage = gunDamage;
        penetratingPower = type.penetratingPower;
    }

    public EntityBullet(World world, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, float speed, boolean shot, InfoType shotFrom)
    {
        this(world, Vec3.createVectorHelper(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ), shooter.rotationYaw, shooter.rotationPitch, shooter, spread, gunDamage, type1, speed, shotFrom);
        shotgun = shot;
    }

    public EntityBullet(World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, InfoType shotFrom)
    {
        this(world, origin, yaw, pitch, shooter, spread, gunDamage, type1, 3.0F, shotFrom);
    }

    public EntityBullet(World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, float speed, InfoType shotFrom)
    {
        this(world, shooter, gunDamage, type1, shotFrom);
        setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * 3.14159265F) * MathHelper.cos((rotationPitch / 180F) * 3.14159265F);
        motionZ = MathHelper.cos((rotationYaw / 180F) * 3.14159265F) * MathHelper.cos((rotationPitch / 180F) * 3.14159265F);
        motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
        setArrowHeading(motionX, motionY, motionZ, spread / 2F, speed);
    }

    public EntityBullet(World world, Vector3f origin, Vector3f direction, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, float speed, InfoType shotFrom)
    {
        this(world, shooter, gunDamage, type1, shotFrom);
        damage = gunDamage;
        setPosition(origin.x, origin.y, origin.z);
        motionX = direction.x;
        motionY = direction.y;
        motionZ = direction.z;
        setArrowHeading(motionX, motionY, motionZ, spread, speed);
    }

    public EntityBullet(World world, Vec3 origin, float yaw, float pitch, double motX, double motY, double motZ, EntityLivingBase shooter, float gunDamage, BulletType type1, InfoType shotFrom)
    {
        this(world, shooter, gunDamage, type1, shotFrom);
        setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = motX;
        motionY = motY;
        motionZ = motZ;
    }

    @Override
    protected void entityInit()
    {}

    public void setArrowHeading(double d, double d1, double d2, float spread, float speed)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d *= speed;
        d1 *= speed;
        d2 *= speed;
        d += rand.nextGaussian() * 0.005D * spread * speed;
        d1 += rand.nextGaussian() * 0.005D * spread * speed;
        d2 += rand.nextGaussian() * 0.005D * spread * speed;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
        getLockOnTarget();
    }

    private void getLockOnTarget()
    {
        if(type.lockOnToPlanes || type.lockOnToVehicles || type.lockOnToMechas || type.lockOnToLivings || type.lockOnToPlayers)
        {
            Vector3f motionVec = new Vector3f(motionX, motionY, motionZ);
            Entity closestEntity = null;
            float closestAngle = type.maxLockOnAngle * 3.14159265F / 180F;
            for(Object obj : worldObj.loadedEntityList)
            {
                Entity entity = (Entity)obj;
                if((type.lockOnToMechas && entity instanceof EntityMecha) || (type.lockOnToVehicles && entity instanceof EntityVehicle) || (type.lockOnToPlanes && entity instanceof EntityPlane) || (type.lockOnToPlayers && entity instanceof EntityPlayer) || (type.lockOnToLivings && entity instanceof EntityLivingBase))
                {
                    Vector3f relPosVec = new Vector3f(entity.posX - posX, entity.posY - posY, entity.posZ - posZ);
                    float angle = Math.abs(Vector3f.angle(motionVec, relPosVec));
                    if(angle < closestAngle)
                    {
                        closestEntity = entity;
                        closestAngle = angle;
                    }
                }
            }
            if(closestEntity != null)
                lockedOnTo = closestEntity;
        }
    }

    @Override
    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;
        if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(d * d + d2 * d2);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / 3.1415927410125732D);
            setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        ticksInAir++;
        if(type != null)
            if(ticksInAir > type.fuse && type.fuse > 0 && !isDead)
                setDead();
        if(ticksExisted > bulletLife)
            setDead();
        if(isDead)
            return;
        ArrayList<BulletHit> hits = new ArrayList<BulletHit>();
        Vector3f origin = new Vector3f(posX, posY, posZ), motion = new Vector3f(motionX, motionY, motionZ);
        float speed = motion.length();
        for(int i = 0; i < worldObj.loadedEntityList.size(); i++)
        {
            Object obj = worldObj.loadedEntityList.get(i);
            if(obj instanceof EntityDriveable)
            {
                EntityDriveable driveable = (EntityDriveable)obj;
                if(driveable.isDead() || driveable.isPartOfThis(owner))
                    continue;
                if(getDistanceToEntity(driveable) <= driveable.getDriveableType().bulletDetectionRadius + speed)
                {
                    ArrayList<BulletHit> driveableHits = driveable.attackFromBullet(origin, motion);
                    hits.addAll(driveableHits);
                }
            }
            else if(obj instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)obj;
                PlayerData data = PlayerHandler.getPlayerData(player);
                boolean shouldDoNormalHitDetect = false;
                if(data != null)
                {
                    if(player.isDead)
                        continue;
                    if(player == owner && ticksInAir < 20)
                        continue;
                    int snapshotToTry = pingOfShooter / 50;
                    if(snapshotToTry >= data.snapshots.length)
                        snapshotToTry = data.snapshots.length - 1;
                    if(snapshotToTry < 0)
                        snapshotToTry = 0;
                    PlayerSnapshot snapshot = data.snapshots[snapshotToTry];
                    if(snapshot == null)
                        snapshot = data.snapshots[0];
                    if(snapshot == null)
                        shouldDoNormalHitDetect = true;
                    else
                    {
                        ArrayList<BulletHit> playerHits = snapshot.raytrace(origin, motion);
                        hits.addAll(playerHits);
                    }
                }
                if(data == null || shouldDoNormalHitDetect)
                {
                    MovingObjectPosition mop = player.boundingBox.calculateIntercept(origin.toVec3(), Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ));
                    if(mop != null)
                    {
                        Vector3f hitPoint = new Vector3f(mop.hitVec.xCoord - posX, mop.hitVec.yCoord - posY, mop.hitVec.zCoord - posZ);
                        float hitLambda = 1F;
                        if(motion.x != 0F)
                            hitLambda = hitPoint.x / motion.x;
                        else if(motion.y != 0F)
                            hitLambda = hitPoint.y / motion.y;
                        else if(motion.z != 0F)
                            hitLambda = hitPoint.z / motion.z;
                        if(hitLambda < 0)
                            hitLambda = -hitLambda;
                        hits.add(new PlayerBulletHit(new PlayerHitbox(player, new RotatedAxes(), new Vector3f(), new Vector3f(), new Vector3f(), EnumHitboxType.BODY), hitLambda));
                    }
                }
            }
            else
            {
                Entity entity = (Entity)obj;
                if(entity != this && entity != owner && !entity.isDead && (entity instanceof EntityLivingBase || entity instanceof EntityAAGun || entity instanceof EntityGrenade))
                {
                    MovingObjectPosition mop = entity.boundingBox.calculateIntercept(origin.toVec3(), Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ));
                    if(mop != null)
                    {
                        Vector3f hitPoint = new Vector3f(mop.hitVec.xCoord - posX, mop.hitVec.yCoord - posY, mop.hitVec.zCoord - posZ);
                        float hitLambda = 1F;
                        if(motion.x != 0F)
                            hitLambda = hitPoint.x / motion.x;
                        else if(motion.y != 0F)
                            hitLambda = hitPoint.y / motion.y;
                        else if(motion.z != 0F)
                            hitLambda = hitPoint.z / motion.z;
                        if(hitLambda < 0)
                            hitLambda = -hitLambda;
                        hits.add(new EntityHit(entity, hitLambda));
                    }
                }
            }
        }
        Vec3 posVec = Vec3.createVectorHelper(posX, posY, posZ), nextPosVec = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition hit = worldObj.func_147447_a(posVec, nextPosVec, false, true, true);
        posVec = Vec3.createVectorHelper(posX, posY, posZ);
        if(hit != null)
        {
            Vec3 hitVec = posVec.subtract(hit.hitVec);
            float lambda = 1;
            if(motionX != 0)
                lambda = (float)(hitVec.xCoord / motionX);
            else if(motionY != 0)
                lambda = (float)(hitVec.yCoord / motionY);
            else if(motionZ != 0)
                lambda = (float)(hitVec.zCoord / motionZ);
            if(lambda < 0)
                lambda = -lambda;
            hits.add(new BlockHit(hit, lambda));
        }
        if(!hits.isEmpty())
        {
            Collections.sort(hits);
            for(BulletHit bulletHit : hits)
            {
                if(bulletHit instanceof DriveableHit)
                {
                    DriveableHit driveableHit = (DriveableHit)bulletHit;
                    penetratingPower = driveableHit.driveable.bulletHit(this, driveableHit, penetratingPower);
                }
                else if(bulletHit instanceof PlayerBulletHit)
                {
                    PlayerBulletHit playerHit = (PlayerBulletHit)bulletHit;
                    penetratingPower = playerHit.hitbox.hitByBullet(this, penetratingPower);
                }
                else if(bulletHit instanceof EntityHit)
                {
                    EntityHit entityHit = (EntityHit)bulletHit;
                    if(entityHit.entity.attackEntityFrom(getBulletDamage(false), damage * type.damageVsLiving) && entityHit.entity instanceof EntityLivingBase)
                    {
                        EntityLivingBase living = (EntityLivingBase)entityHit.entity;
                        for(PotionEffect effect : type.hitEffects)
                            living.addPotionEffect(new PotionEffect(effect));
                        living.arrowHitTimer++;
                        living.hurtResistantTime = living.maxHurtResistantTime / 2;
                    }
                    if(type.setEntitiesOnFire)
                        entityHit.entity.setFire(20);
                    penetratingPower -= 1F;
                }
                else if(bulletHit instanceof BlockHit)
                {
                    BlockHit blockHit = (BlockHit)bulletHit;
                    MovingObjectPosition raytraceResult = blockHit.raytraceResult;
                    int xTile = raytraceResult.blockX, yTile = raytraceResult.blockY, zTile = raytraceResult.blockZ;
                    Block block = worldObj.getBlock(xTile, yTile, zTile);
                    if(type.breaksGlass && block.getMaterial() == Material.glass && FileAPI.configBoolean("weapons.break.glass"))
                    {
                        System.out.println("testa");
                        worldObj.setBlockToAir(xTile, yTile, zTile);
                        WatchDogs.proxy.playBlockBreakSound(xTile, yTile, zTile, block);
                    }
                    setPosition(hit.hitVec.xCoord, hit.hitVec.yCoord, hit.hitVec.zCoord);
                    setDead();
                    break;
                }
                if(penetratingPower <= 0F || (type.explodeOnImpact && ticksInAir > 1))
                {
                    setPosition(posX + motionX * bulletHit.intersectTime, posY + motionY * bulletHit.intersectTime, posZ + motionZ * bulletHit.intersectTime);
                    setDead();
                    break;
                }
            }
        }
        float drag = 0.99F, gravity = 0.02F;
        if(isInWater())
        {
            for(int i = 0; i < 4; i++)
            {
                float bubbleMotion = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * bubbleMotion, posY - motionY * bubbleMotion, posZ - motionZ * bubbleMotion, motionX, motionY, motionZ);
            }
            drag = 0.8F;
        }
        motionX *= drag;
        motionY *= drag;
        motionZ *= drag;
        motionY -= gravity * type.fallSpeed;
        if(lockedOnTo != null)
        {
            double dX = lockedOnTo.posX - posX, dY = lockedOnTo.posY - posY, dZ = lockedOnTo.posZ - posZ, dXYZ = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
            Vector3f relPosVec = new Vector3f(lockedOnTo.posX - posX, lockedOnTo.posY - posY, lockedOnTo.posZ - posZ);
            float angle = Math.abs(Vector3f.angle(motion, relPosVec));
            double lockOnPull = angle / 2F * type.lockOnForce;
            motionX += lockOnPull * dX / dXYZ;
            motionY += lockOnPull * dY / dXYZ;
            motionZ += lockOnPull * dZ / dXYZ;
        }
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        setPosition(posX, posY, posZ);
        float motionXZ = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
        rotationPitch = (float)((Math.atan2(motionY, motionXZ) * 180D) / 3.1415927410125732D);
        for(; rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F)
        {}
        for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F)
        {}
        for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F)
        {}
        for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F)
        {}
        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
    }

    public DamageSource getBulletDamage(boolean headshot)
    {
        if(owner instanceof EntityPlayer)
            return (new EntityDamageSourceGun(type.shortName, this, (EntityPlayer)owner, firedFrom, headshot)).setProjectile();
        else
            return (new EntityDamageSourceIndirect(type.shortName, this, owner)).setProjectile();
    }

    private boolean isPartOfOwner(Entity entity)
    {
        if(owner == null)
            return false;
        if(entity == owner || entity == owner.riddenByEntity || entity == owner.ridingEntity)
            return true;
        if(owner instanceof EntityPlayer)
        {
            if(PlayerHandler.getPlayerData((EntityPlayer)owner, Side.SERVER) == null)
                return false;
            EntityMG mg = PlayerHandler.getPlayerData((EntityPlayer)owner, Side.SERVER).mountingGun;
            if(mg != null && mg == entity)
                return true;
        }
        return owner.ridingEntity instanceof EntitySeat && (((EntitySeat)owner.ridingEntity).driveable == null || ((EntitySeat)owner.ridingEntity).driveable.isPartOfThis(entity));
    }

    @Override
    public void setDead()
    {
        if(isDead)
            return;
        super.setDead();
        if(type.explosionRadius > 0)
            if(owner instanceof EntityPlayer)
                new FlansModExplosion(worldObj, this, (EntityPlayer)owner, firedFrom, posX, posY, posZ, type.explosionRadius, FileAPI.configBoolean("weapons.explosions"));
            else
                worldObj.createExplosion(this, posX, posY, posZ, type.explosionRadius, FileAPI.configBoolean("weapons.explosions"));
        if(type.fireRadius > 0)
            for(float i = -type.fireRadius; i < type.fireRadius; i++)
                for(float k = -type.fireRadius; k < type.fireRadius; k++)
                    for(int j = -1; j < 1; j++)
                        if(worldObj.getBlock((int)(posX + i), (int)(posY + j), (int)(posZ + k)).getMaterial() == Material.air)
                            worldObj.setBlock((int)(posX + i), (int)(posY + j), (int)(posZ + k), Blocks.fire);
        if(type.flak > 0)
            WatchDogs.getPacketHandler().sendToAllAround(new PacketFlak(posX, posY, posZ, type.flak, type.flakParticles), posX, posY, posZ, 200, dimension);
        if(type.dropItemOnHit != null)
        {
            String itemName = type.dropItemOnHit;
            int damage = 0;
            if(itemName.contains("."))
            {
                damage = Integer.parseInt(itemName.split("\\.")[1]);
                itemName = itemName.split("\\.")[0];
            }
            ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);
            entityDropItem(dropStack, 1.0F);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag)
    {
        tag.setString("type", type.shortName);
        if(owner == null)
            tag.setString("owner", "null");
        else
            tag.setString("owner", owner.getCommandSenderName());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag)
    {
        String typeString = tag.getString("type"), ownerName = tag.getString("owner");
        if(typeString != null)
            type = BulletType.getBullet(typeString);

        if(ownerName != null && !ownerName.equals("null"))
            owner = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(ownerName);
    }

    @Override
    public float getShadowSize()
    {
        return type.hitBoxSize;
    }

    public int getBrightnessForRender(float par1)
    {
        if(type.hasLight)
            return 15728880;
        else
        {
            int i = MathHelper.floor_double(posX), j = MathHelper.floor_double(posZ);
            if(worldObj.blockExists(i, 0, j))
            {
                double d0 = (boundingBox.maxY - boundingBox.minY) * 0.66D;
                int k = MathHelper.floor_double(posY - (double)yOffset + d0);
                return worldObj.getLightBrightnessForSkyBlocks(i, k, j, 0);
            }
            else
                return 0;
        }
    }

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        data.writeDouble(motionX);
        data.writeDouble(motionY);
        data.writeDouble(motionZ);
        data.writeInt(lockedOnTo == null ? -1 : lockedOnTo.getEntityId());
        ByteBufUtils.writeUTF8String(data, type.shortName);
        if(owner == null)
            ByteBufUtils.writeUTF8String(data, "null");
        else
            ByteBufUtils.writeUTF8String(data, owner.getCommandSenderName());
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        try
        {
            motionX = data.readDouble();
            motionY = data.readDouble();
            motionZ = data.readDouble();
            int lockedOnToID = data.readInt();
            if(lockedOnToID != -1)
                lockedOnTo = worldObj.getEntityByID(lockedOnToID);
            type = BulletType.getBullet(ByteBufUtils.readUTF8String(data));
            penetratingPower = type.penetratingPower;
            String name = ByteBufUtils.readUTF8String(data);
            for(Object obj : worldObj.loadedEntityList)
                if(((Entity)obj).getCommandSenderName().equals(name))
                    owner = (EntityPlayer)obj;
        }
        catch(Exception e)
        {
            WatchDogs.print("Failed to read bullet owner from server.");
            super.setDead();
            e.printStackTrace();
        }
    }

    @Override
    public boolean isBurning()
    {
        return false;
    }
}
package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.flansmod.common.vector.Vector3f;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import fr.watchdogs.benjaminloison.driveables.IFlanItem;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.packets.PacketPlaySound;
import fr.watchdogs.benjaminloison.packets.PacketReload;
import fr.watchdogs.benjaminloison.packets.PacketSelectOffHandGun;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class ItemGun extends Item implements IFlanItem
{
    public GunType type;
    private static boolean rightMouseHeld, lastRightMouseHeld, leftMouseHeld, lastLeftMouseHeld;
    public int soundDelay;
    public HashMap<String, IIcon> icons = new HashMap<String, IIcon>();

    public ItemGun(GunType gun)
    {
        maxStackSize = 1;
        type = gun;
        type.item = this;
        setMaxDamage(type.numAmmoItemsInGun);
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    public ItemStack getBulletItemStack(ItemStack gun, int id)
    {
        if(!gun.hasTagCompound())
        {
            gun.stackTagCompound = new NBTTagCompound();
            return null;
        }
        if(!gun.stackTagCompound.hasKey("ammo"))
        {
            NBTTagList ammoTagsList = new NBTTagList();
            for(int i = 0; i < type.numAmmoItemsInGun; i++)
                ammoTagsList.appendTag(new NBTTagCompound());
            gun.stackTagCompound.setTag("ammo", ammoTagsList);
            return null;
        }
        return ItemStack.loadItemStackFromNBT(gun.stackTagCompound.getTagList("ammo", Constants.NBT.TAG_COMPOUND).getCompoundTagAt(id));
    }

    public void setBulletItemStack(ItemStack gun, ItemStack bullet, int id)
    {
        if(!gun.hasTagCompound())
            gun.stackTagCompound = new NBTTagCompound();
        if(!gun.stackTagCompound.hasKey("ammo"))
        {
            NBTTagList ammoTagsList = new NBTTagList();
            for(int i = 0; i < type.numAmmoItemsInGun; i++)
                ammoTagsList.appendTag(new NBTTagCompound());
            gun.stackTagCompound.setTag("ammo", ammoTagsList);
        }
        NBTTagCompound ammoTags = gun.stackTagCompound.getTagList("ammo", Constants.NBT.TAG_COMPOUND).getCompoundTagAt(id);
        if(bullet == null)
            ammoTags = new NBTTagCompound();
        bullet.writeToNBT(ammoTags);
    }

    public void onUpdateServer(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
        if(entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            PlayerData data = PlayerHandler.getPlayerData(player);
            if(data == null)
                return;
            if(player.inventory.getCurrentItem() != itemstack)
            {
                if(player.inventory.getCurrentItem() == null || player.inventory.getCurrentItem().getItem() == null || !(player.inventory.getCurrentItem().getItem() instanceof ItemGun))
                {
                    data.isShootingRight = data.isShootingLeft = false;
                    data.offHandGunSlot = 0;
                    (new PacketSelectOffHandGun(0)).handleServerSide(player);
                }
                return;
            }
            if(type.getFireMode(itemstack) == EnumFireMode.BURST && data.burstRoundsRemainingRight > 0)
                player.inventory.setInventorySlotContents(player.inventory.currentItem, tryToShoot(itemstack, type, world, player, false));
            if(data.isShootingRight)
            {
                if(type.getFireMode(itemstack) == EnumFireMode.FULLAUTO)
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, tryToShoot(itemstack, type, world, player, false));
                if(type.useLoopingSounds && data.loopedSoundDelay <= 0 && data.minigunSpeed > 0.1F && !data.reloadingRight)
                {
                    data.loopedSoundDelay = data.shouldPlayWarmupSound ? type.warmupSoundLength : type.loopedSoundLength;
                    PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, WatchDogs.soundRange, player.dimension, data.shouldPlayWarmupSound ? type.warmupSound : type.loopedSound, false);
                    data.shouldPlayWarmupSound = false;
                }
                if(type.getFireMode(itemstack) == EnumFireMode.MINIGUN && data.minigunSpeed > type.minigunStartSpeed)
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, tryToShoot(itemstack, type, world, player, false));
            }
            else if(type.useLoopingSounds && data.shouldPlayCooldownSound)
            {
                PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, WatchDogs.soundRange, player.dimension, type.cooldownSound, false);
                data.shouldPlayCooldownSound = false;
            }
            if(type.oneHanded && data.offHandGunSlot != 0)
            {
                ItemStack offHandGunStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
                if(offHandGunStack != null && offHandGunStack.getItem() instanceof ItemGun)
                {
                    GunType offHandGunType = ((ItemGun)offHandGunStack.getItem()).type;
                    if(offHandGunType.getFireMode(offHandGunStack) == EnumFireMode.BURST && data.burstRoundsRemainingLeft > 0)
                        player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, tryToShoot(offHandGunStack, offHandGunType, world, player, true));
                    if(data.isShootingLeft)
                    {
                        if(offHandGunType.getFireMode(offHandGunStack) == EnumFireMode.FULLAUTO)
                            player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, tryToShoot(offHandGunStack, offHandGunType, world, player, true));
                        if(offHandGunType.useLoopingSounds && data.loopedSoundDelay <= 0 && data.minigunSpeed > 0.1F && !data.reloadingLeft)
                        {
                            data.loopedSoundDelay = data.shouldPlayWarmupSound ? offHandGunType.warmupSoundLength : offHandGunType.loopedSoundLength;
                            PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, WatchDogs.soundRange, player.dimension, data.shouldPlayWarmupSound ? offHandGunType.warmupSound : offHandGunType.loopedSound, false);
                            data.shouldPlayWarmupSound = false;
                        }
                        if(offHandGunType.getFireMode(offHandGunStack) == EnumFireMode.MINIGUN && data.minigunSpeed > offHandGunType.minigunStartSpeed)
                            player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, tryToShoot(offHandGunStack, offHandGunType, world, player, true));
                    }
                    else if(offHandGunType.useLoopingSounds && data.shouldPlayCooldownSound)
                    {
                        PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, WatchDogs.soundRange, player.dimension, offHandGunType.cooldownSound, false);
                        data.shouldPlayCooldownSound = false;
                    }
                }
            }
        }
    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity pEnt, int i, boolean flag)
    {
        onUpdateServer(itemstack, world, pEnt, i, flag);
        if(pEnt instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)pEnt;
            PlayerData data = PlayerHandler.getPlayerData(player);
            if(data == null)
                return;
            if(data.meleeLength > 0 && type.meleePath.size() > 0 && player.inventory.getCurrentItem() == itemstack)
            {
                for(int k = 0; k < type.meleeDamagePoints.size(); k++)
                {
                    Vector3f meleeDamagePoint = type.meleeDamagePoints.get(k), nextPos = type.meleePath.get((data.meleeProgress + 1) % type.meleePath.size()), nextAngles = type.meleePathAngles.get((data.meleeProgress + 1) % type.meleePathAngles.size());
                    RotatedAxes nextAxes = new RotatedAxes().rotateGlobalRoll(-nextAngles.x).rotateGlobalPitch(-nextAngles.z).rotateGlobalYaw(-nextAngles.y);
                    Vector3f nextPosInGunCoords = nextAxes.findLocalVectorGlobally(meleeDamagePoint);
                    Vector3f.add(nextPos, nextPosInGunCoords, nextPosInGunCoords);
                    Vector3f.add(new Vector3f(0F, 0F, 0F), nextPosInGunCoords, nextPosInGunCoords);
                    Vector3f nextPosInPlayerCoords = new RotatedAxes(player.rotationYaw + 90F, player.rotationPitch, 0F).findLocalVectorGlobally(nextPosInGunCoords);
                    nextPosInPlayerCoords.y += 1.6F;
                    Vector3f nextPosInWorldCoords = new Vector3f(player.posX + nextPosInPlayerCoords.x, player.posY + nextPosInPlayerCoords.y, player.posZ + nextPosInPlayerCoords.z), dPos = data.lastMeleePositions[k] == null ? new Vector3f() : Vector3f.sub(nextPosInWorldCoords, data.lastMeleePositions[k], null);
                    {
                        ArrayList<BulletHit> hits = new ArrayList<BulletHit>();
                        for(int j = 0; j < world.loadedEntityList.size(); j++)
                        {
                            Object obj = world.loadedEntityList.get(j);
                            if(obj instanceof EntityPlayer)
                            {
                                EntityPlayer otherPlayer = (EntityPlayer)obj;
                                PlayerData otherData = PlayerHandler.getPlayerData(otherPlayer);
                                boolean shouldDoNormalHitDetect = false;
                                if(otherPlayer == player)
                                    continue;
                                if(otherData != null)
                                {
                                    if(otherPlayer.isDead)
                                        continue;
                                    int snapshotToTry = player instanceof EntityPlayerMP ? ((EntityPlayerMP)player).ping / 50 : 0;
                                    if(snapshotToTry >= otherData.snapshots.length)
                                        snapshotToTry = otherData.snapshots.length - 1;
                                    PlayerSnapshot snapshot = otherData.snapshots[snapshotToTry];
                                    if(snapshot == null)
                                        snapshot = otherData.snapshots[0];
                                    if(snapshot == null)
                                        shouldDoNormalHitDetect = true;
                                    else
                                        hits.addAll(snapshot.raytrace(data.lastMeleePositions[k] == null ? nextPosInWorldCoords : data.lastMeleePositions[k], dPos));
                                }
                                if(otherData == null || shouldDoNormalHitDetect)
                                {
                                    MovingObjectPosition mop = data.lastMeleePositions[k] == null ? player.boundingBox.calculateIntercept(nextPosInWorldCoords.toVec3(), Vec3.createVectorHelper(0F, 0F, 0F)) : player.boundingBox.calculateIntercept(data.lastMeleePositions[k].toVec3(), nextPosInWorldCoords.toVec3());
                                    if(mop != null)
                                    {
                                        Vector3f hitPoint = new Vector3f(mop.hitVec.xCoord - data.lastMeleePositions[k].x, mop.hitVec.yCoord - data.lastMeleePositions[k].y, mop.hitVec.zCoord - data.lastMeleePositions[k].z);
                                        float hitLambda = 1F;
                                        if(dPos.x != 0F)
                                            hitLambda = hitPoint.x / dPos.x;
                                        else if(dPos.y != 0F)
                                            hitLambda = hitPoint.y / dPos.y;
                                        else if(dPos.z != 0F)
                                            hitLambda = hitPoint.z / dPos.z;
                                        if(hitLambda < 0)
                                            hitLambda = -hitLambda;
                                        hits.add(new PlayerBulletHit(new PlayerHitbox(otherPlayer, new RotatedAxes(), new Vector3f(), new Vector3f(), new Vector3f(), EnumHitboxType.BODY), hitLambda));
                                    }
                                }
                            }
                            else
                            {
                                Entity entity = (Entity)obj;
                                if(entity != player && !entity.isDead && (entity instanceof EntityLivingBase || entity instanceof EntityAAGun))
                                {
                                    MovingObjectPosition mop = entity.boundingBox.calculateIntercept(data.lastMeleePositions[k].toVec3(), nextPosInWorldCoords.toVec3());
                                    if(mop != null)
                                    {
                                        Vector3f hitPoint = new Vector3f(mop.hitVec.xCoord - data.lastMeleePositions[k].x, mop.hitVec.yCoord - data.lastMeleePositions[k].y, mop.hitVec.zCoord - data.lastMeleePositions[k].z);
                                        float hitLambda = 1F;
                                        if(dPos.x != 0F)
                                            hitLambda = hitPoint.x / dPos.x;
                                        else if(dPos.y != 0F)
                                            hitLambda = hitPoint.y / dPos.y;
                                        else if(dPos.z != 0F)
                                            hitLambda = hitPoint.z / dPos.z;
                                        if(hitLambda < 0)
                                            hitLambda = -hitLambda;
                                        hits.add(new EntityHit(entity, hitLambda));
                                    }
                                }
                            }
                        }
                        if(!hits.isEmpty())
                        {
                            Collections.sort(hits);
                            float swingDistance = dPos.length();
                            for(BulletHit bulletHit : hits)
                            {
                                if(bulletHit instanceof PlayerBulletHit)
                                {
                                    PlayerBulletHit playerHit = (PlayerBulletHit)bulletHit;
                                    float damageMultiplier = 1F;
                                    switch(playerHit.hitbox.type)
                                    {
                                        case RIGHTITEM:
                                        {
                                            data.meleeProgress = data.meleeLength = 0;
                                            return;
                                        }
                                        case HEAD:
                                            damageMultiplier = 2F;
                                            break;
                                        case LEFTARM:
                                            damageMultiplier = 0.6F;
                                            break;
                                        default:
                                    }
                                    if(playerHit.hitbox.player.attackEntityFrom(getMeleeDamage(player), swingDistance * type.meleeDamage))
                                    {
                                        playerHit.hitbox.player.arrowHitTimer++;
                                        playerHit.hitbox.player.hurtResistantTime = playerHit.hitbox.player.maxHurtResistantTime / 2;
                                    }
                                }
                                else if(bulletHit instanceof EntityHit)
                                {
                                    EntityHit entityHit = (EntityHit)bulletHit;
                                    if(entityHit.entity.attackEntityFrom(DamageSource.causePlayerDamage(player), swingDistance * type.meleeDamage) && entityHit.entity instanceof EntityLivingBase)
                                    {
                                        EntityLivingBase living = (EntityLivingBase)entityHit.entity;

                                        living.arrowHitTimer++;
                                        living.hurtResistantTime = living.maxHurtResistantTime / 2;
                                    }
                                }
                            }
                        }
                    }
                    data.lastMeleePositions[k] = nextPosInWorldCoords;
                }
                data.meleeProgress++;
                if(data.meleeProgress == data.meleeLength)
                    data.meleeProgress = data.meleeLength = 0;
            }
        }
    }

    public DamageSource getMeleeDamage(EntityPlayer attacker)
    {
        return new EntityDamageSourceGun(type.shortName, attacker, attacker, type, false);
    }

    public void onMouseHeld(ItemStack stack, World world, EntityPlayerMP player, boolean left, boolean isShooting)
    {
        PlayerData data = PlayerHandler.getPlayerData(player);
        if(data != null && data.shootClickDelay == 0)
        {
            if(player.ridingEntity instanceof EntitySeat && ((EntitySeat)player.ridingEntity).seatInfo.id == 0)
                return;
            if(left && data.offHandGunSlot != 0)
            {
                ItemStack offHandGunStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
                GunType gunType = ((ItemGun)offHandGunStack.getItem()).type;
                data.isShootingLeft = isShooting;
                if(gunType.getFireMode(offHandGunStack) == EnumFireMode.SEMIAUTO && isShooting)
                {
                    data.isShootingLeft = false;
                    player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, tryToShoot(offHandGunStack, gunType, world, player, true));
                }
                if(gunType.getFireMode(offHandGunStack) == EnumFireMode.BURST && isShooting && data.burstRoundsRemainingLeft == 0)
                {
                    data.isShootingLeft = false;
                    data.burstRoundsRemainingLeft = gunType.numBurstRounds;
                    player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, tryToShoot(offHandGunStack, gunType, world, player, true));
                }
            }
            else
            {
                data.isShootingRight = isShooting;
                if(type.getFireMode(stack) == EnumFireMode.SEMIAUTO && isShooting)
                {
                    data.isShootingRight = false;
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, tryToShoot(stack, type, world, player, false));
                }
                if(type.getFireMode(stack) == EnumFireMode.BURST && isShooting && data.burstRoundsRemainingRight == 0)
                {
                    data.isShootingRight = false;
                    data.burstRoundsRemainingRight = type.numBurstRounds;
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, tryToShoot(stack, type, world, player, false));
                }
            }
            if(type.useLoopingSounds && isShooting)
                data.shouldPlayWarmupSound = true;
        }
    }

    public ItemStack tryToShoot(ItemStack gunStack, GunType gunType, World world, EntityPlayerMP entityplayer, boolean left)
    {
        if(type.deployable)
            return gunStack;
        PlayerData data = PlayerHandler.getPlayerData(entityplayer);
        if((left && data.shootTimeLeft <= 0) || (!left && data.shootTimeRight <= 0))
        {
            int bulletID = 0;
            ItemStack bulletStack = null;
            for(; bulletID < gunType.numAmmoItemsInGun; bulletID++)
            {
                ItemStack checkingStack = getBulletItemStack(gunStack, bulletID);
                if(checkingStack != null && checkingStack.getItem() != null && checkingStack.getItemDamage() < checkingStack.getMaxDamage())
                {
                    bulletStack = checkingStack;
                    break;
                }
            }
            if(bulletStack == null)
            {
                if(reload(gunStack, gunType, world, entityplayer, false, left))
                {
                    data.shootTimeRight = data.shootTimeLeft = (int)gunType.getReloadTime(gunStack);
                    if(left)
                    {
                        data.reloadingLeft = true;
                        data.burstRoundsRemainingLeft = 0;
                    }
                    else
                    {
                        data.reloadingRight = true;
                        data.burstRoundsRemainingRight = 0;
                    }
                    WatchDogs.getPacketHandler().sendTo(new PacketReload(left), entityplayer);
                    if(gunType.reloadSound != null)
                        PacketPlaySound.sendSoundPacket(entityplayer.posX, entityplayer.posY, entityplayer.posZ, WatchDogs.soundRange, entityplayer.dimension, gunType.reloadSound, true);
                }
            }
            else if(bulletStack.getItem() instanceof ItemShootable)
            {
                shoot(gunStack, gunType, world, bulletStack, entityplayer, left);
                bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
                setBulletItemStack(gunStack, bulletStack, bulletID);
                if(gunType.getFireMode(gunStack) == EnumFireMode.BURST)
                {
                    if(left && data.burstRoundsRemainingLeft > 0)
                        data.burstRoundsRemainingLeft--;
                    if(!left && data.burstRoundsRemainingRight > 0)
                        data.burstRoundsRemainingRight--;
                }
                if(gunType.consumeGunUponUse)
                    return null;
            }
        }
        return gunStack;
    }

    public boolean reload(ItemStack gunStack, GunType gunType, World world, EntityPlayer player, boolean forceReload, boolean left)
    {
        return reload(gunStack, gunType, world, player, player.inventory, player.capabilities.isCreativeMode, forceReload);
    }

    public boolean reload(ItemStack gunStack, GunType gunType, World world, Entity entity, IInventory inventory, boolean creative, boolean forceReload)
    {
        if(gunType.deployable)
            return false;
        if(forceReload && !gunType.canForceReload)
            return false;
        boolean reloadedSomething = false;
        for(int i = 0; i < gunType.numAmmoItemsInGun; i++)
        {
            ItemStack bulletStack = getBulletItemStack(gunStack, i);
            if(bulletStack == null || bulletStack.getItemDamage() == bulletStack.getMaxDamage() || forceReload)
            {
                int bestSlot = -1, bulletsInBestSlot = 0;
                for(int j = 0; j < inventory.getSizeInventory(); j++)
                {
                    ItemStack item = inventory.getStackInSlot(j);
                    if(item != null && item.getItem() instanceof ItemShootable && gunType.isAmmo(((ItemShootable)(item.getItem())).type))
                    {
                        int bulletsInThisSlot = item.getMaxDamage() - item.getItemDamage();
                        if(bulletsInThisSlot > bulletsInBestSlot)
                        {
                            bestSlot = j;
                            bulletsInBestSlot = bulletsInThisSlot;
                        }
                    }
                }
                if(bestSlot != -1)
                {
                    ItemStack newBulletStack = inventory.getStackInSlot(bestSlot);
                    ShootableType newBulletType = ((ItemShootable)newBulletStack.getItem()).type;
                    if(bulletStack != null && bulletStack.getItem() instanceof ItemShootable && ((ItemShootable)bulletStack.getItem()).type.dropItemOnReload != null && !creative)
                        dropItem(world, entity, ((ItemShootable)bulletStack.getItem()).type.dropItemOnReload);
                    if(bulletStack != null && bulletStack.getItemDamage() < bulletStack.getMaxDamage())
                        if(!InventoryHelper.addItemStackToInventory(inventory, bulletStack, creative))
                            entity.entityDropItem(bulletStack, 0.5F);
                    ItemStack stackToLoad = newBulletStack.copy();
                    stackToLoad.stackSize = 1;
                    setBulletItemStack(gunStack, stackToLoad, i);
                    if(!creative)
                        newBulletStack.stackSize--;
                    if(newBulletStack.stackSize <= 0)
                        newBulletStack = null;
                    inventory.setInventorySlotContents(bestSlot, newBulletStack);
                    reloadedSomething = true;
                }
            }
        }
        return reloadedSomething;
    }

    public static void dropItem(World world, Entity entity, String itemName)
    {
        if(itemName != null)
        {
            int damage = 0;
            if(itemName.contains("."))
            {
                damage = Integer.parseInt(itemName.split("\\.")[1]);
                itemName = itemName.split("\\.")[0];
            }
            ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);
            entity.entityDropItem(dropStack, 0.5F);
        }
    }

    private void shoot(ItemStack stack, GunType gunType, World world, ItemStack bulletStack, EntityPlayer entityplayer, boolean left)
    {
        ShootableType bullet = ((ItemShootable)bulletStack.getItem()).type;
        if(soundDelay <= 0 && gunType.shootSound != null)
        {
            AttachmentType barrel = gunType.getBarrel(stack);
            boolean silenced = barrel != null && barrel.silencer;
            PacketPlaySound.sendSoundPacket(entityplayer.posX, entityplayer.posY, entityplayer.posZ, WatchDogs.soundRange, entityplayer.dimension, gunType.shootSound, gunType.distortSound, silenced);
            soundDelay = gunType.shootSoundLength;
        }
        for(int k = 0; k < gunType.numBullets; k++)
            world.spawnEntityInWorld(((ItemShootable)bulletStack.getItem()).getEntity(world, entityplayer, (entityplayer.isSneaking() ? 0.7F : 1F) * gunType.getSpread(stack), gunType.getDamage(stack), gunType.getBulletSpeed(stack), gunType.numBullets > 1, bulletStack.getItemDamage(), gunType));
        if(bullet.dropItemOnShoot != null && !entityplayer.capabilities.isCreativeMode)
            dropItem(world, entityplayer, bullet.dropItemOnShoot);
        if(gunType.dropItemOnShoot != null)
            dropItem(world, entityplayer, gunType.dropItemOnShoot);
        if(left)
            PlayerHandler.getPlayerData(entityplayer).shootTimeLeft = gunType.shootDelay;
        else
            PlayerHandler.getPlayerData(entityplayer).shootTimeRight = gunType.shootDelay;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if(type.deployable)
        {
            float cosYaw = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F - 3.141593F), sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F - 3.141593F), cosPitch = -MathHelper.cos(-entityplayer.rotationPitch * 0.01745329F), sinPitch = MathHelper.sin(-entityplayer.rotationPitch * 0.01745329F);
            double length = 5D;
            Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62D - entityplayer.yOffset, entityplayer.posZ), lookVec = posVec.addVector(sinYaw * cosPitch * length, sinPitch * length, cosYaw * cosPitch * length);
            MovingObjectPosition look = world.rayTraceBlocks(posVec, lookVec, true);
            if(look != null && look.typeOfHit == MovingObjectType.BLOCK)
                if(look.sideHit == 1)
                {
                    int playerDir = MathHelper.floor_double(((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3, i = look.blockX, j = look.blockY, k = look.blockZ;
                    if(world.getBlock(i, j, k) == Blocks.snow)
                        j--;
                    if(isSolid(world, i, j, k) && (world.getBlock(i, j + 1, k) == Blocks.air || world.getBlock(i, j + 1, k) == Blocks.snow) && (world.getBlock(i + (playerDir == 1 ? 1 : 0) - (playerDir == 3 ? 1 : 0), j + 1, k - (playerDir == 0 ? 1 : 0) + (playerDir == 2 ? 1 : 0)) == Blocks.air) && (world.getBlock(i + (playerDir == 1 ? 1 : 0) - (playerDir == 3 ? 1 : 0), j, k - (playerDir == 0 ? 1 : 0) + (playerDir == 2 ? 1 : 0)) == Blocks.air || world.getBlock(i + (playerDir == 1 ? 1 : 0) - (playerDir == 3 ? 1 : 0), j, k - (playerDir == 0 ? 1 : 0) + (playerDir == 2 ? 1 : 0)) == Blocks.snow))
                    {
                        for(EntityMG mg : EntityMG.mgs)
                            if(mg.blockX == i && mg.blockY == j + 1 && mg.blockZ == k && !mg.isDead)
                                return itemstack;
                        world.spawnEntityInWorld(new EntityMG(world, i, j + 1, k, playerDir, type));
                        if(!entityplayer.capabilities.isCreativeMode)
                            itemstack.stackSize = 0;
                    }
                }
        }
        return itemstack;
    }

    private boolean isSolid(World world, int i, int j, int k)
    {
        Block block = world.getBlock(i, j, k);
        return block != null && block.getMaterial().isSolid() && block.isOpaqueCube();
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return type.secondaryFunction != EnumSecondaryFunction.MELEE;
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        if(type.meleeSound != null)
            PacketPlaySound.sendSoundPacket(entityLiving.posX, entityLiving.posY, entityLiving.posZ, WatchDogs.soundRange, entityLiving.dimension, type.meleeSound, true);
        if(type.secondaryFunction == EnumSecondaryFunction.CUSTOM_MELEE)
            if(entityLiving instanceof EntityPlayer)
                PlayerHandler.getPlayerData((EntityPlayer)entityLiving).doMelee((EntityPlayer)entityLiving, type.meleeTime, type);
        return type.secondaryFunction != EnumSecondaryFunction.MELEE;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        return true;
    }

    @Override
    public boolean func_150897_b(Block b)
    {
        return false;
    }

    public boolean isItemStackDamageable()
    {
        return true;
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        ItemStack gunStack = new ItemStack(item, 1, 0);
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("Paint", (((ItemGun)item).type).defaultPaintjob.iconName);
        gunStack.stackTagCompound = tags;
        list.add(gunStack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 100;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack)
    {
        Multimap map = super.getAttributeModifiers(stack);
        map.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "KnockbackResist", type.knockbackModifier, 0));
        map.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "MovementSpeed", type.moveSpeedModifier - 1F, 2));
        if(type.secondaryFunction == EnumSecondaryFunction.MELEE)
            map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", type.meleeDamage, 0));
        return map;
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }
}
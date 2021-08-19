package fr.watchdogs.benjaminloison.common.teams;

import java.util.ArrayList;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.EntityGrenade;
import fr.watchdogs.benjaminloison.common.guns.EntityMG;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.ItemGun;
import fr.watchdogs.benjaminloison.common.guns.PlayerSnapshot;
import fr.watchdogs.benjaminloison.packets.PacketSelectOffHandGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerData
{
    public String username;
    public float prevRotationRoll, rotationRoll, minigunSpeed;
    public PlayerSnapshot[] snapshots;
    public int offHandGunSlot, shootTimeRight, shootTimeLeft, shootClickDelay, loopedSoundDelay, meleeProgress, meleeLength, burstRoundsRemainingLeft, burstRoundsRemainingRight;
    public EntityMG mountingGun;
    public boolean out, isShootingRight, isShootingLeft, reloadingRight, reloadingLeft, shouldPlayCooldownSound, shouldPlayWarmupSound;
    public ArrayList<EntityGrenade> remoteExplosives = new ArrayList<EntityGrenade>();
    public Vector3f[] lastMeleePositions;

    public PlayerData(String name)
    {
        username = name;
        snapshots = new PlayerSnapshot[WatchDogs.numPlayerSnapshots];
    }

    public void tick(EntityPlayer player)
    {
        if(shootTimeRight > 0)
            shootTimeRight--;
        if(shootTimeRight == 0)
            reloadingRight = false;
        if(shootTimeLeft > 0)
            shootTimeLeft--;
        if(shootTimeLeft == 0)
            reloadingLeft = false;
        if(shootClickDelay > 0)
            shootClickDelay--;
        if(isShootingRight && !reloadingRight)
            minigunSpeed += 2F;
        minigunSpeed *= 0.9F;
        if(loopedSoundDelay > 0)
            loopedSoundDelay--;
        if(loopedSoundDelay == 0 && !isShootingRight)
            shouldPlayCooldownSound = true;
        System.arraycopy(snapshots, 0, snapshots, 1, snapshots.length - 2 + 1);
        snapshots[0] = new PlayerSnapshot(player);
    }

    public void playerKilled()
    {
        mountingGun = null;
        isShootingRight = isShootingLeft = false;
        snapshots = new PlayerSnapshot[WatchDogs.numPlayerSnapshots];
    }

    public void selectOffHandWeapon(EntityPlayer player, int slot)
    {
        if(isValidOffHandWeapon(player, slot))
            offHandGunSlot = slot;
    }

    public boolean isValidOffHandWeapon(EntityPlayer player, int slot)
    {
        if(slot == 0)
            return true;
        if(slot - 1 == player.inventory.currentItem)
            return false;
        ItemStack stackInSlot = player.inventory.getStackInSlot(slot - 1);
        if(stackInSlot == null)
            return false;
        if(stackInSlot.getItem() instanceof ItemGun)
            if(((ItemGun)stackInSlot.getItem()).type.oneHanded)
                return true;
        return false;
    }

    public void cycleOffHandItem(EntityPlayer player, int dWheel)
    {
        if(dWheel < 0)
            for(offHandGunSlot = ((offHandGunSlot + 1) % 10); !isValidOffHandWeapon(player, offHandGunSlot); offHandGunSlot = ((offHandGunSlot + 1) % 10));
        else if(dWheel > 0)
            for(offHandGunSlot = ((offHandGunSlot + 9) % 10); !isValidOffHandWeapon(player, offHandGunSlot); offHandGunSlot = ((offHandGunSlot + 9) % 10));
        WatchDogs.getPacketHandler().sendToServer(new PacketSelectOffHandGun(offHandGunSlot));
    }

    public void doMelee(EntityPlayer player, int meleeTime, GunType type)
    {
        meleeLength = meleeTime;
        lastMeleePositions = new Vector3f[type.meleePath.size()];
        for(int k = 0; k < type.meleeDamagePoints.size(); k++)
        {
            Vector3f nextAngles = type.meleePathAngles.get(0), nextPosInPlayerCoords = new RotatedAxes(player.rotationYaw + 90, player.rotationPitch, 0).findLocalVectorGlobally(new RotatedAxes(-nextAngles.y, -nextAngles.z, nextAngles.x).findLocalVectorGlobally(type.meleeDamagePoints.get(k)));
            Vector3f.add(type.meleePath.get(0), nextPosInPlayerCoords, nextPosInPlayerCoords);
            lastMeleePositions[k] = new Vector3f(player.posX + nextPosInPlayerCoords.x, player.posY + nextPosInPlayerCoords.y, player.posZ + nextPosInPlayerCoords.z);
        }
    }
}
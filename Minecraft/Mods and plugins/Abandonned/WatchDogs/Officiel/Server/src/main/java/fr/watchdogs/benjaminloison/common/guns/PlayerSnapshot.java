package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;

import com.flansmod.common.vector.Vector3f;

import fr.watchdogs.benjaminloison.api.RotatedAxes;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class PlayerSnapshot
{
    public EntityPlayer player;
    public Vector3f pos;
    public ArrayList<PlayerHitbox> hitboxes;
    public long time;

    public PlayerSnapshot(EntityPlayer p)
    {
        player = p;
        pos = new Vector3f(p.posX, p.posY, p.posZ);
        hitboxes = new ArrayList<PlayerHitbox>();
        RotatedAxes bodyAxes = new RotatedAxes(p.renderYawOffset, 0F, 0F), headAxes = new RotatedAxes(p.rotationYawHead - p.renderYawOffset, 0F, -p.rotationPitch);
        hitboxes.add(new PlayerHitbox(player, bodyAxes, new Vector3f(0F, 0F, 0F), new Vector3f(-0.25F, 0F, -0.15F), new Vector3f(0.5F, 1.4F, 0.3F), EnumHitboxType.BODY));
        hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(headAxes), new Vector3f(0.0F, 1.4F, 0F), new Vector3f(-0.25F, 0F, -0.25F), new Vector3f(0.5F, 0.5F, 0.5F), EnumHitboxType.HEAD));
        float yHead = (p.rotationYawHead - p.renderYawOffset) / (180F / (float)Math.PI), xHead = p.rotationPitch / (180F / (float)Math.PI), zRight = 0.0F, zLeft = 0.0F, yRight = -0.1F + yHead - ((float)Math.PI / 2F), yLeft = 0.1F + yHead + 0.4F - ((float)Math.PI / 2F), xRight = -((float)Math.PI / 2F) + xHead, xLeft = -((float)Math.PI / 2F) + xHead;
        zRight += MathHelper.cos(p.ticksExisted * 0.09F) * 0.05F + 0.05F;
        zLeft -= MathHelper.cos(p.ticksExisted * 0.09F) * 0.05F + 0.05F;
        xRight += MathHelper.sin(p.ticksExisted * 0.067F) * 0.05F;
        xLeft -= MathHelper.sin(p.ticksExisted * 0.067F) * 0.05F;
        RotatedAxes leftArmAxes = (new RotatedAxes()).rotateGlobalPitchInRads(xLeft).rotateGlobalYawInRads((float)Math.PI + yLeft).rotateGlobalRollInRads(-zLeft), rightArmAxes = (new RotatedAxes()).rotateGlobalPitchInRads(xRight).rotateGlobalYawInRads((float)Math.PI + yRight).rotateGlobalRollInRads(-zRight);
        float originZRight = MathHelper.sin(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F, originXRight = -MathHelper.cos(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F, originZLeft = -MathHelper.sin(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F, originXLeft = MathHelper.cos(-p.renderYawOffset * 3.14159265F / 180F) * 5.0F / 16F;
        hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(leftArmAxes), new Vector3f(originXLeft, 1.3F, originZLeft), new Vector3f(-2F / 16F, -0.6F, -2F / 16F), new Vector3f(0.25F, 0.7F, 0.25F), EnumHitboxType.LEFTARM));
        hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(rightArmAxes), new Vector3f(originXRight, 1.3F, originZRight), new Vector3f(-2F / 16F, -0.6F, -2F / 16F), new Vector3f(0.25F, 0.7F, 0.25F), EnumHitboxType.RIGHTARM));
        ItemStack playerRightHandStack = player.getCurrentEquippedItem();
        if(playerRightHandStack != null && playerRightHandStack.getItem() instanceof ItemGun)
        {
            GunType gunType = ((ItemGun)playerRightHandStack.getItem()).type;
            if(gunType.shield)
                hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(rightArmAxes), new Vector3f(originXRight, 1.3F, originZRight), new Vector3f(gunType.shieldOrigin.y, -1.05F + gunType.shieldOrigin.x, -1F / 16F + gunType.shieldOrigin.z), new Vector3f(gunType.shieldDimensions.y, gunType.shieldDimensions.x, gunType.shieldDimensions.z), EnumHitboxType.RIGHTITEM));
            PlayerData data = PlayerHandler.getPlayerData(player);
            if(gunType.oneHanded && data.offHandGunSlot != 0)
            {
                ItemStack leftHandStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
                if(leftHandStack != null && leftHandStack.getItem() instanceof ItemGun)
                {
                    GunType leftGunType = ((ItemGun)leftHandStack.getItem()).type;
                    if(leftGunType.shield)
                        hitboxes.add(new PlayerHitbox(player, bodyAxes.findLocalAxesGlobally(leftArmAxes), new Vector3f(originXLeft, 1.3F, originZLeft), new Vector3f(leftGunType.shieldOrigin.y, -1.05F + leftGunType.shieldOrigin.x, -1F / 16F + leftGunType.shieldOrigin.z), new Vector3f(leftGunType.shieldDimensions.y, leftGunType.shieldDimensions.x, leftGunType.shieldDimensions.z), EnumHitboxType.LEFTITEM));
                }
            }
        }
    }

    public ArrayList<BulletHit> raytrace(Vector3f origin, Vector3f motion)
    {
        ArrayList<BulletHit> hits = new ArrayList<BulletHit>();
        for(PlayerHitbox hitbox : hitboxes)
        {
            PlayerBulletHit hit = hitbox.raytrace(Vector3f.sub(origin, pos, null), motion);
            if(hit != null && hit.intersectTime >= 0F && hit.intersectTime <= 1F)
                hits.add(hit);
        }
        return hits;
    }

    public void renderSnapshot()
    {
        for(PlayerHitbox hitbox : hitboxes)
            hitbox.renderHitbox(player.worldObj, pos);
    }
}
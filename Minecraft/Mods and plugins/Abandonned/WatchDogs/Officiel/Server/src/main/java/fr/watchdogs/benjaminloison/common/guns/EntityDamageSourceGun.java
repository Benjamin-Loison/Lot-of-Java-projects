package fr.watchdogs.benjaminloison.common.guns;

import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;

public class EntityDamageSourceGun extends EntityDamageSourceIndirect
{
    public InfoType weapon;
    public EntityPlayer shooter;
    public boolean headshot;

    public EntityDamageSourceGun(String s, Entity entity, EntityPlayer player, InfoType wep, boolean head)
    {
        super(s, entity, player);
        weapon = wep;
        shooter = player;
        headshot = head;
    }

    @Override
    public IChatComponent func_151519_b(EntityLivingBase living)
    {
        if(!(living instanceof EntityPlayer) || shooter == null || PlayerHandler.getPlayerData(shooter) == null)
            return super.func_151519_b(living);
        return new ChatComponentText("");
    }
}
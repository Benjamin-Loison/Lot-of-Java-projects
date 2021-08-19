package fr.watchdogs.benjaminloison.common.guns;

import com.flansmod.common.vector.Vector3f;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import fr.watchdogs.benjaminloison.common.teams.PlayerData;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.driveables.IFlanItem;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemGrenade extends ItemShootable implements IFlanItem
{
    public GrenadeType type;

    public ItemGrenade(GrenadeType t)
    {
        super(t);
        type = t;
        type.item = this;
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack)
    {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", type.meleeDamage, 0));
        return multimap;
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return type.meleeDamage == 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        PlayerData data = PlayerHandler.getPlayerData(player, Side.SERVER);
        if(type.canThrow && data != null && data.shootTimeRight <= 0 && data.shootTimeLeft <= 0)
        {
            data.shootTimeRight = type.throwDelay;
            EntityGrenade grenade = new EntityGrenade(world, type, player);
            world.spawnEntityInWorld(grenade);
            if(type.remote)
                data.remoteExplosives.add(grenade);
            if(!player.capabilities.isCreativeMode)
                stack.stackSize--;
            if(type.dropItemOnThrow != null)
            {
                String itemName = type.dropItemOnDetonate;
                int damage = 0;
                if(itemName.contains("."))
                {
                    damage = Integer.parseInt(itemName.split("\\.")[1]);
                    itemName = itemName.split("\\.")[0];
                }
                world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, InfoType.getRecipeElement(itemName, damage)));
            }
        }
        return stack;
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }

    @Override
    public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, double motionX, double motionY, double motionZ, EntityLivingBase shooter, float gunDamage, int itemDamage, InfoType shotFrom)
    {
        return null;
    }

    @Override
    public EntityShootable getEntity(World worldObj, Vector3f origin, Vector3f direction, EntityLivingBase thrower, float spread, float damage, float speed, int itemDamage, InfoType shotFrom)
    {
        return getGrenade(worldObj, thrower);
    }

    @Override
    public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float damage, int itemDamage, InfoType shotFrom)
    {
        return null;
    }

    @Override
    public EntityShootable getEntity(World worldObj, EntityLivingBase player, float bulletSpread, float damage, float bulletSpeed, boolean b, int itemDamage, InfoType shotFrom)
    {
        return getGrenade(worldObj, player);
    }

    public EntityGrenade getGrenade(World world, EntityLivingBase thrower)
    {
        EntityGrenade grenade = new EntityGrenade(world, type, thrower);
        if(type.remote && thrower instanceof EntityPlayer)
            PlayerHandler.getPlayerData((EntityPlayer)thrower).remoteExplosives.add(grenade);
        return grenade;
    }
}
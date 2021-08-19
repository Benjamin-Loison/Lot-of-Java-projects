package fr.watchdogs.benjaminloison.common.teams;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.IFlanItem;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class ItemTeamArmour extends ItemArmor implements ISpecialArmor, IFlanItem
{
    public ArmourType type;
    protected static final UUID[] uuid = new UUID[] {UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()};

    public ItemTeamArmour(ArmourType t)
    {
        super(ItemArmor.ArmorMaterial.CLOTH, 0, t.type);
        type = t;
        type.item = this;
        GameRegistry.registerItem(this, type.shortName, WatchDogs.MODID);
    }

    public ItemTeamArmour(ItemArmor.ArmorMaterial armorMaterial, int renderIndex, int armourType)
    {
        super(armorMaterial, renderIndex, armourType);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return new ArmorProperties(1, type.defence, Integer.MAX_VALUE);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return (int)(type.defence * 20);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {}

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String s)
    {
        return WatchDogs.MODID + ":armor/" + type.armourTextureName + "_" + (type.type == 2 ? "2" : "1") + ".png";
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b)
    {
        if(type.description != null)
        {
            Collections.addAll(lines, type.description.split("_"));
        }
        if(Math.abs(type.jumpModifier - 1) > 0.01)
            lines.add("\u00a73+" + (int)((type.jumpModifier - 1) * 100) + "% Jump Height");
        if(type.smokeProtection)
            lines.add("\u00a72+Smoke Protection");
        if(type.nightVision)
            lines.add("\u00a72+Night Vision");
        if(type.negateFallDamage)
            lines.add("\u00a72+Negates Fall Damage");
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack)
    {
        Multimap map = super.getAttributeModifiers(stack);
        map.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(uuid[type.type], "KnockbackResist", type.knockbackModifier, 0));
        map.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(uuid[type.type], "MovementSpeed", type.moveSpeedModifier - 1, 2));
        return map;
    }

    @Override
    public InfoType getInfoType()
    {
        return type;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if(type.nightVision && WatchDogs.ticker % 25 == 0)
            player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 250));
        if(type.jumpModifier > 1.01 && WatchDogs.ticker % 25 == 0)
            player.addPotionEffect(new PotionEffect(Potion.jump.id, 250, (int)((type.jumpModifier - 1) * 2), true));
        if(type.negateFallDamage)
            player.fallDistance = 0;
    }
}
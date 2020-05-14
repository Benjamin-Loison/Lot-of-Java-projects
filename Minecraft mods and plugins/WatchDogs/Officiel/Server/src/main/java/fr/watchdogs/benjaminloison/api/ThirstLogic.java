package fr.watchdogs.benjaminloison.api;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.packets.NetworkHandler;
import fr.watchdogs.benjaminloison.packets.PacketUpdateClient0;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class ThirstLogic
{
    public EntityPlayer player;
    public int thirstLevel, movementSpeed, timer;
    public float thirstSaturation, thirstExhaustion;
    public DamageThirst thirst;
    private Config config = WatchDogs.config;
    public PoisonLogic poisonLogic = new PoisonLogic();

    public ThirstLogic(EntityPlayer player)
    {
        thirstLevel = Constants.MAX_LEVEL;
        thirstSaturation = Constants.MAX_SATURATION;
        this.player = player;
        thirst = new DamageThirst();
        readData();
    }

    public void onTick()
    {
        int difSet = WatchDogs.config.PEACEFUL_ON ? 1 : player.worldObj.difficultySetting.getDifficultyId();
        if(thirstExhaustion > 5)
        {
            thirstExhaustion = 0;
            if(thirstSaturation > 0)
                thirstSaturation = Math.max(thirstSaturation - 1, 0);
            else if(difSet > 0)
                thirstLevel = Math.max(thirstLevel - 1, 0);
        }
        if(thirstLevel <= 6)
        {
            player.setSprinting(false);
            if(thirstLevel <= 0)
            {
                timer++;
                if(timer > 200)
                    if((player.getHealth() > 10) || (player.getHealth() > (WatchDogs.config.DEATH_FROM_THIRST ? 0 : (difSet == 3 ? 0 : 1)) && difSet >= 2))
                    {
                        player.attackEntityFrom(thirst, 1);
                        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 1));
                        timer = 0;
                    }
            }
        }
        computeExhaustion();
        poisonLogic.onTick(player);
        writeData();
        NetworkHandler.networkWrapper.sendTo(new PacketUpdateClient0(this), (EntityPlayerMP)player);
    }

    public void readData()
    {
        if(player != null)
        {
            NBTTagCompound oldnbt = player.getEntityData(), nbt = oldnbt.getCompoundTag("ThirstMod");
            if(nbt.hasKey("level"))
            {
                thirstLevel = nbt.getInteger("level");
                thirstExhaustion = nbt.getFloat("exhaustion");
                thirstSaturation = nbt.getFloat("saturation");
                timer = nbt.getInteger("timer");
                poisonLogic.changeValues(nbt.getBoolean("poisoned"), nbt.getInteger("poisonTime"));
            }
        }
    }

    public void writeData()
    {
        if(player != null)
        {
            NBTTagCompound oldNBT = player.getEntityData(), nbt = oldNBT.getCompoundTag("ThirstMod");
            if(!oldNBT.hasKey("ThirstMod"))
                oldNBT.setTag("ThirstMod", nbt);
            nbt.setInteger("level", thirstLevel);
            nbt.setFloat("exhaustion", thirstExhaustion);
            nbt.setFloat("saturation", thirstSaturation);
            nbt.setInteger("timer", timer);
            nbt.setBoolean("poisoned", poisonLogic.isPlayerPoisoned());
            nbt.setInteger("poisonTime", poisonLogic.getPoisonTimeRemaining());
        }
    }

    public void computeExhaustion()
    {
        int movement = player.isRiding() ? 0 : movementSpeed;
        float exhaustAmplifier = isNight() ? config.NIGHT_RATE : 1, multiplier = getCurrentBiome(player).equals("Desert") ? config.DESERT_RATE : 1;
        if(player.isInsideOfMaterial(Material.water))
        {
            if(movement > 0)
                addExhaustion(config.IN_WATER_RATE * movement * 0.003F * exhaustAmplifier);
        }
        else if(player.isInWater())
        {
            if(movement > 0)
                addExhaustion(config.IN_WATER_RATE * movement * 0.003F * exhaustAmplifier);
        }
        else if(player.onGround)
        {
            if(movement > 0)
                if(player.isSprinting())
                    addExhaustion(config.RUNNING_RATE * movement * 0.018F * multiplier * exhaustAmplifier);
                else
                    addExhaustion(config.WALKING_RATE * movement * 0.018F * multiplier * exhaustAmplifier);
        }
        else if(!player.onGround && !player.isRiding())
            if(player.isSprinting())
                addExhaustion((config.JUMP_RATE * 2) * multiplier * exhaustAmplifier);
            else
                addExhaustion(config.JUMP_RATE * multiplier * exhaustAmplifier);
    }

    public boolean isNight()
    {
        return !player.worldObj.isDaytime();
    }

    public static String getCurrentBiome(EntityPlayer player)
    {
        return player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX, (int)player.posZ).biomeName;
    }

    public void addStats(int thirst, float sat)
    {
        thirstLevel = Math.min(thirst + thirstLevel, Constants.MAX_LEVEL);
        thirstSaturation = Math.min(thirstSaturation + (thirst * sat * 2), thirstLevel);
    }

    public void addExhaustion(float exh)
    {
        thirstExhaustion = Math.min(thirstExhaustion + exh, 40);
    }

    public void setStats(int level, float sat)
    {
        thirstLevel = level;
        thirstSaturation = sat;
    }

    public boolean isThirstAllowedByDifficulty()
    {
        if(WatchDogs.config.PEACEFUL_ON)
            return true;
        else
            return player.worldObj.difficultySetting.getDifficultyId() > 0;
    }

    @Override
    public String toString()
    {
        return String.format("%s, Level = %d, Saturation = %.2f, Exhaustion = %.2f", player.getDisplayName(), thirstLevel, thirstSaturation, thirstExhaustion);
    }

    public static class DamageThirst extends DamageSource
    {
        public DamageThirst()
        {
            super("thirst");
            setDamageBypassesArmor();
            setDamageIsAbsolute();
        }
    }
}
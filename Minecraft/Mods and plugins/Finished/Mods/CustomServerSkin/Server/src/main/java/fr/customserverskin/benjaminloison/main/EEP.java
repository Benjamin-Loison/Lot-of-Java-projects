package fr.customserverskin.benjaminloison.main;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class EEP implements IExtendedEntityProperties
{
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
    public static final Map<String, String> players = new HashMap<String, String>();
    private EntityPlayer player;
    public String skin;

    public EEP(EntityPlayer player)
    {
        this.player = player;
        skin = "";
    }

    public void setSkin(String skin)
    {
    	this.skin = skin;
    	players.put(player.getDisplayName(), skin);
        sync();
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(CustomServerSkin.NAME, new EEP(player));
    }

    public static final EEP get(EntityPlayer player)
    {
        return (EEP)player.getExtendedProperties(CustomServerSkin.NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setString("skin", skin);
        compound.setTag(CustomServerSkin.NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(CustomServerSkin.NAME);
        skin = properties.getString("skin");
    }

    @Override
    public void init(Entity e, World w)
    {}

    public final void sync()
    {
        CustomServerSkin.instance.network.sendToAll(new PacketUpdate(player.getDisplayName(), skin));
    }

    private static String getSaveKey(EntityPlayer player)
    {
        return player.getDisplayName() + ":" + CustomServerSkin.NAME;
    }

    public static void saveProxyData(EntityPlayer player)
    {
        NBTTagCompound savedData = new NBTTagCompound();
        get(player).saveNBTData(savedData);
        storeEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        EEP playerData = get(player);
        NBTTagCompound savedData = getEntityData(getSaveKey(player));
        if(savedData != null)
            playerData.loadNBTData(savedData);
        playerData.sync();
    }
    
    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        NBTTagCompound tag = extendedEntityData.remove(name);
        extendedEntityData.put(name, tag);
        return tag;
    }
}
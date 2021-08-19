package fr.watchdogs.benjaminloison.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class EEPNPC implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = "EEPNPC";

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {}

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {}

    @Override
    public void init(Entity entity, World world)
    {}

    public static final EEPNPC get(Entity player)
    {
        return (EEPNPC)player.getExtendedProperties(EXT_PROP_NAME);
    }
}
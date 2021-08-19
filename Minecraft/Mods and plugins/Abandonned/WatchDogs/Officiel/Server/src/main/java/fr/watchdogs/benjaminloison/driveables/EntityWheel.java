package fr.watchdogs.benjaminloison.driveables;

import com.flansmod.common.vector.Vector3f;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWheel extends Entity implements IEntityAdditionalSpawnData
{
    public EntityDriveable vehicle;
    public int ID;
    private int vehicleID;

    public EntityWheel(World world)
    {
        super(world);
        setSize(1, 1);
        stepHeight = 1;
    }

    public EntityWheel(World world, EntityDriveable entity, int i)
    {
        this(world);
        vehicle = entity;
        vehicleID = entity.getEntityId();
        ID = i;
        initPosition();
    }

    public void initPosition()
    {
        Vector3f wheelVector = vehicle.axes.findLocalVectorGlobally(vehicle.getDriveableType().wheelPositions[ID].position);
        setPosition(vehicle.posX + wheelVector.x, vehicle.posY + wheelVector.y, vehicle.posZ + wheelVector.z);
        stepHeight = vehicle.getDriveableType().wheelStepHeight;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
    }

    @Override
    protected void fall(float k)
    {
        if(vehicle == null || k <= 0)
            return;
        int i = MathHelper.ceiling_float_int(k - 3);
        if(i > 0)
            vehicle.attackPart(vehicle.getDriveableType().wheelPositions[ID].part, DamageSource.fall, i);
    }

    @Override
    protected void entityInit()
    {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tags)
    {
        setDead();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tags)
    {}

    @Override
    public void onUpdate()
    {
        if(vehicle == null)
            return;
        if(!addedToChunk)
            worldObj.spawnEntityInWorld(this);
    }

    public double getSpeedXZ()
    {
        return Math.sqrt(motionX * motionX + motionZ * motionZ);
    }

    @Override
    public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i)
    {}

    @Override
    public void writeSpawnData(ByteBuf data)
    {
        data.writeInt(vehicleID);
        data.writeInt(ID);
    }

    @Override
    public void readSpawnData(ByteBuf data)
    {
        vehicleID = data.readInt();
        ID = data.readInt();
        vehicle = (EntityDriveable)worldObj.getEntityByID(vehicleID);
        if(vehicle != null)
            setPosition(posX, posY, posZ);
    }
}
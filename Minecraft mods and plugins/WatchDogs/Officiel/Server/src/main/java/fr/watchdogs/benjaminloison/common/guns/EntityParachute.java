package fr.watchdogs.benjaminloison.common.guns;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityParachute extends Entity implements IEntityAdditionalSpawnData
{
    public ToolType type;

    public EntityParachute(World w)
    {
        super(w);
        ignoreFrustumCheck = true;
    }

    public EntityParachute(World w, ToolType t, EntityPlayer player)
    {
        this(w);
        type = t;
        setPosition(player.posX, player.posY, player.posZ);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if(riddenByEntity == null || riddenByEntity.ridingEntity != this)
            setDead();
        if(riddenByEntity != null)
            riddenByEntity.fallDistance = 0;
        motionY = -0.1;
        if(riddenByEntity != null && riddenByEntity instanceof EntityLivingBase)
        {
            float speedMultiplier = 0.002F;
            double moveForwards = ((EntityLivingBase)this.riddenByEntity).moveForward, moveStrafing = ((EntityLivingBase)this.riddenByEntity).moveStrafing, sinYaw = -Math.sin((riddenByEntity.rotationYaw * (float)Math.PI / 180.0)), cosYaw = Math.cos((this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0));
            motionX += (moveForwards * sinYaw + moveStrafing * cosYaw) * speedMultiplier;
            motionZ += (moveForwards * cosYaw - moveStrafing * sinYaw) * speedMultiplier;
            prevRotationYaw = rotationYaw;
            rotationYaw = riddenByEntity.rotationYaw;
        }
        motionX *= 0.8F;
        motionZ *= 0.8F;
        moveEntity(motionX, motionY, motionZ);
        if(onGround || worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)).getMaterial() == Material.water)
            setDead();
    }

    @Override
    protected void fall(float par1)
    {}

    @Override
    public boolean attackEntityFrom(DamageSource source, float f)
    {
        setDead();
        return true;
    }

    @Override
    protected void entityInit()
    {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound tags)
    {
        type = ToolType.getType(tags.getString("Type"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tags)
    {
        tags.setString("Type", type.shortName);
    }

    @Override
    public ItemStack getPickedResult(MovingObjectPosition target)
    {
        return new ItemStack(type.item, 1, 0);
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, type.shortName);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {
        type = ToolType.getType(ByteBufUtils.readUTF8String(additionalData));
    }
}
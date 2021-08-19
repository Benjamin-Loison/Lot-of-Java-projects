package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntityMecha;
import fr.watchdogs.benjaminloison.driveables.EnumMechaSlotType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.item.ItemStack;

public class PacketMechaControl extends PacketDriveableControl
{
    public float legYaw, legSwing;
    public ItemStack leftStack, rightStack;

    public PacketMechaControl()
    {}

    public PacketMechaControl(EntityDriveable driveable)
    {
        super(driveable);
        EntityMecha mecha = (EntityMecha)driveable;
        legYaw = mecha.legAxes.getYaw();
        legSwing = mecha.legSwing;
        leftStack = mecha.inventory.getStackInSlot(EnumMechaSlotType.leftTool);
        rightStack = mecha.inventory.getStackInSlot(EnumMechaSlotType.rightTool);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        super.encodeInto(ctx, data);
        data.writeFloat(legYaw);
        data.writeFloat(legSwing);
        ByteBufUtils.writeItemStack(data, leftStack);
        ByteBufUtils.writeItemStack(data, rightStack);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data)
    {
        super.decodeInto(ctx, data);
        legYaw = data.readFloat();
        legSwing = data.readFloat();
        leftStack = ByteBufUtils.readItemStack(data);
        rightStack = ByteBufUtils.readItemStack(data);
    }

    @Override
    protected void updateDriveable(EntityDriveable driveable, boolean clientSide)
    {
        super.updateDriveable(driveable, clientSide);
        EntityMecha mecha = (EntityMecha)driveable;
        mecha.legAxes.setAngles(legYaw, 0, 0);
        mecha.legSwing = legSwing / 2;
    }
}
package fr.chequemod.benjaminloison.entity;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.chequemod.benjaminloison.common.ChequeMod;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityBanker extends EntityLiving
{
    public EntityBanker(World world)
    {
        super(world);
    }

    public boolean isMovementCeased()
    {
        return true;
    }

    @Override
    public void onLivingUpdate()
    {}

    public boolean interact(EntityPlayer p)
    {
        if(p.getHeldItem() != null)
            if(p.getHeldItem().getItem().equals(ChequeMod.cheque))
            {
                ByteArrayDataOutput o = ByteStreams.newDataOutput();
                o.writeUTF("Restitution");
                ((EntityClientPlayerMP)p).sendQueue.addToSendQueue(new C17PacketCustomPayload("ChequeMod", o.toByteArray()));
            }
            else if(p.getHeldItem().getItem().equals(ChequeMod.hammer))
                return false;
            else
                p.addChatMessage(new ChatComponentText(I18n.format("this.npc.accept.only.cheques")));
        else
            p.addChatMessage(new ChatComponentText(I18n.format("this.npc.accept.only.cheques")));
        return true;
    }

    @Override
    public boolean isEntityInvulnerable()
    {
        return true;
    }
}
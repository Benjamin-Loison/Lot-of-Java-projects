package com.degraduck.minefus.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiPacketMimibiote
  extends AbstractPacket
{
  private int id;
  
  public OpenGuiPacketMimibiote() {}
  
  public OpenGuiPacketMimibiote(int id)
  {
    this.id = id;
  }
  
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
  {
    buffer.writeInt(id);
  }
  
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
  {
    id = buffer.readInt();
  }
  
  public void handleClientSide(EntityPlayer player) {}
  
  public void handleServerSide(EntityPlayer player)
  {
    player.openGui(Dofus.instance, id, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
  }
}

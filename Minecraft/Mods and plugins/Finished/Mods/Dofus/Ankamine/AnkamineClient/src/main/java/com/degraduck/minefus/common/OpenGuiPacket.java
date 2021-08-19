package com.degraduck.minefus.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiPacket
  extends AbstractPacket
{
  private int id;
  
  public OpenGuiPacket() {}
  
  public OpenGuiPacket(int id)
  {
      System.out.println("0 " + id);
    this.id = id;
  }
  
  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
  {
      System.out.println("1 " + id);
    buffer.writeInt(id);
  }
  
  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
  {
      System.out.println("2 " + id);
    id = buffer.readInt();
  }
  
  public void handleClientSide(EntityPlayer player) {
      System.out.println(id);}
  
  public void handleServerSide(EntityPlayer player)
  {
      System.out.println(id);
    player.openGui(Dofus.instance, id, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
  }
}

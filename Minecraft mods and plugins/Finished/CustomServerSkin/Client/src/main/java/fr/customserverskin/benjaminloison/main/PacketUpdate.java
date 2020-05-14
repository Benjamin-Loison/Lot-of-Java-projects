package fr.customserverskin.benjaminloison.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.resources.I18n;

public class PacketUpdate implements IMessage
{
    static String user, skin;

    public PacketUpdate()
    {}

    @Override
    public void toBytes(ByteBuf buf)
    {}

    @Override
    public void fromBytes(ByteBuf buf)
    {
        user = ByteBufUtils.readUTF8String(buf);
        skin = ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<PacketUpdate, IMessage>
    {
        @Override
        public IMessage onMessage(PacketUpdate message, MessageContext ctx)
        {
            SkinManager.eep.put(user, skin);
            SkinManager.getDownloadImageHat(SkinManager.getLocationHat(skin), skin);
            return null;
        }
    }
}
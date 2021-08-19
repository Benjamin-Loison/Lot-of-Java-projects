package fr.watchdogs.benjaminloison.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketUpdateClient1 implements IMessage
{
    static boolean escorted, cuffed, vip, vipp, rebel, staff, police, adjoint, brigadier, sergent, adjudant, major, aspirant, lieutenant, capitaine, commandant, colonel, general, bac, transaltis, doctor, repairman, antv, bike, drive, truck, boat, gun, pilot, diamond, archeology;
    static String gang, gangInviting, reasonInterpol[];
    static long money, cash, moneyInterpol[];

    public PacketUpdateClient1()
    {}

    public PacketUpdateClient1(boolean escorted, boolean cuffed, long cash, long money, boolean vip, boolean vipp, boolean rebel, boolean staff, boolean police, boolean adjoint, boolean brigadier, boolean sergent, boolean adjudant, boolean major, boolean aspirant, boolean lieutenant, boolean capitaine, boolean commandant, boolean colonel, boolean general, boolean bac, boolean transaltis, boolean doctor, boolean repairman, boolean antv, boolean bike, boolean drive, boolean truck, boolean boat, boolean gun, boolean pilot, boolean diamond, boolean archeology, String gang, String gangInviting, String[] reasonInterpol, long[] moneyInterpol)
    {
        this.escorted = escorted;
        this.cuffed = cuffed;
        this.cash = cash;
        this.money = money;
        this.vip = vip;
        this.vipp = vipp;
        this.rebel = rebel;
        this.staff = staff;
        this.police = police;
        this.adjoint = adjoint;
        this.brigadier = brigadier;
        this.sergent = sergent;
        this.adjudant = adjudant;
        this.major = major;
        this.aspirant = aspirant;
        this.lieutenant = lieutenant;
        this.capitaine = capitaine;
        this.commandant = commandant;
        this.colonel = colonel;
        this.general = general;
        this.bac = bac;
        this.transaltis = transaltis;
        this.doctor = doctor;
        this.repairman = repairman;
        this.antv = antv;
        this.bike = bike;
        this.drive = drive;
        this.truck = truck;
        this.boat = boat;
        this.gun = gun;
        this.pilot = pilot;
        this.diamond = diamond;
        this.archeology = archeology;
        this.gang = gang;
        this.gangInviting = gangInviting;
        this.reasonInterpol = reasonInterpol;
        this.moneyInterpol = moneyInterpol;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(escorted);
        buf.writeBoolean(cuffed);
        buf.writeLong(cash);
        buf.writeLong(money);
        buf.writeBoolean(vip);
        buf.writeBoolean(vipp);
        buf.writeBoolean(rebel);
        buf.writeBoolean(staff);
        buf.writeBoolean(police);
        buf.writeBoolean(adjoint);
        buf.writeBoolean(brigadier);
        buf.writeBoolean(sergent);
        buf.writeBoolean(adjudant);
        buf.writeBoolean(major);
        buf.writeBoolean(aspirant);
        buf.writeBoolean(lieutenant);
        buf.writeBoolean(capitaine);
        buf.writeBoolean(commandant);
        buf.writeBoolean(colonel);
        buf.writeBoolean(general);
        buf.writeBoolean(bac);
        buf.writeBoolean(transaltis);
        buf.writeBoolean(doctor);
        buf.writeBoolean(repairman);
        buf.writeBoolean(antv);
        buf.writeBoolean(bike);
        buf.writeBoolean(drive);
        buf.writeBoolean(truck);
        buf.writeBoolean(boat);
        buf.writeBoolean(gun);
        buf.writeBoolean(pilot);
        buf.writeBoolean(diamond);
        buf.writeBoolean(archeology);
        ByteBufUtils.writeUTF8String(buf, gang);
        ByteBufUtils.writeUTF8String(buf, gangInviting);
        buf.writeInt(reasonInterpol.length);
        for(int i = 0; i < reasonInterpol.length; i++)
            ByteBufUtils.writeUTF8String(buf, reasonInterpol[i]);
        buf.writeInt(moneyInterpol.length);
        for(int i = 0; i < moneyInterpol.length; i++)
            buf.writeLong(moneyInterpol[i]);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {}

    public static class Handler implements IMessageHandler<PacketUpdateClient1, IMessage>
    {
        @Override
        public IMessage onMessage(PacketUpdateClient1 message, MessageContext ctx)
        {
            return null;
        }
    }
}
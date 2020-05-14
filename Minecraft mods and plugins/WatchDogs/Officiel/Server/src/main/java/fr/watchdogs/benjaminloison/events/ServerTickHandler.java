package fr.altiscraft.benjaminloison.events;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.entity.EEP;
import fr.watchdogs.benjaminloison.packets.PacketMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class ServerTickHandler
{
    static int serverTimeRobbery, serverTimeSalary, x, y, z;
    public static boolean robbery;
    static EntityPlayerMP robber;

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent e)
    {
        serverTimeSalary++;
        serverTimeRobbery++;
        if(robbery)
            if(serverTimeRobbery > FileAPI.configNumber("time.in.ticks.atm.robbery"))
            {
                for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                    if(EEP.get(player).police)
                        AltisCraft.instance.network.sendTo(new PacketMessage("atm.has.been.stolen"), player);
                File atm = new File(FileAPI.atm + x + "," + y + "," + z + ".txt");
                try
                {
                    Scanner scan = new Scanner(atm);
                    String moneyATMActually = scan.nextLine();
                    int moneyInATM = Integer.parseInt(moneyATMActually);
                    FileWriter fw = new FileWriter(atm, false);
                    fw.write("0");
                    fw.close();
                    EEP.get(robber).addCash(moneyInATM);
                    AltisCraft.instance.network.sendTo(new PacketMessage("atm.has.been.stolen.with.amount.for.you", FileAPI.number(moneyInATM)), robber);
                    robbery = false;
                    scan.close();
                }
                catch(Exception ex)
                {}
            }
            else if(serverTimeRobbery % FileAPI.configNumber("time.in.ticks.actualizing.informations.atm.robbery") == 0)
            {
                if(robber.getDistance(x, y, z) <= FileAPI.configNumber("range.atm.robbery"))
                    AltisCraft.instance.network.sendTo(new PacketMessage("robbery.in.progress", serverTimeRobbery / 20, FileAPI.configNumber("time.in.ticks.atm.robbery") / 20), robber);
                else
                {
                    AltisCraft.instance.network.sendTo(new PacketMessage("robbery.stopped.you.are.too.far"), robber);
                    robbery = false;
                    for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                        if(EEP.get(player).police)
                            AltisCraft.instance.network.sendTo(new PacketMessage("robbery.stopped.robber.is.too.far"), player);
                }
            }
        if(serverTimeSalary >= FileAPI.configNumber("time.in.ticks.beetween.every.pay"))
            for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
            {
                EEP props = EEP.get(player);
                props.addMoney(FileAPI.configNumber("pay.basic"));
                AltisCraft.instance.network.sendTo(new PacketMessage("you.have.received.your.pay"), player);
                serverTimeSalary = 0;
                if(props.police)
                    props.addMoney(FileAPI.configNumber("pay.police"));
                else if(props.adjoint)
                    props.addMoney(FileAPI.configNumber("pay.adjoint"));
                else if(props.brigadier)
                    props.addMoney(FileAPI.configNumber("pay.brigadier"));
                else if(props.sergent)
                    props.addMoney(FileAPI.configNumber("pay.sergent"));
                else if(props.adjudant)
                    props.addMoney(FileAPI.configNumber("pay.adjudant"));
                else if(props.major)
                    props.addMoney(FileAPI.configNumber("pay.major"));
                else if(props.aspirant)
                    props.addMoney(FileAPI.configNumber("pay.aspirant"));
                else if(props.lieutenant)
                    props.addMoney(FileAPI.configNumber("pay.lieutenant"));
                else if(props.capitaine)
                    props.addMoney(FileAPI.configNumber("pay.capitaine"));
                else if(props.commandant)
                    props.addMoney(FileAPI.configNumber("pay.commandant"));
                else if(props.colonel)
                    props.addMoney(FileAPI.configNumber("pay.colonel"));
                else if(props.general)
                    props.addMoney(FileAPI.configNumber("pay.general"));
                else if(props.bac)
                    props.addMoney(FileAPI.configNumber("pay.bac"));
                else if(props.antv)
                    props.addMoney(FileAPI.configNumber("pay.antv"));
                else if(props.repairman)
                    props.addMoney(FileAPI.configNumber("pay.repairman"));
                else if(props.vip)
                    props.addMoney(FileAPI.configNumber("pay.vip"));
                else if(props.vipp)
                    props.addMoney(FileAPI.configNumber("pay.vipp"));
                else if(props.transaltis)
                    props.addMoney(FileAPI.configNumber("pay.transaltis"));
                else if(props.doctor)
                    props.addMoney(FileAPI.configNumber("pay.doctor"));
                else if(props.staff)
                    props.addMoney(FileAPI.configNumber("pay.staff"));
            }
    }

    public static void atmRobbery(EntityPlayerMP robber, int x, int y, int z)
    {
        serverTimeRobbery = 0;
        robbery = true;
        ServerTickHandler.robber = robber;
        ServerTickHandler.x = x;
        ServerTickHandler.y = y;
        ServerTickHandler.z = z;
    }
}
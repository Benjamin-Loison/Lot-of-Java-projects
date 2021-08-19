package fr.watchdogs.benjaminloison.entity;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.packets.PacketUpdateClient1;
import fr.watchdogs.benjaminloison.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class EEP implements IExtendedEntityProperties
{
    public final static String EXT_PROP_NAME = WatchDogs.NAME;
    private EntityPlayer player;
    public long cash, money, moneyInterpol[] = new long[50];
    public boolean escorted, cuffed, vip, vipp, rebel, staff, police, adjoint, brigadier, sergent, adjudant, aspirant, major, lieutenant, capitaine, commandant, colonel, general, bac, transaltis, doctor, repairman, antv, bike, drive, truck, boat, gun, pilot, diamond, archeology;
    public String gang, gangInviting, reasonInterpol[] = new String[50];
    private boolean set;

    public EEP(EntityPlayer player)
    {
        this.player = player;
        escorted = false;
        cuffed = false;
        cash = 0;
        money = 0;
        vip = false;
        vipp = false;
        rebel = false;
        staff = false;
        police = false;
        adjoint = false;
        brigadier = false;
        sergent = false;
        adjudant = false;
        aspirant = false;
        major = false;
        lieutenant = false;
        capitaine = false;
        commandant = false;
        colonel = false;
        general = false;
        bac = false;
        transaltis = false;
        doctor = false;
        repairman = false;
        antv = false;
        bike = false;
        drive = false;
        truck = false;
        boat = false;
        gun = false;
        pilot = false;
        diamond = false;
        archeology = false;
        gang = "";
        gangInviting = "";
        for(int i = 0; i < reasonInterpol.length; i++)
            reasonInterpol[i] = "";
        for(int i = 0; i < moneyInterpol.length; i++)
            moneyInterpol[i] = 0;
    }

    public boolean get(String name)
    {
        if(name.equalsIgnoreCase("Player"))
            return true;
        if(name.equalsIgnoreCase("escorted"))
            return escorted;
        if(name.equalsIgnoreCase("cuffed"))
            return cuffed;
        if(name.equalsIgnoreCase("vip"))
            return vip;
        if(name.equalsIgnoreCase("vipp"))
            return vipp;
        if(name.equalsIgnoreCase("rebel"))
            return rebel;
        if(name.equalsIgnoreCase("staff"))
            return staff;
        if(name.equalsIgnoreCase("police"))
            return police;
        if(name.equalsIgnoreCase("adjoint"))
            return adjoint;
        if(name.equalsIgnoreCase("brigadier"))
            return brigadier;
        if(name.equalsIgnoreCase("sergent"))
            return sergent;
        if(name.equalsIgnoreCase("adjudant"))
            return adjudant;
        if(name.equalsIgnoreCase("aspirant"))
            return aspirant;
        if(name.equalsIgnoreCase("major"))
            return major;
        if(name.equalsIgnoreCase("lieutenant"))
            return lieutenant;
        if(name.equalsIgnoreCase("capitaine"))
            return capitaine;
        if(name.equalsIgnoreCase("commandant"))
            return commandant;
        if(name.equalsIgnoreCase("colonel"))
            return colonel;
        if(name.equalsIgnoreCase("general"))
            return general;
        if(name.equalsIgnoreCase("bac"))
            return bac;
        if(name.equalsIgnoreCase("transaltis"))
            return transaltis;
        if(name.equalsIgnoreCase("doctor"))
            return doctor;
        if(name.equalsIgnoreCase("repairman"))
            return repairman;
        if(name.equalsIgnoreCase("antv"))
            return antv;
        if(name.equalsIgnoreCase("bike"))
            return bike;
        if(name.equalsIgnoreCase("drive"))
            return drive;
        if(name.equalsIgnoreCase("truck"))
            return truck;
        if(name.equalsIgnoreCase("boat"))
            return boat;
        if(name.equalsIgnoreCase("gun"))
            return gun;
        if(name.equalsIgnoreCase("pilot"))
            return pilot;
        if(name.equalsIgnoreCase("diamond"))
            return diamond;
        if(name.equalsIgnoreCase("archeology"))
            return archeology;
        return false;
    }

    public void set(String name, String value)
    {
        if(name.equalsIgnoreCase("gang"))
            gang = value;
        else if(name.equalsIgnoreCase("gangInviting"))
            gangInviting = value;
        sync();
    }

    public void set(String name, boolean value)
    {
        if(name.equalsIgnoreCase("escorted"))
            escorted = value;
        else if(name.equalsIgnoreCase("cuffed"))
            cuffed = value;
        else if(name.equalsIgnoreCase("vip"))
            vip = value;
        else if(name.equalsIgnoreCase("vipp"))
            vipp = value;
        else if(name.equalsIgnoreCase("rebel"))
            rebel = value;
        else if(name.equalsIgnoreCase("staff"))
            staff = value;
        else if(name.equalsIgnoreCase("police"))
            police = value;
        else if(name.equalsIgnoreCase("adjoint"))
            adjoint = value;
        else if(name.equalsIgnoreCase("brigadier"))
            brigadier = value;
        else if(name.equalsIgnoreCase("sergent"))
            sergent = value;
        else if(name.equalsIgnoreCase("adjudant"))
            adjudant = value;
        else if(name.equalsIgnoreCase("aspirant"))
            aspirant = value;
        else if(name.equalsIgnoreCase("major"))
            major = value;
        else if(name.equalsIgnoreCase("lieutenant"))
            lieutenant = value;
        else if(name.equalsIgnoreCase("capitaine"))
            capitaine = value;
        else if(name.equalsIgnoreCase("commandant"))
            commandant = value;
        else if(name.equalsIgnoreCase("colonel"))
            colonel = value;
        else if(name.equalsIgnoreCase("general"))
            general = value;
        else if(name.equalsIgnoreCase("bac"))
            bac = value;
        else if(name.equalsIgnoreCase("transaltis"))
            transaltis = value;
        else if(name.equalsIgnoreCase("doctor"))
            doctor = value;
        else if(name.equalsIgnoreCase("repairman"))
            repairman = value;
        else if(name.equalsIgnoreCase("antv"))
            antv = value;
        else if(name.equalsIgnoreCase("bike"))
            bike = value;
        else if(name.equalsIgnoreCase("drive"))
            drive = value;
        else if(name.equalsIgnoreCase("bike"))
            bike = value;
        else if(name.equalsIgnoreCase("truck"))
            truck = value;
        else if(name.equalsIgnoreCase("boat"))
            boat = value;
        else if(name.equalsIgnoreCase("gun"))
            gun = value;
        else if(name.equalsIgnoreCase("pilot"))
            pilot = value;
        else if(name.equalsIgnoreCase("diamond"))
            diamond = value;
        else if(name.equalsIgnoreCase("archeology"))
            archeology = value;
        sync();
    }

    public void set(String name, long value)
    {
        if(name.equalsIgnoreCase("cash"))
            cash = value;
        else if(name.equalsIgnoreCase("money"))
            money = value;
        sync();
    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(EEP.EXT_PROP_NAME, new EEP(player));
    }

    public static final EEP get(EntityPlayer player)
    {
        return (EEP)player.getExtendedProperties(EXT_PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setBoolean("Escorted", false);
        properties.setBoolean("Cuffed", false);
        properties.setLong("Cash", cash);
        properties.setLong("Money", money);
        properties.setBoolean("VIP", vip);
        properties.setBoolean("VIPP", vipp);
        properties.setBoolean("Rebel", rebel);
        properties.setBoolean("Staff", staff);
        properties.setBoolean("Police", police);
        properties.setBoolean("Adjoint", adjoint);
        properties.setBoolean("Brigadier", brigadier);
        properties.setBoolean("Sergent", sergent);
        properties.setBoolean("Adjudant", adjudant);
        properties.setBoolean("Major", major);
        properties.setBoolean("Aspirant", aspirant);
        properties.setBoolean("Lieutenant", lieutenant);
        properties.setBoolean("Capitaine", capitaine);
        properties.setBoolean("Commandant", commandant);
        properties.setBoolean("Colonel", colonel);
        properties.setBoolean("General", general);
        properties.setBoolean("BAC", bac);
        properties.setBoolean("TransAltis", transaltis);
        properties.setBoolean("Doctor", doctor);
        properties.setBoolean("Repairman", repairman);
        properties.setBoolean("ANTV", antv);
        properties.setBoolean("Bike", bike);
        properties.setBoolean("Drive", drive);
        properties.setBoolean("Truck", truck);
        properties.setBoolean("Boat", boat);
        properties.setBoolean("Gun", gun);
        properties.setBoolean("Pilot", pilot);
        properties.setBoolean("Diamond", diamond);
        properties.setBoolean("Archeology", archeology);
        properties.setBoolean("Siren", false);
        properties.setString("Gang", gang);
        properties.setString("Gang Inviting", gangInviting);
        for(int i = 0; i < reasonInterpol.length; i++)
            properties.setString("Reason Interpol" + i, reasonInterpol[i]);
        for(int i = 0; i < moneyInterpol.length; i++)
            properties.setLong("Money Interpol" + i, moneyInterpol[i]);
        compound.setTag(EXT_PROP_NAME, properties);
    }

    public void saveNBTDataMoney(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setLong("Money", money);
        properties.setBoolean("VIP", vip);
        properties.setBoolean("VIPP", vipp);
        properties.setBoolean("Rebel", rebel);
        properties.setBoolean("Staff", staff);
        properties.setBoolean("Police", police);
        properties.setBoolean("Adjoint", adjoint);
        properties.setBoolean("Brigadier", brigadier);
        properties.setBoolean("Sergent", sergent);
        properties.setBoolean("Adjudant", adjudant);
        properties.setBoolean("Aspirant", aspirant);
        properties.setBoolean("Major", major);
        properties.setBoolean("Lieutenant", lieutenant);
        properties.setBoolean("Capitaine", capitaine);
        properties.setBoolean("Commandant", commandant);
        properties.setBoolean("Colonel", colonel);
        properties.setBoolean("General", general);
        properties.setBoolean("BAC", bac);
        properties.setBoolean("TransAltis", transaltis);
        properties.setBoolean("Doctor", doctor);
        properties.setBoolean("Repairman", repairman);
        properties.setBoolean("ANTV", antv);
        properties.setBoolean("Bike", bike);
        properties.setBoolean("Drive", drive);
        properties.setBoolean("Truck", truck);
        properties.setBoolean("Boat", boat);
        properties.setBoolean("Gun", gun);
        properties.setBoolean("Pilot", pilot);
        properties.setBoolean("Diamond", diamond);
        properties.setBoolean("Archeology", archeology);
        properties.setString("Gang", gang);
        properties.setString("Gang Inviting", gangInviting);
        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        escorted = properties.getBoolean("Escorted");
        cuffed = properties.getBoolean("Cuffed");
        cash = properties.getLong("Cash");
        money = properties.getLong("Money");
        vip = properties.getBoolean("VIP");
        vipp = properties.getBoolean("VIPP");
        rebel = properties.getBoolean("Rebel");
        staff = properties.getBoolean("Staff");
        police = properties.getBoolean("Police");
        adjoint = properties.getBoolean("Adjoint");
        brigadier = properties.getBoolean("Brigadier");
        sergent = properties.getBoolean("Sergent");
        adjudant = properties.getBoolean("Adjudant");
        major = properties.getBoolean("Major");
        aspirant = properties.getBoolean("Aspirant");
        lieutenant = properties.getBoolean("Lieutenant");
        capitaine = properties.getBoolean("Capitaine");
        commandant = properties.getBoolean("Commandant");
        colonel = properties.getBoolean("Colonel");
        general = properties.getBoolean("General");
        bac = properties.getBoolean("BAC");
        transaltis = properties.getBoolean("TransAltis");
        doctor = properties.getBoolean("Doctor");
        repairman = properties.getBoolean("Repairman");
        antv = properties.getBoolean("ANTV");
        bike = properties.getBoolean("Bike");
        drive = properties.getBoolean("Drive");
        truck = properties.getBoolean("Truck");
        boat = properties.getBoolean("Boat");
        gun = properties.getBoolean("Gun");
        pilot = properties.getBoolean("Pilot");
        diamond = properties.getBoolean("Diamond");
        archeology = properties.getBoolean("Archeology");
        gang = properties.getString("Gang");
        gangInviting = properties.getString("Gang Inviting");
        for(int i = 0; i < reasonInterpol.length; i++)
            reasonInterpol[i] = properties.getString("Reason Interpol" + i);
        for(int i = 0; i < moneyInterpol.length; i++)
            moneyInterpol[i] = properties.getInteger("Money Interpol" + i);
    }

    @Override
    public void init(Entity e, World w)
    {}

    public final void sync()
    {
        WatchDogs.instance.network.sendTo(new PacketUpdateClient1(escorted, cuffed, cash, money, vip, vipp, rebel, staff, police, adjoint, brigadier, sergent, adjudant, major, aspirant, lieutenant, capitaine, commandant, colonel, general, bac, transaltis, doctor, repairman, antv, bike, drive, truck, boat, gun, pilot, diamond, archeology, gang, gangInviting, reasonInterpol, moneyInterpol), (EntityPlayerMP)player);
    }

    private static String getSaveKey(EntityPlayer player)
    {
        return player.getDisplayName() + ":" + EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player)
    {
        NBTTagCompound savedData = new NBTTagCompound();
        EEP.get(player).saveNBTData(savedData);
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        EEP playerData = EEP.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
        if(savedData != null)
            playerData.loadNBTData(savedData);
        playerData.sync();
    }

    /*public void creditCash(ItemStack item, long prix)
    {
        EntityPlayerMP thePlayer = (EntityPlayerMP)player;
        if(player.inventory.hasItem(WatchDogs.creditCard))
            if(cash + money >= prix)
            {
                money -= prix - cash;
                cash = 0;
                boolean isEmpty = false;
                for(int i = 0; i <= player.inventory.getSizeInventory() - 5; i++)
                    if(player.inventory.mainInventory[i] == item || player.inventory.mainInventory[i] == null)
                    {
                        isEmpty = true;
                        break;
                    }
                if(isEmpty)
                {
                    player.inventory.addItemStackToInventory(item);
                    WatchDogs.instance.network.sendTo(new PacketMessage("item.bought"), thePlayer);
                }
                else
                    WatchDogs.instance.network.sendTo(new PacketMessage("you.have.no.place"), thePlayer);
            }
            else
                WatchDogs.instance.network.sendTo(new PacketMessage("you.have.not.enough.money.in.globality"), thePlayer);
        else
            WatchDogs.instance.network.sendTo(new PacketMessage("you.have.not.enough.money"), thePlayer);
        sync();
    }*/

    public String searchAllReasons()
    {
        String recherchesraisonall = "";
        for(int i = 0; i < reasonInterpol.length; i++)
            if(i == 0)
                recherchesraisonall = recherchesraisonall + reasonInterpol[i];
            else
                recherchesraisonall = recherchesraisonall + " " + reasonInterpol[i];
        return recherchesraisonall;
    }

    public long allMoneyInterpol()
    {
        int number = 0;
        for(int i = 0; i < moneyInterpol.length; i++)
            if(moneyInterpol[i] != 0)
                number += moneyInterpol[i];
            else
                break;
        return number;
    }

    public void resetInterpol()
    {
        for(int i = 0; i < reasonInterpol.length; i++)
        {
            reasonInterpol[i] = "";
            moneyInterpol[i] = 0;
        }
        sync();
    }

    public void resetCuffed()
    {
        cuffed = false;
        escorted = false;
        sync();
    }

    public void addInterpol(String reason, long money)
    {
        if(!police)
        {
            set = false;
            for(int i = 0; i < reasonInterpol.length && !set; i++)
                if(reasonInterpol[i].equals("") && reasonInterpol[i] != null && !set)
                {
                    reasonInterpol[i] = reason;
                    moneyInterpol[i] = money;
                    set = true;
                }
            sync();
        }
    }

    public boolean sufficientCash(long amount)
    {
        if(amount <= cash)
            return true;
        return false;
    }

    public boolean sufficientMoney(long amount)
    {
        if(amount <= money)
            return true;
        return false;
    }

    public String buyCashTest(long amount, ItemStack item)
    {
        if(amount <= cash)
        {
            if(item != null)
            {
                boolean isEmpty = false;
                for(int i = 0; i <= player.inventory.getSizeInventory() - 5; i++)
                    if(player.inventory.mainInventory[i] == item || player.inventory.mainInventory[i] == null)
                    {
                        isEmpty = true;
                        break;
                    }
                if(isEmpty)
                    return "bought.item";
                else
                    return "not.enough.space";
            }
        }
        else
            return "not.enough.money";
        return null;
    }

    /*public void buyMoneyGang(long amount, ItemStack item, EntityPlayerMP sender)
    {
        if(getMoneyGang() >= amount)
        {
            if(item != null)
            {
                boolean isEmpty = false;
                for(int i = 0; i <= player.inventory.getSizeInventory() - 5; i++)
                    if(player.inventory.mainInventory[i] == item || player.inventory.mainInventory[i] == null)
                    {
                        isEmpty = true;
                        break;
                    }
                if(isEmpty)
                {
                    delMoneyGang(amount);
                    player.inventory.addItemStackToInventory(item);
                    WatchDogs.instance.network.sendTo(new PacketMessage("you.have.bought.the.item.for", FileAPI.number(amount)), sender);
                    for(EntityPlayerMP player : (List<EntityPlayerMP>)MinecraftServer.getServer().getConfigurationManager().playerEntityList)
                        if(EEP.get(player).gang.equals(gang) && player != sender)
                            WatchDogs.instance.network.sendTo(new PacketMessage("the.player.has.bought.the.item.at", sender.getDisplayName(), item.getDisplayName(), FileAPI.number(amount)), player);
                }
                else
                    WatchDogs.instance.network.sendTo(new PacketMessage("you.have.not.enough.place"), sender);
            }
        }
        else
            WatchDogs.instance.network.sendTo(new PacketMessage("you.have.not.enough.money"), sender);
    }*/

    public void buyCash(long amount, ItemStack item)
    {
        if(amount <= cash)
            if(item != null)
            {
                boolean isEmpty = false;
                for(int i = 0; i <= player.inventory.getSizeInventory() - 5; i++)
                    if(player.inventory.mainInventory[i] == item || player.inventory.mainInventory[i] == null)
                    {
                        isEmpty = true;
                        break;
                    }
                if(isEmpty)
                {
                    delCash(amount);
                    player.inventory.addItemStackToInventory(item);
                }
            }
    }

    /*public void buyLicense(long amount, String license)
    {
        if(amount <= cash)
        {
            EEP props = EEP.get(player);
            WatchDogs.instance.network.sendTo(new PacketMessage("you.have.bought.the.item.for", FileAPI.number(amount)), (EntityPlayerMP)player);
            props.set(license, true);
            props.delCash(amount);
        }
        else
            WatchDogs.instance.network.sendTo(new PacketMessage("you.have.not.enough.money"), (EntityPlayerMP)player);
    }

    public void buyDye(long amount, int color)
    {
        if(amount <= cash)
        {
            WatchDogs.instance.network.sendTo(new PacketMessage("you.have.bought.the.item.for", FileAPI.number(amount)), (EntityPlayerMP)player);
            player.inventory.addItemStackToInventory(new ItemStack(Items.dye, 1, color));
            EEP.get(player).delCash(amount);
        }
        else
            WatchDogs.instance.network.sendTo(new PacketMessage("you.have.not.enough.money"), (EntityPlayerMP)player);
    }*/

    public void addCash(long amount)
    {
        cash += amount;
        sync();
    }

    public void delCashAddMoney(long amount)
    {
        if(amount <= cash)
        {
            cash -= amount;
            money += amount;
            sync();
        }
    }

    public void delCash(long amount)
    {
        if(amount <= cash)
        {
            cash -= amount;
            sync();
        }
    }

    public void delMoney(long amount)
    {
        if(amount <= money)
        {
            money -= amount;
            sync();
        }
    }

    public void delMoneyAddCash(long amount)
    {
        if(amount <= money)
        {
            money -= amount;
            cash += amount;
            sync();
        }
    }

    public void addMoney(long amount)
    {
        money += amount;
        sync();
    }

    public void delMoneyGang(long amount)
    {
        File gangFile = new File(FileAPI.gang + gang + ".txt"), gangTemp = new File(FileAPI.gang + gang + ".temp");
        try
        {
            Scanner sc = new Scanner(gangFile), sct = new Scanner(gangFile);
            FileWriter fw = new FileWriter(gangTemp);
            fw.write(sc.nextLine());
            sc.nextLine();
            sct.nextLine();
            sct.nextLine();
            if(sct.hasNextLine())
                sct.nextLine();
            fw.write("\n" + (getMoneyGang() - amount));
            while(sc.hasNextLine())
            {
                if(sct.hasNextLine())
                    fw.write(sc.nextLine() + "\n");
                else
                    fw.write(sc.nextLine());
                sct.nextLine();
            }
            fw.close();
            sc.close();
            sct.close();
            gangFile.delete();
            gangTemp.renameTo(gangFile);
        }
        catch(Exception e)
        {}
    }

    public int getMoneyGang()
    {
        int amount = -1;
        try
        {
            Scanner sc = new Scanner(new File(FileAPI.gang + gang + ".txt"));
            if(sc.hasNextLine())
                sc.nextLine();
            if(sc.hasNextLine())
            {
                String scan = sc.nextLine();
                if(StringUtils.isNumeric(scan))
                    amount = Integer.parseInt(scan);
            }
            sc.close();
        }
        catch(Exception e)
        {}
        return amount;
    }
}
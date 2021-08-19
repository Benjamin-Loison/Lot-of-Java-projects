package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ToolType extends InfoType
{
    public static HashMap<String, ToolType> tools = new HashMap<String, ToolType>();
    public boolean parachute, remote, destroyOnEmpty = true, healPlayers, healDriveables;
    public ArrayList<ItemStack> rechargeRecipe = new ArrayList<ItemStack>();
    public int healAmount, toolLife, foodness, EUPerCharge;

    public ToolType(TypeFile file)
    {
        super(file);
    }

    @Override
    protected void postRead(TypeFile file)
    {
        tools.put(shortName, this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("Parachute"))
                parachute = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("ExplosiveRemote"))
                remote = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Heal") || split[0].equals("HealPlayers"))
                healPlayers = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Repair") || split[0].equals("RepairVehicles"))
                healDriveables = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("HealAmount") || split[0].equals("RepairAmount"))
                healAmount = Integer.parseInt(split[1]);
            else if(split[0].equals("ToolLife") || split[0].equals("ToolUses"))
                toolLife = Integer.parseInt(split[1]);
            else if(split[0].equals("EUPerCharge"))
                EUPerCharge = Integer.parseInt(split[1]);
            else if(split[0].equals("RechargeRecipe"))
                for(int i = 0; i < (split.length - 1) / 2; i++)
                {
                    boolean damaged = split[2 * i + 2].contains(".");
                    rechargeRecipe.add(getRecipeElement(damaged ? split[2 * i + 2].split("\\.")[0] : split[2 * i + 2], Integer.parseInt(split[2 * i + 1]), damaged ? Integer.parseInt(split[2 * i + 2].split("\\.")[1]) : 0, shortName));
                }
            else if(split[0].equals("DestroyOnEmpty"))
                destroyOnEmpty = Boolean.parseBoolean(split[1].toLowerCase());
            else if(split[0].equals("Food") || split[0].equals("Foodness"))
                foodness = Integer.parseInt(split[1]);
        }
        catch(Exception e)
        {
            WatchDogs.print("Reading file failed : " + shortName);
            e.printStackTrace();
        }
    }

    @Override
    public void addRecipe(Item item)
    {
        super.addRecipe(item);
        if(rechargeRecipe.size() < 1)
            return;
        rechargeRecipe.add(new ItemStack(item, 1, toolLife));
        GameRegistry.addShapelessRecipe(new ItemStack(item, 1, 0), rechargeRecipe.toArray());
    }

    public static ToolType getType(String shortName)
    {
        return tools.get(shortName);
    }
}
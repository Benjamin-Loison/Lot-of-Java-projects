package fr.watchdogs.benjaminloison.common.teams;

import java.util.ArrayList;
import java.util.HashMap;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.item.ItemStack;

public class ArmourBoxType extends InfoType
{
    public BlockArmourBox block;
    public ArrayList<ArmourBoxEntry> pages = new ArrayList<ArmourBoxEntry>();
    public static HashMap<String, ArmourBoxType> boxes = new HashMap<String, ArmourBoxType>();

    public ArmourBoxType(TypeFile file)
    {
        super(file);
    }

    @Override
    public void postRead(TypeFile file)
    {
        super.postRead(file);
        boxes.put(shortName, this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].toLowerCase().equals("addarmour") || split[0].toLowerCase().equals("addarmor"))
            {
                String name = split[2];
                for(int i = 3; i < split.length; i++)
                    name = name + " " + split[i];
                ArmourBoxEntry entry = new ArmourBoxEntry(split[1], name);
                for(int i = 0; i < 4; i++)
                {
                    String line = null;
                    line = file.readLine();
                    if(line == null)
                        continue;
                    if(line.startsWith("//"))
                    {
                        i--;
                        continue;
                    }
                    String[] lineSplit = line.split(" ");
                    entry.armours[i] = ArmourType.getArmourType(lineSplit[0]);
                    for(int j = 0; j < (lineSplit.length - 1) / 2; j++)
                    {
                        ItemStack stack = null;
                        if(lineSplit[j * 2 + 1].contains("."))
                            stack = getRecipeElement(lineSplit[j * 2 + 1].split("\\.")[0], Integer.valueOf(lineSplit[j * 2 + 2]), Integer.valueOf(lineSplit[j * 2 + 1].split("\\.")[1]), shortName);
                        else
                            stack = getRecipeElement(lineSplit[j * 2 + 1], Integer.valueOf(lineSplit[j * 2 + 2]), 0, shortName);

                        if(stack != null)
                            entry.requiredStacks[i].add(stack);
                    }
                }
                pages.add(entry);
            }
        }
        catch(Exception e)
        {
            WatchDogs.print("Reading gun box file failed : " + shortName);
            e.printStackTrace();
        }
    }

    public class ArmourBoxEntry
    {
        public String shortName, name = "";
        public ArmourType[] armours;
        public ArrayList<ItemStack>[] requiredStacks;

        public ArmourBoxEntry(String s, String s1)
        {
            shortName = s;
            name = s1;
            armours = new ArmourType[4];
            requiredStacks = new ArrayList[4];
            for(int i = 0; i < 4; i++)
                requiredStacks[i] = new ArrayList<ItemStack>();
        }
    }

    public static ArmourBoxType getBox(String boxShortName)
    {
        return boxes.get(boxShortName);
    }
}
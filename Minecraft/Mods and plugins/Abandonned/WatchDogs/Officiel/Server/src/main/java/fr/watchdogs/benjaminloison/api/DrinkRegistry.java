package fr.watchdogs.benjaminloison.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

import cpw.mods.fml.common.registry.GameData;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DrinkRegistry
{
    public DrinkRegistry()
    {
        try
        {
            findFiles();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void findFiles() throws Exception
    {
        File modsDir = new File(WatchDogs.getMinecraftDir(), "/mods");
        List<URI> mods = new ArrayList<URI>();
        if(modsDir.listFiles().length > 0)
            for(int i = 0; i < modsDir.listFiles().length; i++)
                if(modsDir.listFiles()[i].getName().endsWith(".zip") || modsDir.listFiles()[i].getName().endsWith(".jar"))
                    mods.add(modsDir.listFiles()[i].toURI());
        for(int i = 0; i < mods.size(); i++)
        {
            ZipFile zip = new ZipFile(new File(mods.get(i)));
            try
            {
                init(new BufferedReader(new InputStreamReader(zip.getInputStream(zip.getEntry("drinks.txt")))));
            }
            catch(Exception e)
            {}
            zip.close();
        }
        File contentDir = new File(WatchDogs.getMinecraftDir(), "/mods/" + WatchDogs.NAME + "/external/");
        contentDir.mkdirs();
        createInstructions(new File(contentDir, "usage.info"));
        createDrinks(new File(contentDir, "minecraft-fr.watchdogs.benjaminloison.items.txt"));
        if(contentDir.listFiles().length > 0)
            for(int i = 0; i < contentDir.listFiles().length; i++)
            {
                if((!contentDir.listFiles()[i].isFile()) || (!contentDir.listFiles()[i].getName().endsWith(".txt")))
                    continue;
                init(new BufferedReader(new FileReader(contentDir.listFiles()[i])));
            }
    }

    public void init(BufferedReader br)
    {
        try
        {
            while(br.ready())
            {
                String line = br.readLine();
                if(line.startsWith("//"))
                    continue;
                else
                    readFiles(line.split(" "));
            }
        }
        catch(Exception e)
        {}
    }

    public void readFiles(String[] elements)
    {
        String id = null, drinkRecipeID = null;
        int level = 0, metadata = 0;
        float saturation = 0f, chance = 0.0f;
        boolean poison = false;
        for(int i = 0; i < elements.length; i++)
            switch(i)
            {
                case 0:
                    id = elements[i];
                    break;
                case 1:
                    level = Integer.parseInt(elements[i]);
                    break;
                case 2:
                    saturation = Float.parseFloat(elements[i]);
                    break;
                case 3:
                    poison = Boolean.parseBoolean(elements[i]);
                    break;
                case 4:
                    chance = Float.parseFloat(elements[i]);
                    break;
                case 5:
                    metadata = Integer.parseInt(elements[i]);
                    break;
                case 6:
                    drinkRecipeID = elements[i];
                    break;
            }
        Item item = (Item)GameData.getItemRegistry().getObject(id);
        if(item != null)
            DrinkLists.addDrink(new ItemStack(item, 0, metadata), level, saturation, poison, chance);
    }

    public void createDrinks(File file) throws Exception
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("minecraft:mushroom_stew 7 1.2\n");
        writer.write("minecraft:milk_bucket 8 3.4\n");
        writer.write("minecraft:potion 3 1.4 true 0.4 0");
        writer.close();
    }

    public void createInstructions(File file) throws Exception
    {
        if(!file.exists())
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Lets hope you're not an idiot. :D\n\n");
            writer.write("item_id thirst_replenish thirst_saturation causes_poison poison_chance metadata recipe_id\n\n");
            writer.write("Copy and paste this in a new [.txt] file. Replace all of the above with the correct values.\n");
            writer.write("    item_id = String (consult wiki or mod author for item id)\n" + "    thirst_replenish = Integer (one to twenty)\n" + "    thirst_saturation = Decimal (consult food wiki page for details)\n" + "    causes_poison = Boolean\n" + "    poison_chance = Decimal (0.1 to 0.9)\n" + "    metadata = Integer (consult wiki or mod author for item metadata)\n" + "    recipe_id = String (adds a recipe for this drink to be brewed in the Drinks Brewer), (consult wiki or mod author for item id)\n\n");
            writer.write("Use the exact format displayed. Do NOT misplace the values or the game WILL crash.\n\n");
            writer.write("Don't forget the spaces between each value!\n\n");
            writer.write("One item per line in a [.txt] file in this folder. Ask tarun1998 on the forums about any issues.");
            writer.close();
        }
    }
}
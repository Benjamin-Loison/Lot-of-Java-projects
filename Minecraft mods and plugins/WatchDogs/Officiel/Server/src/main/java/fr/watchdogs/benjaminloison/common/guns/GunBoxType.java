package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GunBoxType extends InfoType
{
    public BlockGunBox block;
    public int numGuns, nextGun = -1;
    public InfoType[] guns;
    public ShootableType[] bullets, altBullets;
    public List<ItemStack>[] gunParts, bulletParts, altBulletParts;
    private static int lastIconIndex = 2;
    public static HashMap<String, GunBoxType> gunBoxMap = new HashMap<String, GunBoxType>();

    public GunBoxType(TypeFile file)
    {
        super(file);
    }

    @Override
    public void preRead(TypeFile file)
    {
        super.preRead(file);
        for(String line : file.lines)
        {
            if(line == null)
                break;
            if(line.startsWith("//"))
                continue;
            String[] split = line.split(" ");
            if(split.length < 2)
                continue;
            if(split[0].equals("NumGuns"))
            {
                numGuns = Integer.parseInt(split[1]);
                guns = new InfoType[numGuns];
                bullets = new ShootableType[numGuns];
                altBullets = new ShootableType[numGuns];
                gunParts = new List[numGuns];
                bulletParts = new List[numGuns];
                altBulletParts = new List[numGuns];
                for(int i = 0; i < numGuns; i++)
                {
                    gunParts[i] = new ArrayList<ItemStack>();
                    bulletParts[i] = new ArrayList<ItemStack>();
                    altBulletParts[i] = new ArrayList<ItemStack>();
                }
            }
        }
    }

    @Override
    public void postRead(TypeFile file)
    {
        super.postRead(file);
        gunBoxMap.put(this.shortName, this);
    }

    @Override
    protected void read(String[] split, TypeFile file)
    {
        super.read(split, file);
        try
        {
            if(split[0].equals("AddGun"))
            {
                nextGun++;
                if(gunParts[nextGun] == null)
                    WatchDogs.print("NumGuns was not found or was incorrect");
                guns[nextGun] = InfoType.getType(split[1]);
                for(int i = 0; i < (split.length - 2) / 2; i++)
                    if(split[i * 2 + 3].contains("."))
                        gunParts[nextGun].add(getRecipeElement(split[i * 2 + 3].split("\\.")[0], Integer.parseInt(split[i * 2 + 2]), Integer.valueOf(split[i * 2 + 3].split("\\.")[1]), shortName));
                    else
                        gunParts[nextGun].add(getRecipeElement(split[i * 2 + 3], Integer.parseInt(split[i * 2 + 2]), 0, shortName));
            }
            else if(split[0].equals("AddAmmo"))
            {
                if(bulletParts[nextGun] == null)
                    WatchDogs.print("NumGuns was not found or was incorrect");
                bullets[nextGun] = ShootableType.getShootableType(split[1]);
                for(int i = 0; i < (split.length - 2) / 2; i++)
                    if(split[i * 2 + 3].contains("."))
                        bulletParts[nextGun].add(getRecipeElement(split[i * 2 + 3].split("\\.")[0], Integer.parseInt(split[i * 2 + 2]), Integer.valueOf(split[i * 2 + 3].split("\\.")[1]), shortName));
                    else
                        bulletParts[nextGun].add(getRecipeElement(split[i * 2 + 3], Integer.parseInt(split[i * 2 + 2]), 0, shortName));
            }
            else if(split[0].equals("AddAltAmmo") || split[0].equals("AddAlternateAmmo"))
            {
                if(altBulletParts[nextGun] == null)
                    WatchDogs.print("NumGuns was not found or was incorrect");
                altBullets[nextGun] = ShootableType.getShootableType(split[1]);
                for(int i = 0; i < (split.length - 2) / 2; i++)
                    if(split[i * 2 + 3].contains("."))
                        altBulletParts[nextGun].add(getRecipeElement(split[i * 2 + 3].split("\\.")[0], Integer.parseInt(split[i * 2 + 2]), Integer.valueOf(split[i * 2 + 3].split("\\.")[1]), shortName));
                    else
                        altBulletParts[nextGun].add(getRecipeElement(split[i * 2 + 3], Integer.parseInt(split[i * 2 + 2]), 0, shortName));
            }
        }
        catch(Exception e)
        {
            WatchDogs.print("Reading gun box file failed : " + shortName);
            e.printStackTrace();
        }
    }

    public static GunBoxType getBox(String s)
    {
        return gunBoxMap.get(s);
    }

    public static GunBoxType getBox(Block block)
    {
        for(GunBoxType type : gunBoxMap.values())
            if(type.block == block)
                return type;
        return null;
    }

    @Override
    public void addRecipe(Item par1Item)
    {
        if(smeltableFrom != null)
            GameRegistry.addSmelting(getRecipeElement(smeltableFrom, 0), new ItemStack(item), 0.0F);
        if(recipeLine == null)
            return;
        try
        {
            if(!shapeless)
            {
                int rows = 3;
                if(((String)recipe[0]).charAt(0) == ' ' && ((String)recipe[1]).charAt(0) == ' ' && ((String)recipe[2]).charAt(0) == ' ')
                {
                    for(int i = 0; i < 3; i++)
                        recipe[i] = ((String)recipe[i]).substring(1);
                    if(((String)recipe[0]).charAt(0) == ' ' && ((String)recipe[1]).charAt(0) == ' ' && ((String)recipe[2]).charAt(0) == ' ')
                        for(int i = 0; i < 3; i++)
                            recipe[i] = ((String)recipe[i]).substring(1);
                }
                int last = ((String)recipe[0]).length() - 1;
                if(((String)recipe[0]).charAt(last) == ' ' && ((String)recipe[1]).charAt(last) == ' ' && ((String)recipe[2]).charAt(last) == ' ')
                {
                    for(int i = 0; i < 3; i++)
                        recipe[i] = ((String)recipe[i]).substring(0, last);
                    last--;
                    if(((String)recipe[0]).charAt(last) == ' ' && ((String)recipe[1]).charAt(last) == ' ' && ((String)recipe[2]).charAt(last) == ' ')
                        for(int i = 0; i < 3; i++)
                            recipe[i] = ((String)recipe[i]).substring(0, 0);
                }
                if(recipe[0].equals(" ") || recipe[0].equals("  ") || recipe[0].equals("   "))
                {
                    Object[] newRecipe = new Object[recipe.length - 1];
                    newRecipe[0] = recipe[1];
                    newRecipe[1] = recipe[2];
                    recipe = newRecipe;
                    rows--;
                    if(recipe[0].equals(" ") || recipe[0].equals("  ") || recipe[0].equals("   "))
                    {
                        Object[] newRecipe1 = new Object[recipe.length - 1];
                        newRecipe1[0] = recipe[1];
                        recipe = newRecipe1;
                        rows--;
                    }
                }
                if(recipe[rows - 1].equals(" ") || recipe[rows - 1].equals("  ") || recipe[rows - 1].equals("   "))
                {
                    Object[] newRecipe = new Object[recipe.length - 1];
                    newRecipe[0] = recipe[0];
                    newRecipe[1] = recipe[1];
                    recipe = newRecipe;
                    rows--;
                    if(recipe[rows - 1].equals(" ") || recipe[rows - 1].equals("  ") || recipe[rows - 1].equals("   "))
                    {
                        Object[] newRecipe1 = new Object[recipe.length - 1];
                        newRecipe1[0] = recipe[0];
                        recipe = newRecipe1;
                        rows--;
                    }
                }
                for(int i = 0; i < (recipeLine.length - 1) / 2; i++)
                {
                    recipe[i * 2 + rows] = recipeLine[i * 2 + 1].charAt(0);
                    if(recipeLine[i * 2 + 2].contains("."))
                        recipe[i * 2 + rows + 1] = getRecipeElement(recipeLine[i * 2 + 2].split("\\.")[0], Integer.valueOf(recipeLine[i * 2 + 2].split("\\.")[1]));
                    else
                        recipe[i * 2 + rows + 1] = getRecipeElement(recipeLine[i * 2 + 2], 0);
                }
                GameRegistry.addRecipe(new ItemStack(block, recipeOutput, 0), recipe);
            }
            else
            {
                recipe = new Object[recipeLine.length - 1];
                for(int i = 0; i < (recipeLine.length - 1); i++)
                    if(recipeLine[i + 1].contains("."))
                        recipe[i] = getRecipeElement(recipeLine[i + 1].split("\\.")[0], Integer.valueOf(recipeLine[i + 1].split("\\.")[1]));
                    else
                        recipe[i] = getRecipeElement(recipeLine[i + 1], 0);
                GameRegistry.addShapelessRecipe(new ItemStack(block, recipeOutput, 0), recipe);
            }
        }
        catch(Exception e)
        {
            WatchDogs.print("Failed to add recipe for : " + shortName);
            e.printStackTrace();
        }
    }
}
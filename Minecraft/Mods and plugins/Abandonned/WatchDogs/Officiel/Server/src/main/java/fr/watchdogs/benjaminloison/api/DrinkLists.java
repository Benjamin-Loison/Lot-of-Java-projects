package fr.watchdogs.benjaminloison.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class DrinkLists
{
    public static List<Drink> LOADED_DRINKS = new ArrayList<Drink>(), EXTERNAL_DRINKS = new ArrayList<Drink>();

    public static void addDrink(ItemStack item, int replenish, float saturation)
    {
        Drink d = new Drink(item, replenish, saturation, false, 0f);
        LOADED_DRINKS.add(d);
    }

    public static void addDrink(ItemStack item, int replenish, float saturation, boolean poison, float poisonChance)
    {
        Drink d = new Drink(item, replenish, saturation, poison, poisonChance);
        LOADED_DRINKS.add(d);
        EXTERNAL_DRINKS.add(d);
    }

    public static class Drink
    {
        public ItemStack item;
        public int replenish, storeRecipe, brewTime;
        public float saturation, poisonChance;
        public boolean poison;

        public Drink(ItemStack item, int rep, float sat, boolean poisonable, float chance)
        {
            this.item = item;
            replenish = rep;
            saturation = sat;
            poison = poisonable;
            poisonChance = chance;
            storeRecipe = replenish + (int)sat;
            brewTime = replenish * 30;
        }
    }
}
package fr.altiscraft.benjaminloison.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Food extends ItemFood
{
    private List<PotionEffect> effects = new ArrayList();
    private List<Double> propabilities = new ArrayList();

    public Food(String unlocalizedName, int healAmount, float saturationModifier, int size)
    {
        super(healAmount, saturationModifier, true);
        setUnlocalizedName(unlocalizedName);
        setAlwaysEdible();
        setMaxStackSize(size);
    }

    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
    {
        super.onFoodEaten(stack, world, player);
        for(int i = 0; i < effects.size(); i++)
            if(effects.get(i) != null && (((PotionEffect)effects.get(i)).getPotionID() > 0) && (Math.random() < ((Double)propabilities.get(i)).doubleValue()))
                player.addPotionEffect(new PotionEffect(((PotionEffect)effects.get(i)).getPotionID(), ((PotionEffect)effects.get(i)).getDuration(), ((PotionEffect)effects.get(i)).getAmplifier(), ((PotionEffect)effects.get(i)).getIsAmbient()));
    }

    public Food addPotionEffect(PotionEffect effect, double propability)
    {
        effects.add(effect);
        propabilities.add(Double.valueOf(propability));
        return this;
    }

    public Food addPotionEffect(PotionEffect effect)
    {
        return addPotionEffect(effect, 1.0D);
    }

    public Food removePotionEffect(PotionEffect effect)
    {
        int index = effects.indexOf(effect);
        if(index == -1)
            return this;
        effects.remove(index);
        propabilities.remove(index);
        return this;
    }
}
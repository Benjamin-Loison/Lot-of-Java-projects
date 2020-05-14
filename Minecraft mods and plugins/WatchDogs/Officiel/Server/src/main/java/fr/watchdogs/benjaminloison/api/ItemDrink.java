package fr.watchdogs.benjaminloison.api;

import java.util.List;
import java.util.Random;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemDrink extends Item
{
    public int itemColour, itemStackSize, thirstHeal, hungerHeal, potionID, duration;
    public boolean specialEffect, alwaysDrinkable, curesPotion;
    public Item returnItem = Items.glass_bottle;
    public ItemStack recipeItem;
    private IIcon drinkable, overlay;
    public float saturationHeal, poisonChance, hungerSatHeal;

    public ItemDrink(int thirst, float saturation, int colour, int stacksize, boolean effect, boolean drinkable, String name)
    {
        thirstHeal = thirst;
        saturationHeal = saturation;
        itemColour = colour;
        specialEffect = effect;
        alwaysDrinkable = drinkable;
        setUnlocalizedName(name);
        setMaxStackSize(stacksize);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(canDrink(player) || alwaysDrinkable || player.capabilities.isCreativeMode)
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
            stack.stackSize--;
            PlayerContainer playerContainer = PlayerContainer.getPlayer(player);
            playerContainer.addStats(thirstHeal, saturationHeal);
            if(poisonChance > 0 && WatchDogs.config.POISON_ON)
                if(new Random().nextFloat() < poisonChance)
                    playerContainer.getStats().poisonLogic.poisonPlayer();
            if(curesPotion)
                player.curePotionEffects(new ItemStack(Items.milk_bucket));
            if(hungerHeal > 0 && hungerSatHeal > 0)
                player.getFoodStats().addStats(hungerHeal, hungerSatHeal);
            if(potionID > 0)
                player.addPotionEffect(new PotionEffect(potionID, duration * 20, 1));
            player.inventory.addItemStackToInventory(new ItemStack(returnItem));
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedItemTooltip)
    {
        super.addInformation(stack, player, list, advancedItemTooltip);
        String s2 = Float.toString(Float.parseFloat(Integer.toString(thirstHeal)) / 2);
        list.add("Heals " + (s2.endsWith(".0") ? s2.replace(".0", "") : s2) + " Droplets");
        if(recipeItem != null)
            list.add("Ingredient: " + recipeItem.getDisplayName());
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass)
    {
        if(pass == 0)
            return specialEffect;
        return false;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.drink;
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int damageValue, int currentPass)
    {
        return currentPass == 0 ? overlay : drinkable;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public ItemDrink setPotionEffect(int i, int j)
    {
        potionID = i;
        duration = j;
        return this;
    }

    public ItemDrink setPoisoningChance(float chance)
    {
        poisonChance = chance;
        return this;
    }

    public ItemDrink healFood(int level, float saturation)
    {
        hungerHeal = level;
        hungerSatHeal = saturation;
        return this;
    }

    public ItemDrink setReturn(Item item)
    {
        returnItem = item;
        return this;
    }

    public ItemDrink setCuresPotions(boolean b)
    {
        curesPotion = b;
        return this;
    }

    public ItemDrink setRecipeItem(Item i)
    {
        recipeItem = new ItemStack(i);
        return this;
    }

    public boolean canDrink(EntityPlayer player)
    {
            return ClientStats.getInstance().level < Constants.MAX_LEVEL;
    }
}
package fr.watchdogs.benjaminloison.api;

import java.util.List;
import java.util.Random;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemInternalDrink extends Item
{
    public boolean addItem;
    public int id, thirstHeal;
    public float thirstSaturation, thirstPoison;
    public Item returnItem;

    public ItemInternalDrink(int replenish, float saturation, float poison, String texture, int stacksize)
    {
        id = 1;
        thirstHeal = replenish;
        thirstSaturation = saturation;
        thirstPoison = poison;
        setMaxStackSize(stacksize > 0 ? stacksize : Constants.DRINKS_STACKSIZE);
    }

    public ItemInternalDrink(String texture)
    {
        super();
        id = 0;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        stack.stackSize--;
        PlayerContainer playerCon = PlayerContainer.getPlayer(player);
        playerCon.addStats(thirstHeal, thirstSaturation);
        if((thirstPoison > 0) && WatchDogs.config.POISON_ON)
            if(new Random().nextFloat() < thirstPoison)
                playerCon.getStats().poisonLogic.poisonPlayer();
        player.inventory.addItemStackToInventory(new ItemStack(returnItem));
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
    {
        super.addInformation(stack, player, list, flag);
        String s2 = Float.toString(Float.parseFloat(Integer.toString(thirstHeal)) / 2);
        list.add("Heals " + (s2.endsWith(".0") ? s2.replace(".0", "") : s2) + " Droplets");
    }

    public ItemInternalDrink setReturnItem(Item returnItem)
    {
        this.returnItem = returnItem;
        return this;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.drink;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public boolean canDrink(EntityPlayer player)
    {
        return ClientStats.getInstance().level < Constants.MAX_LEVEL;
    }
}
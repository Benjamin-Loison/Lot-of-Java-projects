package fr.watchdogs.benjaminloison.api;

import java.util.Random;

import fr.watchdogs.benjaminloison.common.WatchDogs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PoisonLogic
{
    private boolean isPlayerPoisoned;
    private int poisonTimeRemaining, selectedPoison = -1;
    private Random random = new Random();

    public void onTick(EntityPlayer player)
    {
        if(isPlayerPoisoned)
            if(selectedPoison == -1)
            {
                selectedPoison = random.nextInt(3);
                poisonTimeRemaining = 800;
            }
            else
            {
                switch(selectedPoison)
                {
                    case 0:
                        increaseExhaustion(player);
                        break;
                    case 1:
                        damagePlayer(player);
                        break;
                    case 2:
                        applyPotionEffects(player);
                        break;
                }
                poisonTimeRemaining = Math.max(poisonTimeRemaining - 1, 0);
                if(poisonTimeRemaining == 0)
                    stopPoison();
            }
    }

    public void poisonPlayer()
    {
        if(WatchDogs.config.POISON_ON)
            isPlayerPoisoned = true;
    }

    public void stopPoison()
    {
        isPlayerPoisoned = false;
        poisonTimeRemaining = 800;
        selectedPoison = -1;
    }

    public void increaseExhaustion(EntityPlayer player)
    {
        PlayerContainer container = PlayerContainer.getPlayer(player);
        if(container != null)
            container.addExhaustion(0.061f);
    }

    public void damagePlayer(EntityPlayer player)
    {
        float playerHealth = player.getHealth();
        if(playerHealth > 5)
            if(poisonTimeRemaining % 80 == 0)
                player.setHealth(playerHealth - 1);
    }

    public void applyPotionEffects(EntityPlayer player)
    {
        if(player.getActivePotionEffects().size() <= 0)
        {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 1));
            player.addPotionEffect(new PotionEffect(Potion.weakness.id, 400, 1));
        }
    }

    public boolean isPlayerPoisoned()
    {
        return isPlayerPoisoned;
    }

    public int getPoisonTimeRemaining()
    {
        return poisonTimeRemaining;
    }

    public void changeValues(boolean isPoisoned, int timer)
    {
        isPlayerPoisoned = isPoisoned;
        poisonTimeRemaining = timer;
    }
}
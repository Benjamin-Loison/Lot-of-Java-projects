 package fr.realfaction.benjaminloison.common;
 
 import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
 
 public class Boisson extends ItemFood
 {
   private PotionEffect[] effects;
   
   public Boisson(String unlocalizedName, int healAmount, float saturationModifier, boolean wolvesFavorite, PotionEffect... effects)
   {
     super(healAmount, saturationModifier, wolvesFavorite);
     setUnlocalizedName(unlocalizedName);
     setTextureName("realfaction:" + unlocalizedName);
   }
   
   protected void onFoodEaten(ItemStack stack, net.minecraft.world.World world, EntityPlayer player)
   {
     super.onFoodEaten(stack, world, player);
   }
   
   public EnumAction getItemUseAction(ItemStack p_77661_1_)
   {
     return EnumAction.drink;
   }
 }
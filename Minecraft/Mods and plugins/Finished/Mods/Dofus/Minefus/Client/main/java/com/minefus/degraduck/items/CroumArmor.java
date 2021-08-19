package com.minefus.degraduck.items;

import java.util.List;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.models.mobs.ModelChiefOfWarBouftou;
import com.minefus.degraduck.models.mobs.ModelCroum;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class CroumArmor extends ItemArmor
{
    private ModelBase value;

    public CroumArmor()
    {
        super(Minefus.petArmor, 0, 3);
        setUnlocalizedName("croum");
        setTextureName(Minefus.MODID + ":Croum");
        GameRegistry.registerItem(this, "croum");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return false;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 3)
            return Minefus.MODID + ":textures/models/armor/Croum.png";
        return Minefus.MODID + ":textures/models/armor/Empty.png";
    }

    public ModelBiped getArmorModel(EntityLivingBase entityliving, ItemStack stack, int armor)
    {
        if(stack.getItem() == Minefus.croum)
            return new ModelCroum(armor);
        return null;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b)
    {
        list.add("§7Â§oAncien soldat de plomb retraité,");
        list.add("§7Â§ocette créature en métal est");
        list.add("§7Â§odevenue vivante.");
        super.addInformation(stack, player, list, b);
    }

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre pre)
    {
        value = ((ModelBase)ObfuscationReflectionHelper.getPrivateValue(RendererLivingEntity.class, pre.renderer, new String[] {"mainModel", "field_77045_g"}));
        ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, pre.renderer, new ModelChiefOfWarBouftou(), new String[] {"mainModel", "field_77045_g"});
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post post)
    {
        ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, post.renderer, value, new String[] {"mainModel", "field_77045_g"});
    }
}
package com.minefus.degraduck.items;

import com.minefus.degraduck.common.Minefus;
import com.minefus.degraduck.models.items.ModelQuestHelmet;
import com.minefus.degraduck.models.mobs.ModelChiefOfWarBouftou;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class QuestHelmet extends ItemArmor
{
    private ModelBase value;

    public QuestHelmet()
    {
        super(Minefus.bouftouArmor, 0, 0);
        setUnlocalizedName("quest_helmet");
        setTextureName(Minefus.MODID + ":Quest helmet");
        GameRegistry.registerItem(this, "quest_helmet");
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return true;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 0)
            return Minefus.MODID + ":textures/models/armor/Quest helmet.png";
        return Minefus.MODID + ":textures/models/armor/Empty.png";
    }

    public ModelBiped getArmorModel(EntityLivingBase entityliving, ItemStack stack, int armor)
    {
        if(stack.getItem() == Minefus.questHelmet)
            return new ModelQuestHelmet(armor);
        return null;
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
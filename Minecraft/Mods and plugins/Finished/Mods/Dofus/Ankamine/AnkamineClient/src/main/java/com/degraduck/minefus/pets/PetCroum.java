package com.degraduck.minefus.pets;

import java.util.List;

import com.degraduck.minefus.common.Dofus;
import com.degraduck.minefus.models.mobs.ModelChefDeGuerre;
import com.degraduck.minefus.models.pets.ModelPetCroum;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PetCroum extends ItemArmor
{
    private ModelBase value;

    public PetCroum(ItemArmor.ArmorMaterial material, int type)
    {
        super(material, 0, type);
    }

    public boolean getIsRepairable(ItemStack input, ItemStack repair)
    {
        return false;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 3)
            return Dofus.MODID + ":textures/models/armor/pet_croum.png";
        return Dofus.MODID + ":textures/models/armor/stuff_empty.png";
    }

    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityliving, ItemStack stack, int armor)
    {
        if(stack.getItem() == Dofus.PetCroum)
            return new ModelPetCroum(armor);
        return null;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
    {}

    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4)
    {
        par3List.add("§7Â§oAncien soldat de plomb retraitÃ©,");
        par3List.add("§7Â§ocette crÃ©ature en mÃ©tal est");
        par3List.add("§7Â§odevenue vivante.");
        super.addInformation(stack, player, par3List, par4);
    }

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre pre)
    {
        value = ((ModelBase)ObfuscationReflectionHelper.getPrivateValue(RendererLivingEntity.class, pre.renderer, new String[] {"mainModel", "field_77045_g"}));
        ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, pre.renderer, new ModelChefDeGuerre(), new String[] {"mainModel", "field_77045_g"});
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post post)
    {
        ObfuscationReflectionHelper.setPrivateValue(RendererLivingEntity.class, post.renderer, value, new String[] {"mainModel", "field_77045_g"});
    }
}

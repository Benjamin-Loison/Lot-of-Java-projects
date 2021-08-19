package fr.benjaminloison.enyriapvp.stuff;

import fr.benjaminloison.enyriapvp.common.EnyriaPvP;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Blockz
{
    public static Block cerusiteBlock, cerusiteOre, fluoriteBlock, fluoriteOre, curusiteBlock, curusiteOre, rubisBlock, rubisOre, enyriumBlock, enyriumOre, agateBlock, agateOre, cyaniteBlock, cyaniteOre, cesiumBlock, cesiumOre;

    public static void registerBlocks()
    {
        cerusiteBlock = new Block(Material.rock).setUnlocalizedName("cerusite_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        cerusiteOre = new Block(Material.rock).setUnlocalizedName("cerusite_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        curusiteBlock = new Block(Material.rock).setUnlocalizedName("curusite_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        curusiteOre = new Block(Material.rock).setUnlocalizedName("curusite_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        fluoriteBlock = new Block(Material.rock).setUnlocalizedName("fluorite_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        fluoriteOre = new Block(Material.rock).setUnlocalizedName("fluorite_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        rubisBlock = new Block(Material.rock).setUnlocalizedName("rubis_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        rubisOre = new Block(Material.rock).setUnlocalizedName("rubis_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        enyriumBlock = new Block(Material.rock).setUnlocalizedName("enyrium_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        enyriumOre = new Block(Material.rock).setUnlocalizedName("enyrium_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        agateBlock = new Block(Material.rock).setUnlocalizedName("agate_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        agateOre = new Block(Material.rock).setUnlocalizedName("agate_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        cesiumBlock = new Block(Material.rock).setUnlocalizedName("cesium_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        cesiumOre = new Block(Material.rock).setUnlocalizedName("cesium_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        cyaniteBlock = new Block(Material.rock).setUnlocalizedName("cyanite_block").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        cyaniteOre = new Block(Material.rock).setUnlocalizedName("cyanite_ore").setCreativeTab(CreativeTabs.tabBlock).setHardness(7.5F).setResistance(7.5F);
        GameRegistry.registerBlock(cerusiteBlock, "cerusite_block");
        GameRegistry.registerBlock(cerusiteOre, "cerusite_ore");
        GameRegistry.registerBlock(curusiteBlock, "curusite_block");
        GameRegistry.registerBlock(curusiteOre, "curusite_ore");
        GameRegistry.registerBlock(fluoriteBlock, "fluorite_block");
        GameRegistry.registerBlock(fluoriteOre, "fluorite_ore");
        GameRegistry.registerBlock(rubisBlock, "rubis_block");
        GameRegistry.registerBlock(rubisOre, "rubis_ore");
        GameRegistry.registerBlock(enyriumBlock, "enyrium_block");
        GameRegistry.registerBlock(enyriumOre, "enyrium_ore");
        GameRegistry.registerBlock(agateBlock, "agate_block");
        GameRegistry.registerBlock(agateOre, "agate_ore");
        GameRegistry.registerBlock(cesiumBlock, "cesium_block");
        GameRegistry.registerBlock(cesiumOre, "cesium_ore");
        GameRegistry.registerBlock(cyaniteBlock, "cyanite_block");
        GameRegistry.registerBlock(cyaniteOre, "cyanite_ore");
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlocksModels()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cerusiteBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cerusiteOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(curusiteBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(curusiteOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(fluoriteBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(fluoriteOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(rubisBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(rubisOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(enyriumBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(enyriumOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(agateBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(agateOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cesiumBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cesiumOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_ore", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cyaniteBlock), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_block", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(cyaniteOre), 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_ore", "inventory"));
    }
}
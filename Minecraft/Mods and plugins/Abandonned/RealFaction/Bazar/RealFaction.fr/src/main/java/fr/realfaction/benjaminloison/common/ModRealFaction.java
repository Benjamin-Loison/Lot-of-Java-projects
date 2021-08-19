package fr.realfaction.benjaminloison.common;

import java.io.File;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.realfaction.benjaminloison.client.ModelBlockForge;
import fr.realfaction.benjaminloison.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "realfaction.MODID", name = "RealFaction", version = "1.0.0")
public class ModRealFaction {
	public static final String MODID = "realfaction";
	@Mod.Instance("realfaction.MODID")
	public static ModRealFaction instance;
	public static final int guiIDMenu = 0;
	@SidedProxy(clientSide = "fr.realfaction.benjaminloison.proxy.ClientProxy", serverSide = "fr.realfaction.benjaminloison.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Block BlocCraft, BlocForge, Malte, Terre, Vigne;
	public static Item DenierDiamant, DenierFer, DenierOr, JusCarotte, Malt, Biere, Raisin, Vin;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		 if ((event.getSide().isClient()) && (!Minecraft.getMinecraft().mcDataDir.getAbsolutePath().contains("RealFaction")) && (!Minecraft.getMinecraft().mcDataDir.equals(new File(".")))) { Throwables.propagate(new Exception("Launcher non autorisé"));}
		 
		System.out.println("Pré-initialisation !");

		ModelBlockForge modelf = new ModelBlockForge();
		ResourceLocation texturef = new ResourceLocation("realfaction", "textures/models/blocks/ModelBlockForge.png");

		BlocCraft = new BlocCraft().setBlockName("bloccraft").setBlockTextureName("realfaction:bloccraft").setHardness(3.0F);
		BlocForge = new BlocForge().setBlockName("blocforge").setBlockTextureName("realfaction:Forge").setHardness(10.0F);
        Malte = new Malte().setBlockName("malte").setCreativeTab(CreativeTabs.tabMaterials).setBlockTextureName("malte");        
        Terre = new Bloc(Material.ground).setBlockName("terre").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("terre");        
        Vigne = new Vigne().setBlockName("vigne").setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName("vigne");
        
		DenierDiamant = new Argent().setUnlocalizedName("denierdiamant").setTextureName("realfaction:denierdiamant")
				.setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(10);
		DenierFer = new Argent().setUnlocalizedName("denierfer").setTextureName("realfaction:denierfer")
				.setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(50);
		DenierOr = new Argent().setUnlocalizedName("denieror").setTextureName("realfaction:denieror")
				.setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(25);
		Malt = new Item().setUnlocalizedName("malt").setTextureName("realfaction:malt")
				.setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);

		GameRegistry.registerBlock(BlocForge, "blocforge");
		GameRegistry.registerBlock(BlocCraft, "bloccraft");
		GameRegistry.registerBlock(Malte, "malte");
		GameRegistry.registerBlock(Vigne, "Vigne");		
		GameRegistry.registerBlock(Terre, "terre");

		GameRegistry.registerItem(DenierDiamant, "denierdiamant");
		GameRegistry.registerItem(DenierFer, "denierargent");
		GameRegistry.registerItem(DenierOr, "denieror");
		GameRegistry.registerItem(Malt, "malt");		
	    GameRegistry.registerItem(JusCarotte = new Boisson("juscarotte", 5, 0.2F, false, new PotionEffect[0]).setAlwaysEdible().setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(4), "juscarotte");
	    GameRegistry.registerItem(Raisin = new Nourriture("raisin", 2, 0.2F, false, new PotionEffect[0]).setAlwaysEdible().setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(8), "raisin");
	    GameRegistry.registerItem(Vin = new Boisson("vin", 8, 0.2F, false, new PotionEffect[0]).setAlwaysEdible().setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(2), "vin");
	    GameRegistry.registerItem(Biere = new Boisson("biere", 6, 0.2F, false, new PotionEffect[0]).setAlwaysEdible().setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(4), "biere");

		GameRegistry.registerTileEntity(TileEntityForge.class, "modid:forge");

	    GameRegistry.addRecipe(new ItemStack(DenierFer, 3), new Object[] { "###", Character.valueOf('#'), Items.iron_ingot});
	    GameRegistry.addRecipe(new ItemStack(DenierOr, 3), new Object[] { "###", Character.valueOf('#'), Items.gold_ingot});
	    GameRegistry.addRecipe(new ItemStack(DenierDiamant, 3), new Object[] { "###", Character.valueOf('#'), Items.diamond});
	    GameRegistry.addRecipe(new ItemStack(Items.iron_ingot, 2), new Object[] { "###", Character.valueOf('#'), DenierFer});
	    GameRegistry.addRecipe(new ItemStack(Items.gold_ingot, 2), new Object[] { "###", Character.valueOf('#'), DenierOr});
	    GameRegistry.addRecipe(new ItemStack(Items.diamond, 2), new Object[] { "###", Character.valueOf('#'), DenierDiamant});
	    GameRegistry.addRecipe(new ItemStack(Items.saddle, 1), new Object[] { "# #", "#O#", "###", Character.valueOf('#'), Items.iron_ingot, Character.valueOf('O'), Items.leather});
	    GameRegistry.addRecipe(new ItemStack(Items.iron_horse_armor, 1), new Object[] { "  #", "#O#", "###", Character.valueOf('#'), Items.iron_ingot, Character.valueOf('O'), Blocks.wool});
	    GameRegistry.addRecipe(new ItemStack(Items.golden_horse_armor, 1), new Object[] { "  #", "#O#", "###", Character.valueOf('#'), Items.gold_ingot, Character.valueOf('O'), Blocks.wool});
	    GameRegistry.addRecipe(new ItemStack(Items.diamond_horse_armor, 1), new Object[] { "  #", "#O#", "###", Character.valueOf('#'), Items.diamond, Character.valueOf('O'), Blocks.wool});
	    GameRegistry.addRecipe(new ItemStack(Items.name_tag, 1), new Object[] { "  #", " O ", "X  ", Character.valueOf('#'), Items.string, Character.valueOf('O'), Items.paper, Character.valueOf('X'), Items.dye});
	    GameRegistry.addRecipe(new ItemStack(Terre, 1), new Object[] { "#", Character.valueOf('#'), Blocks.dirt});
	    GameRegistry.addRecipe(new ItemStack(Malte, 2), new Object[] { "##", Character.valueOf('#'), Items.wheat});		
	    GameRegistry.addRecipe(new ItemStack(Vigne, 2), new Object[] { "##", Character.valueOf('#'), Blocks.leaves});		
	    GameRegistry.addRecipe(new ItemStack(JusCarotte, 2), new Object[] { "#", "#", "X", Character.valueOf('#'), Items.carrot, Character.valueOf('X'), Items.bowl});
	    GameRegistry.addRecipe(new ItemStack(Vin, 1), new Object[] { "#", "Y", "X", Character.valueOf('#'), Blocks.log, Character.valueOf('X'), Blocks.glass, Character.valueOf('Y'), Raisin});
	    GameRegistry.addRecipe(new ItemStack(Biere, 1), new Object[] { "###", "XXX", "###", Character.valueOf('#'), Blocks.glass_pane, Character.valueOf('X'), Malt});	    
	    GameRegistry.addRecipe(new ItemStack(BlocForge, 1), new Object[] { "#O#", "##X", "UUU", Character.valueOf('#'), Blocks.stained_hardened_clay, Character.valueOf('O'), Blocks.sand, Character.valueOf('U'), Blocks.stonebrick, Character.valueOf('X'), Items.bucket});
	    GameRegistry.addRecipe(new ItemStack(BlocCraft, 1), new Object[] { "###", "#O#", "###", Character.valueOf('#'), Blocks.log, Character.valueOf('O'), Blocks.crafting_table});
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Initialisation !");
		MinecraftForge.EVENT_BUS.register(new ForgeT4EventHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Post-initialisation !");
		proxy.registerRender();
	}
}
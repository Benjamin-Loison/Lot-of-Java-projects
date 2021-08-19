package com.minefus.degraduck.common;

import java.awt.Color;

import com.google.common.base.Throwables;
import com.minefus.degraduck.api.FileAPI;
import com.minefus.degraduck.blocks.BlockMod;
import com.minefus.degraduck.blocks.BlockSlabStone;
import com.minefus.degraduck.entity.EntityAggressiveArakne;
import com.minefus.degraduck.entity.EntityArakne;
import com.minefus.degraduck.entity.EntityBlackBoufton;
import com.minefus.degraduck.entity.EntityBlackYoungBoufton;
import com.minefus.degraduck.entity.EntityBlueLarva;
import com.minefus.degraduck.entity.EntityBluePiou;
import com.minefus.degraduck.entity.EntityBluePioussin;
import com.minefus.degraduck.entity.EntityBlueUnripeLarva;
import com.minefus.degraduck.entity.EntityBouftou;
import com.minefus.degraduck.entity.EntityChampChamp;
import com.minefus.degraduck.entity.EntityChiefOfWarBouftou;
import com.minefus.degraduck.entity.EntityCraquebille;
import com.minefus.degraduck.entity.EntityCraqueboule;
import com.minefus.degraduck.entity.EntityFairfulMoskito;
import com.minefus.degraduck.entity.EntityGreenLarva;
import com.minefus.degraduck.entity.EntityGreenPiou;
import com.minefus.degraduck.entity.EntityGreenPioussin;
import com.minefus.degraduck.entity.EntityGreenUnripeLarva;
import com.minefus.degraduck.entity.EntityLittleBouftou;
import com.minefus.degraduck.entity.EntityLittleChiefOfWarBouftou;
import com.minefus.degraduck.entity.EntityLittleTofu;
import com.minefus.degraduck.entity.EntityLittleWildTournesol;
import com.minefus.degraduck.entity.EntityMilimilou;
import com.minefus.degraduck.entity.EntityMoskito;
import com.minefus.degraduck.entity.EntityOrangeLarva;
import com.minefus.degraduck.entity.EntityOrangeUnripeLarva;
import com.minefus.degraduck.entity.EntityPinkPiou;
import com.minefus.degraduck.entity.EntityPinkPioussin;
import com.minefus.degraduck.entity.EntityPurplePiou;
import com.minefus.degraduck.entity.EntityPurplePioussin;
import com.minefus.degraduck.entity.EntityRedPiou;
import com.minefus.degraduck.entity.EntityRedPioussin;
import com.minefus.degraduck.entity.EntityRoyalBouftou;
import com.minefus.degraduck.entity.EntitySickedArakne;
import com.minefus.degraduck.entity.EntitySickedTofu;
import com.minefus.degraduck.entity.EntityTofu;
import com.minefus.degraduck.entity.EntityUnripeArakne;
import com.minefus.degraduck.entity.EntityVulnerableChampChamp;
import com.minefus.degraduck.entity.EntityWhiteBoufton;
import com.minefus.degraduck.entity.EntityWhiteYoungBoufton;
import com.minefus.degraduck.entity.EntityWildTournesol;
import com.minefus.degraduck.entity.EntityYellowPiou;
import com.minefus.degraduck.entity.EntityYellowPioussin;
import com.minefus.degraduck.events.MinefusEventHandler;
import com.minefus.degraduck.items.BilbyArmor;
import com.minefus.degraduck.items.BouftouAmulet;
import com.minefus.degraduck.items.BouftouBelt;
import com.minefus.degraduck.items.BouftouBoots;
import com.minefus.degraduck.items.BouftouChestplate;
import com.minefus.degraduck.items.BouftouHammer;
import com.minefus.degraduck.items.BouftouHelmet;
import com.minefus.degraduck.items.BouftouRing;
import com.minefus.degraduck.items.CroumArmor;
import com.minefus.degraduck.items.FoodMod;
import com.minefus.degraduck.items.ItemMimibiote;
import com.minefus.degraduck.items.ItemMod;
import com.minefus.degraduck.items.MousseBelt;
import com.minefus.degraduck.items.MousseBoots;
import com.minefus.degraduck.items.MousseChestplate;
import com.minefus.degraduck.items.MousseHelmet;
import com.minefus.degraduck.items.MousseSpade;
import com.minefus.degraduck.items.PotionMod;
import com.minefus.degraduck.items.QuestHelmet;
import com.minefus.degraduck.items.RoyalBouftouAmulet;
import com.minefus.degraduck.items.RoyalBouftouBelt;
import com.minefus.degraduck.items.RoyalBouftouBoots;
import com.minefus.degraduck.items.RoyalBouftouChestplate;
import com.minefus.degraduck.items.RoyalBouftouHelmet;
import com.minefus.degraduck.items.RoyalBouftouRing;
import com.minefus.degraduck.items.RoyalBouftouSword;
import com.minefus.degraduck.packets.PacketPipeline;
import com.minefus.degraduck.proxy.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = Minefus.MODID, name = Minefus.NAME, version = Minefus.VERSION)
public class Minefus
{
    @Mod.Instance(Minefus.MODID)
    public static Minefus instance;
    @SidedProxy(serverSide = "com.minefus.degraduck.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static Block dirt, stone_0, stone_1, grass_full, doubleSlabStone, singleSlabStone;
    public static Item minefus, incarnamKey, farmsKey, bouftouKey, siltedKey, larvaKey, kwakwaKey, tofuKey, royalTofuKey, ghostKey, blopsKey, multiBlopsKeys, royalMouthKey, amaknaBread, bontaPotion, astrubPotion, bouftouWool, piouSeeds, yellowPiouFeather, redPiouFeather, bluePiouFeather, greenPiouFeather, pinkPiouFeather, purplePiouFeather, mushroom, bouftouLeather, royalBouftouWool,
            royalBouftouLeather, bouftouHorn, bouftouEye, bouftouHelmet, bouftouChestplate, bouftouHammer, bouftouBelt, bouftouBoots, bouftouAmulet, bouftouRing, mousseHelmet, mousseChestplate, mousseBelt, mousseBoots, mousseSpade, royalBouftouHelmet, royalBouftouChestplate, royalBouftouBelt, royalBouftouBoots, royalBouftouAmulet, royalBouftouRing, royalBouftouSword, bilby, croum, questHelmet,
            mimibiote;
    public static ArmorMaterial bouftouArmor = EnumHelper.addArmorMaterial("bouftou_armor", 62500000, new int[] {0, 0, 0, 0}, 20), mousseArmor = EnumHelper.addArmorMaterial("mousse_armor", 62500000, new int[] {5, 4, 3, 3}, 20), royalBouftouArmor = EnumHelper.addArmorMaterial("royal_bouftou_armor", 62500000, new int[] {6, 5, 3, 3}, 20),
            petArmor = EnumHelper.addArmorMaterial("pet_armor", 62500000, new int[] {6, 6, 6, 6}, 99);
    public static ToolMaterial hammer = EnumHelper.addToolMaterial("hammer", 1, 9999, 5, 4.5F, 18), spade = EnumHelper.addToolMaterial("spade", 1, 9999, 5, 4, 18), sword = EnumHelper.addToolMaterial("sword", 1, 9999, 5, 6, 18);
    private static int modGuiIndex = 0;
    public static final int GUI_MINEFUS_INV = modGuiIndex++, GUI_MIMIBIOTE_INV = modGuiIndex++;
    public static final String MODID = "minefus", NAME = "Minefus", VERSION = "1.0.0";
    public static final PacketPipeline PACKET_PIPELINE = new PacketPipeline();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //if(!FileAPI.path.contains(NAME))
          //  Throwables.propagate(new Exception("Server non authorized !"));
        print("Pre-Initializing !");
        instance = this;
        dirt = new BlockMod(Material.grass, "dirt", "Dirt");
        stone_0 = new BlockMod(Material.rock, "stone_0", "Stone 0");
        stone_1 = new BlockMod(Material.rock, "stone_1", "Stone 1");
        grass_full = new BlockMod(Material.grass, "grass_full", "Grass Top");
        doubleSlabStone = new BlockSlabStone(true, Material.rock, 2, 10, "double_stone_slab");
        singleSlabStone = new BlockSlabStone(false, Material.rock, 2, 10, "single_stone_slab");
        mushroom = new ItemMod("mushroom", "Mushroom", 64);
        bouftouWool = new ItemMod("bouftou_wool", "Bouftou wool", 64);
        bouftouLeather = new ItemMod("bouftou_leather", "Bouftou leather", 64);
        royalBouftouWool = new ItemMod("royal_bouftou_wool", "Royal bouftou wool", 64);
        royalBouftouLeather = new ItemMod("royal_bouftou_leather", "Royal bouftou leather", 64);
        bouftouHorn = new ItemMod("bouftou_horn", "Bouftou horn", 64);
        bouftouEye = new ItemMod("bouftou_eye", "Bouftou eye", 64);
        piouSeeds = new ItemMod("piou_seeds", "Piou seeds", 64);
        yellowPiouFeather = new ItemMod("yellow_piou_feather", "Yellow piou feather", 64);
        bluePiouFeather = new ItemMod("blue_piou_feather", "Blue piou feather", 64);
        greenPiouFeather = new ItemMod("green_piou_feather", "Green piou feather", 64);
        redPiouFeather = new ItemMod("red_piou_feather", "Red piou feather", 64);
        pinkPiouFeather = new ItemMod("pink_piou_feather", "Pink piou feather", 64);
        purplePiouFeather = new ItemMod("purple_piou_feather", "Purple piou feather", 64);
        bouftouKey = new ItemMod("bouftou_key", "Bouftou key", 1);
        incarnamKey = new ItemMod("incarnam_key", "Incarnam key", 1);
        farmsKey = new ItemMod("farms_key", "Farms key", 1);
        kwakwaKey = new ItemMod("kwakwa_key", "Kwakwa key", 1);
        ghostKey = new ItemMod("ghost_key", "Ghost key", 1);
        siltedKey = new ItemMod("silted_key", "Silted key", 1);
        larvaKey = new ItemMod("larva_key", "Larva key", 1);
        blopsKey = new ItemMod("blops_key", "Blops key", 1);
        multiBlopsKeys = new ItemMod("multi_blop_key", "Multi blop key", 1);
        royalMouthKey = new ItemMod("royal_mouth_key", "Royal mouth key", 1);
        tofuKey = new ItemMod("tofu_key", "Tofu key", 1);
        royalTofuKey = new ItemMod("royal_tofu_key", "Royal tofu key", 1);
        amaknaBread = new FoodMod("amakna_bread", "Amakna bread", 4, 0.3F, false);
        astrubPotion = new PotionMod("astrub", "astrub_potion", "Astrub potion");
        bontaPotion = new PotionMod("bonta", "bonta_potion", "Bonta potion");
        mimibiote = new ItemMimibiote();
        bilby = new BilbyArmor();
        croum = new CroumArmor();
        questHelmet = new QuestHelmet();
        bouftouHelmet = new BouftouHelmet();
        bouftouChestplate = new BouftouChestplate();
        bouftouBelt = new BouftouBelt();
        bouftouBoots = new BouftouBoots();
        bouftouAmulet = new BouftouAmulet();
        bouftouRing = new BouftouRing();
        bouftouHammer = new BouftouHammer();
        mousseHelmet = new MousseHelmet();
        mousseChestplate = new MousseChestplate();
        mousseBelt = new MousseBelt();
        mousseBoots = new MousseBoots();
        mousseSpade = new MousseSpade();
        royalBouftouHelmet = new RoyalBouftouHelmet();
        royalBouftouChestplate = new RoyalBouftouChestplate();
        royalBouftouBelt = new RoyalBouftouBelt();
        royalBouftouBoots = new RoyalBouftouBoots();
        royalBouftouAmulet = new RoyalBouftouAmulet();
        royalBouftouRing = new RoyalBouftouRing();
        royalBouftouSword = new RoyalBouftouSword();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        EntityRegistry.registerGlobalEntityID(EntityArakne.class, "arakne", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityArakne.class, "arakne", 420, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityTofu.class, "tofu", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityTofu.class, "tofu", 421, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityChampChamp.class, "champ_champ", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityChampChamp.class, "champ_champ", 422, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityGreenLarva.class, "green_larva", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityGreenLarva.class, "green_larva", 423, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityOrangeLarva.class, "orange_larva", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityOrangeLarva.class, "orange_larva", 424, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBlueLarva.class, "blue_larva", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBlueLarva.class, "blue_larva", 425, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityMoskito.class, "moskito", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityMoskito.class, "moskito", 426, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBouftou.class, "bouftou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBouftou.class, "bouftou", 427, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityWhiteBoufton.class, "white_boufton", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityWhiteBoufton.class, "white_boufton", 428, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBlackBoufton.class, "black_boufton", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBlackBoufton.class, "black_boufton", 429, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityGreenPiou.class, "green_piou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityGreenPiou.class, "green_piou", 430, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBluePiou.class, "blue_piou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBluePiou.class, "blue_piou", 431, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityYellowPiou.class, "yellow_piou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityYellowPiou.class, "yellow_piou", 432, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityRedPiou.class, "red_piou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityRedPiou.class, "red_piou", 433, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityPurplePiou.class, "purple_piou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityPurplePiou.class, "purple_piou", 434, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityPinkPiou.class, "pink_piou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityPinkPiou.class, "pink_piou", 435, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityWildTournesol.class, "wild_tournesol", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityWildTournesol.class, "wild_tournesol", 436, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityCraquebille.class, "craquebille", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityCraquebille.class, "craquebille", 437, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityCraqueboule.class, "craqueboule", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityCraqueboule.class, "craqueboule", 438, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntitySickedTofu.class, "sicked_tofu", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntitySickedTofu.class, "sicked_tofu", 439, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityLittleTofu.class, "little_tofu", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityLittleTofu.class, "little_tofu", 440, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityAggressiveArakne.class, "aggressive_arakne", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityAggressiveArakne.class, "aggressive_arakne", 441, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntitySickedArakne.class, "sicked_arakne", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntitySickedArakne.class, "sicked_arakne", 442, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityUnripeArakne.class, "unripe_arakne", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityUnripeArakne.class, "unripe_arakne", 443, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityVulnerableChampChamp.class, "vulnerable_champ_champ", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityVulnerableChampChamp.class, "vulnerable_champ_champ", 444, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityGreenUnripeLarva.class, "green_unripe_larva", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityGreenUnripeLarva.class, "green_unripe_larva", 445, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityOrangeUnripeLarva.class, "orange_unripe_larva", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityOrangeUnripeLarva.class, "orange_unripe_larva", 446, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBlueUnripeLarva.class, "blue_unripe_larva", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBlueUnripeLarva.class, "blue_unripe_larva", 447, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityFairfulMoskito.class, "fearful_moskito", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityFairfulMoskito.class, "fearful_moskito", 448, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityLittleBouftou.class, "little_bouftou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityLittleBouftou.class, "little_bouftou", 449, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityWhiteYoungBoufton.class, "young_white_boufton", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityWhiteYoungBoufton.class, "young_white_boufton", 450, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBlackYoungBoufton.class, "young_black_boufton", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBlackYoungBoufton.class, "young_black_boufton", 451, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityGreenPioussin.class, "green_pioussin", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityGreenPioussin.class, "green_pioussin", 452, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityBluePioussin.class, "blue_pioussin", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityBluePioussin.class, "blue_pioussin", 453, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityYellowPioussin.class, "yellow_pioussin", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityYellowPioussin.class, "yellow_pioussin", 454, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityRedPioussin.class, "red_pioussin", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityRedPioussin.class, "red_pioussin", 455, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityPurplePioussin.class, "purple_pioussin", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityPurplePioussin.class, "purple_pioussin", 456, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityPinkPioussin.class, "pink_pioussin", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityPinkPioussin.class, "pink_pioussin", 457, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityLittleWildTournesol.class, "little_tournesol", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityLittleWildTournesol.class, "little_tournesol", 458, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityChiefOfWarBouftou.class, "chief_of_war_bouftou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityChiefOfWarBouftou.class, "chief_of_war_bouftou", 459, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityMilimilou.class, "milimilou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityMilimilou.class, "milimilou", 460, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityRoyalBouftou.class, "royal_bouftou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityRoyalBouftou.class, "royal_bouftou", 461, instance, 40, 1, true);
        EntityRegistry.registerGlobalEntityID(EntityLittleChiefOfWarBouftou.class, "little_chief_of_war_bouftou", EntityRegistry.findGlobalUniqueEntityId(), new Color(247, 218, 119).getRGB(), new Color(133, 156, 43).getRGB());
        EntityRegistry.registerModEntity(EntityLittleChiefOfWarBouftou.class, "little_chief_of_war_bouftou", 462, instance, 40, 1, true);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        PACKET_PIPELINE.initialise();
        MinecraftForge.EVENT_BUS.register(new MinefusEventHandler());
        FMLCommonHandler.instance().bus().register(new MinefusEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
        PACKET_PIPELINE.postInitialise();
        print(FileAPI.config("initialized.post"));
    }

    public static void print(Object object)
    {
        System.out.println("[" + NAME + "] " + object);
    }
}
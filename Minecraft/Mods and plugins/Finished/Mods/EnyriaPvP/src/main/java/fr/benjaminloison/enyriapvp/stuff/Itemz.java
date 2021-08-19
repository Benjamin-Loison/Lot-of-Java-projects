package fr.benjaminloison.enyriapvp.stuff;

import fr.benjaminloison.enyriapvp.common.EnyriaPvP;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBow;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Itemz
{
    public static Item cerusite = new Item().setUnlocalizedName("cerusite").setCreativeTab(CreativeTabs.tabMaterials), cerusitePickaxe, cerusiteAxe, cerusiteShovel, cerusiteHoe, cerusiteSword, cerusiteBow, cerusiteHelmet, cerusiteChestplate, cerusiteLeggings, cerusiteBoots, curusite = new Item().setUnlocalizedName("curusite").setCreativeTab(CreativeTabs.tabMaterials), curusitePickaxe, curusiteAxe,
            curusiteShovel, curusiteHoe, curusiteSword, curusiteBow, curusiteHelmet, curusiteChestplate, curusiteLeggings, curusiteBoots, fluorite = new Item().setUnlocalizedName("fluorite").setCreativeTab(CreativeTabs.tabMaterials), fluoritePickaxe, fluoriteAxe, fluoriteShovel, fluoriteHoe, fluoriteSword, fluoriteBow, fluoriteHelmet, fluoriteChestplate, fluoriteLeggings, fluoriteBoots,
            rubis = new Item().setUnlocalizedName("rubis").setCreativeTab(CreativeTabs.tabMaterials), rubisPickaxe, rubisAxe, rubisShovel, rubisHoe, rubisSword, rubisBow, rubisHelmet, rubisChestplate, rubisLeggings, rubisBoots, enyrium = new Item().setUnlocalizedName("enyrium").setCreativeTab(CreativeTabs.tabMaterials), enyriumPickaxe, enyriumAxe, enyriumShovel, enyriumHoe, enyriumSword,
            enyriumBow, enyriumHelmet, enyriumChestplate, enyriumLeggings, enyriumBoots, agate = new Item().setUnlocalizedName("agate").setCreativeTab(CreativeTabs.tabMaterials), agatePickaxe, agateAxe, agateShovel, agateHoe, agateSword, agateBow, agateHelmet, agateChestplate, agateLeggings, agateBoots, cesium = new Item().setUnlocalizedName("cesium").setCreativeTab(CreativeTabs.tabMaterials),
            cesiumPickaxe, cesiumAxe, cesiumShovel, cesiumHoe, cesiumSword, cesiumBow, cesiumHelmet, cesiumChestplate, cesiumLeggings, cesiumBoots, cyanite = new Item().setUnlocalizedName("cyanite").setCreativeTab(CreativeTabs.tabMaterials), cyanitePickaxe, cyaniteAxe, cyaniteShovel, cyaniteHoe, cyaniteSword, cyaniteBow, cyaniteHelmet, cyaniteChestplate, cyaniteLeggings, cyaniteBoots;
    public static ToolMaterial cerusiteTools = EnumHelper.addToolMaterial("cerusite_tools", 3, 1000, 15.0F, 6.0F, 30), curusiteTools = EnumHelper.addToolMaterial("curusite_tools", 3, 1000, 15.0F, 6.0F, 30), fluoriteTools = EnumHelper.addToolMaterial("fluorite_tools", 3, 1000, 15.0F, 6.0F, 30), rubisTools = EnumHelper.addToolMaterial("rubis_tools", 3, 1000, 15.0F, 6.0F, 30),
            enyriumTools = EnumHelper.addToolMaterial("enyrium_tools", 3, 1000, 15.0F, 6.0F, 30), agateTools = EnumHelper.addToolMaterial("agate_tools", 3, 1000, 15.0F, 6.0F, 30), cesiumTools = EnumHelper.addToolMaterial("cesium_tools", 3, 1000, 15.0F, 6.0F, 30), cyaniteTools = EnumHelper.addToolMaterial("cyanite_tools", 3, 1000, 15.0F, 6.0F, 30);
    public static ArmorMaterial cerusiteArmor = EnumHelper.addArmorMaterial("cerusite_armor", EnyriaPvP.MODID + ":cerusite", 103, new int[] {3, 8, 6, 3}, 10), fluoriteArmor = EnumHelper.addArmorMaterial("fluorite", EnyriaPvP.MODID + ":fluorite", 116, new int[] {3, 8, 6, 3}, 10),
            curusiteArmor = EnumHelper.addArmorMaterial("curusite_armor", EnyriaPvP.MODID + ":curusite", 103, new int[] {3, 8, 6, 3}, 10), rubisArmor = EnumHelper.addArmorMaterial("rubis_armor", EnyriaPvP.MODID + ":rubis", 116, new int[] {3, 8, 6, 3}, 10), enyriumArmor = EnumHelper.addArmorMaterial("enyrium_armor", EnyriaPvP.MODID + ":enyrium", 103, new int[] {3, 8, 6, 3}, 10),
            agateArmor = EnumHelper.addArmorMaterial("agate_armor", EnyriaPvP.MODID + ":agate", 103, new int[] {3, 8, 6, 3}, 10), cesiumArmor = EnumHelper.addArmorMaterial("cesium_armor", EnyriaPvP.MODID + ":cesium", 103, new int[] {3, 8, 6, 3}, 10), cyaniteArmor = EnumHelper.addArmorMaterial("cyanite_armor", EnyriaPvP.MODID + ":cyanite", 103, new int[] {3, 8, 6, 3}, 10);

    public static void registerItems()
    {
        GameRegistry.registerItem(cerusite, "cerusite");
        GameRegistry.registerItem(cerusitePickaxe = new ItemModPickaxe("cerusite_pickaxe", cerusiteTools), "cerusite_pickaxe");
        GameRegistry.registerItem(cerusiteAxe = new ItemModAxe("cerusite_axe", cerusiteTools), "cerusite_axe");
        GameRegistry.registerItem(cerusiteShovel = new ItemModShovel("cerusite_shovel", cerusiteTools), "cerusite_shovel");
        GameRegistry.registerItem(cerusiteHoe = new ItemModHoe("cerusite_hoe", cerusiteTools), "cerusite_hoe");
        GameRegistry.registerItem(cerusiteSword = new ItemModSword("cerusite_sword", cerusiteTools), "cerusite_sword");
        GameRegistry.registerItem(cerusiteBow = new ItemBow().setUnlocalizedName("cerusite_bow"), "cerusite_bow");
        GameRegistry.registerItem(cerusiteHelmet = new ItemModArmor("cerusite_helmet", cerusiteArmor, 1, 0), "cerusite_helmet");
        GameRegistry.registerItem(cerusiteChestplate = new ItemModArmor("cerusite_chestplate", cerusiteArmor, 1, 1), "cerusite_chestplate");
        GameRegistry.registerItem(cerusiteLeggings = new ItemModArmor("cerusite_leggings", cerusiteArmor, 2, 2), "cerusite_leggings");
        GameRegistry.registerItem(cerusiteBoots = new ItemModArmor("cerusite_boots", cerusiteArmor, 1, 3), "cerusite_boots");

        GameRegistry.registerItem(curusite, "curusite");
        GameRegistry.registerItem(curusitePickaxe = new ItemModPickaxe("curusite_pickaxe", curusiteTools), "curusite_pickaxe");
        GameRegistry.registerItem(curusiteAxe = new ItemModAxe("curusite_axe", curusiteTools), "curusite_axe");
        GameRegistry.registerItem(curusiteShovel = new ItemModShovel("curusite_shovel", curusiteTools), "curusite_shovel");
        GameRegistry.registerItem(curusiteHoe = new ItemModHoe("curusite_hoe", curusiteTools), "curusite_hoe");
        GameRegistry.registerItem(curusiteSword = new ItemModSword("curusite_sword", curusiteTools), "curusite_sword");
        GameRegistry.registerItem(curusiteBow = new ItemBow().setUnlocalizedName("curusite_bow"), "curusite_bow");
        GameRegistry.registerItem(curusiteHelmet = new ItemModArmor("curusite_helmet", curusiteArmor, 1, 0), "curusite_helmet");
        GameRegistry.registerItem(curusiteChestplate = new ItemModArmor("curusite_chestplate", curusiteArmor, 1, 1), "curusite_chestplate");
        GameRegistry.registerItem(curusiteLeggings = new ItemModArmor("curusite_leggings", curusiteArmor, 2, 2), "curusite_leggings");
        GameRegistry.registerItem(curusiteBoots = new ItemModArmor("curusite_boots", curusiteArmor, 1, 3), "curusite_boots");

        GameRegistry.registerItem(fluorite, "fluorite");
        GameRegistry.registerItem(fluoritePickaxe = new ItemModPickaxe("fluorite_pickaxe", fluoriteTools), "fluorite_pickaxe");
        GameRegistry.registerItem(fluoriteAxe = new ItemModAxe("fluorite_axe", fluoriteTools), "fluorite_axe");
        GameRegistry.registerItem(fluoriteShovel = new ItemModShovel("fluorite_shovel", fluoriteTools), "fluorite_shovel");
        GameRegistry.registerItem(fluoriteHoe = new ItemModHoe("fluorite_hoe", fluoriteTools), "fluorite_hoe");
        GameRegistry.registerItem(fluoriteSword = new ItemModSword("fluorite_sword", fluoriteTools), "fluorite_sword");
        GameRegistry.registerItem(fluoriteBow = new ItemBow().setUnlocalizedName("fluorite_bow"), "fluorite_bow");
        GameRegistry.registerItem(fluoriteHelmet = new ItemModArmor("fluorite_helmet", fluoriteArmor, 1, 0), "fluorite_helmet");
        GameRegistry.registerItem(fluoriteChestplate = new ItemModArmor("fluorite_chestplate", fluoriteArmor, 1, 1), "fluorite_chestplate");
        GameRegistry.registerItem(fluoriteLeggings = new ItemModArmor("fluorite_leggings", fluoriteArmor, 2, 2), "fluorite_leggings");
        GameRegistry.registerItem(fluoriteBoots = new ItemModArmor("fluorite_boots", fluoriteArmor, 1, 3), "fluorite_boots");

        GameRegistry.registerItem(rubis, "rubis");
        GameRegistry.registerItem(rubisPickaxe = new ItemModPickaxe("rubis_pickaxe", rubisTools), "rubis_pickaxe");
        GameRegistry.registerItem(rubisAxe = new ItemModAxe("rubis_axe", rubisTools), "rubis_axe");
        GameRegistry.registerItem(rubisShovel = new ItemModShovel("rubis_shovel", rubisTools), "rubis_shovel");
        GameRegistry.registerItem(rubisHoe = new ItemModHoe("rubis_hoe", rubisTools), "rubis_hoe");
        GameRegistry.registerItem(rubisSword = new ItemModSword("rubis_sword", rubisTools), "rubis_sword");
        GameRegistry.registerItem(rubisBow = new ItemBow().setUnlocalizedName("rubis_bow"), "rubis_bow");
        GameRegistry.registerItem(rubisHelmet = new ItemModArmor("rubis_helmet", rubisArmor, 1, 0), "rubis_helmet");
        GameRegistry.registerItem(rubisChestplate = new ItemModArmor("rubis_chestplate", rubisArmor, 1, 1), "rubis_chestplate");
        GameRegistry.registerItem(rubisLeggings = new ItemModArmor("rubis_leggings", rubisArmor, 2, 2), "rubis_leggings");
        GameRegistry.registerItem(rubisBoots = new ItemModArmor("rubis_boots", rubisArmor, 1, 3), "rubis_boots");

        GameRegistry.registerItem(enyrium, "enyrium");
        GameRegistry.registerItem(enyriumPickaxe = new ItemModPickaxe("enyrium_pickaxe", enyriumTools), "enyrium_pickaxe");
        GameRegistry.registerItem(enyriumAxe = new ItemModAxe("enyrium_axe", enyriumTools), "enyrium_axe");
        GameRegistry.registerItem(enyriumShovel = new ItemModShovel("enyrium_shovel", enyriumTools), "enyrium_shovel");
        GameRegistry.registerItem(enyriumHoe = new ItemModHoe("enyrium_hoe", enyriumTools), "enyrium_hoe");
        GameRegistry.registerItem(enyriumSword = new ItemModSword("enyrium_sword", enyriumTools), "enyrium_sword");
        GameRegistry.registerItem(enyriumBow = new ItemBow().setUnlocalizedName("enyrium_bow"), "enyrium_bow");
        GameRegistry.registerItem(enyriumHelmet = new ItemModArmor("enyrium_helmet", enyriumArmor, 1, 0), "enyrium_helmet");
        GameRegistry.registerItem(enyriumChestplate = new ItemModArmor("enyrium_chestplate", enyriumArmor, 1, 1), "enyrium_chestplate");
        GameRegistry.registerItem(enyriumLeggings = new ItemModArmor("enyrium_leggings", enyriumArmor, 2, 2), "enyrium_leggings");
        GameRegistry.registerItem(enyriumBoots = new ItemModArmor("enyrium_boots", enyriumArmor, 1, 3), "enyrium_boots");

        GameRegistry.registerItem(agate, "agate");
        GameRegistry.registerItem(agatePickaxe = new ItemModPickaxe("agate_pickaxe", agateTools), "agate_pickaxe");
        GameRegistry.registerItem(agateAxe = new ItemModAxe("agate_axe", agateTools), "agate_axe");
        GameRegistry.registerItem(agateShovel = new ItemModShovel("agate_shovel", agateTools), "agate_shovel");
        GameRegistry.registerItem(agateHoe = new ItemModHoe("agate_hoe", agateTools), "agate_hoe");
        GameRegistry.registerItem(agateSword = new ItemModSword("agate_sword", agateTools), "agate_sword");
        GameRegistry.registerItem(agateBow = new ItemBow().setUnlocalizedName("agate_bow"), "agate_bow");
        GameRegistry.registerItem(agateHelmet = new ItemModArmor("agate_helmet", agateArmor, 1, 0), "agate_helmet");
        GameRegistry.registerItem(agateChestplate = new ItemModArmor("agate_chestplate", agateArmor, 1, 1), "agate_chestplate");
        GameRegistry.registerItem(agateLeggings = new ItemModArmor("agate_leggings", agateArmor, 2, 2), "agate_leggings");
        GameRegistry.registerItem(agateBoots = new ItemModArmor("agate_boots", agateArmor, 1, 3), "agate_boots");

        GameRegistry.registerItem(cesium, "cesium");
        GameRegistry.registerItem(cesiumPickaxe = new ItemModPickaxe("cesium_pickaxe", cesiumTools), "cesium_pickaxe");
        GameRegistry.registerItem(cesiumAxe = new ItemModAxe("cesium_axe", cesiumTools), "cesium_axe");
        GameRegistry.registerItem(cesiumShovel = new ItemModShovel("cesium_shovel", cesiumTools), "cesium_shovel");
        GameRegistry.registerItem(cesiumHoe = new ItemModHoe("cesium_hoe", cesiumTools), "cesium_hoe");
        GameRegistry.registerItem(cesiumSword = new ItemModSword("cesium_sword", cesiumTools), "cesium_sword");
        GameRegistry.registerItem(cesiumBow = new ItemBow().setUnlocalizedName("cesium_bow"), "cesium_bow");
        GameRegistry.registerItem(cesiumHelmet = new ItemModArmor("cesium_helmet", cesiumArmor, 1, 0), "cesium_helmet");
        GameRegistry.registerItem(cesiumChestplate = new ItemModArmor("cesium_chestplate", cesiumArmor, 1, 1), "cesium_chestplate");
        GameRegistry.registerItem(cesiumLeggings = new ItemModArmor("cesium_leggings", cesiumArmor, 2, 2), "cesium_leggings");
        GameRegistry.registerItem(cesiumBoots = new ItemModArmor("cesium_boots", cesiumArmor, 1, 3), "cesium_boots");

        GameRegistry.registerItem(cyanite, "cyanite");
        GameRegistry.registerItem(cyanitePickaxe = new ItemModPickaxe("cyanite_pickaxe", cyaniteTools), "cyanite_pickaxe");
        GameRegistry.registerItem(cyaniteAxe = new ItemModAxe("cyanite_axe", cyaniteTools), "cyanite_axe");
        GameRegistry.registerItem(cyaniteShovel = new ItemModShovel("cyanite_shovel", cyaniteTools), "cyanite_shovel");
        GameRegistry.registerItem(cyaniteHoe = new ItemModHoe("cyanite_hoe", cyaniteTools), "cyanite_hoe");
        GameRegistry.registerItem(cyaniteSword = new ItemModSword("cyanite_sword", cyaniteTools), "cyanite_sword");
        GameRegistry.registerItem(cyaniteBow = new ItemBow().setUnlocalizedName("cyanite_bow"), "cyanite_bow");
        GameRegistry.registerItem(cyaniteHelmet = new ItemModArmor("cyanite_helmet", cyaniteArmor, 1, 0), "cyanite_helmet");
        GameRegistry.registerItem(cyaniteChestplate = new ItemModArmor("cyanite_chestplate", cyaniteArmor, 1, 1), "cyanite_chestplate");
        GameRegistry.registerItem(cyaniteLeggings = new ItemModArmor("cyanite_leggings", cyaniteArmor, 2, 2), "cyanite_leggings");
        GameRegistry.registerItem(cyaniteBoots = new ItemModArmor("cyanite_boots", cyaniteArmor, 1, 3), "cyanite_boots");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemsModels()
    {
        ModelLoader.setCustomModelResourceLocation(cerusite, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusitePickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cerusiteBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cerusite_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(curusite, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusitePickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(curusiteBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":curusite_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(fluorite, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoritePickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(fluoriteBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":fluorite_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(rubis, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisPickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(rubisBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":rubis_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(enyrium, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumPickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(enyriumBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":enyrium_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(agate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agatePickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(agateBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":agate_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(cesium, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumPickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cesiumBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cesium_boots", "inventory"));

        ModelLoader.setCustomModelResourceLocation(cyanite, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyanitePickaxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteAxe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_axe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteHoe, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_hoe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteShovel, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_shovel", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteSword, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_sword", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteBow, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_bow", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteHelmet, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_helmet", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteChestplate, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_chestplate", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteLeggings, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_leggings", "inventory"));
        ModelLoader.setCustomModelResourceLocation(cyaniteBoots, 0, new ModelResourceLocation(EnyriaPvP.MODID + ":cyanite_boots", "inventory"));
    }
}
package fr.benjaminloison.enyriapvp.stuff;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipez
{
    public static void registerRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(Blockz.cerusiteBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.cerusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusite, 9), new Object[] {"#", '#', Blockz.cerusiteBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteHelmet, 1), new Object[] {"###", "# #", '#', Itemz.cerusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.cerusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.cerusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteBoots, 1), new Object[] {"# #", "# #", '#', Itemz.cerusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusitePickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteSword, 1), new Object[] {"#", "#", "I", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteHoe, 1), new Object[] {"##", "I", "I", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cerusiteBow, 1), new Object[] {"#I ", "# I", "#I ", '#', Itemz.cerusite, Character.valueOf('I'), Items.stick});
        
        GameRegistry.addSmelting(Blockz.cerusiteOre, new ItemStack(Itemz.cerusite), 1.0F);
        GameRegistry.addRecipe(new ItemStack(Blockz.curusiteBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.curusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusite, 9), new Object[] {"#", '#', Blockz.curusiteBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteHelmet, 1), new Object[] {"###", "# #", '#', Itemz.curusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.curusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.curusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteBoots, 1), new Object[] {"# #", "# #", '#', Itemz.curusite});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusitePickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteSword, 1), new Object[] {"#", "#", "I", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.curusiteHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.curusite, Character.valueOf('I'), Items.stick});
        GameRegistry.addSmelting(Blockz.curusiteOre, new ItemStack(Itemz.curusite), 1.0F);

        GameRegistry.addRecipe(new ItemStack(Blockz.fluoriteBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.fluorite});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluorite, 9), new Object[] {"#", '#', Blockz.fluoriteBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteHelmet, 1), new Object[] {"###", "# #", '#', Itemz.fluorite});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.fluorite});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.fluorite});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteBoots, 1), new Object[] {"# #", "# #", '#', Itemz.fluorite});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoritePickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteSword, 1), new Object[] {"#", "#", "I", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.fluoriteHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.fluorite, Character.valueOf('I'), Items.stick});
        GameRegistry.addSmelting(Blockz.fluoriteOre, new ItemStack(Itemz.fluorite), 1.0F);

        GameRegistry.addRecipe(new ItemStack(Blockz.rubisBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.rubis});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubis, 9), new Object[] {"#", '#', Blockz.rubisBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisHelmet, 1), new Object[] {"###", "# #", '#', Itemz.rubis});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.rubis});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.rubis});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisBoots, 1), new Object[] {"# #", "# #", '#', Itemz.rubis});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisPickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisSword, 1), new Object[] {"#", "#", "I", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.rubisHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.rubis, Character.valueOf('I'), Items.stick});
        GameRegistry.addSmelting(Blockz.rubisOre, new ItemStack(Itemz.rubis), 1.0F);

        GameRegistry.addRecipe(new ItemStack(Blockz.enyriumBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.enyrium});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyrium, 9), new Object[] {"#", '#', Blockz.enyriumBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumHelmet, 1), new Object[] {"###", "# #", '#', Itemz.enyrium});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.enyrium});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.enyrium});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumBoots, 1), new Object[] {"# #", "# #", '#', Itemz.enyrium});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumPickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumSword, 1), new Object[] {"#", "#", "I", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.enyriumHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.enyrium, Character.valueOf('I'), Items.stick});

        GameRegistry.addRecipe(new ItemStack(Blockz.agateBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.agate});
        GameRegistry.addRecipe(new ItemStack(Itemz.agate, 9), new Object[] {"#", '#', Blockz.agateBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateHelmet, 1), new Object[] {"###", "# #", '#', Itemz.agate});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.agate});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.agate});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateBoots, 1), new Object[] {"# #", "# #", '#', Itemz.agate});
        GameRegistry.addRecipe(new ItemStack(Itemz.agatePickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.agate, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.agate, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateSword, 1), new Object[] {"#", "#", "I", '#', Itemz.agate, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.agate, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.agate, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.agate, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.agateHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.agate, Character.valueOf('I'), Items.stick});

        GameRegistry.addRecipe(new ItemStack(Blockz.cyaniteBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.cyanite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyanite, 9), new Object[] {"#", '#', Blockz.cyaniteBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteHelmet, 1), new Object[] {"###", "# #", '#', Itemz.cyanite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.cyanite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.cyanite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteBoots, 1), new Object[] {"# #", "# #", '#', Itemz.cyanite});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyanitePickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteSword, 1), new Object[] {"#", "#", "I", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cyaniteHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.cyanite, Character.valueOf('I'), Items.stick});

        GameRegistry.addRecipe(new ItemStack(Blockz.cesiumBlock, 1), new Object[] {"###", "###", "###", '#', Itemz.cesium});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesium, 9), new Object[] {"#", '#', Blockz.cesiumBlock});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumHelmet, 1), new Object[] {"###", "# #", '#', Itemz.cesium});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumChestplate, 1), new Object[] {"# #", "###", "###", '#', Itemz.cesium});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumLeggings, 1), new Object[] {"###", "# #", "# #", '#', Itemz.cesium});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumBoots, 1), new Object[] {"# #", "# #", '#', Itemz.cesium});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumPickaxe, 1), new Object[] {"###", " I ", " I ", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumShovel, 1), new Object[] {"#", "I", "I", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumSword, 1), new Object[] {"#", "#", "I", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumAxe, 1), new Object[] {"##", "#I", " I", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumAxe, 1), new Object[] {"##", "I#", "I ", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumHoe, 1), new Object[] {"##", "I ", "I ", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
        GameRegistry.addRecipe(new ItemStack(Itemz.cesiumHoe, 1), new Object[] {"##", " I", " I", '#', Itemz.cesium, Character.valueOf('I'), Items.stick});
    }
}
package fr.watchdogs.benjaminloison.api;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class Temperature
{
    public float airTemperture;
    public EntityPlayer player;
    public HashMap<String, Float> specialBiomes = new HashMap<String, Float>(), specialBlocks = new HashMap<String, Float>();

    public Temperature(EntityPlayer player)
    {
        this.player = player;
        initiateMaps();
    }

    public void onTick()
    {
        airTemperture = getBiomeTemp() + getBlockProximity() + getDepth() + getMisc();
    }

    public float getBiomeTemp()
    {
        String biome = ThirstLogic.getCurrentBiome(player);
        if(biome != null)
            return specialBiomes.get(biome);
        return 25f;
    }

    public float getBlockProximity()
    {
        float temp = 0f;
        for(String blockName : specialBlocks.keySet())
        {
            Block block = (Block)Block.blockRegistry.getObject(blockName.replace("tile.", ""));
            for(int x = -3; x < 3; x++)
                for(int z = -3; z < 3; z++)
                    for(int y = -2; y < 4; y++)
                        if(player.worldObj.getBlock((int)player.posX + x, (int)player.posY + y, (int)player.posZ + z).getUnlocalizedName().equals(block.getUnlocalizedName()))
                            temp += specialBlocks.get(blockName);
        }
        return temp;
    }

    public float getDepth()
    {
        float temp = 0;
        if(player.isInsideOfMaterial(Material.water))
            for(int y = (int)player.posY + 2; y < player.worldObj.getHeight(); y++)
                if(player.worldObj.getBlock((int)player.posX, y, (int)player.posZ).getUnlocalizedName().equals(Blocks.water.getUnlocalizedName()))
                    temp -= 2;
        return temp;
    }

    public float getMisc()
    {
        float temp = 0;
        if(player.isInWater() || player.isInsideOfMaterial(Material.water))
            temp -= 5.5;
        else if(player.isInsideOfMaterial(Material.lava))
            temp += 100;
        if(player.worldObj.isRaining())
            temp -= 6.5;
        if(player.isBurning())
            temp += 30;
        return temp;
    }

    public void initiateMaps()
    {
        addBiomes();
        addBlocks();
    }

    public void addBiomes()
    {
        for(BiomeGenBase base : BiomeGenBase.getBiomeGenArray())
            if(base != null)
                if(base.biomeName.contains("Frozen") || base.biomeName.contains("Ice") || base.biomeName.contains("Taiga"))
                    specialBiomes.put(base.biomeName, 5f);
                else if(base.biomeName.contains("River") || base.biomeName.contains("Ocean"))
                    specialBiomes.put(base.biomeName, 25f);
                else if(base.biomeName.contains("Extreme"))
                    specialBiomes.put(base.biomeName, 15f);
                else if(base.biomeName.contains("Forest") || base.biomeName.contains("Mushroom") || base.biomeName.contains("Swamp"))
                    specialBiomes.put(base.biomeName, 30f);
                else if(base.biomeName.contains("Desert") || base.biomeName.contains("Hell"))
                    specialBiomes.put(base.biomeName, 40f);
                else
                    specialBiomes.put(base.biomeName, 25f);
    }

    public void addBlocks()
    {
        specialBlocks.put(Blocks.lava.getUnlocalizedName(), 0.75f);
        specialBlocks.put(Blocks.ice.getUnlocalizedName(), -0.5f);
    }
}
package fr.benjaminloison.enyriapvp.stuff;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ModWorldGen implements IWorldGenerator
{
    private WorldGenerator genCerusiteOre, genCurusiteOre, genFluoriteOre, genRubisOre, genEnyriumOre, genAgateOre, genCyaniteOre, genCesiumOre;

    public ModWorldGen()
    {
        genCerusiteOre = new WorldGenMinable(Blockz.cerusiteOre.getDefaultState(), 2);
        genCurusiteOre = new WorldGenMinable(Blockz.curusiteOre.getDefaultState(), 2);
        genFluoriteOre = new WorldGenMinable(Blockz.fluoriteOre.getDefaultState(), 2);
        genRubisOre = new WorldGenMinable(Blockz.rubisOre.getDefaultState(), 2);
        genEnyriumOre = new WorldGenMinable(Blockz.enyriumOre.getDefaultState(), 2);
        genAgateOre = new WorldGenMinable(Blockz.agateOre.getDefaultState(), 2);
        genCyaniteOre = new WorldGenMinable(Blockz.cyaniteOre.getDefaultState(), 2);
        genCesiumOre = new WorldGenMinable(Blockz.cesiumOre.getDefaultState(), 2);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimensionId())
        {
            case 0:
            {
                runGenerator(genCerusiteOre, world, random, chunkX, chunkZ, 1, 5, 10);
                runGenerator(genCurusiteOre, world, random, chunkX, chunkZ, 1, 5, 10);
                runGenerator(genFluoriteOre, world, random, chunkX, chunkZ, 1, 5, 10);
                runGenerator(genRubisOre, world, random, chunkX, chunkZ, 1, 5, 10);
                runGenerator(genEnyriumOre, world, random, chunkX, chunkZ, 1, 1, 6);
                runGenerator(genAgateOre, world, random, chunkX, chunkZ, 1, 1, 8);
                runGenerator(genCyaniteOre, world, random, chunkX, chunkZ, 1, 2, 15);
                runGenerator(genCesiumOre, world, random, chunkX, chunkZ, 1, 5, 20);
                break;
            }
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight)
    {
        if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        for(int i = 0; i < chancesToSpawn; i++)
            generator.generate(world, rand, new BlockPos(chunk_X * 16 + rand.nextInt(16), minHeight + rand.nextInt(maxHeight - minHeight + 1), chunk_Z * 16 + rand.nextInt(16)));
    }
}
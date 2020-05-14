package fr.watchdogs.benjaminloison.common.guns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.watchdogs.benjaminloison.driveables.InfoType;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FlansModExplosion extends Explosion
{
    private int boomRadius = 16;
    private Random explosionRNG = new Random();
    private HashMap<EntityPlayer, Vec3> playerLocations = new HashMap<EntityPlayer, Vec3>();
    private World worldObj;
    public InfoType type;
    public EntityPlayer player;
    public List nonProcessedBlockPositions = new ArrayList();
    private float radius;

    public FlansModExplosion(World w, Entity e, EntityPlayer p, InfoType t, double x, double y, double z, float r, boolean breakBlocks)
    {
        super(w, e, x, y, z, r);
        radius = r;
        worldObj = w;
        type = t;
        player = p;
        isFlaming = false;
        isSmoking = breakBlocks;
        doExplosionA();
        doExplosionB(true);
        if(!breakBlocks)
            affectedBlockPositions.clear();
        for(Object playerEntity : worldObj.playerEntities)
        {
            EntityPlayer entityplayer = (EntityPlayer)playerEntity;
            if(entityplayer.getDistanceSq(x, y, z) < 4096)
                ((EntityPlayerMP)entityplayer).playerNetServerHandler.sendPacket(new S27PacketExplosion(x, y, z, r, affectedBlockPositions, (Vec3)func_77277_b().get(entityplayer)));
        }
    }

    @Override
    public void doExplosionA()
    {
        float f = explosionSize;
        HashSet hashset = new HashSet();
        double d0, d1, d2;
        for(int i = 0; i < boomRadius; ++i)
            for(int j = 0; j < boomRadius; ++j)
                for(int k = 0; k < boomRadius; ++k)
                    if(i == 0 || i == boomRadius - 1 || j == 0 || j == boomRadius - 1 || k == 0 || k == boomRadius - 1)
                    {
                        double d3 = (i / (boomRadius - 1) * 2 - 1), d4 = (j / (boomRadius - 1) * 2 - 1), d5 = (k / (boomRadius - 1) * 2 - 1), d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        float f1 = explosionSize * (0.7F + worldObj.rand.nextFloat() * 0.6F);
                        d0 = explosionX;
                        d1 = explosionY;
                        d2 = explosionZ;
                        for(float f2 = 0.3F; f1 > 0; f1 -= f2 * 0.75F)
                        {
                            int l = MathHelper.floor_double(d0), i1 = MathHelper.floor_double(d1), j1 = MathHelper.floor_double(d2);
                            Block block = worldObj.getBlock(l, i1, j1);
                            float f3 = exploder != null ? exploder.func_145772_a(this, worldObj, l, i1, j1, block) : block.getExplosionResistance(exploder, worldObj, l, i1, j1, explosionX, explosionY, explosionZ);
                            f1 -= (f3 + 0.3F) * f2;
                            if(f1 > 0 && (exploder == null || exploder.func_145774_a(this, worldObj, l, i1, j1, block, f1)))
                                hashset.add(new ChunkPosition(l, i1, j1));
                            d0 += d3 * f2;
                            d1 += d4 * f2;
                            d2 += d5 * f2;
                        }
                    }
        nonProcessedBlockPositions.addAll(hashset);
        explosionSize *= 2;
        int i = MathHelper.floor_double(explosionX - explosionSize - 1), j = MathHelper.floor_double(explosionX + explosionSize + 1), k = MathHelper.floor_double(explosionY - explosionSize - 1), l1 = MathHelper.floor_double(explosionY + explosionSize + 1), i2 = MathHelper.floor_double(explosionZ - explosionSize - 1), j2 = MathHelper.floor_double(explosionZ + explosionSize + 1);
        Vec3 vec3 = Vec3.createVectorHelper(explosionX, explosionY, explosionZ);
        for(Object aList : worldObj.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBox(i, k, i2, j, l1, j2)))
        {
            Entity entity = (Entity)aList;
            double d7 = entity.getDistance(explosionX, explosionY, explosionZ) / explosionSize;
            if(d7 <= 1)
            {
                d0 = entity.posX - explosionX;
                d1 = entity.posY + entity.getEyeHeight() - explosionY;
                d2 = entity.posZ - explosionZ;
                double d8 = MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                if(d8 != 0)
                {
                    d0 /= d8;
                    d1 /= d8;
                    d2 /= d8;
                    double d9 = worldObj.getBlockDensity(vec3, entity.boundingBox), d10 = (1 - d7) * d9;
                    entity.attackEntityFrom(player == null || type == null ? DamageSource.setExplosionSource(this) : new EntityDamageSourceGun(type.shortName, entity, player, type, false), ((int)((d10 * d10 + d10) / 2 * 8 * explosionSize + 1)));
                    double d11 = EnchantmentProtection.func_92092_a(entity, d10);
                    entity.motionX += d0 * d11;
                    entity.motionY += d1 * d11;
                    entity.motionZ += d2 * d11;
                    if(entity instanceof EntityPlayer)
                        playerLocations.put((EntityPlayer)entity, Vec3.createVectorHelper(d0 * d10, d1 * d10, d2 * d10));
                }
            }
        }
        explosionSize = f;
    }

    @Override
    public void doExplosionB(boolean b)
    {
        worldObj.playSoundEffect(explosionX, explosionY, explosionZ, "random.explode", 4, (1 + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
        if(explosionSize >= 2 && isSmoking)
            worldObj.spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1, 0, 0);
        else
            worldObj.spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1, 0, 0);
        Iterator iterator;
        ChunkPosition chunkposition;
        int i, j, k;
        Block block;
        if(isSmoking)
            worldObj.createExplosion(player, explosionX, explosionY, explosionZ, radius, true);
        if(isFlaming)
        {
            iterator = nonProcessedBlockPositions.iterator();
            while(iterator.hasNext())
            {
                chunkposition = (ChunkPosition)iterator.next();
                i = chunkposition.chunkPosX;
                j = chunkposition.chunkPosY;
                k = chunkposition.chunkPosZ;
                block = worldObj.getBlock(i, j, k);
                Block blockBelow = worldObj.getBlock(i, j - 1, k);
                if(block == null && blockBelow.isOpaqueCube() && explosionRNG.nextInt(3) == 0)
                    worldObj.setBlock(i, j, k, Blocks.fire);
            }
        }
    }

    @Override
    public Map func_77277_b()
    {
        return playerLocations;
    }

    public EntityLivingBase func_94613_c()
    {
        return exploder == null ? null : (exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed)exploder).getTntPlacedBy() : (exploder instanceof EntityLivingBase ? (EntityLivingBase)exploder : null));
    }
}
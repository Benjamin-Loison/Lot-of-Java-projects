package fr.watchdogs.benjaminloison.proxy;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.PlayerContainer;
import fr.watchdogs.benjaminloison.common.WatchDogs;
import fr.watchdogs.benjaminloison.common.guns.ContainerGunModTable;
import fr.watchdogs.benjaminloison.driveables.ContainerDriveableInventory;
import fr.watchdogs.benjaminloison.driveables.ContainerDriveableMenu;
import fr.watchdogs.benjaminloison.driveables.ContainerMechaInventory;
import fr.watchdogs.benjaminloison.driveables.DriveablePart;
import fr.watchdogs.benjaminloison.driveables.DriveableType;
import fr.watchdogs.benjaminloison.driveables.EntityDriveable;
import fr.watchdogs.benjaminloison.driveables.EntityMecha;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import fr.watchdogs.benjaminloison.driveables.EnumDriveablePart;
import fr.watchdogs.benjaminloison.driveables.EnumType;
import fr.watchdogs.benjaminloison.driveables.ItemPart;
import fr.watchdogs.benjaminloison.driveables.PartType;
import fr.watchdogs.benjaminloison.packets.PacketBreakSound;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy
{
    protected static Pattern zipJar = Pattern.compile("(.+).(zip|jar)$");
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    public void serverTick(EntityPlayer player)
    {
        PlayerContainer handler = PlayerContainer.getPlayer(player);
        if(handler != null)
            if(!player.capabilities.isCreativeMode)
                handler.getStats().onTick();
            else
                PlayerContainer.addPlayer(player);
    }

    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return extendedEntityData.remove(name);
    }

    public List<File> getContentList(Method method, ClassLoader classloader)
    {
        List<File> contentPacks = new ArrayList<File>();
        contentPacks.add(new File(FileAPI.acfr + "WatchDogs.fr.jar"));
        return contentPacks;
    }

    public Container getServerGui(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        switch(ID)
        {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                return new ContainerGunModTable(player.inventory, world);
            case 3:
                return new ContainerDriveableMenu(player.inventory, world);
            case 4:
                return new ContainerDriveableMenu(player.inventory, world, true, ((EntitySeat)player.ridingEntity).driveable);
            case 5:
                return null;
            case 6:
                return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 0);
            case 7:
                return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 1);
            case 8:
                return new ContainerDriveableMenu(player.inventory, world, true, ((EntitySeat)player.ridingEntity).driveable);
            case 9:
                return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 2);
            case 10:
                return new ContainerMechaInventory(player.inventory, world, (EntityMecha)((EntitySeat)player.ridingEntity).driveable);
            case 11:
                return null;
            case 12:
                return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 3);
        }
        return null;
    }

    public void playBlockBreakSound(int x, int y, int z, Block blockHit)
    {
        WatchDogs.packetHandler.sendToAll(new PacketBreakSound(x, y, z, blockHit));
    }

    public void craftDriveable(EntityPlayer player, DriveableType type)
    {
        InventoryPlayer temporaryInventory = new InventoryPlayer(null);
        temporaryInventory.copyInventory(player.inventory);
        boolean canCraft = true;
        for(ItemStack recipeStack : type.recipe)
        {
            int totalAmountFound = 0;
            for(int n = 0; n < player.inventory.getSizeInventory(); n++)
            {
                ItemStack stackInSlot = player.inventory.getStackInSlot(n);
                if(stackInSlot != null && stackInSlot.getItem() == recipeStack.getItem() && stackInSlot.getItemDamage() == recipeStack.getItemDamage())
                {
                    int amountFound = Math.min(stackInSlot.stackSize, recipeStack.stackSize - totalAmountFound);
                    stackInSlot.stackSize -= amountFound;
                    if(stackInSlot.stackSize <= 0)
                        stackInSlot = null;
                    player.inventory.setInventorySlotContents(n, stackInSlot);
                    totalAmountFound += amountFound;
                    if(totalAmountFound == recipeStack.stackSize)
                        break;
                }
            }
            if(totalAmountFound < recipeStack.stackSize)
            {
                canCraft = false;
                break;
            }
        }
        if(!canCraft)
        {
            player.inventory.copyInventory(temporaryInventory);
            return;
        }
        HashMap<PartType, ItemStack> engines = new HashMap<PartType, ItemStack>();
        for(int n = 0; n < temporaryInventory.getSizeInventory(); n++)
        {
            ItemStack stackInSlot = temporaryInventory.getStackInSlot(n);
            if(stackInSlot != null && stackInSlot.getItem() instanceof ItemPart)
            {
                PartType partType = ((ItemPart)stackInSlot.getItem()).type;
                if(partType.category == 2 && partType.worksWith.contains(EnumType.getFromObject(type)))
                    if(engines.containsKey(partType))
                        engines.get(partType).stackSize += stackInSlot.stackSize;
                    else
                        engines.put(partType, stackInSlot);
            }
        }
        float bestEngineSpeed = -1;
        ItemStack bestEngineStack = null;
        for(PartType part : engines.keySet())
            if(part.engineSpeed > bestEngineSpeed && engines.get(part).stackSize >= type.numEngines())
            {
                bestEngineSpeed = part.engineSpeed;
                bestEngineStack = engines.get(part);
            }
        if(bestEngineStack == null)
        {
            player.inventory.copyInventory(temporaryInventory);
            return;
        }
        int numEnginesAcquired = 0;
        for(int n = 0; n < player.inventory.getSizeInventory(); n++)
        {
            ItemStack stackInSlot = player.inventory.getStackInSlot(n);
            if(stackInSlot != null && stackInSlot.getItem() == bestEngineStack.getItem())
            {
                int amountFound = Math.min(stackInSlot.stackSize, type.numEngines() - numEnginesAcquired);
                stackInSlot.stackSize -= amountFound;
                if(stackInSlot.stackSize <= 0)
                    stackInSlot = null;
                player.inventory.setInventorySlotContents(n, stackInSlot);
                numEnginesAcquired += amountFound;
                if(numEnginesAcquired == type.numEngines())
                    break;
            }
        }
        ItemStack driveableStack = new ItemStack(type.item);
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("Engine", ((ItemPart)bestEngineStack.getItem()).type.shortName);
        tags.setString("Type", type.shortName);
        for(EnumDriveablePart part : EnumDriveablePart.values())
        {
            tags.setInteger(part.getShortName() + "_Health", type.health.get(part) == null ? 0 : type.health.get(part).health);
            tags.setBoolean(part.getShortName() + "_Fire", false);
        }
        driveableStack.stackTagCompound = tags;
        if(!player.inventory.addItemStackToInventory(driveableStack))
            player.dropPlayerItemWithRandomChoice(driveableStack, false);
    }

    public void repairDriveable(EntityPlayer driver, EntityDriveable driving, DriveablePart part)
    {
        for(EnumDriveablePart parent : part.type.getParents())
            if(!driving.isPartIntact(parent))
                return;
        InventoryPlayer temporaryInventory = new InventoryPlayer(null);
        temporaryInventory.copyInventory(driver.inventory);
        boolean canRepair = true;
        ArrayList<ItemStack> stacksNeeded = driving.getDriveableType().getItemsRequired(part, driving.getDriveableData().engine);
        for(ItemStack stackNeeded : stacksNeeded)
        {
            int totalAmountFound = 0;
            for(int m = 0; m < temporaryInventory.getSizeInventory(); m++)
            {
                ItemStack stackInSlot = temporaryInventory.getStackInSlot(m);
                if(stackInSlot != null && stackInSlot.getItem() == stackNeeded.getItem() && stackInSlot.getItemDamage() == stackNeeded.getItemDamage())
                {
                    int amountFound = Math.min(stackInSlot.stackSize, stackNeeded.stackSize - totalAmountFound);
                    stackInSlot.stackSize -= amountFound;
                    if(stackInSlot.stackSize <= 0)
                        stackInSlot = null;
                    temporaryInventory.setInventorySlotContents(m, stackInSlot);
                    totalAmountFound += amountFound;
                    if(totalAmountFound == stackNeeded.stackSize)
                        break;
                }
            }
            if(totalAmountFound < stackNeeded.stackSize)
                canRepair = false;
        }
        if(canRepair)
        {
            driver.inventory.copyInventory(temporaryInventory);
            part.health = Math.max(1, part.maxHealth / 10);
            part.onFire = false;
            part.dead = false;
            driving.checkParts();
        }
    }
}
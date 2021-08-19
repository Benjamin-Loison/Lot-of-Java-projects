package fr.watchdogs.benjaminloison.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.watchdogs.benjaminloison.api.Config;
import fr.watchdogs.benjaminloison.api.ContentLoader;
import fr.watchdogs.benjaminloison.api.DrinkRegistry;
import fr.watchdogs.benjaminloison.api.FileAPI;
import fr.watchdogs.benjaminloison.api.ItemLoader;
import fr.watchdogs.benjaminloison.blocks.BlockFlansWorkbench;
import fr.watchdogs.benjaminloison.common.guns.AAGunType;
import fr.watchdogs.benjaminloison.common.guns.AttachmentType;
import fr.watchdogs.benjaminloison.common.guns.BulletType;
import fr.watchdogs.benjaminloison.common.guns.EntityAAGun;
import fr.watchdogs.benjaminloison.common.guns.EntityBullet;
import fr.watchdogs.benjaminloison.common.guns.EntityGrenade;
import fr.watchdogs.benjaminloison.common.guns.EntityMG;
import fr.watchdogs.benjaminloison.common.guns.EntityParachute;
import fr.watchdogs.benjaminloison.common.guns.GrenadeType;
import fr.watchdogs.benjaminloison.common.guns.GunType;
import fr.watchdogs.benjaminloison.common.guns.ItemAAGun;
import fr.watchdogs.benjaminloison.common.guns.ItemAttachment;
import fr.watchdogs.benjaminloison.common.guns.ItemBullet;
import fr.watchdogs.benjaminloison.common.guns.ItemGrenade;
import fr.watchdogs.benjaminloison.common.guns.ItemGun;
import fr.watchdogs.benjaminloison.common.guns.ItemTool;
import fr.watchdogs.benjaminloison.common.guns.ToolType;
import fr.watchdogs.benjaminloison.common.teams.ArmourType;
import fr.watchdogs.benjaminloison.common.teams.ItemTeamArmour;
import fr.watchdogs.benjaminloison.common.teams.PlayerHandler;
import fr.watchdogs.benjaminloison.driveables.EntityMecha;
import fr.watchdogs.benjaminloison.driveables.EntityPlane;
import fr.watchdogs.benjaminloison.driveables.EntitySeat;
import fr.watchdogs.benjaminloison.driveables.EntityVehicle;
import fr.watchdogs.benjaminloison.driveables.EntityWheel;
import fr.watchdogs.benjaminloison.driveables.EnumType;
import fr.watchdogs.benjaminloison.driveables.InfoType;
import fr.watchdogs.benjaminloison.driveables.ItemMecha;
import fr.watchdogs.benjaminloison.driveables.ItemMechaAddon;
import fr.watchdogs.benjaminloison.driveables.ItemPart;
import fr.watchdogs.benjaminloison.driveables.ItemPlane;
import fr.watchdogs.benjaminloison.driveables.ItemVehicle;
import fr.watchdogs.benjaminloison.driveables.MechaItemType;
import fr.watchdogs.benjaminloison.driveables.MechaType;
import fr.watchdogs.benjaminloison.driveables.PartType;
import fr.watchdogs.benjaminloison.driveables.PlaneType;
import fr.watchdogs.benjaminloison.driveables.TypeFile;
import fr.watchdogs.benjaminloison.driveables.VehicleType;
import fr.watchdogs.benjaminloison.events.CommonTickHandler;
import fr.watchdogs.benjaminloison.events.EventSystem;
import fr.watchdogs.benjaminloison.packets.NetworkHandler;
import fr.watchdogs.benjaminloison.packets.PacketHandler;
import fr.watchdogs.benjaminloison.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

@Mod(modid = WatchDogs.MODID, name = WatchDogs.NAME, version = WatchDogs.VERSION)
public class WatchDogs
{
    public static int ticker;
    public static long lastTime;
    public static File flanDir;
    public static final float soundRange = 50, driveableUpdateRange = 200;
    public static final int numPlayerSnapshots = 20;
    public static float armourSpawnRate = 0.25F;
    public static final PacketHandler packetHandler = new PacketHandler();
    public static final PlayerHandler playerHandler = new PlayerHandler();
    public static final CommonTickHandler tickHandler = new CommonTickHandler();
    public static ArrayList<ItemBullet> bulletItems = new ArrayList<ItemBullet>();
    public static ArrayList<ItemGun> gunItems = new ArrayList<ItemGun>();
    public static ArrayList<ItemAttachment> attachmentItems = new ArrayList<ItemAttachment>();
    public static ArrayList<ItemPart> partItems = new ArrayList<ItemPart>();
    public static ArrayList<ItemPlane> planeItems = new ArrayList<ItemPlane>();
    public static ArrayList<ItemVehicle> vehicleItems = new ArrayList<ItemVehicle>();
    public static ArrayList<ItemMechaAddon> mechaToolItems = new ArrayList<ItemMechaAddon>();
    public static ArrayList<ItemMecha> mechaItems = new ArrayList<ItemMecha>();
    public static ArrayList<ItemAAGun> aaGunItems = new ArrayList<ItemAAGun>();
    public static ArrayList<ItemGrenade> grenadeItems = new ArrayList<ItemGrenade>();
    public static ArrayList<ItemTool> toolItems = new ArrayList<ItemTool>();
    public static ArrayList<ItemTeamArmour> armourItems = new ArrayList<ItemTeamArmour>();
    public static SimpleNetworkWrapper network;
    public static EventSystem eventHook = new EventSystem();
    public static Config config = new Config();
    public static final String NAME = "WatchDogs", MODID = "watchdogs", VERSION = "Hacked";
    @Instance(MODID)
    public static WatchDogs instance;
    @SidedProxy(serverSide = "fr.watchdogs.benjaminloison.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static Item explosiveVest, lockPick, creditCard, map, backpack, dynamite, phone, gps, gangGPS, altisCraft, hammer;
    public static Block bank, invisible, basket, pedestrian, stool, fireplace, emptyFireplace, shutter, fan, frenchFlag, atm, vATM, stop, noEntry, bin, vFloorLamp, floorLamp, fence, cocoBlock;
    public static ToolMaterial tools = EnumHelper.addToolMaterial("tools", 2, 1000, 6, 0, 14);
    public static ArmorMaterial vest = EnumHelper.addArmorMaterial("vest", 1, new int[] {0, 2}, 1), sunGlassesArmor = EnumHelper.addArmorMaterial("sun_glasses_armor", 1, new int[] {3}, 1), glassesArmor = EnumHelper.addArmorMaterial("glasses_armor", 1, new int[] {2}, 1), adjointArmor = EnumHelper.addArmorMaterial("adjoint_armor", 1, new int[] {1}, 1),
            brigadierArmor = EnumHelper.addArmorMaterial("brigadier_armor", 1, new int[] {1, 1, 1, 1}, 1), sergentArmor = EnumHelper.addArmorMaterial("sergent_armor", 1, new int[] {1, 2, 2, 1}, 1), adjudantArmor = EnumHelper.addArmorMaterial("adjudant_armor", 1, new int[] {2, 2, 2, 2}, 1), majorArmor = EnumHelper.addArmorMaterial("major_armor", 1, new int[] {2, 3, 3, 2}, 1),
            bacArmor = EnumHelper.addArmorMaterial("bac_armor", 1, new int[] {2, 3, 3, 2}, 1), aspirantArmor = EnumHelper.addArmorMaterial("aspirant_armor", 1, new int[] {3, 3, 3, 3}, 1), lieutenantArmor = EnumHelper.addArmorMaterial("lieutenant_armor", 1, new int[] {3, 4, 4, 3}, 1), capitaineArmor = EnumHelper.addArmorMaterial("capitaine_armor", 1, new int[] {4, 4, 4, 4}, 1),
            commandantArmor = EnumHelper.addArmorMaterial("commandant_armor", 1, new int[] {4, 5, 5, 4}, 1), colonelArmor = EnumHelper.addArmorMaterial("armorColonel", 1, new int[] {5, 5, 5, 5}, 1), generalArmor = EnumHelper.addArmorMaterial("general_armor", 1, new int[] {5, 5, 5, 5}, 1), outfit0 = EnumHelper.addArmorMaterial("outfit0", 1, new int[] {1, 2, 2, 1}, 1),
            rebelArmor = EnumHelper.addArmorMaterial("rebel_armor", 1, new int[] {2, 3, 3, 2}, 1), gang0Armor = EnumHelper.addArmorMaterial("gang_0_armor", 1, new int[] {2, 3, 3, 2}, 1), gang1armor = EnumHelper.addArmorMaterial("gang_1_armor", 1, new int[] {2, 3, 3, 2}, 1), gang2armor = EnumHelper.addArmorMaterial("gang_2_armor", 1, new int[] {2, 3, 3, 2}, 1),
            gang3armor = EnumHelper.addArmorMaterial("gang_3_armor", 1, new int[] {2, 6, 3, 2}, 1), rescueArmor = EnumHelper.addArmorMaterial("rescue_armor", 1, new int[] {1, 2, 2, 1}, 1);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // soft antitheft check (useless because code is now on GitHub)
        //if(!new File("").getAbsolutePath().contains(NAME))
        //    Throwables.propagate(new Exception("You don't have to run this !"));
        print(FileAPI.config("initializing.pre"));
        instance = this;
        FMLCommonHandler.instance().bus().register(eventHook);
        MinecraftForge.EVENT_BUS.register(eventHook);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, eventHook);
        loadMain();
        flanDir = new File(event.getModConfigurationDirectory().getParentFile(), "/mods/" + NAME + "/");
        if(!flanDir.exists())
        {
            flanDir.mkdirs();
            flanDir.mkdir();
        }
        GameRegistry.registerBlock(new BlockFlansWorkbench(), "flans_workbench");
        readContentPacks(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        packetHandler.initialise();
        for(InfoType type : InfoType.infoTypes)
            type.addRecipe();
        EntityRegistry.registerGlobalEntityID(EntityPlane.class, "Plane", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityPlane.class, "Plane", 90, this, 250, 3, false);
        EntityRegistry.registerGlobalEntityID(EntityVehicle.class, "Vehicle", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityVehicle.class, "Vehicle", 95, this, 250, 10, false);
        EntityRegistry.registerGlobalEntityID(EntitySeat.class, "Seat", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntitySeat.class, "Seat", 99, this, 250, 20, false);
        EntityRegistry.registerGlobalEntityID(EntityWheel.class, "Wheel", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityWheel.class, "Wheel", 103, this, 250, 20, false);
        EntityRegistry.registerGlobalEntityID(EntityParachute.class, "Parachute", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityParachute.class, "Parachute", 101, this, 40, 20, false);
        EntityRegistry.registerGlobalEntityID(EntityMecha.class, "Mecha", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityMecha.class, "Mecha", 102, this, 250, 20, false);
        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 96, this, 40, 100, false);
        EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "Grenade", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 100, this, 40, 100, true);
        EntityRegistry.registerGlobalEntityID(EntityMG.class, "MG", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityMG.class, "MG", 91, this, 40, 5, true);
        EntityRegistry.registerGlobalEntityID(EntityAAGun.class, "AAGun", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityAAGun.class, "AAGun", 92, this, 40, 500, false);
        FMLCommonHandler.instance().bus().register(instance);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        new DrinkRegistry();
        print(FileAPI.config("initialized.post"));
    }

    public static String getMinecraftDir()
    {
        try
        {
            Field mcDataDir = Loader.class.getDeclaredField("minecraftDir");
            if(mcDataDir != null)
            {
                mcDataDir.setAccessible(true);
                return ((File)mcDataDir.get(null)).getAbsolutePath();
            }
            throw new Exception();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void loadMain()
    {
        new NetworkHandler();
        new ItemLoader();
        new ContentLoader();
    }

    @SubscribeEvent
    public void playerDrops(PlayerDropsEvent event)
    {
        for(int i = event.drops.size() - 1; i >= 0; i--)
        {
            InfoType type = InfoType.getType(event.drops.get(i).getEntityItem());
            if(type != null && !type.canDrop)
                event.drops.remove(i);
        }
    }

    @SubscribeEvent
    public void playerDrops(ItemTossEvent event)
    {
        InfoType type = InfoType.getType(event.entityItem.getEntityItem());
        if(type != null && !type.canDrop)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onLivingSpecialSpawn(LivingSpawnEvent.CheckSpawn event)
    {
        if(event.world.rand.nextDouble() < armourSpawnRate && event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton)
            if(event.world.rand.nextBoolean() && ArmourType.armours.size() > 0)
            {
                ArmourType armour = ArmourType.armours.get(event.world.rand.nextInt(ArmourType.armours.size()));
                if(armour != null && armour.type != 2)
                    event.entityLiving.setCurrentItemOrArmor(armour.type + 1, new ItemStack(armour.item));
            }
    }

    private void getTypeFiles(List<File> contentPacks)
    {
        for(File contentPack : contentPacks)
            if(contentPack.isDirectory())
                for(EnumType typeToCheckFor : EnumType.values())
                {
                    File typesDir = new File(contentPack, "/" + typeToCheckFor.folderName + "/");
                    if(!typesDir.exists())
                        continue;
                    for(File file : typesDir.listFiles())
                    {
                        try
                        {
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String[] splitName = file.getName().split("/");
                            TypeFile typeFile = new TypeFile(typeToCheckFor, splitName[splitName.length - 1].split("\\.")[0]);
                            for(;;)
                            {
                                String line = null;
                                try
                                {
                                    line = reader.readLine();
                                }
                                catch(Exception e)
                                {
                                    break;
                                }
                                if(line == null)
                                    break;
                                typeFile.lines.add(line);
                            }
                            reader.close();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            else
                try
                {
                    ZipInputStream zipStream = new ZipInputStream(new FileInputStream(contentPack));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream));
                    ZipEntry zipEntry = zipStream.getNextEntry();
                    do
                    {
                        zipEntry = zipStream.getNextEntry();
                        if(zipEntry == null)
                            continue;
                        TypeFile typeFile = null;
                        for(EnumType type : EnumType.values())
                            if(zipEntry.getName().startsWith(type.folderName + "/") && zipEntry.getName().split(type.folderName + "/").length > 1 && zipEntry.getName().split(type.folderName + "/")[1].length() > 0)
                            {
                                String[] splitName = zipEntry.getName().split("/");
                                typeFile = new TypeFile(type, splitName[splitName.length - 1].split("\\.")[0]);
                            }
                        if(typeFile == null)
                            continue;
                        for(;;)
                        {
                            String line = null;
                            try
                            {
                                line = reader.readLine();
                            }
                            catch(Exception e)
                            {
                                break;
                            }
                            if(line == null)
                                break;
                            typeFile.lines.add(line);
                        }
                    }
                    while(zipEntry != null);
                    reader.close();
                    zipStream.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
    }

    private void readContentPacks(FMLPreInitializationEvent event)
    {
        Method method = null;
        try
        {
            method = (java.net.URLClassLoader.class).getDeclaredMethod("addURL", java.net.URL.class);
            method.setAccessible(true);
        }
        catch(Exception e)
        {
            print("Failed to get class loader. All content loading will now fail.");
            e.printStackTrace();
        }
        List<File> contentPacks = proxy.getContentList(method, (net.minecraft.server.MinecraftServer.class).getClassLoader());
        getTypeFiles(contentPacks);
        for(EnumType type : EnumType.values())
        {
            Class<? extends InfoType> typeClass = type.getTypeClass();
            for(TypeFile typeFile : TypeFile.files.get(type))
            {
                try
                {
                    InfoType infoType = (typeClass.getConstructor(TypeFile.class).newInstance(typeFile));
                    infoType.read(typeFile);
                    switch(type)
                    {
                        case bullet:
                            bulletItems.add((ItemBullet)new ItemBullet((BulletType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case attachment:
                            attachmentItems.add((ItemAttachment)new ItemAttachment((AttachmentType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case gun:
                            gunItems.add((ItemGun)new ItemGun((GunType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case grenade:
                            grenadeItems.add((ItemGrenade)new ItemGrenade((GrenadeType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case part:
                            partItems.add((ItemPart)new ItemPart((PartType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case plane:
                            planeItems.add((ItemPlane)new ItemPlane((PlaneType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case vehicle:
                            vehicleItems.add((ItemVehicle)new ItemVehicle((VehicleType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case aa:
                            aaGunItems.add((ItemAAGun)new ItemAAGun((AAGunType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case mechaItem:
                            mechaToolItems.add((ItemMechaAddon)new ItemMechaAddon((MechaItemType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case mecha:
                            mechaItems.add((ItemMecha)new ItemMecha((MechaType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case tool:
                            toolItems.add((ItemTool)new ItemTool((ToolType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        case armour:
                            armourItems.add((ItemTeamArmour)new ItemTeamArmour((ArmourType)infoType).setUnlocalizedName(infoType.shortName));
                            break;
                        default:
                            print("Unrecognised type for " + infoType.shortName);
                            break;
                    }
                }
                catch(Exception e)
                {
                    print("Failed to add " + type.name() + " : " + typeFile.name);
                    e.printStackTrace();
                }
            }
        }
    }

    public static PacketHandler getPacketHandler()
    {
        return instance.packetHandler;
    }

    public static void print(Object object)
    {
        System.out.println("[" + NAME + "] " + object);
    }
}

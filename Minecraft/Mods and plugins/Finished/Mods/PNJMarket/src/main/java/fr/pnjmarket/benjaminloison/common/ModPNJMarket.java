package fr.pnjmarket.benjaminloison.common;

import java.io.File;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "pnjmarket", name = "PNJMarket", version = "1.0.0")
public class ModPNJMarket
{
    public static final String MODID = "pnjmarket";
    @Instance(MODID)
    public static ModPNJMarket instance;
    @SidedProxy(clientSide = "fr.pnjmarket.benjaminloison.common.ClientProxy", serverSide = "fr.pnjmarket.benjaminloison.common.CommonProxy")
    public static CommonProxy proxy;
    public static Item Marteau;
    public static CreativeTabs PNJTab = new CreativeTabs("PNJMarket")
    {
        public Item getTabIconItem()
        {
            return ModPNJMarket.Marteau;
        }
    };

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("Pré-initialisation !");
        // minecraft_189016 or Forevercraft
        // soft antitheft check (useless because code is now on GitHub)
        //if(!new File("").getAbsolutePath().contains(new String(new byte[] {0x6d, 0x69, 0x6e, 0x65, 0x63, 0x72, 0x61, 0x66, 0x74, 0x5f, 0x31, 0x38, 0x39, 0x30, 0x31, 0x36}, Charsets.UTF_8)) && !new File("").getAbsolutePath().contains(new String(new byte[] {0x46, 0x6f, 0x72, 0x65, 0x76, 0x65, 0x72, 0x63, 0x72, 0x61, 0x66, 0x74}, Charsets.UTF_8)))
        //    Throwables.propagate(new Throwable("Server/Launcher non authorized !"));
        instance = this;
        Marteau = new Marteau().setUnlocalizedName("Marteau").setTextureName("pnjmarket:Marteau").setCreativeTab(PNJTab).setMaxStackSize(1);
        GameRegistry.registerItem(Marteau, "marteau");
        network = NetworkRegistry.INSTANCE.newSimpleChannel("T4Channel");
        network.registerMessage(PacketSyncDW.Handler.class, PacketSyncDW.class, 0, Side.SERVER);
        network.registerMessage(PacketKillNPC.Handler.class, PacketKillNPC.class, 1, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ForgeT4EventHandler());
        EntityRegistry.registerGlobalEntityID(EntityMarketPNJ.class, "marketPNJ", EntityRegistry.findGlobalUniqueEntityId(), 0, 0);
        EntityRegistry.registerModEntity(EntityMarketPNJ.class, "marketPNJ", 420, instance, 40, 1, true);
        FMLCommonHandler.instance().bus().register(instance);
        System.out.println("Initialisation !");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.registerRender();
        System.out.println("Post-initialisation !");
    }
}

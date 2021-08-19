package fr.chequemod.benjaminloison.common;

import java.io.File;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.chequemod.benjaminloison.entity.EntityBanker;
import fr.chequemod.benjaminloison.packets.PacketKillNPC;
import net.minecraft.item.Item;

@Mod(modid = ChequeMod.MODID, version = "InDev", name = ChequeMod.NAME)
public class ChequeMod
{
    public static final String MODID = "chequemod", NAME = "ChequeMod";
    @Instance(MODID)
    public static ChequeMod instance;
    public SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        print("Initializing...");
        //if(!new File("").getAbsolutePath().contains("Arcanite"))
          //  Throwables.propagate(new Exception("Server not authorized !"));
        instance = this;
        GameRegistry.registerItem(new Item().setUnlocalizedName("cheque").setMaxStackSize(1), "cheque");
        GameRegistry.registerItem(new Item().setUnlocalizedName("hammer").setMaxStackSize(1), "hammer");
        network = NetworkRegistry.INSTANCE.newSimpleChannel(NAME);
        network.registerMessage(PacketKillNPC.Handler.class, PacketKillNPC.class, 0, Side.SERVER);
        EntityRegistry.registerGlobalEntityID(EntityBanker.class, "banker", EntityRegistry.findGlobalUniqueEntityId(), 0, 0);
        EntityRegistry.registerModEntity(EntityBanker.class, "banker", 420, instance, 40, 1, true);
        print("Initialized !");
    }

    public static void print(Object object)
    {
        System.out.println("[" + NAME + "] " + object);
    }
}
package fr.chequemod.benjaminloison.common;

import com.google.common.base.Throwables;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.chequemod.benjaminloison.entity.EntityBanker;
import fr.chequemod.benjaminloison.packets.PacketKillNPC;
import fr.chequemod.benjaminloison.proxy.ClientProxy;
import fr.chequemod.benjaminloison.proxy.EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ChequeMod.MODID, version = "InDev", name = ChequeMod.NAME)
public class ChequeMod
{
    public static final String MODID = "chequemod", NAME = "ChequeMod";
    @Instance(MODID)
    public static ChequeMod instance;
    public SimpleNetworkWrapper network;
    @SidedProxy(clientSide = "fr.chequemod.benjaminloison.proxy.ClientProxy")
    public static ClientProxy proxy;
    public static Item cheque, hammer;
    public static EventHandler eventHandler;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        print("Initializing...");
        //if(!Minecraft.getMinecraft().mcDataDir.getAbsolutePath().contains(".Arcanite"))
          //  Throwables.propagate(new Exception(I18n.format("launcher.non.authorized")));
        instance = this;
        cheque = new Cheque();
        GameRegistry.registerItem(cheque, "cheque");
        hammer = new Hammer();
        GameRegistry.registerItem(hammer, "hammer");
        network = NetworkRegistry.INSTANCE.newSimpleChannel(NAME);
        network.registerMessage(PacketKillNPC.Handler.class, PacketKillNPC.class, 0, Side.SERVER);
        EntityRegistry.registerGlobalEntityID(EntityBanker.class, "banker", EntityRegistry.findGlobalUniqueEntityId(), 0, 0);
        EntityRegistry.registerModEntity(EntityBanker.class, "banker", 420, instance, 40, 1, true);
        eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);
        print(I18n.format("initialized"));
    }

    public static void print(Object object)
    {
        System.out.println("[" + NAME + "] " + object);
    }
}
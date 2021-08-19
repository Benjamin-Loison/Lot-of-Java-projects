package fr.benjaminloison.enyriapvp.common;

import fr.benjaminloison.enyriapvp.event.EventHandler;
import fr.benjaminloison.enyriapvp.proxy.ClientProxy;
import fr.benjaminloison.enyriapvp.stuff.Blockz;
import fr.benjaminloison.enyriapvp.stuff.Itemz;
import fr.benjaminloison.enyriapvp.stuff.Recipez;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EnyriaPvP.MODID, name = "EnyriaPvP", version = EnyriaPvP.VERSION)
public class EnyriaPvP
{
    public static final String MODID = "EnyriaPvP", VERSION = "Universal";
    @Mod.Instance(MODID)
    public static EnyriaPvP instance;
    @SidedProxy(clientSide = "fr.benjaminloison.enyriapvp.proxy.ClientProxy", serverSide = "fr.benjaminloison.enyriapvp.proxy.CommonProxy")
    public static ClientProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        print("Begin of pre-initialization !");
        Itemz.registerItems();
        Blockz.registerBlocks();
        Recipez.registerRecipes();
        if(event.getSide().isClient())
        {
            Itemz.registerItemsModels();
            Blockz.registerBlocksModels();
        }
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        print("End of pre-initialization !");
    }

    public void print(Object object)
    {
        System.out.println("[" + MODID + "] " + object);
    }
}
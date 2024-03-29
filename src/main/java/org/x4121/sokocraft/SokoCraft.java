package org.x4121.sokocraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.x4121.sokocraft.client.handler.KeyInputEventHandler;
import org.x4121.sokocraft.handler.ConfigurationHandler;
import org.x4121.sokocraft.init.ModBlocks;
import org.x4121.sokocraft.init.ModItems;
import org.x4121.sokocraft.init.ModTileEntities;
import org.x4121.sokocraft.init.Recipes;
import org.x4121.sokocraft.proxy.Proxy;
import org.x4121.sokocraft.reference.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class SokoCraft {
    @Mod.Instance(Reference.MOD_ID)
    public static SokoCraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static Proxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

        proxy.registerKeyBindings();

        ModTileEntities.init();
        ModItems.init();
        ModBlocks.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        Recipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    // https://www.youtube.com/watch?v=Xxx5N_XjBm0
}

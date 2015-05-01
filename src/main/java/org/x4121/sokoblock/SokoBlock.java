package org.x4121.sokoblock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SokoBlock.MODID, name = SokoBlock.NAME, version = SokoBlock.VERSION)
public class SokoBlock {
    public static final String MODID = "SokoBlock";
    public static final String NAME = "SokoBlock";
    public static final String VERSION = "1.7.10-0.1";

    @Mod.Instance(SokoBlock.MODID)
    public static SokoBlock instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}

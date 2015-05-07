package org.x4121.sokocraft.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;

import java.io.File;

public class ConfigurationHandler {
    private static boolean wrenchRecipeEnabled = true;
    private static boolean unbreakableBlocks = false;
    private static int cratePushRange = 64;
    public static Configuration configuration;

    public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        configuration.load();
        wrenchRecipeEnabled = configuration.getBoolean(Names.Configuration.WRENCH_RECIPE, Names.Configuration.CATEGORY, true, Names.Configuration.WRENCH_RECIPE_DESC);
        unbreakableBlocks = configuration.getBoolean(Names.Configuration.UNBREAKABLE, Names.Configuration.CATEGORY, false, Names.Configuration.UNBREAKABLE_DESC);
        cratePushRange = configuration.getInt(Names.Configuration.CRATE_RANGE, Names.Configuration.CATEGORY, 64, 1, 64, Names.Configuration.CRATE_RANGE_DESC);

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    public static boolean isWrenchRecipeEnabled() {
        return wrenchRecipeEnabled;
    }

    public static boolean areBlocksUnbreakable() {
        return unbreakableBlocks;
    }

    public static int getCratePushRange() {
        return cratePushRange;
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}

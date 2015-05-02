package org.x4121.sokoblock.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.x4121.sokoblock.item.ItemSokoBlock;
import org.x4121.sokoblock.item.ItemSokoWrench;

public class ModItems {
    public static final ItemSokoBlock sokoWrench = new ItemSokoWrench();

    public static void init() {
        GameRegistry.registerItem(sokoWrench, "sokoWrench");
    }
}

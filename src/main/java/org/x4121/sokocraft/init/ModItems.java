package org.x4121.sokocraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.x4121.sokocraft.item.ItemSokoCraft;
import org.x4121.sokocraft.item.ItemSokoWrench;

public class ModItems {
    public static final ItemSokoCraft sokoWrench = new ItemSokoWrench();

    public static void init() {
        GameRegistry.registerItem(sokoWrench, "sokoWrench");
    }
}

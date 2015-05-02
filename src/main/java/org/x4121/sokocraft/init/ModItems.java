package org.x4121.sokocraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.x4121.sokocraft.item.ItemSokoCraft;
import org.x4121.sokocraft.item.ItemSokoWrench;
import org.x4121.sokocraft.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static final ItemSokoCraft wrench = new ItemSokoWrench();

    public static void init() {
        GameRegistry.registerItem(wrench, "wrench");
    }
}

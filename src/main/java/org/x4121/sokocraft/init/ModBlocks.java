package org.x4121.sokocraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.x4121.sokocraft.block.BlockSokoCraft;
import org.x4121.sokocraft.block.BlockSokoCrate;
import org.x4121.sokocraft.block.BlockSokoCrateDetector;
import org.x4121.sokocraft.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockSokoCraft crate = new BlockSokoCrate();
    public static final BlockSokoCraft crateDetector = new BlockSokoCrateDetector();

    public static void init() {
        GameRegistry.registerBlock(crate, "crate");
        GameRegistry.registerBlock(crateDetector, "crateDetector");
    }
}

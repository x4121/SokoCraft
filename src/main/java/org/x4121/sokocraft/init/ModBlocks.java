package org.x4121.sokocraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.x4121.sokocraft.block.*;
import org.x4121.sokocraft.item.ItemBlockCrate;
import org.x4121.sokocraft.item.ItemBlockCrateDetector;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockSokoCraft crate = new BlockCrate();
    public static final BlockSokoCraft crateDetector = new BlockCrateDetector();
    public static final BlockSokoCraft cratePlacer = new BlockCratePlacer();
    public static final BlockSokoCraft crateDispenser = new BlockCrateDispenser();


    public static void init() {
        GameRegistry.registerBlock(crate, ItemBlockCrate.class, Names.Blocks.CRATE);
        GameRegistry.registerBlock(crateDetector, ItemBlockCrateDetector.class, Names.Blocks.CRATE_DETECTOR);
        GameRegistry.registerBlock(cratePlacer, Names.Blocks.CRATE_PLACER);
        GameRegistry.registerBlock(crateDispenser, Names.Blocks.CRATE_DISPENSER);
    }
}

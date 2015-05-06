package org.x4121.sokocraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;
import org.x4121.sokocraft.tileentity.TileEntityCrateDetector;
import org.x4121.sokocraft.tileentity.TileEntityCrateDispenser;
import org.x4121.sokocraft.tileentity.TileEntityCratePlacer;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityCrateDetector.class, Names.Blocks.CRATE_DETECTOR);
        GameRegistry.registerTileEntity(TileEntityCrateDispenser.class, Names.Blocks.CRATE_DISPENSER);
        GameRegistry.registerTileEntity(TileEntityCratePlacer.class, Names.Blocks.CRATE_PLACER);
    }
}
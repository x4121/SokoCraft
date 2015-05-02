package org.x4121.sokocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSokoCrateDetector extends BlockSokoCraft {
    public BlockSokoCrateDetector() {
        super();
        setBlockName("crateDetector");
        setBlockUnbreakable();
        setResistance(6000000.0F);
        setStepSound(Block.soundTypeStone);
    }
}

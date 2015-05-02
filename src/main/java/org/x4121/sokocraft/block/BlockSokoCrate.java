package org.x4121.sokocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSokoCrate extends BlockSokoCraft {
    public BlockSokoCrate() {
        super(Material.wood);
        setBlockName("crate");
        setBlockUnbreakable();
        setResistance(6000000.0F);
        setStepSound(Block.soundTypeWood);
    }
}

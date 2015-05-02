package org.x4121.sokocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialPortal;

public class BlockSokoCraft extends Block {
    public BlockSokoCraft(Material material) {
        super(material);
    }

    public BlockSokoCraft() {
        this(MaterialPortal.rock);
    }
}

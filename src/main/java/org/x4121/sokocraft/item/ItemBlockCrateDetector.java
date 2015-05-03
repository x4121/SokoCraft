package org.x4121.sokocraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockCrateDetector extends ItemBlockWithMetadata {
    public ItemBlockCrateDetector(Block block) {
        super(block, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + stack.getItemDamage();
    }
}

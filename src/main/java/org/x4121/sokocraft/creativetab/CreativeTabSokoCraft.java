package org.x4121.sokocraft.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.x4121.sokocraft.init.ModBlocks;
import org.x4121.sokocraft.reference.Reference;

public class CreativeTabSokoCraft {
    public static final CreativeTabs SOKOCRAFT_TAB = new CreativeTabs(Reference.LC_MOD_ID) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.crate);
        }
    };
}

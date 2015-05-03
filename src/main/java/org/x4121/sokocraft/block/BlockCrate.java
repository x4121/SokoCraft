package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.x4121.sokocraft.reference.Colors;
import org.x4121.sokocraft.reference.Names;

import java.util.List;

public class BlockCrate extends BlockSokoCraft {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockCrate() {
        super();
        setBlockName(Names.Blocks.CRATE);
        setStepSound(Block.soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[Colors.MAX];

        for (Colors color: Colors.values()) {
            icons[color.getCode()] = register.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()) + "_" + color.getLCName());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
        for (Colors color: Colors.values()) {
            subItems.add(new ItemStack(this, 1, color.getCode()));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (Colors.getCodes().contains(meta)) {
            return icons[meta % icons.length];
        } else {
            return icons[0];
        }
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }
}

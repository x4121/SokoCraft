package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialPortal;
import net.minecraft.client.renderer.texture.IIconRegister;
import org.x4121.sokocraft.creativetab.CreativeTabSokoCraft;
import org.x4121.sokocraft.reference.Reference;

public class BlockSokoCraft extends Block {
    public BlockSokoCraft(Material material) {
        super(material);
        setCreativeTab(CreativeTabSokoCraft.SOKOCRAFT_TAB);
    }

    public BlockSokoCraft() {
        this(MaterialPortal.rock);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}

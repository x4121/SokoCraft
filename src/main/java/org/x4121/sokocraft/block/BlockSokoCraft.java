package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.x4121.sokocraft.creativetab.CreativeTabSokoCraft;
import org.x4121.sokocraft.handler.ConfigurationHandler;
import org.x4121.sokocraft.reference.Reference;


public abstract class BlockSokoCraft extends Block {

    protected BlockSokoCraft(Material material) {
        super(material);
        setCreativeTab(CreativeTabSokoCraft.SOKOCRAFT_TAB);
        setResistance(6000000.0F);
        if (ConfigurationHandler.areBlocksUnbreakable()) {
            setBlockUnbreakable();
        } else {
            setHardness(0.5F);
        }
    }

    protected BlockSokoCraft() {
        this(Material.piston);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Reference.LC_MOD_ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
}

package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;
import org.x4121.sokocraft.tileentity.TileEntityCratePlacer;

public class BlockCratePlacer extends BlockContainerSokoCraft {

    public BlockCratePlacer() {
        super();
        setBlockName(Names.Blocks.CRATE_PLACER);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerUncoloredBlockIcons(register);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return super.getUncoloredIcon(world, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return super.getUncoloredIcon(side);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCratePlacer();
    }
}

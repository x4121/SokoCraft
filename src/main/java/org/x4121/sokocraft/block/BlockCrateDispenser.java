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
import org.x4121.sokocraft.tileentity.TileEntityCrateDetector;
import org.x4121.sokocraft.tileentity.TileEntityCrateDispenser;
import org.x4121.sokocraft.util.LogHelper;

public class BlockCrateDispenser extends BlockContainerSokoCraft {
    private boolean hasRedstoneSignal = false;

    public BlockCrateDispenser() {
        super();
        setBlockName(Names.Blocks.CRATE_DISPENSER);
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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!world.isRemote) {
            if (world.isBlockIndirectlyGettingPowered(x, y, z) && !hasRedstoneSignal) {
                hasRedstoneSignal = true;
                LogHelper.info("plop");
            } else if (!world.isBlockIndirectlyGettingPowered(x, y, z)) {
                hasRedstoneSignal = false;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCrateDispenser();
    }
}

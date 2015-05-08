package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.x4121.sokocraft.init.ModBlocks;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;
import org.x4121.sokocraft.tileentity.TileEntityCratePlacer;
import org.x4121.sokocraft.util.LogHelper;

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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!world.isRemote) {
            TileEntityCratePlacer te = (TileEntityCratePlacer) world.getTileEntity(x, y, z);
            boolean hasRedstoneSignal = te.hasRedStoneSignal();
            if (world.isBlockIndirectlyGettingPowered(x, y, z) && !hasRedstoneSignal) {
                te.setRedstoneSignal(true);
                placeBlock(world, x, y, z);
            } else if (!world.isBlockIndirectlyGettingPowered(x, y, z) && hasRedstoneSignal) {
                te.setRedstoneSignal(false);
            }
        }
    }

    private void placeBlock(World world, int x, int y, int z) {
        TileEntityCratePlacer te = (TileEntityCratePlacer) world.getTileEntity(x, y, z);
        ForgeDirection direction = te.getOrientation();
        if (isFacingAir(world, x, y, z, direction)) {
            placeCrate(world, x, y, z, direction);
        }
    }

    private boolean isFacingAir(World world, int x, int y, int z, ForgeDirection direction) {
        switch (direction) {
            case DOWN:
                return world.isAirBlock(x, y - 1, z);
            case UP:
                return world.isAirBlock(x, y + 1, z);
            case NORTH:
                return world.isAirBlock(x, y, z - 1);
            case EAST:
                return world.isAirBlock(x + 1, y, z);
            case SOUTH:
                return world.isAirBlock(x, y, z + 1);
            case WEST:
                return world.isAirBlock(x - 1, y, z);
            default:
                return false;
        }
    }



    private void placeCrate(World world, int x, int y, int z, ForgeDirection direction) {
        switch (direction) {
            case DOWN:
                world.setBlock(x, y - 1, z, ModBlocks.crate);
                world.notifyBlocksOfNeighborChange(x, y - 1, z, ModBlocks.crate);
                break;
            case UP:
                world.setBlock(x, y + 1, z, ModBlocks.crate);
                world.notifyBlocksOfNeighborChange(x, y + 1, z, ModBlocks.crate);
                break;
            case NORTH:
                world.setBlock(x, y, z - 1, ModBlocks.crate);
                world.notifyBlocksOfNeighborChange(x, y, z - 1, ModBlocks.crate);
                break;
            case EAST:
                world.setBlock(x + 1, y, z, ModBlocks.crate);
                world.notifyBlocksOfNeighborChange(x + 1, y, z, ModBlocks.crate);
                break;
            case SOUTH:
                world.setBlock(x, y, z + 1, ModBlocks.crate);
                world.notifyBlocksOfNeighborChange(x, y, z + 1, ModBlocks.crate);
                break;
            case WEST:
                world.setBlock(x - 1, y, z, ModBlocks.crate);
                world.notifyBlocksOfNeighborChange(x - 1, y, z, ModBlocks.crate);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCratePlacer();
    }
}

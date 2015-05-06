package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.x4121.sokocraft.init.ModBlocks;
import org.x4121.sokocraft.reference.Colors;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.tileentity.TileEntityCrateDetector;

import java.util.List;

public class BlockCrateDetector extends BlockContainerSokoCraft {

    public BlockCrateDetector() {
        super();
        setBlockName(Names.Blocks.CRATE_DETECTOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerColoredBlockIcons(register, getUnwrappedUnlocalizedName(getUnlocalizedName()));
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
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return super.getColoredIcon(world, x, y, z, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return super.getColoredIcon(side, meta);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        return ((TileEntityCrateDetector)world.getTileEntity(x, y, z)).isPowered() ? 25 : 0;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
        if (!world.isRemote && neighbor.isAssociatedBlock(ModBlocks.crate)) {
            TileEntityCrateDetector te = (TileEntityCrateDetector)world.getTileEntity(x, y, z);
            ForgeDirection orientation = te.getOrientation();
            if (isFacingCrate(world, x, y, z, orientation, world.getBlockMetadata(x, y, z))) {
                te.setPowered(true);
                world.notifyBlocksOfNeighborChange(x, y, z, this);
            } else {
                te.setPowered(false);
                world.notifyBlocksOfNeighborChange(x, y, z, this);
            }
        }
    }

    private boolean isFacingCrate(World world, int x, int y, int z, ForgeDirection direction, int meta) {
        Colors color = Colors.getColor(meta);
        if (color == null) return false;
        switch (direction) {
            case DOWN:
                return (isCrate(world, x, y - 1, z) && hasSameMeta(world, x, y -1, z, meta));
            case UP:
                return (isCrate(world, x, y + 1, z) && hasSameMeta(world, x, y + 1, z, meta));
            case NORTH:
                return (isCrate(world, x, y, z - 1) && hasSameMeta(world, x, y, z - 1, meta));
            case EAST:
                return (isCrate(world, x + 1, y, z) && hasSameMeta(world, x + 1, y, z, meta));
            case SOUTH:
                return (isCrate(world, x, y, z + 1) && hasSameMeta(world, x, y, z + 1, meta));
            case WEST:
                return (isCrate(world, x - 1, y, z) && hasSameMeta(world, x - 1, y, z, meta));
            default:
                return false;
        }
    }

    private boolean isCrate(World world, int x, int y, int z) {
        return world.getBlock(x, y, z).isAssociatedBlock(ModBlocks.crate);
    }

    private boolean hasSameMeta(World world, int x, int y, int z, int meta) {
        int crateMeta = world.getBlockMetadata(x, y, z);
        return (meta == 0 || crateMeta == 0 || meta == crateMeta);
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCrateDetector();
    }
}

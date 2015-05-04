package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;
import org.x4121.sokocraft.util.LogHelper;

public class BlockCrateDispenser extends BlockSokoCraft {
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    private boolean hasRedstoneSignal = false;

    public BlockCrateDispenser() {
        super();
        setBlockName(Names.Blocks.CRATE_DISPENSER);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        sideIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.SIDE);
        topIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.TOP);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) {
            return topIcon;
        } else if (side == 2) {
            return blockIcon;
        } else {
            return sideIcon;
        }
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
}

package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.x4121.sokocraft.reference.Colors;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;
import org.x4121.sokocraft.tileentity.TileEntityCrateDetector;
import org.x4121.sokocraft.tileentity.TileEntitySokoCraft;

import java.util.List;

public abstract class BlockContainerSokoCraft extends BlockSokoCraft implements ITileEntityProvider {
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    protected BlockContainerSokoCraft(Material material)
    {
        super(material);
        this.isBlockContainer = true;
        setStepSound(Block.soundTypePiston);
    }

    protected BlockContainerSokoCraft() {
        this(Material.piston);
    }

    @SideOnly(Side.CLIENT)
    public void registerColoredBlockIcons(IIconRegister register, String name) {
        icons = new IIcon[Colors.MAX];
        for (Colors color: Colors.values()) {
            icons[color.getCode()] = register.registerIcon(name + "_" + color.getLCName());
        }
        sideIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.SIDE);
        topIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.TOP);
    }

    @SideOnly(Side.CLIENT)
    public void registerUncoloredBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        sideIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.SIDE);
        topIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.TOP);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getColoredIcon(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.getBlockMetadata(x, y, z);
        TileEntitySokoCraft te = (TileEntitySokoCraft) world.getTileEntity(x, y, z);
        int front = 3;
        int top = 1;
        int bottom = 0;
        if (te != null) {
            switch (te.getOrientation()) {
                case UP:
                    front = 1;
                    top = 2;
                    bottom = 3;
                    break;
                case DOWN:
                    front = 0;
                    top = 3;
                    bottom = 2;
                    break;
                case NORTH:
                    front = 2;
                    break;
                case EAST:
                    front = 5;
                    break;
                case SOUTH:
                    front = 3;
                    break;
                case WEST:
                    front = 4;
            }
        }
        if (side == bottom || side == top) {
            return topIcon;
        } else if (side == front && Colors.getCodes().contains(meta)) {
            return icons[meta % icons.length];
        } else {
            return sideIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getUncoloredIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntitySokoCraft te = (TileEntitySokoCraft) world.getTileEntity(x, y, z);
        int front = 3;
        int top = 1;
        int bottom = 0;
        if (te != null) {
            switch (te.getOrientation()) {
                case UP:
                    front = 1;
                    top = 2;
                    bottom = 3;
                    break;
                case DOWN:
                    front = 0;
                    top = 3;
                    bottom = 2;
                    break;
                case NORTH:
                    front = 2;
                    break;
                case EAST:
                    front = 5;
                    break;
                case SOUTH:
                    front = 3;
                    break;
                case WEST:
                    front = 4;
            }
        }
        if (side == bottom || side == top) {
            return topIcon;
        } else if (side == front) {
            return blockIcon;
        } else {
            return sideIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getColoredIcon(int side, int meta)
    {
        if (side == 0 || side == 1) {
            return topIcon;
        } else if (side == 3 && Colors.getCodes().contains(meta)) {
            return icons[meta % icons.length];
        } else {
            return sideIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getUncoloredIcon(int side)
    {
        if (side == 0 || side == 1) {
            return topIcon;
        } else if (side == 3) {
            return blockIcon;
        } else {
            return sideIcon;
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }


    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int unknown)
    {
        super.breakBlock(world, x, y, z, block, unknown);
        world.removeTileEntity(x, y, z);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int event, int arg)
    {
        super.onBlockEventReceived(world, x, y, z, event, arg);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(event, arg);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileEntitySokoCraft) {
            TileEntitySokoCraft tesc = (TileEntitySokoCraft) te;
            tesc.setOrientation(determineOrientation(x, y, z, entity));
            world.markBlockForUpdate(x, y, z);
        }
    }

    public static int determineOrientation(int x, int y, int z, EntityLivingBase entity)
    {
        if (MathHelper.abs((float) entity.posX - (float) x) < 2.0F && MathHelper.abs((float)entity.posZ - (float)z) < 2.0F)
        {
            double d0 = entity.posY + 1.82D - (double)entity.yOffset;

            if (d0 - (double)y > 2.0D)
            {
                return 1;
            }

            if ((double)y - d0 > 0.0D)
            {
                return 0;
            }
        }

        int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }
}

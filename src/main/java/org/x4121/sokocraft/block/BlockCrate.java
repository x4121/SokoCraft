package org.x4121.sokocraft.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.x4121.sokocraft.handler.ConfigurationHandler;
import org.x4121.sokocraft.init.ModBlocks;
import org.x4121.sokocraft.reference.Colors;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.util.LogHelper;

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

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        world.notifyBlocksOfNeighborChange(x, y, z, this);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float dX, float dY, float dZ)
    {
        if (world.isRemote || player.getCurrentEquippedItem() != null) return false;
        ForgeDirection moveDir = ForgeDirection.getOrientation(side).getOpposite();
        if (canMove(world, x, y, z, moveDir)) {
            move(world, x, y, z, moveDir);
            return true;
        } else return false;
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isRemote || player.getCurrentEquippedItem() != null) return;
        double dX = x - player.posX + 0.5;
        double dY = y - player.posY + 0.5;
        double dZ = z - player.posZ + 0.5;
        ForgeDirection moveDir = getFarthestDirection(dX, dY, dZ).getOpposite();
        int remainingMoves = ConfigurationHandler.getCratePushRange();
        while (remainingMoves > 0 && canMove(world, x, y, z, moveDir)) {
            move(world, x, y, z, moveDir);
            remainingMoves--;
            x = nextX(x, moveDir);
            y = nextY(y, moveDir);
            z = nextZ(z, moveDir);
        }
    }

    private boolean canMove(World world, int x, int y, int z, ForgeDirection direction) {
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

    private void move(World world, int x, int y, int z, ForgeDirection direction) {
        int oldMeta = world.getBlockMetadata(x, y, z);
        world.setBlockToAir(x, y, z);
        switch (direction) {
            case DOWN:
                world.setBlock(x, y - 1, z, this);
                world.setBlockMetadataWithNotify(x, y - 1, z, oldMeta, 3);
                //world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
                break;
            case UP:
                world.setBlock(x, y + 1, z, this);
                //world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
                world.setBlockMetadataWithNotify(x, y + 1, z, oldMeta, 3);
                break;
            case NORTH:
                world.setBlock(x, y, z - 1, this);
                //world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
                world.setBlockMetadataWithNotify(x, y, z - 1, oldMeta, 3);
                break;
            case EAST:
                world.setBlock(x + 1, y, z, this);
                //world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
                world.setBlockMetadataWithNotify(x + 1, y, z, oldMeta, 3);
                break;
            case SOUTH:
                world.setBlock(x, y, z + 1, this);
                //world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
                world.setBlockMetadataWithNotify(x, y, z + 1, oldMeta, 3);
                break;
            case WEST:
                world.setBlock(x - 1, y, z, this);
                //world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
                world.setBlockMetadataWithNotify(x - 1, y, z, oldMeta, 3);
        }
    }

    private ForgeDirection getFarthestDirection(double dX, double dY, double dZ) {
        double absX = Math.abs(dX);
        double absY = Math.abs(dY);
        double absZ = Math.abs(dZ);
        double max = Math.max(absX, Math.max(absY, absZ));

        if (max == absX) {
            if (dX > 0) {
                return ForgeDirection.WEST;
            } else {
                return ForgeDirection.EAST;
            }
        } else if (max == absY) {
            if (dY > 0) {
                return ForgeDirection.DOWN;
            } else {
                return ForgeDirection.UP;
            }
        } else {
            if (dZ > 0) {
                return ForgeDirection.NORTH;
            } else {
                return ForgeDirection.SOUTH;
            }
        }
    }

    private int nextX(int x, ForgeDirection moveDir) {
        if (moveDir == ForgeDirection.EAST) {
            return x + 1;
        } else if (moveDir == ForgeDirection.WEST) {
            return x - 1;
        } else {
            return x;
        }
    }

    private int nextY(int y, ForgeDirection moveDir) {
        if (moveDir == ForgeDirection.UP) {
            return y + 1;
        } else if (moveDir == ForgeDirection.DOWN) {
            return y -1;
        } else {
            return y;
        }
    }

    private int nextZ(int z, ForgeDirection moveDir) {
        if (moveDir == ForgeDirection.SOUTH) {
            return z + 1;
        } else if (moveDir == ForgeDirection.NORTH) {
            return z - 1;
        } else {
            return z;
        }
    }
}

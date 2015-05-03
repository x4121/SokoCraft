package org.x4121.sokocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerSokoCraft extends BlockSokoCraft implements ITileEntityProvider {

    protected BlockContainerSokoCraft(Material material)
    {
        super(material);
        this.isBlockContainer = true;
        setStepSound(Block.soundTypePiston);
    }

    protected BlockContainerSokoCraft() {
        this(Material.piston);
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
}

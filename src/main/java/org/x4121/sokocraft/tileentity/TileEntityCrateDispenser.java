package org.x4121.sokocraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import org.x4121.sokocraft.reference.Names;

public class TileEntityCrateDispenser extends TileEntitySokoCraft {
    private boolean redstoneSignal;

    public TileEntityCrateDispenser() {
        redstoneSignal = false;
    }

    public boolean hasRedStoneSignal() {
        return redstoneSignal;
    }

    public void setRedstoneSignal(boolean powered) {
        this.redstoneSignal = powered;
        this.updateEntity();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey(Names.NBT.HAS_REDSTONE)) {
            redstoneSignal = nbtTagCompound.getBoolean(Names.NBT.HAS_REDSTONE);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean(Names.NBT.HAS_REDSTONE, redstoneSignal);
    }
}

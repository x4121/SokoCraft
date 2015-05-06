package org.x4121.sokocraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import org.x4121.sokocraft.reference.Names;

public class TileEntityCrateDetector extends TileEntitySokoCraft {
    private boolean powered;

    public TileEntityCrateDetector() {
        powered = false;
    }

    public boolean isPowered() {
        return powered;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
        this.updateEntity();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        if (nbtTagCompound.hasKey(Names.NBT.POWERED)) {
            powered = nbtTagCompound.getBoolean(Names.NBT.POWERED);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setBoolean(Names.NBT.POWERED, powered);
    }
}

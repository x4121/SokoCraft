package org.x4121.sokocraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.x4121.sokocraft.block.BlockSokoCraft;
import org.x4121.sokocraft.reference.Names;

import java.util.List;

public class ItemWrench extends ItemSokoCraft {
    private enum Mode {
        PUSH("Push"),
        ROTATE("Rotate"),
        WRENCH("Wrench");
        private final String name;
        Mode(String name) {
            this.name = name;
        }
        public String toString() {
            return name;
        }
    }
    public ItemWrench() {
        super();
        setUnlocalizedName(Names.Items.WRENCH);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote && !player.isSneaking()) {
            itemStack.setItemDamage((itemStack.getItemDamage() + 1) % Mode.values().length);
            player.addChatComponentMessage(new ChatComponentText(getModeString(itemStack.getItemDamage())));
        }
        return itemStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        Block block = world.getBlock(x, y, z);
        if (world.isRemote || !(block instanceof BlockSokoCraft)) {
            return false;
        }
        switch (getMode(itemStack.getItemDamage())) {
            case PUSH:
                break;
            case ROTATE:
                break;
            case WRENCH:
                if (player.isSneaking()) {
                    block.breakBlock(world, x, y, z, block, 0);
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean useExtraInfo) {
        info.add(getModeString(itemStack.getItemDamage()));
    }

    private Mode getMode(int dmg) {
        return Mode.values()[dmg];
    }

    private String getModeString(int dmg) {
        return "Mode: " + Mode.values()[dmg];
    }

}

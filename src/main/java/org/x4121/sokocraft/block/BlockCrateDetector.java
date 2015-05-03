package org.x4121.sokocraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.x4121.sokocraft.reference.Colors;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;
import org.x4121.sokocraft.tileentity.TileEntityCrateDetector;

import java.util.List;

public class BlockCrateDetector extends BlockContainerSokoCraft {
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockCrateDetector() {
        super();
        setBlockName(Names.Blocks.CRATE_DETECTOR);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[Colors.MAX];
        for (Colors color: Colors.values()) {
            icons[color.getCode()] = register.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()) + "_" + color.getLCName());
        }
        sideIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.SIDE);
        topIcon = register.registerIcon(Reference.LC_MOD_ID + ":" + Names.Blocks.ABSTRACT_CRATE_INTERACTOR + Names.Helper.TOP);
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
        if (side == 0 || side == 1) {
            return topIcon;
        } else if (side == 2 && Colors.getCodes().contains(meta)) {
            return icons[meta % icons.length];
        } else {
            return sideIcon;
        }
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityCrateDetector();
    }

    public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
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

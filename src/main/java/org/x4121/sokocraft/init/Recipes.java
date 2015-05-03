package org.x4121.sokocraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.x4121.sokocraft.handler.ConfigurationHandler;
import org.x4121.sokocraft.reference.Colors;
import org.x4121.sokocraft.reference.Names;

public class Recipes {
    private static final ItemStack crate_blue = new ItemStack(ModBlocks.crate, 1, Colors.BLUE.getCode());
    private static final ItemStack crate_green = new ItemStack(ModBlocks.crate, 1, Colors.GREEN.getCode());
    private static final ItemStack crate_red = new ItemStack(ModBlocks.crate, 1, Colors.RED.getCode());

    private static final ItemStack detector_blue = new ItemStack(ModBlocks.crateDetector, 1, Colors.BLUE.getCode());
    private static final ItemStack detector_green = new ItemStack(ModBlocks.crateDetector, 1, Colors.GREEN.getCode());
    private static final ItemStack detector_red = new ItemStack(ModBlocks.crateDetector, 1, Colors.RED.getCode());

    public static void init() {
        registerOres();
        registerRecipes();
    }

    private static void registerOres() {
        OreDictionary.registerOre(Names.Blocks.CRATE, new ItemStack(ModBlocks.crate));
        OreDictionary.registerOre(Names.Blocks.CRATE, crate_blue);
        OreDictionary.registerOre(Names.Blocks.CRATE, crate_green);
        OreDictionary.registerOre(Names.Blocks.CRATE, crate_red);

        OreDictionary.registerOre(Names.Blocks.CRATE_DETECTOR, new ItemStack(ModBlocks.crateDetector));
        OreDictionary.registerOre(Names.Blocks.CRATE_DETECTOR, detector_blue);
        OreDictionary.registerOre(Names.Blocks.CRATE_DETECTOR, detector_green);
        OreDictionary.registerOre(Names.Blocks.CRATE_DETECTOR, detector_red);
    }

    private static void registerRecipes() {
        /* Items */
        if (ConfigurationHandler.isWrenchRecipeEnabled()) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wrench),
                    " s ",
                    " cs",
                    "s  ",
                    's', "stickWood",
                    'c', Names.Blocks.CRATE));
        }

        /* Blocks */
        // Crates
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.crate, 8),
                "isi",
                "srs",
                "isi",
                'i', new ItemStack(Blocks.iron_bars),
                's', "slabWood",
                'r', "blockRedstone"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.crate, 8, Colors.BLUE.getCode()),
                "ccc",
                "cdc",
                "ccc",
                'c', Names.Blocks.CRATE,
                'd', "dyeBlue"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.crate, 1, Colors.BLUE.getCode()),
                Names.Blocks.CRATE,
                "dyeBlue"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.crate, 8, Colors.GREEN.getCode()),
                "ccc",
                "cdc",
                "ccc",
                'c', Names.Blocks.CRATE,
                'd', "dyeGreen"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.crate, 1, Colors.GREEN.getCode()),
                Names.Blocks.CRATE,
                "dyeGreen"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.crate, 8, Colors.RED.getCode()),
                "ccc",
                "cdc",
                "ccc",
                'c', Names.Blocks.CRATE,
                'd', "dyeRed"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.crate, 1, Colors.RED.getCode()),
                Names.Blocks.CRATE,
                "dyeRed"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.crate),
                Names.Blocks.CRATE));

        // TileEntities
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.crateDetector),
                new ItemStack(ModBlocks.crate),
                new ItemStack(Blocks.hopper));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.crateDetector, 1, Colors.BLUE.getCode()),
                crate_blue,
                new ItemStack(Blocks.hopper));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.crateDetector, 1, Colors.GREEN.getCode()),
                crate_green,
                new ItemStack(Blocks.hopper));
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.crateDetector, 1, Colors.RED.getCode()),
                crate_red,
                new ItemStack(Blocks.hopper));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.crateDetector),
                Names.Blocks.CRATE_DETECTOR));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.cratePlacer),
                Names.Blocks.CRATE,
                new ItemStack(Blocks.dropper)));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.crateDispenser),
                Names.Blocks.CRATE,
                new ItemStack(Blocks.dispenser)));
    }
}

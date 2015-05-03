package org.x4121.sokocraft.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import org.x4121.sokocraft.handler.ConfigurationHandler;
import org.x4121.sokocraft.reference.Names;
import org.x4121.sokocraft.reference.Reference;

public class Config extends GuiConfig {
    public Config(GuiScreen screen) {
        super(screen,
                new ConfigElement<>(ConfigurationHandler.configuration.getCategory(Names.Configuration.CATEGORY)).getChildElements(),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }
}

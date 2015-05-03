package org.x4121.sokocraft.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import org.x4121.sokocraft.client.settings.Keybindings;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeyBindings() {
        ClientRegistry.registerKeyBinding(Keybindings.configure);
        ClientRegistry.registerKeyBinding(Keybindings.push);
    }
}

package org.x4121.sokocraft.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import org.x4121.sokocraft.client.settings.Keybindings;
import org.x4121.sokocraft.reference.Key;

public class KeyInputEventHandler {

    private static Key getPressedKeyBinding() {
        if (Keybindings.configure.isPressed()) {
            return Key.CONFIGURE;
        } else if (Keybindings.push.isPressed()) {
            return Key.PUSH;
        } else {
            return Key.UNKNOWN;
        }
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {

    }
}

package org.x4121.sokocraft.client.settings;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.x4121.sokocraft.reference.Names;

public class Keybindings {
    public static KeyBinding configure = new KeyBinding(Names.Keys.CONFIGURE, Keyboard.KEY_C, Names.Keys.CATEGORY);
    public static KeyBinding push = new KeyBinding(Names.Keys.PUSH, Keyboard.KEY_P, Names.Keys.CATEGORY);
}

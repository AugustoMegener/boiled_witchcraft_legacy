package org.kitowashere.boiled_witchcraft.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import static org.kitowashere.boiled_witchcraft.BoiledWitchcraft.MODID;

public class KeyBinding {
    public static final String KEY_CATEGORY_GLYPH_CONFIG = "key.category." + MODID + ".glyph_config";

    public static final String KEY_CONFIG_MAGIC_UP = "key." + MODID + ".config_magic.up";
    public static final String KEY_CONFIG_MAGIC_DOWN = "key." + MODID + ".config_magic.down";
    public static final String KEY_CONFIG_WRAP_GLYPH = "key." + MODID + ".wrap_glyph";

    public static final KeyMapping CONFIG_UP_KEY = new KeyMapping(
            KEY_CONFIG_MAGIC_UP, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP, KEY_CATEGORY_GLYPH_CONFIG
    );
    public static final KeyMapping CONFIG_DOWN_KEY = new KeyMapping(
            KEY_CONFIG_MAGIC_DOWN, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_DOWN, KEY_CATEGORY_GLYPH_CONFIG
    );
    public static final KeyMapping WRAP_GLYPH_KEY = new KeyMapping(
            KEY_CONFIG_WRAP_GLYPH, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_APOSTROPHE, KEY_CATEGORY_GLYPH_CONFIG
    );
}

package org.thetomtomson.shadowslavecraft.keyinput.custom;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {

    public static final KeyMapping OPEN_GUI_KEY = new KeyMapping(
            "key.shadowslavecraft.open_gui",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            "key.categories.shadowslavecraft"
    );
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModKeyMappings::onRegisterKeyMappings);
    }

    private static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(OPEN_GUI_KEY);
    }
}

package org.thetomtomson.shadowslavecraft.keyinput;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import org.thetomtomson.shadowslavecraft.keyinput.custom.ModKeyMappings;
import org.thetomtomson.shadowslavecraft.screen.custom.SpellMenu;

import static org.thetomtomson.shadowslavecraft.Shadowslavecraft.MODID;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();

        if (ModKeyMappings.OPEN_GUI_KEY.consumeClick() && mc.screen == null) {
            mc.setScreen(new SpellMenu());
        }
    }


}

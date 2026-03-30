package org.thetomtomson.shadowslavecraft.keyinput;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import org.thetomtomson.shadowslavecraft.data.ModDataAttachments;
import org.thetomtomson.shadowslavecraft.data.player.custom.SpellPlayerData;
import org.thetomtomson.shadowslavecraft.keyinput.custom.ModKeyMappings;
import org.thetomtomson.shadowslavecraft.screen.custom.SpellMenu;

import static org.thetomtomson.shadowslavecraft.Shadowslavecraft.MODID;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();

        // Sprawdzamy czy gracz istnieje i czy kliknięto guzik
        // Wewnątrz KeyInputHandler.java
        if (ModKeyMappings.OPEN_GUI_KEY.consumeClick() && mc.screen == null) {
            // Pobieramy zsynchronizowane dane z Data Attachment
            SpellPlayerData data = mc.player.getData(ModDataAttachments.SPELL_DATA);
            mc.setScreen(new SpellMenu(data)); //
        }
    }

    // Pomocnicza metoda - dostosuj ją do swojego systemu zapisu danych
    private static SpellPlayerData getPlayerData(Player player) {
        // Tutaj powinna znaleźć się logika pobierania danych, o której wspomniałeś w Shadowslavecraft.java
        // Jeśli używasz NeoForge Data Attachments:
        // return player.getData(YourModData.SPELL_DATA_ATTACHMENT);

        // Na ten moment zwracamy nowy obiekt, aby kod się kompilował:
        return new SpellPlayerData();
    }
}
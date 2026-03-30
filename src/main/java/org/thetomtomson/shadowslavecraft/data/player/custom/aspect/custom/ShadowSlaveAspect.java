package org.thetomtomson.shadowslavecraft.data.player.custom.aspect.custom;

import net.minecraft.server.level.ServerPlayer;
import org.thetomtomson.shadowslavecraft.data.player.custom.RankSS;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.Aspect;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.AspectAbility;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.AspectLegacy;
// TODO:Dodać prawdziwą funkcjonalność
public class ShadowSlaveAspect extends Aspect {
    @Override
    protected void init() {
        this.aspectName = "Shadow Slave";
        this.description = "\"You are a miraculous shadow left behind by a dead god. As a divine shadow, you possess plenty of strange and wondrous powers. However, your existence is empty and lonesome; you mourn the passing of your former master and long to find a new one.\"";
        this.rank = RankSS.AH_DIVINE;

        // Definiujemy 7 umiejętności dla każdej rangi
        abilities.put(1,
                new AspectAbility(
                        "Shadow Control",
                        "\"Your shadow is more independent than most. It is an invaluable helper.\"",
                        RankSS.AH_DORMANT) {
            @Override
            public void activate(ServerPlayer player) { /* Obrażenia/Efekt */ }
        });

        // ... dodajesz kolejne dla Awakened, Ascended itd.

        this.legacy = new AspectLegacy("Shadow Dance");
        this.legacy.addStep("Zrób pierwszy krok w tańcu", "Atrybut: Zręczność +1");
    }
}

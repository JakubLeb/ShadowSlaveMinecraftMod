package org.thetomtomson.shadowslavecraft.data.player.custom.aspect;

import net.minecraft.server.level.ServerPlayer;
import org.thetomtomson.shadowslavecraft.data.player.custom.RankSS;

public abstract class AspectAbility {
    private final String name;
    private final String description;
    private final RankSS requiredRank;

    public AspectAbility(String name, String description, RankSS requiredRank) {
        this.name = name;
        this.description = description;
        this.requiredRank = requiredRank;
    }

    // Główna metoda wywoływana przy użyciu skilla
    public abstract void activate(ServerPlayer player);

    // Tu możesz dodać obsługę efektów wizualnych
    public void playVisuals(ServerPlayer player) {
        // Logika pakietów animacji / cząsteczek
    }

    public String getName() { return name; }
    public RankSS getRequiredRank() { return requiredRank; }
}
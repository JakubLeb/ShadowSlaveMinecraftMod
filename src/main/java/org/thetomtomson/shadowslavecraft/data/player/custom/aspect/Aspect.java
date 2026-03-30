package org.thetomtomson.shadowslavecraft.data.player.custom.aspect;

import org.thetomtomson.shadowslavecraft.data.player.custom.Flaw.Flaw;
import org.thetomtomson.shadowslavecraft.data.player.custom.RankSS;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Aspect {
    protected String aspectName;
    protected String description;
    protected RankSS rank;
    protected final Map<Integer, AspectAbility> abilities = new LinkedHashMap<>();
    protected AspectLegacy legacy;
    protected Flaw flaw;


    public Aspect() {
        init();
    }

    // Metoda, w której definiujesz konkretne dane w klasach potomnych
    protected abstract void init();

    public AspectAbility getAbility(RankSS rank) {
        return abilities.get(rank);
    }

    public String getAspectName() { return aspectName; }
    public AspectLegacy getLegacy() { return legacy; }
}
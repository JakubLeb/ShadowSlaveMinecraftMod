package org.thetomtomson.shadowslavecraft.data.player.custom.aspect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
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

    // Indeks aktualnie ukończonego kroku Legacy (0 = żaden)
    protected int legacyProgress = 0;

    public Aspect() {
        init();
    }

    /** Definiuje dane aspektu — implementowana w klasach potomnych. */
    protected abstract void init();

    // ── NBT ───────────────────────────────────────────────────────────────────

    /**
     * Zapisuje dynamiczny stan aspektu (rank, postęp legacy, flaw).
     * Wywoływane przez AspectFactory.toNBT().
     * Klasa potomna może nadpisać tę metodę i wywołać super, by dodać własne pola.
     */
    public void saveExtraToNBT(CompoundTag tag) {
        // Ranga aspektu
        if (rank != null) {
            tag.putString("Rank", rank.name());
        }

        // Postęp Legacy
        tag.putInt("LegacyProgress", legacyProgress);

        // Ukończone kroki Legacy (lista stringów z opisami nagród)
        if (legacy != null) {
            ListTag completedSteps = new ListTag();
            for (int i = 0; i < legacyProgress && i < legacy.getSteps().size(); i++) {
                completedSteps.add(StringTag.valueOf(legacy.getSteps().get(i).reward()));
            }
            tag.put("LegacyCompletedSteps", completedSteps);
        }

        // Flaw
        if (flaw != null) {
            tag.put("Flaw", flaw.toNBT());
        }
    }

    /**
     * Wczytuje dynamiczny stan aspektu z NBT.
     * Wywoływane przez AspectFactory.fromNBT() po stworzeniu instancji.
     */
    public void loadExtraFromNBT(CompoundTag tag) {
        // Ranga aspektu
        if (tag.contains("Rank")) {
            try {
                this.rank = RankSS.valueOf(tag.getString("Rank"));
            } catch (IllegalArgumentException ignored) {
                // zostaje ranga ustawiona przez init()
            }
        }

        // Postęp Legacy
        this.legacyProgress = tag.getInt("LegacyProgress");

        // Flaw
        if (tag.contains("Flaw")) {
            if (this.flaw == null) this.flaw = new Flaw();
            this.flaw.fromNBT(tag.getCompound("Flaw"));
        }
    }

    // ── Gettery / Settery ─────────────────────────────────────────────────────

    public String getAspectName()      { return aspectName; }
    public String getDescription()     { return description; }
    public RankSS getRank()            { return rank; }
    public AspectLegacy getLegacy()    { return legacy; }
    public Flaw getFlaw()              { return flaw; }
    public int getLegacyProgress()     { return legacyProgress; }

    public void setRank(RankSS rank)               { this.rank = rank; }
    public void setLegacyProgress(int progress)    { this.legacyProgress = progress; }
    public void setFlaw(Flaw flaw)                 { this.flaw = flaw; }

    public AspectAbility getAbility(RankSS rank) {
        return abilities.get(rank);
    }

    /** Przesuwa postęp Legacy o jeden krok, jeśli to możliwe. */
    public boolean advanceLegacy() {
        if (legacy == null) return false;
        if (legacyProgress >= legacy.getSteps().size()) return false;
        legacyProgress++;
        return true;
    }
}
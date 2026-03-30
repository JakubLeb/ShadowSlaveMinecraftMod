package org.thetomtomson.shadowslavecraft.data.player.custom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum RankSS {
    // ── Default
    ALL_MUNDANE     ("Mundane",       RankType.HUMAN_GIVEN,        0, 0x888888),

    // ── Nightmare Creatures ───────────────────────────────────────────────────
    NC_DORMANT      ("Dormant",       RankType.NIGHTMARE_CREATURE, 1, 0x880000),
    NC_AWAKENED     ("Awakened",      RankType.NIGHTMARE_CREATURE, 2, 0xAA0000),
    NC_FALLEN       ("Fallen",        RankType.NIGHTMARE_CREATURE, 3, 0xCC0000),
    NC_CORRUPTED    ("Corrupted",     RankType.NIGHTMARE_CREATURE, 4, 0xFF0000),
    NC_GREAT        ("Great",         RankType.NIGHTMARE_CREATURE, 5, 0xFF2200),
    NC_CURSED       ("Cursed",        RankType.NIGHTMARE_CREATURE, 6, 0xFF4400),
    NC_UNHOLY       ("Unholy",        RankType.NIGHTMARE_CREATURE, 7, 0xFF6600),

    // ── Human Given Names ─────────────────────────────────────────────────────
    HGN_SLEEPER     ("Sleeper",       RankType.HUMAN_GIVEN,        1, 0x555577),
    HGN_AWAKENED    ("Awakened",      RankType.HUMAN_GIVEN,        2, 0x7777AA),
    HGN_MASTER      ("Master",        RankType.HUMAN_GIVEN,        3, 0x4488FF),
    HGN_SAINT       ("Saint",         RankType.HUMAN_GIVEN,        4, 0xFFDD00),
    HGN_SOVEREIGN   ("Sovereign",     RankType.HUMAN_GIVEN,        5, 0xFF6600),
    HGN_SPIRIT      ("Spirit",        RankType.HUMAN_GIVEN,        6, 0xCC44CC),
    HGN_GOD         ("God",           RankType.HUMAN_GIVEN,        7, 0xFFFFFF),

    // ── Awakened Humans ───────────────────────────────────────────────────────
    AH_DORMANT      ("Dormant",       RankType.AWAKENED_HUMAN,     1, 0x333355),
    AH_AWAKENED     ("Awakened",      RankType.AWAKENED_HUMAN,     2, 0x4466AA),
    AH_ASCENDED     ("Ascended",      RankType.AWAKENED_HUMAN,     3, 0x00CCAA),
    AH_TRANSCENDENT ("Transcendent",  RankType.AWAKENED_HUMAN,     4, 0xFFAA00),
    AH_SUPREME      ("Supreme",       RankType.AWAKENED_HUMAN,     5, 0xFF3300),
    AH_SACRED       ("Sacred",        RankType.AWAKENED_HUMAN,     6, 0xCC44CC),
    AH_DIVINE       ("Divine",        RankType.AWAKENED_HUMAN,     7, 0xFFEEAA);

    // ─────────────────────────────────────────────────────────────────────────

    public enum RankType {
        NIGHTMARE_CREATURE  ("Nightmare Creatures"),
        HUMAN_GIVEN         ("Human Given Names"),
        AWAKENED_HUMAN      ("Awakened Humans");

        private final String displayName;

        RankType(String displayName) { this.displayName = displayName; }

        public String getDisplayName() { return displayName; }
    }

    // ─────────────────────────────────────────────────────────────────────────

    private final String   displayName;
    private final RankType type;
    private final int      level;
    private final int      color;

    RankSS(String displayName, RankType type, int level, int color) {
        this.displayName = displayName;
        this.type        = type;
        this.level       = level;
        this.color       = color;
    }

    public String   getDisplayName() { return displayName; }
    public RankType getType()        { return type; }
    public int      getLevel()       { return level; }
    public int      getColor()       { return color; }

    // full name with type, e.g. "Awakened Humans — Divine"
    public String getFullName() {
        return type.getDisplayName() + " — " + displayName;
    }

    // rank name only, e.g. "Divine"
    public String getRank() {
        return displayName;
    }

    // next rank IN THE SAME path (null if max)
    public RankSS next() {
        for (RankSS r : values()) {
            if (r.type == this.type && r.level == this.level + 1) return r;
        }
        return null;
    }

    // previous rank IN THE SAME path (null if min)
    public RankSS previous() {
        for (RankSS r : values()) {
            if (r.type == this.type && r.level == this.level - 1) return r;
        }
        return null;
    }

    // all ranks of a given path
    public static List<RankSS> getRanksByType(RankType type) {
        return Arrays.stream(values())
                .filter(r -> r.type == type)
                .sorted(Comparator.comparingInt(r -> r.level))
                .toList();
    }
}
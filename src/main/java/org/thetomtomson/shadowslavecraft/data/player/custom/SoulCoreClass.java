package org.thetomtomson.shadowslavecraft.data.player.custom;

public enum SoulCoreClass {
    NONE ("None",0,0, 0xFFFFFF),
    BEAST   ("Beast",   1, 1000,   0xAACC66),
    MONSTER ("Monster", 2, 2000,  0x66AACC),
    DEMON   ("Demon",   3, 3000, 0xCC6666),
    DEVIL   ("Devil",   4, 4000,  0xCC44AA),
    TYRANT  ("Tyrant",  5, 5000,  0xFF6600),
    TERROR  ("Terror",  6, 6000,  0xFF2200),
    TITAN   ("Titan",   7, 7000,  0xFFDD00);

    private final String name;
    private final int    count;           // required Soul Cores
    private final int    shardThreshold;  // Soul Shards / Shadow Fragments to fill one Core
    private final int    color;

    SoulCoreClass(String name, int count, int shardThreshold, int color) {
        this.name               = name;
        this.count              = count;
        this.shardThreshold     = shardThreshold;
        this.color              = color;
    }

    public String getName()               { return name; }
    public int    getCount()              { return count; }
    public int    getShardThreshold()     { return shardThreshold; }
    public int    getColor()              { return color; }

    public SoulCoreClass next() {
        int next = this.ordinal() + 1;
        return next < values().length ? values()[next] : null;
    }

    public SoulCoreClass previous() {
        int prev = this.ordinal() - 1;
        return prev >= 0 ? values()[prev] : null;
    }

    public static SoulCoreClass getByCount(int count) {
        for (SoulCoreClass s : values()) {
            if (s.count == count) return s;
        }
        return null;
    }
}
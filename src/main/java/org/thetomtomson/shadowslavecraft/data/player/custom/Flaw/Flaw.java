package org.thetomtomson.shadowslavecraft.data.player.custom.Flaw;

import net.minecraft.nbt.CompoundTag;

public class Flaw {
    protected String name;
    protected String description;

    public Flaw() {}

    public Flaw(String name, String description) {
        this.name        = name;
        this.description = description;
    }

    // ── NBT ───────────────────────────────────────────────────────────────────

    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("name",        name        != null ? name        : "");
        tag.putString("description", description != null ? description : "");
        return tag;
    }

    public void fromNBT(CompoundTag tag) {
        this.name        = tag.getString("name");
        this.description = tag.getString("description");
    }

    // ── Gettery ───────────────────────────────────────────────────────────────

    public String getName()        { return name; }
    public String getDescription() { return description; }
}
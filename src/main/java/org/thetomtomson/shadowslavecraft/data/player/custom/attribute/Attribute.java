package org.thetomtomson.shadowslavecraft.data.player.custom.attribute;

import net.minecraft.nbt.CompoundTag;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.custom.Fated;

public abstract class Attribute {
    protected final String name;
    protected final String description;
    protected final AttributeOrigin origin;

    protected Attribute(String name, String description, AttributeOrigin origin) {
        this.name        = name;
        this.description = description;
        this.origin      = origin;
    }

    public String getName()            { return name; }
    public String getDescription()     { return description; }
    public AttributeOrigin getOrigin() { return origin; }

    public abstract CompoundTag toNBT();

    public static Attribute fromNBT(CompoundTag tag) {
        try {
            AttributeOrigin origin = AttributeOrigin.valueOf(tag.getString("origin"));
            String name        = tag.getString("name");
            String description = tag.getString("description");

            return switch (origin) {
                case NIGHTMARE -> new Fated(name, description);
                case LINEAGE   -> new Fated(name, description);
                case DIVINE    -> new Fated(name, description);
            };
        } catch (IllegalArgumentException e) {
            // uszkodzone dane — zwróć domyślny atrybut
            return new Fated("Unknown", "Corrupted data");
        }
    }
}
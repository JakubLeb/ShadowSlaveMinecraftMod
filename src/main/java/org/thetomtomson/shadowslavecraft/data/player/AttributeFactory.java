// AttributeFactory.java
package org.thetomtomson.shadowslavecraft.data.player;

import net.minecraft.nbt.CompoundTag;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.Attribute;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.AttributeOrigin;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.custom.Fated;

public class AttributeFactory {

    /** Odczytuje jeden atrybut z NBT na podstawie pola "origin" */
    public static Attribute fromNBT(CompoundTag tag) {
        AttributeOrigin origin = AttributeOrigin.valueOf(tag.getString("origin"));
        String name        = tag.getString("name");
        String description = tag.getString("description");

        return switch (origin) {
            case NIGHTMARE -> new Fated(name, description);
            case LINEAGE   -> new Fated(name, description); // placeholder
            case DIVINE    -> new Fated(name, description); // placeholder
        };
    }
}
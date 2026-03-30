// NightmareAttribute.java
package org.thetomtomson.shadowslavecraft.data.player.custom.attribute.custom;

import net.minecraft.nbt.CompoundTag;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.Attribute;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.AttributeOrigin;

public class Fated extends Attribute {
    // TODO: Dać temu atrybutowi funkcjonalność
    public Fated(String name, String description) {
        super(
                "Fated",
                "\"The strings of fate wrap tightly around you. Unlikely events, both good and bad, are drawn by your presence. There are those who are blessed, and there are those who are cursed… but rarely both.\"",
                AttributeOrigin.NIGHTMARE);
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("origin",      origin.name());
        tag.putString("name",        name);
        tag.putString("description", description);
        return tag;
    }

    @Override
    public void fromNBT(CompoundTag tag) {
        // pola są final — odczyt przez fabrykę AttributeFactory
    }
}
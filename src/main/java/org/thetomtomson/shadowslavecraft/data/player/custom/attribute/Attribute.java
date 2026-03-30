package org.thetomtomson.shadowslavecraft.data.player.custom.attribute;

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

    // każda podklasa wie jak zapisać/odczytać siebie z NBT
    public abstract net.minecraft.nbt.CompoundTag toNBT();
    public abstract void fromNBT(net.minecraft.nbt.CompoundTag tag);
}
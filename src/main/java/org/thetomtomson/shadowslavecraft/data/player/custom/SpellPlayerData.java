// SpellPlayerData.java
package org.thetomtomson.shadowslavecraft.data.player.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.SimpleContainer;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.Aspect;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.Attribute;
import org.thetomtomson.shadowslavecraft.data.player.AttributeFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellPlayerData {
    private String name = "";
    private TrueNames true_Name = TrueNames.DEFAULT;
    private RankSS rank = RankSS.ALL_MUNDANE;
    private SoulCoreClass soulCore = SoulCoreClass.NONE;
    private int soul_Shards_or_Shadow_Fragments = 0;
    private int to_Fill_Soul_Shards_or_Shadow_Fragments = 1000;
    private final SimpleContainer memories = new SimpleContainer(108);
    private final List<Attribute> attributes = new ArrayList<>();
    private Aspect playerAspect;
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    /** Dodaje atrybut — zwraca false jeśli gracz już go posiada (po nazwie) */
    public boolean addAttribute(Attribute attribute) {
        boolean alreadyHas = attributes.stream()
                .anyMatch(a -> a.getName().equalsIgnoreCase(attribute.getName()));
        if (alreadyHas) return false;
        attributes.add(attribute);
        return true;
    }

    public boolean removeAttribute(String name) {
        return attributes.removeIf(a -> a.getName().equalsIgnoreCase(name));
    }

    public boolean hasAttribute(String name) {
        return attributes.stream().anyMatch(a -> a.getName().equalsIgnoreCase(name));
    }

    // ── NBT ───────────────────────────────────────────────────────────────────
    public CompoundTag save(CompoundTag tag) {
        ListTag attrList = new ListTag();
        for (Attribute attr : attributes) {
            attrList.add(attr.toNBT());
        }
        tag.put("Attributes", attrList);
        return tag;
    }

    public void load(CompoundTag tag) {
        attributes.clear();
        ListTag attrList = tag.getList("Attributes", Tag.TAG_COMPOUND);
        for (int i = 0; i < attrList.size(); i++) {
            attributes.add(AttributeFactory.fromNBT(attrList.getCompound(i)));
        }
    }
}
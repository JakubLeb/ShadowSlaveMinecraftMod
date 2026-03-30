// SpellPlayerData.java
package org.thetomtomson.shadowslavecraft.data.player.custom;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.AspectFactory;
import org.thetomtomson.shadowslavecraft.data.player.AttributeFactory;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.Aspect;
import org.thetomtomson.shadowslavecraft.data.player.custom.attribute.Attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellPlayerData {
    private String name                                  = "";
    private TrueNames true_Name                          = TrueNames.DEFAULT;
    private RankSS rank                                  = RankSS.ALL_MUNDANE;
    private SoulCoreClass soulCore                       = SoulCoreClass.NONE;
    private int soul_Shards_or_Shadow_Fragments          = 0;
    private int to_Fill_Soul_Shards_or_Shadow_Fragments  = 1000;
    private final SimpleContainer memories               = new SimpleContainer(108);
    private final List<Attribute> attributes             = new ArrayList<>();
    private Aspect playerAspect;

    // ── Gettery / Settery ─────────────────────────────────────────────────────

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public TrueNames getTrueName()           { return true_Name; }
    public void setTrueName(TrueNames tn)    { this.true_Name = tn; }

    public RankSS getRank()                  { return rank; }
    public void setRank(RankSS rank)         { this.rank = rank; }

    public SoulCoreClass getSoulCore()       { return soulCore; }
    public void setSoulCore(SoulCoreClass sc){ this.soulCore = sc; }

    public int getSoulShards()               { return soul_Shards_or_Shadow_Fragments; }
    public void setSoulShards(int amount)    { this.soul_Shards_or_Shadow_Fragments = amount; }

    public int getSoulShardsMax()            { return to_Fill_Soul_Shards_or_Shadow_Fragments; }
    public void setSoulShardsMax(int max)    { this.to_Fill_Soul_Shards_or_Shadow_Fragments = max; }

    public SimpleContainer getMemories()     { return memories; }

    public Aspect getPlayerAspect()          { return playerAspect; }
    public void setPlayerAspect(Aspect a)    { this.playerAspect = a; }

    public List<Attribute> getAttributes()   { return Collections.unmodifiableList(attributes); }

    // ── Atrybuty ──────────────────────────────────────────────────────────────

    /** Dodaje atrybut — zwraca false jeśli gracz już go posiada (po nazwie). */
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

    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        // Podstawowe pola
        tag.putString("Name",          name);
        tag.putString("TrueName",      true_Name.name());
        tag.putString("Rank",          rank.name());
        tag.putString("SoulCore",      soulCore.name());
        tag.putInt   ("SoulShards",    soul_Shards_or_Shadow_Fragments);
        tag.putInt   ("SoulShardsMax", to_Fill_Soul_Shards_or_Shadow_Fragments);

        // Atrybuty
        ListTag attrList = new ListTag();
        for (Attribute attr : attributes) {
            attrList.add(attr.toNBT());
        }
        tag.put("Attributes", attrList);

        // Memories (ekwipunek)
        ListTag memoriesTag = new ListTag();
        for (int i = 0; i < memories.getContainerSize(); i++) {
            ItemStack stack = memories.getItem(i);
            if (!stack.isEmpty()) {
                CompoundTag slotTag = new CompoundTag();
                slotTag.putByte("Slot", (byte) i);
                memoriesTag.add(stack.save(registries, slotTag));
            }
        }
        tag.put("Memories", memoriesTag);

        // Aspekt
        tag.put("Aspect", AspectFactory.toNBT(playerAspect));

        return tag;
    }

    public void load(CompoundTag tag, HolderLookup.Provider registries) {
        // Podstawowe pola — z zabezpieczeniem przed złymi danymi
        name = tag.getString("Name");

        try {
            true_Name = TrueNames.valueOf(tag.getString("TrueName"));
        } catch (IllegalArgumentException e) {
            true_Name = TrueNames.DEFAULT;
        }

        try {
            rank = RankSS.valueOf(tag.getString("Rank"));
        } catch (IllegalArgumentException e) {
            rank = RankSS.ALL_MUNDANE;
        }

        try {
            soulCore = SoulCoreClass.valueOf(tag.getString("SoulCore"));
        } catch (IllegalArgumentException e) {
            soulCore = SoulCoreClass.NONE;
        }

        soul_Shards_or_Shadow_Fragments         = tag.getInt("SoulShards");
        to_Fill_Soul_Shards_or_Shadow_Fragments = tag.getInt("SoulShardsMax");

        // Atrybuty
        attributes.clear();
        ListTag attrList = tag.getList("Attributes", Tag.TAG_COMPOUND);
        for (int i = 0; i < attrList.size(); i++) {
            Attribute attr = AttributeFactory.fromNBT(attrList.getCompound(i));
            if (attr != null) attributes.add(attr);
        }

        // Memories
        memories.clearContent();
        ListTag memoriesTag = tag.getList("Memories", Tag.TAG_COMPOUND);
        for (int i = 0; i < memoriesTag.size(); i++) {
            CompoundTag slotTag = memoriesTag.getCompound(i);
            int slot = slotTag.getByte("Slot") & 0xFF;
            if (slot < memories.getContainerSize()) {
                memories.setItem(slot, ItemStack.parseOptional(registries, slotTag));
            }
        }

        // Aspekt
        if (tag.contains("Aspect", Tag.TAG_COMPOUND)) {
            playerAspect = AspectFactory.fromNBT(tag.getCompound("Aspect"));
        }
    }
}
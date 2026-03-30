package org.thetomtomson.shadowslavecraft.networking;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.thetomtomson.shadowslavecraft.Shadowslavecraft;

public record SyncSpellDataPayload(CompoundTag dataTag) implements CustomPacketPayload {
    public static final Type<SyncSpellDataPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Shadowslavecraft.MODID, "sync_spell_data"));

    public static final StreamCodec<FriendlyByteBuf, SyncSpellDataPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG, SyncSpellDataPayload::dataTag,
            SyncSpellDataPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
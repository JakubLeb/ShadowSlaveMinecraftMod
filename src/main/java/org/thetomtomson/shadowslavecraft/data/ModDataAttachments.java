package org.thetomtomson.shadowslavecraft.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag; // Ważny import!
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.thetomtomson.shadowslavecraft.Shadowslavecraft;
import org.thetomtomson.shadowslavecraft.data.player.custom.SpellPlayerData;

import java.util.function.Supplier;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Shadowslavecraft.MODID);

    public static final Supplier<AttachmentType<SpellPlayerData>> SPELL_DATA = ATTACHMENT_TYPES.register(
            "spell_player_data",
            () -> AttachmentType.builder(() -> new SpellPlayerData())
                    .serialize(
                            // 1. ZAPISYWANIE (Serializer)
                            // Musimy jawnie stworzyć nowy tag, wypełnić go i GO ZWRÓCIĆ.
                            (data, registries) -> {
                                CompoundTag tag = new CompoundTag();
                                data.save(tag, registries); // Twoja metoda wypełnia tag danymi
                                return (Tag) tag; // Zwracamy tag jako ogólny typ Tag
                            },
                            // 2. ODCZYTYWANIE (Deserializer)
                            // Musimy stworzyć nowy obiekt danych i "nakarmić" go tagiem z pliku.
                            (tag, registries) -> {
                                SpellPlayerData data = new SpellPlayerData();
                                if (tag instanceof CompoundTag compound) {
                                    data.load(compound, registries); // Twoja metoda ładuje dane z tagu
                                }
                                return data;
                            }
                    )
                    .copyOnDeath()
                    .build()
    );
}
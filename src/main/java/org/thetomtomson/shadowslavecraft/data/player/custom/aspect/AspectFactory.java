package org.thetomtomson.shadowslavecraft.data.player.custom.aspect;

import net.minecraft.nbt.CompoundTag;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.Aspect;
import org.thetomtomson.shadowslavecraft.data.player.custom.aspect.custom.ShadowSlaveAspect;

/**
 * Fabryka aspektów — odczytuje aspekt z NBT na podstawie pola "AspectClass".
 *
 * Jak dodać nowy aspekt:
 *  1. Stwórz klasę rozszerzającą Aspect (np. FlameAspect)
 *  2. Dodaj case w switch poniżej: "FlameAspect" -> new FlameAspect()
 */
public class AspectFactory {

    /**
     * Tworzy instancję aspektu na podstawie zapisanego CompoundTag.
     * Zwraca null jeśli tag jest pusty lub klasa jest nieznana.
     */
    public static Aspect fromNBT(CompoundTag tag) {
        if (tag == null || tag.isEmpty()) return null;

        String className = tag.getString("AspectClass");

        Aspect aspect = switch (className) {
            case "ShadowSlaveAspect" -> new ShadowSlaveAspect();
            // TODO: dodawaj kolejne aspekty tutaj, np.:
            // case "FlameAspect"    -> new FlameAspect();
            // case "BeastAspect"    -> new BeastAspect();
            default -> null;
        };

        if (aspect == null) return null;

        // Wczytaj dodatkowy stan aspektu (ranga, legacy progress itp.)
        aspect.loadExtraFromNBT(tag);
        return aspect;
    }

    /**
     * Zapisuje aspekt do nowego CompoundTag.
     * Zwraca pusty tag jeśli aspekt jest null.
     */
    public static CompoundTag toNBT(Aspect aspect) {
        CompoundTag tag = new CompoundTag();
        if (aspect == null) return tag;

        // Klucz identyfikujący klasę aspektu
        tag.putString("AspectClass", aspect.getClass().getSimpleName());

        // Dodatkowy stan aspektu
        aspect.saveExtraToNBT(tag);

        return tag;
    }
}
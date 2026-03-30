package org.thetomtomson.shadowslavecraft.data.player.custom;

// This enum will contain True Name
// Each True name will be writen as:
// - TN_ + True name => "True Name"
// or
// - N_ + True name => "True Name"
// or
// - A_ +True name => "True Name"
public enum TrueNames {
    DEFAULT ("──"),
    //─────────────────────────────────────────────────────────────────
    N_SONG_EUNBIN ("Beastmaster"),
    TN_BEASTMASTER ("Beastmaster"),
    //─────────────────────────────────────────────────────────────────
    TN_BLOODWAVE ("Bloodwave"),
    //─────────────────────────────────────────────────────────────────
    TN_BROKEN_SWORD ("Broken Sword"),
    //─────────────────────────────────────────────────────────────────
    N_CASSIE ("Song of the Fallen"),
    TN_SONG_OF_THE_FALLEN ("Song of the Fallen"),
    //─────────────────────────────────────────────────────────────────
    N_COR ("Wake of Ruin"),
    TN_WAKE_OF_RUIN ("Wake of Ruin"),
    //─────────────────────────────────────────────────────────────────
    TN_DIRE_FANG ("Dire Fang"),
    //─────────────────────────────────────────────────────────────────
    N_EFFIE ("Raised by Wolves"),
    N_ATHENA("Raised by Wolves"),
    TN_RAISED_BY_WOLVES ("Raised by Wolves"),
    //─────────────────────────────────────────────────────────────────
    N_GILEAD ("Summer Knight"),
    TN_SUMMER_KNIGHT ("Summer Knight"),
    //─────────────────────────────────────────────────────────────────
    N_HEL ("Death Singer"),
    TN_DEATH_SINGER ("Death Singer"),
    //─────────────────────────────────────────────────────────────────
    TN_IMMORTAL_FLAME ("Immortal Flame"),
    //─────────────────────────────────────────────────────────────────
    N_JEST ("Not So Funny Anymore"),
    TN_NOT_SO_FUNNY_ANYMORE ("Not So Funny Anymore"),
    //─────────────────────────────────────────────────────────────────
    N_KAI ("Nightingale"),
    TN_NIGHTINGALE ("Nightingale"),
    //─────────────────────────────────────────────────────────────────
    N_KI_SONG("Ravensong"),
    TN_RAVENSONG ("Ravensong"),
    //─────────────────────────────────────────────────────────────────
    TN_LONESOME_HOWL ("Lonesome Howl"),
    //─────────────────────────────────────────────────────────────────
    N_MADOC ("Whispering Blade"),
    TN_WHISPERING_BLADE ("Whispering Blade"),
    //─────────────────────────────────────────────────────────────────
    N_MOONVEIL ("Black Moon"),
    TN_BLACK_MOON ("Black Moon"),
    //─────────────────────────────────────────────────────────────────
    N_NEPHIS ("Changing Star"),
    TN_CHANGING_STAR ("Changing Star"),
    A_LIGHT_BRINGER("Changing Star"),
    //─────────────────────────────────────────────────────────────────
    N_RAIN ("Promise of a Distant Sky"),
    TN_PROMISE_OF_A_DISTANT_SKY ("Promise of a Distant Sky"),
    //─────────────────────────────────────────────────────────────────
    N_REVEL ("Lightslayer"),
    N_DARK_DANCER ("Lightslayer"),
    TN_LIGHTSLAYER ("Lightslayer"),
    //─────────────────────────────────────────────────────────────────
    N_RIVALEN ("Shield Wall"),
    TN_SHIELD_WALL ("Shield Wall"),
    //─────────────────────────────────────────────────────────────────
    //N_SEISHAN ("???")
    //─────────────────────────────────────────────────────────────────
    TN_SILENT_STALKER ("Silent Stalker"),
    //─────────────────────────────────────────────────────────────────
    TN_SMILE_OF_HAVEN("Smile of Haven"),
    //─────────────────────────────────────────────────────────────────
    N_SUNLESS ("Lost from Light"),
    N_SUNNY ("Lost from Light"),
    TN_LOST_FROM_LIGHT ("Lost from Light"),
    A_SHADOW_SLAVE ("Lost from Light"),
    //─────────────────────────────────────────────────────────────────
    N_THANE ("Dream Merchant"),
    TN_DREAM_MERCHANT ("Dream Merchant"),
    //─────────────────────────────────────────────────────────────────
    N_TYRIS ("Sky Tide"),
    TN_SKY_TIDE ("Sky Tide");

    private final String displayTrueName;

    TrueNames(String displayName) {
        this.displayTrueName = displayName;
    }
    public String getDisplayName() {
        return displayTrueName;
    }

}

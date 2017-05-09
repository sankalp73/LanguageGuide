package com.example.mahe.languageguide10;

/**
 * Created by MAHE on 4/7/2017.
 */

public enum Language {
    HINDI("hi"),
    URDU("ur"),
    ALBANIAN("sq"),
    ARMENIAN("hy"),
    AZERBAIJANI("az"),
    BELARUSIAN("be"),
    BULGARIAN("bg"),
    CATALAN("ca"),
    CROATIAN("hr"),
    CZECH("cs"),
    DANISH("da"),
    DUTCH("nl"),
    ENGLISH("en"),
    ESTONIAN("et"),
    FINNISH("fi"),
    FRENCH("fr"),
    GERMAN("de"),
    GEORGIAN("ka"),
    GREEK("el"),
    HUNGARIAN("hu"),
    HEBREW("he"),
    KANNADA("kn"),
    PUNJABI("pa"),
    MARATHI("mr"),
    ITALIAN("it"),
    LATVIAN("lv"),
    LITHUANIAN("lt"),
    MACEDONIAN("mk"),
    NORWEGIAN("no"),
    NEPALI("ne"),
    TAMIL("ta"),
    TELUGU("te"),
    BENGALI("bn"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SERBIAN("sr"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SPANISH("es"),
    SWEDISH("sv"),
    TURKISH("tr"),
    MALAYALAM("ml"),
    JAPANESE("ja"),
    CHINESE("zh"),
    KOREAN("ko"),
    UKRAINIAN("uk");

    /**
     * String representation of this language.
     */
    public final String language;

    /**
     * Enum constructor.
     * @param pLanguage The language identifier.
     */
    Language(final String pLanguage) {
        language = pLanguage;
    }

    public static Language fromString(final String pLanguage) {
        for (Language l : values()) {
            if (l.toString().equals(pLanguage)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Returns the String representation of this language.
     * @return The String representation of this language.
     */
    @Override
    public String toString() {
        return language;
    }

}
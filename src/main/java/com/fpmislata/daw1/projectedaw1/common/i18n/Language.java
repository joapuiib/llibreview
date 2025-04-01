package com.fpmislata.daw1.projectedaw1.common.i18n;

import lombok.Getter;

import java.util.Locale;

@Getter
public enum Language {
    CA("ca", "es-ct"),
    EN("en", "gb");

    private final String prefix;
    private final String flag;
    private final Locale locale;

    Language(String prefix, String flag) {
        this.prefix = prefix;
        this.flag = flag;
        this.locale = new Locale(prefix);
    }

    public static Language getByPrefix(String prefix) {
        for (Language language : values()) {
            if (language.getPrefix().equalsIgnoreCase(prefix)) {
                return language;
            }
        }
        throw new IllegalArgumentException("No language found with prefix: " + prefix);
    }
}

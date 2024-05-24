package com.fpmislata.daw1.projectedaw1.common.i18n;

import lombok.Getter;

@Getter
public enum Language {
    CA("ca", "es-ct"),
    EN("en", "gb");

    private final String prefix;
    private final String flag;

    Language(String prefix, String flag) {
        this.prefix = prefix;
        this.flag = flag;
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

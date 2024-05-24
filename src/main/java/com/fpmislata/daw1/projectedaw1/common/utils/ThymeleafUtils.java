package com.fpmislata.daw1.projectedaw1.common.utils;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
public class ThymeleafUtils {
    public String formatDecimal(double number, int places) {
        String formatString = "%." + places + "f";
        return String.format(formatString, number);
    }

    public Language getLanguageByPrefix(String prefix) {
        return Language.getByPrefix(prefix);
    }
}

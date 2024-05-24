package com.fpmislata.daw1.projectedaw1.common.utils;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("unused")
@Component
public class ThymeleafUtils {
    public double round(double number, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(number);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public Language getLanguageByPrefix(String prefix) {
        return Language.getByPrefix(prefix);
    }
}

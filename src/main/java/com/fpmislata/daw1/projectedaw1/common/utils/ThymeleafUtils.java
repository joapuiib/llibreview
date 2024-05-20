package com.fpmislata.daw1.projectedaw1.common.utils;

import org.springframework.stereotype.Component;

@Component
public class ThymeleafUtils {
    public String formatDecimal(double number, int places) {
        String formatString = "%." + places + "f";
        return String.format(formatString, number);
    }
}

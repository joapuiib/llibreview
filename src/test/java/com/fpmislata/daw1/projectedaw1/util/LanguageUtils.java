package com.fpmislata.daw1.projectedaw1.util;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;

public class LanguageUtils {
    public static List<Arguments> languagesProvider() {
        return Arrays.stream(Language.values()).map(Arguments::of).toList();
    }
}

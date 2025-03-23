package com.fpmislata.daw1.projectedaw1.util;

import com.fpmislata.daw1.projectedaw1.common.i18n.Language;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

public class LocaleInvocationContextProvider implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true; // Always provide contexts
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Arrays.stream(Language.values())
                .map(lang -> new LocaleTestContext(lang.getLocale()));
    }

    static class LocaleTestContext implements TestTemplateInvocationContext {
        private final Locale locale;

        LocaleTestContext(Locale locale) {
            this.locale = locale;
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return "Locale: " + locale;
        }
    }
}

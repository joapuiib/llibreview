package com.fpmislata.daw1.projectedaw1.common.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class AppPropertiesReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties("application.properties"); // Carrega les propietats per defecte

        // Comprova si SPRING_PROFILES_ACTIVE està configurat o utilitza el perfil per defecte (dev)
        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");
        if (activeProfile == null) {
            activeProfile = System.getProperty("spring.profiles.active");
        }

        if (activeProfile != null) {
            log.info("Perfil actiu: {}", activeProfile);
            loadProperties("application-" + activeProfile + ".properties");
        } else {
            log.warn("No s'ha especificat cap perfil actiu");
        }

    }

    private static void loadProperties(String filename) {
        try (
                InputStream input = Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(filename)
        ) {
            if (input == null) {
                System.out.println("No s'ha trobat el fitxer " + filename);
                return;
            }

            // Carrega les propietats del fitxer
            PROPERTIES.load(input);

            // Replace environment variables in properties
            for (String name : PROPERTIES.stringPropertyNames()) {
                String value = PROPERTIES.getProperty(name);
                String resolvedValue = resolveEnvironmentVariables(value);
                PROPERTIES.setProperty(name, resolvedValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String resolveEnvironmentVariables(String value) {
        Pattern pattern = Pattern.compile("^\\$\\{([^}]+)}$");
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            String envVarName = matcher.group(1);
            String envVarValue = System.getenv(envVarName);
            if (envVarValue != null) {
                return envVarValue;
            } else {
                log.warn("La variable d'entorn {} no està definida", envVarName);
            }
        }
        return value;
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
package com.fpmislata.daw1.projectedaw1.common.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class AppPropertiesReader {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties("application.properties"); // Carga las propiedades por defecto

        // Detectar el perfil y cargar las propiedades correspondientes
        String activeProfile = getProperty("spring.profiles.active");
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
                System.out.println("Lo siento, no se pudo encontrar el archivo " + filename);
                return;
            }

            // Cargar las propiedades desde el archivo de configuración
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
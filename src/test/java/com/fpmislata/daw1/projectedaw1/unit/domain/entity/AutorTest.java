package com.fpmislata.daw1.projectedaw1.unit.domain.entity;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTest {
    @Nested
    class Constrctors {
        @Test
        void testEmptyConstructor() {
            Autor autor = new Autor();
            assertNotNull(autor);
        }

        @Test
        void testConstructorWithAllAttributes() {
            Autor autor = new Autor(1, "nom",  "biografia", LocalDate.parse("1970-01-01"),  "rutaImatge.jpg");
            assertAll(
                    () -> assertNotNull(autor),
                    () -> assertEquals(1, autor.getId()),
                    () -> assertEquals("nom", autor.getNom()),
                    () -> assertEquals("biografia", autor.getBiografia()),
                    () -> assertEquals(LocalDate.parse("1970-01-01"), autor.getDataNaixement()),
                    () -> assertEquals("rutaImatge.jpg", autor.getRutaImatge())
            );
        }
    }
}

package com.fpmislata.daw1.projectedaw1.unit.domain.entity;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class LlibreTest {
    @Nested
    class Constrctors {
        @Test
        void testEmptyConstructor() {
            Llibre llibre = new Llibre();
            assertNotNull(llibre);
        }

        @Test
        void testConstructorWithAllAttributes() {
            Llibre llibre = new Llibre("isbn", "titol", "resum", LocalDate.parse("1970-01-01"), 100, "rutaImatge.jpg");
            assertAll(
                    () -> assertNotNull(llibre),
                    () -> assertEquals("isbn", llibre.getIsbn()),
                    () -> assertEquals("titol", llibre.getTitol()),
                    () -> assertEquals("resum", llibre.getResum()),
                    () -> assertEquals(LocalDate.parse("1970-01-01"), llibre.getDataPublicacio()),
                    () -> assertEquals(100, llibre.getNombrePagines()),
                    () -> assertEquals("rutaImatge.jpg", llibre.getRutaImatge())
            );
        }
    }
}

package com.fpmislata.daw1.projectedaw1.unit.persistance.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LlibreDaoJdbcTest extends JdbcTest {
    private final LlibreDao llibreDao = new LlibreDaoJdbc();
    public final List<Llibre> expectedLlibreList = LlibreData.llibreList;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllLlibres() {
            List<Llibre> result = llibreDao.findAll();
            assertEquals(expectedLlibreList, result);
        }
    }

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_shouldReturnLlibre() {
            Llibre result = llibreDao.findByIsbn("isbn1");
            assertEquals(expectedLlibreList.get(0), result);
        }

        @Test
        void findByDifferentIsbn_shouldReturnDifferentLlibre() {
            Llibre result = llibreDao.findByIsbn("isbn2");
            assertEquals(expectedLlibreList.get(1), result);
        }

        @Test
        void findByNonExistingIsbn_shouldReturnNull() {
            Llibre result = llibreDao.findByIsbn("-1");
            assertNull(result);
        }
    }

    @Nested
    class FindLatest {
        @Test
        void findLatest_0_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            List<Llibre> result = llibreDao.findLatest(0);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_1_shouldReturnLatestBook() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5));
            List<Llibre> result = llibreDao.findLatest(1);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(
                    expectedLlibreList.get(5),
                    expectedLlibreList.get(4),
                    expectedLlibreList.get(3)
            );
            List<Llibre> result = llibreDao.findLatest(3);
            assertEquals(expected, result);
        }
    }

    @Disabled("llibreDao::insert no s'ha implementat encara")
    @Test
    void insert_shouldInsertLlibre() {
        Llibre llibre = new Llibre("7", "Llibre 7", "Resum 7", LocalDate.parse("2023-09-02"), 111, "imatge7.png");
        boolean inserted = llibreDao.insert(llibre);
        Llibre insertedLlibre = llibreDao.findByIsbn("7");

        assertAll(
            () -> assertTrue(inserted),
            () -> assertEquals(llibre, insertedLlibre),
            () -> assertEquals(7, llibreDao.findAll().size())
        );
    }

    @Disabled("llibreDao::insert no s'ha implementat encara")
    @Test
    void insert_shouldThrowErrorIfLlibreExists() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("1");

        assertAll(
                () -> assertThrows(RuntimeException.class, () -> llibreDao.insert(llibre)),
                () -> assertEquals(6, llibreDao.findAll().size())
        );
    }
}

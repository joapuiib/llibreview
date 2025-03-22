package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

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
    public final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_shouldReturnLlibre() {
            Llibre result = llibreDao.findByIsbn("isbn1");
            assertEquals(llibreList.getFirst(), result);
        }

        @Test
        void findByDifferentIsbn_shouldReturnDifferentLlibre() {
            Llibre result = llibreDao.findByIsbn("isbn2");
            assertEquals(llibreList.get(1), result);
        }

        @Test
        void findByNonExistingIsbn_shouldReturnNull() {
            Llibre result = llibreDao.findByIsbn("-1");
            assertNull(result);
        }
    }

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllLlibres() {
            List<Llibre> result = llibreDao.findAll();
            assertEquals(llibreList, result);
        }
    }

    @Nested
    class FindLlibresByAuthor {
        @Test
        void givenAuthorWithNoBooks_whenFindBooksByAuthor_thenEmptyList() {
            List<Llibre> result = llibreDao.findLlibresByAutorId(3);
            assertEquals(0, result.size());
        }

        @Test
        void givenAuthorWithSingleBook_whenFindBooksByAuthor_thenListWithSingleBook() {
            List<Llibre> result = llibreDao.findLlibresByAutorId(2);
            assertAll(
                    () -> assertEquals(1, result.size()),
                    () -> assertEquals(llibreList.get(2), result.getFirst())
            );
        }

        @Test
        void givenAuthorWithMultipleBooks_whenFindBooksByAuthor_thenListWithMultipleBooks() {
            List<Llibre> result = llibreDao.findLlibresByAutorId(1);
            assertAll(
                    () -> assertEquals(2, result.size()),
                    () -> assertEquals(llibreList.get(1), result.getFirst()),
                    () -> assertEquals(llibreList.get(2), result.get(1))
            );
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
            List<Llibre> expected = List.of(llibreList.get(5));
            List<Llibre> result = llibreDao.findLatest(1);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(
                    llibreList.get(5),
                    llibreList.get(4),
                    llibreList.get(3)
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

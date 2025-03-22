package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.AutorDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AutorDaoJdbcTest extends JdbcTest {
    private final AutorDao autorDao = new AutorDaoJdbc();

    public final List<Autor> autorList = AutorData.AUTOR_LIST;

    @Nested
    class FindById {
        @Test
        void givenActor_shouldReturnAutor() {
            Autor result = autorDao.findById(1);
            assertEquals(autorList.getFirst(), result);
        }

        @Test
        void givenDifferentActor_shouldReturnDifferentAutor() {
            Autor result = autorDao.findById(2);
            assertEquals(autorList.get(1), result);
        }

        @Test
        void givenNoActor_shouldReturnNull() {
            Autor result = autorDao.findById(4);
            assertNull(result);
        }
    }

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllAutors() {
            List<Autor> result = autorDao.findAll();
            assertEquals(autorList, result);
        }
    }

    @Nested
    class FindAuthorsByIsbn {
        @Test
        void givenBookWithNoAuthors_whenFindAuthorsByIsbn_thenEmptyList() {
            List<Autor> autorList = autorDao.findAutorsByLlibreIsbn("isbn1");
            assertEquals(0, autorList.size());
        }

        @Test
        void givenBookWithSingleAuthor_whenFindAuthorsByIsbn_thenListWithSingleAuthor() {
            List<Autor> result = autorDao.findAutorsByLlibreIsbn("isbn2");
            assertAll(
                    () -> assertEquals(1, result.size()),
                    () -> assertEquals(result.getFirst(), autorList.getFirst())
            );
        }

        @Test
        void givenBookWithMultipleAuthors_whenFindAuthorsByIsbn_thenListWithMultipleAuthors() {
            List<Autor> result = autorDao.findAutorsByLlibreIsbn("isbn3");
            assertAll(
                    () -> assertEquals(2, result.size(), "Expected 2 authors for isbn3"),
                    () -> assertEquals(result.getFirst(), autorList.getFirst()),
                    () -> assertEquals(result.get(1), autorList.get(1))
            );
        }
    }

}

package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EscriuDaoJdbcTest extends JdbcTest {
    private final EscriuDao escriuDao = new EscriuDaoJdbc();
    public final List<Autor> autorList = AutorData.AUTOR_LIST;
    public final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindAuthorsByIsbn {
        @Test
        void givenBookWithNoAuthors_whenFindAuthorsByIsbn_thenEmptyList() {
            List<Autor> autorList = escriuDao.findAutorsByLlibreIsbn("isbn1");
            assertEquals(0, autorList.size());
        }

        @Test
        void givenBookWithSingleAuthor_whenFindAuthorsByIsbn_thenListWithSingleAuthor() {
            List<Autor> autorList = escriuDao.findAutorsByLlibreIsbn("isbn2");
            assertAll(
                    () -> assertEquals(1, autorList.size()),
                    () -> assertEquals(EscriuDaoJdbcTest.this.autorList.get(0), autorList.get(0))
            );
        }

        @Test
        void givenBookWithMultipleAuthors_whenFindAuthorsByIsbn_thenListWithMultipleAuthors() {
            List<Autor> autorList = escriuDao.findAutorsByLlibreIsbn("isbn3");
            assertAll(
                    () -> assertEquals(2, autorList.size()),
                    () -> assertEquals(EscriuDaoJdbcTest.this.autorList.get(0), autorList.get(0)),
                    () -> assertEquals(EscriuDaoJdbcTest.this.autorList.get(1), autorList.get(1))
            );
        }
    }

    @Nested
    class FindBooksByAuthor {
        @Test
        void givenAuthorWithNoBooks_whenFindBooksByAuthor_thenEmptyList() {
            List<Llibre> autorList = escriuDao.findLlibresByAutorId(3);
            assertEquals(0, autorList.size());
        }

        @Test
        void givenAuthorWithSingleBook_whenFindBooksByAuthor_thenListWithSingleBook() {
            List<Llibre> autorList = escriuDao.findLlibresByAutorId(2);
            assertAll(
                    () -> assertEquals(1, autorList.size()),
                    () -> assertEquals(llibreList.get(2), autorList.get(0))
            );
        }

        @Test
        void givenAuthorWithMultipleBooks_whenFindBooksByAuthor_thenListWithMultipleBooks() {
            List<Llibre> autorList = escriuDao.findLlibresByAutorId(1);
            assertAll(
                    () -> assertEquals(2, autorList.size()),
                    () -> assertEquals(llibreList.get(1), autorList.get(0)),
                    () -> assertEquals(llibreList.get(2), autorList.get(1))
            );
        }
    }
}

package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.RessenyaDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RessenyaDaoJdbcTest extends JdbcTest {
    private final RessenyaDao ressenyaDao = new RessenyaDaoJdbc();

    private static final Ressenya NEW_RESSENYA_NO_VALORACIO = new Ressenya("isbn1", "user2", "Ressenya 3", LocalDate.parse("2024-05-02"));
    private static final Ressenya NEW_RESSENYA = new Ressenya("isbn2", "user2", "Ressenya 3", LocalDate.parse("2024-05-02"));
    private static final List<Ressenya> RESSENYA_LIST = RessenyaData.RESSENYA_LIST;
    private static final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private static final List<Usuari> USUARI_LIST = UsuariData.USUARI_LIST;

    @Nested
    class FindByLlibreIsbnAndUsername {
        @Test
        void givenNoRessenya_shouldReturnNull() {
            Ressenya result = ressenyaDao.findByLlibreIsbnAndUsername("isbn0", "user0");
            assertNull(result);
        }

        @Test
        void givenRessenya_shouldReturnRessenya() {
            Ressenya result = ressenyaDao.findByLlibreIsbnAndUsername("isbn1", "user1");
            assertEquals(RESSENYA_LIST.getFirst(), result);
        }

        @Test
        void givenDifferentRessenya_shouldReturnDifferentRessenya() {
            Ressenya result = ressenyaDao.findByLlibreIsbnAndUsername("isbn2", "user1");
            assertEquals(RESSENYA_LIST.get(1), result);
        }
    }


    @Nested
    class FindByLlibre {
        @Test
        void whenLlibreHasNoRessenyes_shouldReturnEmptyList() {
            Llibre llibre = LLIBRE_LIST.get(2);
            List<Ressenya> expected = List.of();

            String isbn = llibre.getIsbn();
            List<Ressenya> result = ressenyaDao.findByLlibreIsbn(isbn);

            assertEquals(expected, result);
        }

        @Test
        void whenLlibreHasRessenyes_shouldReturnRessenyes() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            List<Ressenya> expected = List.of(RESSENYA_LIST.getFirst());

            String isbn = llibre.getIsbn();
            List<Ressenya> result = ressenyaDao.findByLlibreIsbn(isbn);

            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByUsuari {
        @Test
        void whenUsuariHasNoRessenyes_shouldReturnEmptyList() {
            Usuari usuari = USUARI_LIST.get(1);
            List<Ressenya> expected = List.of();

            String username = usuari.getUsername();
            List<Ressenya> result = ressenyaDao.findByUsername(username);

            assertEquals(expected, result);
        }

        @Test
        void whenUsuariHasRessenyes_shouldReturnRessenyes() {
            Usuari usuari = USUARI_LIST.getFirst();

            String username = usuari.getUsername();
            List<Ressenya> result = ressenyaDao.findByUsername(username);

            assertEquals(RESSENYA_LIST, result);
        }
    }
    @Nested
    class Insert {
        @Test
        void givenNewRessenya_shouldInsertRessenya() {
            int rowsAffected = ressenyaDao.insert(NEW_RESSENYA);

            assertEquals(1, rowsAffected);
            Ressenya result = ressenyaDao.findByLlibreIsbnAndUsername(NEW_RESSENYA.getIsbn(), NEW_RESSENYA.getUsername());
            assertEquals(NEW_RESSENYA, result);
        }

        @Test
        void givenNewRessenyaWithNoValoracio_shouldRaiseError() {
            assertThrows(RuntimeException.class, () -> ressenyaDao.insert(NEW_RESSENYA_NO_VALORACIO));
        }

        @Test
        void givenExistingRessenya_shouldRaiseError() {
            assertThrows(RuntimeException.class, () -> ressenyaDao.insert(RESSENYA_LIST.getFirst()));
        }
    }

    @Nested
    class Update {
        @Test
        void givenNoRessenya_shouldAffectNoRows() {
            int rowsAffected = ressenyaDao.update(NEW_RESSENYA);
            assertEquals(0, rowsAffected);
        }

        @Test
        void givenRessenya_shouldUpdateRessenya() {
            Ressenya ressenya = RESSENYA_LIST.getFirst().clone();
            ressenya.setComentari("Ressenya 1 modificada");
            int rowsAffected = ressenyaDao.update(ressenya);

            assertEquals(1, rowsAffected);
            Ressenya result = ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername());
            assertEquals(ressenya, result);
        }
    }

    @Nested
    class Delete {
        @Test
        void givenNoRessenya_shouldAffectNoRows() {
            int rowsAffected = ressenyaDao.delete("isbn0", "user0");
            assertEquals(0, rowsAffected);
        }

        @Test
        void givenRessenya_shouldDeleteRessenya() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            int rowsAffected = ressenyaDao.delete(ressenya.getIsbn(), ressenya.getUsername());

            assertEquals(1, rowsAffected);
            Ressenya result = ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername());
            assertNull(result);
        }
    }
}

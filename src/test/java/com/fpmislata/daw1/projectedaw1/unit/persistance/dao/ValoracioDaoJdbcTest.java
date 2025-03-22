package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.ValoracioDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValoracioDaoJdbcTest extends JdbcTest {
    private final ValoracioDao valoracioDao = new ValoracioDaoJdbc();

    private static final Valoracio NEW_VALORACIO = new Valoracio("isbn1", "user2", 3, LocalDate.parse("2024-05-02"));
    private static final List<Valoracio> valoracioList = ValoracioData.VALORACIO_LIST;

    @Nested
    class FindByLlibreIsbnAndUsername {
        @Test
        void givenNoValoracio_shouldReturnNull() {
            Valoracio result = valoracioDao.findByLlibreIsbnAndUsername("isbn0", "user0");
            assertNull(result);
        }

        @Test
        void givenValoracio_shouldReturnValoracio() {
            Valoracio result = valoracioDao.findByLlibreIsbnAndUsername("isbn1", "user1");
            assertEquals(valoracioList.getFirst(), result);
        }

        @Test
        void givenDifferentValoracio_shouldReturnDifferentValoracio() {
            Valoracio result = valoracioDao.findByLlibreIsbnAndUsername("isbn2", "user1");
            assertEquals(valoracioList.get(1), result);
        }
    }

    @Nested
    class Insert {
        @Test
        void givenNewValoracio_shouldInsertValoracio() {
            int rowsAffected = valoracioDao.insert(NEW_VALORACIO);

            assertEquals(1, rowsAffected);
            Valoracio result = valoracioDao.findByLlibreIsbnAndUsername(NEW_VALORACIO.getIsbn(), NEW_VALORACIO.getUsername());
            assertEquals(NEW_VALORACIO, result);
        }

        @Test
        void givenExistingValoracio_shouldRaiseError() {
            assertThrows(RuntimeException.class, () -> valoracioDao.insert(valoracioList.getFirst()));
        }
    }

    @Nested
    class Update {
        @Test
        void givenNoValoracio_shouldAffectNoRows() {
            int rowsAffected = valoracioDao.update(NEW_VALORACIO);
            assertEquals(0, rowsAffected);
        }

        @Test
        void givenValoracio_shouldUpdateValoracio() {
            Valoracio valoracio = valoracioList.getFirst().clone();
            valoracio.setPuntuacio(10);
            int rowsAffected = valoracioDao.update(valoracio);

            assertEquals(1, rowsAffected);
            Valoracio result = valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername());
            assertEquals(valoracio, result);
        }
    }

    @Nested
    class Delete {
        @Test
        void givenNoValoracio_shouldAffectNoRows() {
            int rowsAffected = valoracioDao.delete("isbn0", "user0");
            assertEquals(0, rowsAffected);
        }

        @Test
        void givenValoracio_shouldDeleteValoracio() {
            Valoracio valoracio = valoracioList.getFirst();
            int rowsAffected = valoracioDao.delete(valoracio.getIsbn(), valoracio.getUsername());

            assertEquals(1, rowsAffected);
            Valoracio result = valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername());
            assertNull(result);
        }
    }
}

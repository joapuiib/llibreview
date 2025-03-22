package com.fpmislata.daw1.projectedaw1.unit.persistance.dao;

import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.UsuariDaoJdbc;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuariDaoJdbcTest extends JdbcTest {
    private final UsuariDao userDao = new UsuariDaoJdbc();

    public final Usuari NEW_USUARI = new Usuari("user5", "user5@localhost",LocalDate.parse("2021-01-01"));
    public final List<Usuari> userList = UsuariData.USUARI_LIST;

    @Nested
    class FindByUsername {
        @Test
        void givenUsername_shouldReturnUsuari() {
            Usuari expected = userList.getFirst();

            Usuari result = userDao.findByUsername(expected.getUsername());

            assertEquals(expected, result);
        }

        @Test
        void givenDifferentUsername_shouldReturnDifferentUsuari() {
            Usuari expected = userList.get(1);
            Usuari result = userDao.findByUsername(expected.getUsername());
            assertEquals(expected, result);
        }

        @Test
        void givenInexistentUsername_shouldReturnNull() {
            Usuari result = userDao.findByUsername("inexistent");
            assertNull(result);
        }
    }

    @Nested
    class FindByEmail {
        @Test
        void givenEmail_shouldReturnUsuari() {
            Usuari expected = userList.getFirst();
            Usuari result = userDao.findByEmail(expected.getEmail());
            assertEquals(expected, result);
        }

        @Test
        void givenDifferentEmail_shouldReturnDifferentUsuari() {
            Usuari expected = userList.get(1);
            Usuari result = userDao.findByEmail(expected.getEmail());
            assertEquals(expected, result);
        }

        @Test
        void givenInexistentEmail_shouldReturnNull() {
            Usuari result = userDao.findByEmail("inexistent");
            assertNull(result);
        }
    }

    @Nested
    class Insert {
        @Test
        void givenUsuari_shouldCreateUsuari() {
            int rowsAffected = userDao.insert(NEW_USUARI, "passwordHash");

            assertEquals(1, rowsAffected);
            Usuari result = userDao.findByUsername(NEW_USUARI.getUsername());
            assertEquals(NEW_USUARI, result);
        }

        @Test
        void givenExistingUsuari_shouldRaiseException() {
            assertThrows(RuntimeException.class, () -> userDao.insert(userList.getFirst(), "passwordHash"));
        }
    }

    @Nested
    class Login {
        @Test
        void givenCorrectCredentials_shouldReturnTrue() {
            String passwordHash = "$2a$10$7P2f2u72PfbOGJtL7CQTruW5WZ0.cgT9jUbnbfo.2wvE.gaaYVvn2";
            String username = userList.getFirst().getUsername();
            boolean result = userDao.login(username, passwordHash);
            assertTrue(result);
        }

        @Test
        void givenIncorrectCredentials_shouldReturnFalse() {
            boolean result = userDao.login(userList.getFirst().getUsername(), "incorrect");
            assertFalse(result);
        }
    }
}

package com.fpmislata.daw1.projectedaw1.integration;

import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.service.UsuariService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.UsuariServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.UsuariDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.UsuariRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuariServiceImplJdbcDaoTest extends JdbcTest {
    private final UsuariService usuariService = new UsuariServiceImpl(
            new UsuariRepositoryImpl(
                    new UsuariDaoJdbc()
            )
    );

    private final List<Usuari> Usuari_LIST = UsuariData.USUARI_LIST;

    @Nested
    class FindByUsername {
        @Test
        void givenUsername_shouldReturnUser() {
            Usuari expectedUsuari = Usuari_LIST.get(0);
            Usuari result = usuariService.findByUsername("user1");
            assertEquals(expectedUsuari, result);
        }

        @Test
        void givenDifferentUsername_shouldReturnDifferentUser() {
            Usuari expectedUsuari = Usuari_LIST.get(1);
            Usuari result = usuariService.findByUsername("user2");
            assertEquals(expectedUsuari, result);
        }

        @Test
        void givenNonExistentUsername_shouldReturnNull() {
            Usuari result = usuariService.findByUsername("nonExistentUsername");
            assertNull(result);
        }
    }

    @Nested
    class FindByEmail {
        @Test
        void givenEmail_shouldReturnUser() {
            Usuari expectedUsuari = Usuari_LIST.get(0);
            Usuari result = usuariService.findByEmail("user1@localhost");
            assertEquals(expectedUsuari, result);
        }

        @Test
        void givenDifferentEmail_shouldReturnDifferentUser() {
            Usuari expectedUsuari = Usuari_LIST.get(1);
            Usuari result = usuariService.findByEmail("user2@localhost");
            assertEquals(expectedUsuari, result);
        }

        @Test
        void givenNonExistentEmail_shouldReturnNull() {
            Usuari result = usuariService.findByEmail("nonExistentEmail");
            assertNull(result);
        }
    }

    @Nested
    class Create {
        @Test
        void givenNonExistentUsuari_shouldCreateUsuari() {
            usuariService.create("newUser", "newUser@localhost", "newUser", "newUser");
            Usuari result = usuariService.findByUsername("newUser");
            assertAll(
                    () -> assertNotNull(result),
                    () -> assertEquals("newUser", result.getUsername()),
                    () -> assertEquals("newUser@localhost", result.getEmail())
            );
        }

        @Test
        void givenExistentUsuari_shouldNotCreateUsuari() {
            Usuari expectedUsuari = Usuari_LIST.get(0);
            assertThrows(RuntimeException.class, () -> usuariService.create(expectedUsuari.getUsername(), "newUser@localhost", "", ""));
        }

        @Test
        void givenExistentEmail_shouldNotCreateUsuari() {
            Usuari expectedUsuari = Usuari_LIST.get(0);
            assertThrows(RuntimeException.class, () -> usuariService.create("newUser", expectedUsuari.getEmail(), "", ""));
        }

        @Test
        void givenNonMatchingPasswords_shouldNotCreateUsuari() {
            assertThrows(RuntimeException.class, () -> usuariService.create("newUser", "newUser@localhost", "newUser", "newUser2"));
        }
    }

    @Nested
    class Login {

        @BeforeEach
        void setup() {
            UserSession.setSession(new MockHttpSession());
        }

        @Test
        void givenCorrectCredentials() {
            Usuari expectedLoggedUsuari = UsuariData.USUARI_LIST.get(0);
            usuariService.login("user1", "user1");

            Usuari actualLoggedUsuari = UserSession.getUser();
            assertEquals(expectedLoggedUsuari, actualLoggedUsuari);
        }

        @Test
        void givenIncorrectCredentials() {
            assertThrows(RuntimeException.class, () -> usuariService.login("user1", "user2"));
            assertNull(UserSession.getUser());
        }

        @Test
        void givenNonExistentUsername() {
            assertThrows(RuntimeException.class, () -> usuariService.login("nonExistentUsername", "password"));
            assertNull(UserSession.getUser());
        }
    }
}
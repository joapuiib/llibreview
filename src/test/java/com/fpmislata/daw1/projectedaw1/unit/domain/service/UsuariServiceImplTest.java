package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.common.utils.EncryptionUtils;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.UsuariServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UsuariRepository;
import com.fpmislata.daw1.projectedaw1.security.UserSession;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuariServiceImplTest {
    @Mock
    private UsuariRepository usuariRepository;

    @InjectMocks
    private UsuariServiceImpl usuariService;

    final List<Usuari> usuariList = UsuariData.USUARI_LIST;

    @Nested
    class FindByUsername {
        @Test
        void givenInexistentUsername_shouldReturnNull() {
            when(usuariRepository.findByUsername("username")).thenReturn(null);

            Usuari result = usuariService.findByUsername("username");
            assertNull(result);
        }

        @Test
        void givenExistingUsername_shouldReturnUsuari() {
            Usuari expected = usuariList.getFirst();
            when(usuariRepository.findByUsername(expected.getUsername())).thenReturn(expected);

            Usuari result = usuariService.findByUsername(expected.getUsername());
            assertSame(expected, result);
        }
    }

    @Nested
    class FindByEmail {
        @Test
        void givenInexistentEmail_shouldReturnNull() {
            String inexistentEmail = "inexistent@localhost";
            when(usuariRepository.findByEmail(inexistentEmail)).thenReturn(null);

            Usuari result = usuariService.findByEmail(inexistentEmail);
            assertNull(result);
        }

        @Test
        void givenExistingEmail_shouldReturnUsuari() {
            Usuari expected = usuariList.getFirst();
            when(usuariRepository.findByEmail(expected.getEmail())).thenReturn(expected);

            Usuari result = usuariService.findByEmail(expected.getEmail());
            assertSame(expected, result);
        }
    }

    @Nested
    class Create {
        @Test
        void givenValidUsuari_shouldCreateUsuari() {
            Usuari usuari = usuariList.getFirst().clone();
            String password = "1234";
            String passwordHash = "1234hash";

            when(usuariRepository.findByUsername(usuari.getUsername())).thenReturn(null);
            when(usuariRepository.findByEmail(usuari.getEmail())).thenReturn(null);

            try (MockedStatic<EncryptionUtils> _ = mockStatic(EncryptionUtils.class)) {
                when(EncryptionUtils.hashPassword(password)).thenReturn(passwordHash);

                usuariService.create(usuari, password, password);

                // Check that the password has been hashed
                verify(usuariRepository).create(usuari, passwordHash);
            }
        }

        @Test
        void givenExistingUsername_shouldThrowException() {
            Usuari usuari = usuariList.getFirst();
            when(usuariRepository.findByUsername(usuari.getUsername())).thenReturn(usuari);

            assertThrows(RuntimeException.class, () -> usuariService.create(usuari, "1234", "1234"));
        }

        @Test
        void givenExistingEmail_shouldThrowException() {
            Usuari usuari = usuariList.getFirst();
            when(usuariRepository.findByUsername(usuari.getUsername())).thenReturn(null);
            when(usuariRepository.findByEmail(usuari.getEmail())).thenReturn(usuari);

            assertThrows(RuntimeException.class, () -> usuariService.create(usuari, "1234", "1234"));
        }

        @Test
        void givenDifferentPasswords_shouldThrowException() {
            Usuari usuari = usuariList.getFirst();

            assertThrows(RuntimeException.class, () -> usuariService.create(usuari, "1234", "4321"));
        }
    }

    @Nested
    class Login {
        @Test
        void givenValidCredentials_shouldLogin() {
            Usuari usuari = usuariList.getFirst();
            String password = "1234";
            String passwordHash = usuari.getPasswordHash();

            when(usuariRepository.findByUsername(usuari.getUsername())).thenReturn(usuari);

            // Mock the static method of UserSession and EncrytionUtils
            try (
                    MockedStatic<UserSession> mockedUserSession = mockStatic(UserSession.class);
                    MockedStatic<EncryptionUtils> _ = mockStatic(EncryptionUtils.class)
            ) {
                when(EncryptionUtils.checkPassword(password, passwordHash)).thenReturn(true);

                boolean correct = usuariService.login(usuari.getUsername(), password);

                assertAll(
                        () -> assertTrue(correct),
                        () -> mockedUserSession.verify(() -> UserSession.setUser(usuari))
                );
            }
        }

        @Test
        void givenInvalidCredentials_shouldReturnFalse() {
            Usuari usuari = usuariList.getFirst();
            String password = "1234";
            String passwordHash = usuari.getPasswordHash();

            when(usuariRepository.findByUsername(usuari.getUsername())).thenReturn(usuari);

            try (
                    MockedStatic<UserSession> mockedUserSession = mockStatic(UserSession.class);
                    MockedStatic<EncryptionUtils> _ = mockStatic(EncryptionUtils.class)
            ) {
                when(EncryptionUtils.checkPassword(password, passwordHash)).thenReturn(false);

                boolean correct = usuariService.login(usuari.getUsername(), password);

                assertAll(
                        () -> assertFalse(correct),
                        mockedUserSession::verifyNoInteractions
                );
            }
        }
    }
}
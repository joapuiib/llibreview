package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.UsuariRepositoryImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuariRepositoryImplTest {

    @Mock
    private UsuariDao usuariDao;

    @InjectMocks
    private UsuariRepositoryImpl usuariRepository;

    private final Usuari NEW_USUARI = new Usuari("user5", "user5@localhost", LocalDate.parse("2021-01-01"));
    private final List<Usuari> usuariList = UsuariData.USUARI_LIST;

    @Nested
    class FindByUsername {
        @Test
        void givenInexistentUsername_shouldReturnNull() {
            when(usuariDao.findByUsername(NEW_USUARI.getUsername())).thenReturn(null);

            Usuari result = usuariRepository.findByUsername(NEW_USUARI.getUsername());
            assertNull(result);
        }

        @Test
        void givenExistingUsername_shouldReturnUsuari() {
            Usuari expectedUsuari = usuariList.getFirst();
            when(usuariDao.findByUsername(expectedUsuari.getUsername())).thenReturn(expectedUsuari);

            Usuari result = usuariRepository.findByUsername(expectedUsuari.getUsername());
            assertSame(expectedUsuari, result);
        }

        @Test
        void givenDifferentExistingUsername_shouldReturnUsuari() {
            Usuari expectedUsuari = usuariList.get(1);
            when(usuariDao.findByUsername(expectedUsuari.getUsername())).thenReturn(expectedUsuari);

            Usuari result = usuariRepository.findByUsername(expectedUsuari.getUsername());
            assertSame(expectedUsuari, result);
        }
    }

    @Nested
    class FindByEmail {
        @Test
        void givenInexistentEmail_shouldReturnNull() {
            when(usuariDao.findByEmail(NEW_USUARI.getEmail())).thenReturn(null);

            Usuari result = usuariRepository.findByEmail(NEW_USUARI.getEmail());
            assertNull(result);
        }

        @Test
        void givenExistingEmail_shouldReturnUsuari() {
            Usuari expectedUsuari = usuariList.getFirst();
            when(usuariDao.findByEmail(expectedUsuari.getEmail())).thenReturn(expectedUsuari);

            Usuari result = usuariRepository.findByEmail(expectedUsuari.getEmail());
            assertSame(expectedUsuari, result);
        }

        @Test
        void givenDifferentExistingEmail_shouldReturnUsuari() {
            Usuari expectedUsuari = usuariList.get(1);
            when(usuariDao.findByEmail(expectedUsuari.getEmail())).thenReturn(expectedUsuari);

            Usuari result = usuariRepository.findByEmail(expectedUsuari.getEmail());
            assertSame(expectedUsuari, result);
        }
    }

    @Nested
    class Create {
        @Test
        void givenUsuari_whenOneRowAffected_shouldInsertAndReturnTrue() {
            String passwordHash = "passwordHash";
            when(usuariDao.insert(NEW_USUARI, passwordHash)).thenReturn(1);

            boolean result = usuariRepository.create(NEW_USUARI, passwordHash);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(usuariDao).insert(NEW_USUARI, passwordHash)
            );
        }

        @Test
        void givenUsuari_whenNoRowAffected_shouldReturnFalse() {
            String passwordHash = "passwordHash";
            when(usuariDao.insert(NEW_USUARI, passwordHash)).thenReturn(0);

            boolean result = usuariRepository.create(NEW_USUARI, passwordHash);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(usuariDao).insert(NEW_USUARI, passwordHash)
            );
        }
    }
}
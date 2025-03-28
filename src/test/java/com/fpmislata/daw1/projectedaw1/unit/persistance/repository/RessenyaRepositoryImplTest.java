package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.UsuariData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.RessenyaRepositoryImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RessenyaRepositoryImplTest {

    @Mock
    private RessenyaDao ressenyaDao;

    @InjectMocks
    private RessenyaRepositoryImpl ressenyaRepository;

    private static final List<Ressenya> RESSENYA_LIST = RessenyaData.RESSENYA_LIST;
    private static final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private static final List<Usuari> USUARI_LIST = UsuariData.USUARI_LIST;

    @Nested
    class FindByLlibreIsbnAndUsername {
        @Test
        void givenExistingRessenya_shouldReturnRessenya() {
            Ressenya expected = RESSENYA_LIST.getFirst();
            when(ressenyaDao.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername())).thenReturn(expected);

            Ressenya result = ressenyaRepository.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername());
            assertEquals(expected, result);
        }

        @Test
        void givenNonExistingRessenya_shouldReturnNull() {
            String isbn = "isbn0";
            String username = "user0";
            when(ressenyaDao.findByLlibreIsbnAndUsername(isbn, username)).thenReturn(null);

            Ressenya result = ressenyaRepository.findByLlibreIsbnAndUsername(isbn, username);
            assertNull(result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void whenLlibreHasNoRessenyes_shouldReturnEmptyList() {
            Llibre llibre = LLIBRE_LIST.get(1);
            List<Ressenya> expected = List.of();

            String isbn = llibre.getIsbn();
            when(ressenyaDao.findByLlibreIsbn(isbn)).thenReturn(expected);

            List<Ressenya> result = ressenyaRepository.findByLlibreIsbn(isbn);
            assertTrue(result.isEmpty());
        }

        @Test
        void whenLlibreHasRessenyes_shouldReturnRessenyes() {
            Llibre llibre = LLIBRE_LIST.getFirst();
            List<Ressenya> expected = List.of(RESSENYA_LIST.getFirst());

            String isbn = llibre.getIsbn();
            when(ressenyaDao.findByLlibreIsbn(isbn)).thenReturn(expected);

            List<Ressenya> result = ressenyaRepository.findByLlibreIsbn(isbn);
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
            when(ressenyaDao.findByUsername(username)).thenReturn(expected);

            List<Ressenya> result = ressenyaRepository.findByUsername(username);
            assertTrue(result.isEmpty());
        }

        @Test
        void whenUsuariHasRessenyes_shouldReturnRessenyes() {
            Usuari usuari = USUARI_LIST.getFirst();
            List<Ressenya> expected = RESSENYA_LIST;

            String username = usuari.getUsername();
            when(ressenyaDao.findByUsername(username)).thenReturn(expected);

            List<Ressenya> result = ressenyaRepository.findByUsername(username);
            assertEquals(expected, result);
        }
    }

    @Nested
    class Exists {
        @Test
        void givenExistingRessenya_shouldReturnTrue() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(ressenya);

            boolean result = ressenyaRepository.exists(ressenya.getIsbn(), ressenya.getUsername());
            assertTrue(result);
        }

        @Test
        void givenNonExistingRessenya_shouldReturnFalse() {
            String isbn = "isbn0";
            String username = "user0";
            when(ressenyaDao.findByLlibreIsbnAndUsername(isbn, username)).thenReturn(null);

            boolean result = ressenyaRepository.exists(isbn, username);
            assertFalse(result);
        }
    }

    @Nested
    class Save {
        @Test
        void givenNewRessenya_shouldInsertAndReturnTrue() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(null);
            when(ressenyaDao.insert(ressenya)).thenReturn(1);

            boolean result = ressenyaRepository.save(ressenya);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(ressenyaDao).insert(ressenya)
            );
        }

        @Test
        void givenNewRessenya_whenNoInserted_shouldReturnFalse() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(null);
            when(ressenyaDao.insert(ressenya)).thenReturn(0);

            boolean result = ressenyaRepository.save(ressenya);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(ressenyaDao).insert(ressenya)
            );
        }

        @Test
        void givenExistingRessenya_shouldUpdateAndReturnTrue() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(ressenya);
            when(ressenyaDao.update(ressenya)).thenReturn(1);

            boolean result = ressenyaRepository.save(ressenya);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(ressenyaDao).update(ressenya)
            );
        }

        @Test
        void givenExistingRessenya_whenNoUpdated_shouldReturnFalse() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.findByLlibreIsbnAndUsername(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(ressenya);
            when(ressenyaDao.update(ressenya)).thenReturn(0);

            boolean result = ressenyaRepository.save(ressenya);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(ressenyaDao).update(ressenya)
            );
        }
    }

    @Nested
    class Delete {
        @Test
        void givenExistingRessenya_shouldDeleteAndReturnTrue() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.delete(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(1);

            boolean result = ressenyaRepository.delete(ressenya);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(ressenyaDao).delete(ressenya.getIsbn(), ressenya.getUsername())
            );
        }

        @Test
        void givenNonExistingRessenya_shouldReturnFalse() {
            Ressenya ressenya = RESSENYA_LIST.getFirst();
            when(ressenyaDao.delete(ressenya.getIsbn(), ressenya.getUsername())).thenReturn(0);

            boolean result = ressenyaRepository.delete(ressenya);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(ressenyaDao).delete(ressenya.getIsbn(), ressenya.getUsername())
            );
        }
    }
}
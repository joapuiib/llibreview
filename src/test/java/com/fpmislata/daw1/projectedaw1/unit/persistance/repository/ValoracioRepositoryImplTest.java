package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.ValoracioRepositoryImpl;
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
class ValoracioRepositoryImplTest {

    @Mock
    private ValoracioDao valoracioDao;

    @Mock
    private RessenyaDao ressenyaDao;

    @InjectMocks
    private ValoracioRepositoryImpl valoracioRepository;

    private final List<Valoracio> valoracioList = ValoracioData.VALORACIO_LIST;

    @Nested
    class FindByLlibreIsbnAndUsername {
        @Test
        void givenExistingValoracio_shouldReturnValoracio() {
            Valoracio expected = valoracioList.getFirst();
            when(valoracioDao.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername())).thenReturn(expected);
            when(ressenyaDao.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername())).thenReturn(null);

            Valoracio result = valoracioRepository.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername());
            assertEquals(expected, result);
        }

        @Test
        void givenExistingValoracioWithRessenya_shouldReturnValoracioWithRessenya() {
            Valoracio expected = valoracioList.getFirst();
            Ressenya ressenya = new Ressenya(expected.getIsbn(), expected.getUsername(), "comentari", LocalDate.parse("2021-01-01"));
            when(valoracioDao.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername())).thenReturn(expected);
            when(ressenyaDao.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername())).thenReturn(ressenya);

            Valoracio result = valoracioRepository.findByLlibreIsbnAndUsername(expected.getIsbn(), expected.getUsername());
            assertAll(
                    () -> assertEquals(expected, result),
                    () -> assertEquals(ressenya, result.getRessenya())
            );
        }

        @Test
        void givenNonExistingValoracio_shouldReturnNull() {
            String isbn = "isbn0";
            String username = "user0";
            when(valoracioDao.findByLlibreIsbnAndUsername(isbn, username)).thenReturn(null);

            Valoracio result = valoracioRepository.findByLlibreIsbnAndUsername(isbn, username);
            assertNull(result);
        }
    }

    @Nested
    class Exists {
        @Test
        void givenExistingValoracio_shouldReturnTrue() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(valoracio);
            when(ressenyaDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(null);

            boolean result = valoracioRepository.exists(valoracio.getIsbn(), valoracio.getUsername());
            assertTrue(result);
        }

        @Test
        void givenNonExistingValoracio_shouldReturnFalse() {
            String isbn = "isbn0";
            String username = "user0";
            when(valoracioDao.findByLlibreIsbnAndUsername(isbn, username)).thenReturn(null);

            boolean result = valoracioRepository.exists(isbn, username);
            assertFalse(result);
        }
    }

    @Nested
    class Save {
        @Test
        void givenNewValoracio_shouldInsertAndReturnTrue() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(null);
            when(valoracioDao.insert(valoracio)).thenReturn(1);

            boolean result = valoracioRepository.save(valoracio);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(valoracioDao).insert(valoracio)
            );
        }

        @Test
        void givenNewValoracio_whenNoInserted_shouldReturnFalse() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(null);
            when(valoracioDao.insert(valoracio)).thenReturn(0);

            boolean result = valoracioRepository.save(valoracio);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(valoracioDao).insert(valoracio)
            );
        }

        @Test
        void givenExistingValoracio_shouldUpdateAndReturnTrue() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(valoracio);
            when(ressenyaDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(null);
            when(valoracioDao.update(valoracio)).thenReturn(1);

            boolean result = valoracioRepository.save(valoracio);
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(valoracioDao).update(valoracio)
            );
        }

        @Test
        void givenExistingValoracio_whenNoUpdated_shouldReturnFalse() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(valoracio);
            when(ressenyaDao.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(null);
            when(valoracioDao.update(valoracio)).thenReturn(0);

            boolean result = valoracioRepository.save(valoracio);
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(valoracioDao).update(valoracio)
            );
        }
    }

    @Nested
    class Delete {
        @Test
        void givenExistingValoracio_shouldDeleteAndReturnTrue() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.delete(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(1);

            boolean result = valoracioRepository.delete(valoracio.getIsbn(), valoracio.getUsername());
            assertAll(
                    () -> assertTrue(result),
                    () -> verify(valoracioDao).delete(valoracio.getIsbn(), valoracio.getUsername())
            );
        }

        @Test
        void givenNonExistingValoracio_shouldReturnFalse() {
            Valoracio valoracio = valoracioList.getFirst();
            when(valoracioDao.delete(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(0);

            boolean result = valoracioRepository.delete(valoracio.getIsbn(), valoracio.getUsername());
            assertAll(
                    () -> assertFalse(result),
                    () -> verify(valoracioDao).delete(valoracio.getIsbn(), valoracio.getUsername())
            );
        }
    }
}
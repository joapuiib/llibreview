package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorRepostoryImplTest {

    @Mock
    private AutorDao autorDao;
    @Mock
    private EscriuDao escriuDao;

    @InjectMocks
    private AutorRepositoryImpl autorRepository;

    private final List<Autor> autorList = AutorData.autorList;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnEmptyList() {
            when(autorDao.findAll()).thenReturn(List.of());

            List<Autor> result = autorRepository.findAll();
            assertTrue(result.isEmpty());
        }

        @Test
        void findAll_shouldReturnAllAutors() {
            when(autorDao.findAll()).thenReturn(autorList);

            List<Autor> result = autorRepository.findAll();
            assertEquals(autorList, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void findById_givenIdNotFound_shouldReturnNull() {
            int id = 1;
            when(autorDao.findById(id)).thenReturn(null);

            Autor result = autorRepository.findById(id);
            assertNull(result);
        }

        @Test
        void findById_givenId_shouldReturnAutor() {
            Autor expectedAutor = autorList.get(0);
            when(autorDao.findById(expectedAutor.getId())).thenReturn(expectedAutor);

            Autor result = autorRepository.findById(expectedAutor.getId());
            assertSame(expectedAutor, result);
        }

        @Test
        void findById_givenDifferentId_shouldReturnDifferentAutor() {
            Autor expectedAutor = autorList.get(1);
            when(autorDao.findById(expectedAutor.getId())).thenReturn(expectedAutor);

            Autor result = autorRepository.findById(expectedAutor.getId());
            assertSame(expectedAutor, result);
        }
    }

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_givenLlibreWithNoAutors_shouldReturnEmptyList() {
            String isbn = "0";
            when(escriuDao.findAutorsByLlibreIsbn(isbn)).thenReturn(List.of());

            List<Autor> result = autorRepository.findByLlibreIsbn(isbn);
            assertTrue(result.isEmpty());
        }

        @Test
        void findByIsbn_givenLlibreWithSingleAutor_shouldReturnAutors() {
            String isbn = "1";
            List<Autor> expectedAutors = List.of(autorList.get(0));
            when(escriuDao.findAutorsByLlibreIsbn(isbn)).thenReturn(expectedAutors);

            List<Autor> result = autorRepository.findByLlibreIsbn(isbn);
            assertEquals(expectedAutors, result);
        }

        @Test
        void findByIsbn_givenLlibreWithMultipleAutors_shouldReturnAutors() {
            String isbn = "2";
            List<Autor> expectedAutors = List.of(autorList.get(0), autorList.get(1));
            when(escriuDao.findAutorsByLlibreIsbn(isbn)).thenReturn(expectedAutors);

            List<Autor> result = autorRepository.findByLlibreIsbn(isbn);
            assertEquals(expectedAutors, result);
        }
    }
}
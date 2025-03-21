package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.AutorRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {
    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    final List<Autor> autorList = AutorData.AUTOR_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnEmptyList() {
            when(autorRepository.findAll()).thenReturn(List.of());

            List<Autor> result = autorService.findAll();
            assertTrue(result.isEmpty());
        }

        @Test
        void findAll_shouldReturnAllAutors() {
            when(autorRepository.findAll()).thenReturn(autorList);

            List<Autor> result = autorService.findAll();
            assertEquals(autorList, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void findById_givenIdNotFound_shouldReturnNull() {
            int id = 1;
            when(autorRepository.findById(id)).thenReturn(null);

            Autor result = autorService.findById(id);
            assertNull(result);
        }

        @Test
        void findById_givenId_shouldReturnAutor() {
            Autor expectedAutor = autorList.get(0);
            when(autorRepository.findById(expectedAutor.getId())).thenReturn(expectedAutor);

            Autor result = autorService.findById(expectedAutor.getId());
            assertSame(expectedAutor, result);
        }

        @Test
        void findById_givenDifferentId_shouldReturnDifferentAutor() {
            Autor expectedAutor = autorList.get(1);
            when(autorRepository.findById(expectedAutor.getId())).thenReturn(expectedAutor);

            Autor result = autorService.findById(expectedAutor.getId());
            assertSame(expectedAutor, result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void findByLlibre_givenLlibreNotFound_shouldReturnEmptyList() {
            Llibre llibre = new Llibre();
            llibre.setIsbn("isbn");

            when(autorRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(List.of());

            List<Autor> result = autorService.findByLlibre(llibre);
            assertTrue(result.isEmpty());
        }

        @Test
        void findByLlibre_shouldReturnAutors() {
            Llibre llibre = new Llibre();
            llibre.setIsbn("isbn");

            List<Autor> expectedAutorList = List.of(
                    autorList.get(0),
                    autorList.get(1)
            );
            when(autorRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expectedAutorList);

            List<Autor> result = autorService.findByLlibre(llibre);
            assertSame(expectedAutorList, result);
        }
    }
}
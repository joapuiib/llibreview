package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;
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
class LlibreServiceImplTest {
    @Mock
    private LlibreRepository llibreRepository;

    @InjectMocks
    private LlibreServiceImpl llibreService;

    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;
    private final List<Autor> autorList = AutorData.AUTOR_LIST;

    @Nested
    class FindAll {
        @Test
        void givenAllLlibres_shouldReturnAllLlibres() {
            when(llibreRepository.findAll()).thenReturn(llibreList);
            List<Llibre> result = llibreService.findAll();
            assertEquals(llibreList, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void givenLlibreIsbn_shouldReturnLlibre() {
            Llibre expectedLlibre = llibreList.get(0);
            when(llibreRepository.findByIsbn(expectedLlibre.getIsbn())).thenReturn(expectedLlibre);

            Llibre result = llibreService.findByIsbn("isbn1");
            assertEquals(expectedLlibre, result);
        }

        @Test
        void givenDifferentLlibreIsbn_shouldReturnDifferentLlibre() {
            Llibre expectedLlibre = llibreList.get(1);
            when(llibreRepository.findByIsbn(expectedLlibre.getIsbn())).thenReturn(expectedLlibre);

            Llibre result = llibreService.findByIsbn("isbn2");
            assertEquals(expectedLlibre, result);
        }

        @Test
        void givenNonExistentLlibreIsbn_shouldReturnNull() {
            when(llibreRepository.findByIsbn("inexistentIsbn")).thenReturn(null);

            Llibre result = llibreService.findByIsbn("inexistentIsbn");
            assertNull(result);
        }
    }

    @Nested
    class FindLatest {
        @Test
        void givenN0_shouldReturnEmptyList() {
            when(llibreRepository.findLatest(0)).thenReturn(List.of());

            List<Llibre> result = llibreService.findLatest(0);
            assertEquals(List.of(), result);
        }

        @Test
        void givenN1_shouldReturn1MostRecentLlibres() {
            List<Llibre> expected = List.of(llibreList.get(5));
            when(llibreRepository.findLatest(1)).thenReturn(expected);

            List<Llibre> result = llibreService.findLatest(1);
            assertEquals(expected, result);
        }

        @Test
        void givenN2_shouldReturn2MostRecentLlibres() {
            List<Llibre> expected = List.of(llibreList.get(5), llibreList.get(4));
            when(llibreRepository.findLatest(2)).thenReturn(expected);

            List<Llibre> result = llibreService.findLatest(2);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByAutor {
        @Test
        void givenAutorWithNoLlibres_shouldReturnEmptyList() {
            Autor autor = autorList.get(2);
            when(llibreRepository.findByAutorId(autor.getId())).thenReturn(List.of());

            List<Llibre> result = llibreService.findByAutor(autor);
            assertEquals(List.of(), result);
        }

        @Test
        void givenAutorWithSingleLlibre_shouldReturnSingleLlibreByAutor() {
            Autor autor = autorList.get(1);
            List<Llibre> expected = List.of(llibreList.get(2));
            when(llibreRepository.findByAutorId(autor.getId())).thenReturn(expected);

            List<Llibre> result = llibreService.findByAutor(autor);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorWithMultipleLlibres_shouldReturnMultipleLlibresByAutor() {
            Autor autor = autorList.get(0);
            List<Llibre> expected = List.of(llibreList.get(1), llibreList.get(2));
            when(llibreRepository.findByAutorId(autor.getId())).thenReturn(expected);

            List<Llibre> result = llibreService.findByAutor(autor);
            assertEquals(expected, result);
        }
    }
}
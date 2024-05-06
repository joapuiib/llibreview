package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.mock.persistance.repository.LlibreRepositoryMock;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LlibreServiceImplTest {
    private final LlibreServiceImpl llibreService = new LlibreServiceImpl(
            new LlibreRepositoryMock()
    );

    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;
    private final List<Autor> autorList = AutorData.AUTOR_LIST;

    @Nested
    class FindAll {
        @Test
        void givenAllLlibres_shouldReturnAllLlibres() {
            List<Llibre> result = llibreService.findAll();
            assertEquals(llibreList, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void givenLlibreIsbn_shouldReturnLlibre() {
            Llibre expectedLlibre = llibreList.get(0);
            Llibre result = llibreService.findByIsbn("isbn1");
            assertEquals(expectedLlibre, result);
        }

        @Test
        void givenDifferentLlibreIsbn_shouldReturnDifferentLlibre() {
            Llibre expectedLlibre = llibreList.get(1);
            Llibre result = llibreService.findByIsbn("isbn2");
            assertEquals(expectedLlibre, result);
        }

        @Test
        void givenNonExistentLlibreIsbn_shouldReturnNull() {
            Llibre result = llibreService.findByIsbn("inexistentIsbn");
            assertNull(result);
        }
    }

    @Nested
    class FindLatest {
        @Test
        void givenN0_shouldReturnEmptyList() {
            List<Llibre> result = llibreService.findLatest(0);
            assertEquals(List.of(), result);
        }

        @Test
        void givenN1_shouldReturn1MostRecentLlibres() {
            List<Llibre> result = llibreService.findLatest(1);
            List<Llibre> expected = List.of(llibreList.get(5));
            assertEquals(expected, result);
        }

        @Test
        void givenN2_shouldReturn2MostRecentLlibres() {
            List<Llibre> result = llibreService.findLatest(2);
            List<Llibre> expected = List.of(llibreList.get(5), llibreList.get(4));
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByAutor {
        @Test
        void givenAutorWithNoLlibres_shouldReturnEmptyList() {
            Autor autor = autorList.get(2);
            List<Llibre> result = llibreService.findByAutor(autor);
            assertEquals(List.of(), result);
        }

        @Test
        void givenAutorWithSingleLlibre_shouldReturnSingleLlibreByAutor() {
            Autor autor = autorList.get(1);
            List<Llibre> result = llibreService.findByAutor(autor);
            List<Llibre> expected = List.of(llibreList.get(2));
            assertEquals(expected, result);
        }

        @Test
        void givenAutorWithMultipleLlibres_shouldReturnMultipleLlibresByAutor() {
            Autor autor = autorList.get(0);
            List<Llibre> result = llibreService.findByAutor(autor);
            List<Llibre> expected = List.of(llibreList.get(1), llibreList.get(2));
            assertEquals(expected, result);
        }
    }
}
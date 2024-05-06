package com.fpmislata.daw1.projectedaw1.integration.domain.service;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.data.EscriuData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.EscriuDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.LlibreDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.EscriuTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LlibreServiceImplMemoryDaoTest {
    private final LlibreTableMemory llibreTableMemory = Mockito.mock(LlibreTableMemory.class);
    private final EscriuTableMemory escriuTableMemory = Mockito.mock(EscriuTableMemory.class);

    private final LlibreService llibreService = new LlibreServiceImpl(
            new LlibreRepositoryImpl(
                    new LlibreDaoMemory(llibreTableMemory),
                    new EscriuDaoMemory(escriuTableMemory, llibreTableMemory, null)
            )
    );

    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private final List<Autor> AUTOR_LIST = AutorData.AUTOR_LIST;

    @BeforeEach
    void setup() {
        when(llibreTableMemory.get()).thenReturn(LlibreData.LLIBRE_RECORD_LIST);
        when(escriuTableMemory.get()).thenReturn(EscriuData.ESCRIU_RECORD_LIST);
    }

    @Nested
    class FindAll {
        @Test
        void givenAllLlibres_shouldReturnAllLlibres() {
            List<Llibre> result = llibreService.findAll();
            assertEquals(LLIBRE_LIST, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void givenLlibreIsbn_shouldReturnLlibre() {
            Llibre expectedLlibre = LLIBRE_LIST.get(0);
            Llibre result = llibreService.findByIsbn("isbn1");
            assertEquals(expectedLlibre, result);
        }

        @Test
        void givenDifferentLlibreIsbn_shouldReturnDifferentLlibre() {
            Llibre expectedLlibre = LLIBRE_LIST.get(1);
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
            List<Llibre> expected = List.of(LLIBRE_LIST.get(5));
            assertEquals(expected, result);
        }

        @Test
        void givenN2_shouldReturn2MostRecentLlibres() {
            List<Llibre> result = llibreService.findLatest(2);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(5), LLIBRE_LIST.get(4));
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByAutor {
        @Test
        void givenAutorWithNoLlibres_shouldReturnEmptyList() {
            Autor autor = AUTOR_LIST.get(2);
            List<Llibre> result = llibreService.findByAutor(autor);
            assertEquals(List.of(), result);
        }

        @Test
        void givenAutorWithSingleLlibre_shouldReturnSingleLlibreByAutor() {
            Autor autor = AUTOR_LIST.get(1);
            List<Llibre> result = llibreService.findByAutor(autor);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(2));
            assertEquals(expected, result);
        }

        @Test
        void givenAutorWithMultipleLlibres_shouldReturnMultipleLlibresByAutor() {
            Autor autor = AUTOR_LIST.get(0);
            List<Llibre> result = llibreService.findByAutor(autor);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(1), LLIBRE_LIST.get(2));
            assertEquals(expected, result);
        }
    }
}
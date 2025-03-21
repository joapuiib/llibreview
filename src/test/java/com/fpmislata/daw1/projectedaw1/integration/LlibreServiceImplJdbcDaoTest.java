package com.fpmislata.daw1.projectedaw1.integration;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreGenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LlibreServiceImplJdbcDaoTest extends JdbcTest {
    private final LlibreService llibreService = new LlibreServiceImpl(
            new LlibreRepositoryImpl(
                    new LlibreDaoJdbc(),
                    new EscriuDaoJdbc(),
                    new LlibreGenereDaoJdbc()
            )
    );

    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;
    private final List<Autor> AUTOR_LIST = AutorData.AUTOR_LIST;
    private final List<Genere> GENERE_LIST = GenereData.GENERE_LIST;

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

    @Nested
    class FindByGenere {
        @Test
        void givenGenereWithNoLlibres_shouldReturnEmptyList() {
            Genere genere = GENERE_LIST.get(2);
            List<Llibre> result = llibreService.findByGenere(genere);
            assertEquals(List.of(), result);
        }

        @Test
        void givenGenereWithSingleLlibre_shouldReturnSingleLlibreByAutor() {
            Genere genere = GENERE_LIST.get(1);
            List<Llibre> result = llibreService.findByGenere(genere);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(0));
            assertEquals(expected, result);
        }

        @Test
        void givenGenereWithMultipleLlibres_shouldReturnMultipleLlibresByAutor() {
            Genere genere = GENERE_LIST.get(0);
            List<Llibre> result = llibreService.findByGenere(genere);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(0), LLIBRE_LIST.get(1));
            assertEquals(expected, result);
        }

    }

    @Nested
    class FindMostRead {
        @Test
        void givenN0_shouldReturnEmptyList() {
            List<Llibre> result = llibreService.findMostRead(0);
            assertEquals(List.of(), result);
        }

        @Test
        void givenN1_shouldReturn1MostReadLlibres() {
            List<Llibre> result = llibreService.findMostRead(1);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(1));
            assertEquals(expected, result);
        }

        @Test
        void givenN2_shouldReturn2MostReadLlibres() {
            List<Llibre> result = llibreService.findMostRead(2);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(1), LLIBRE_LIST.get(0));
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindBestRated {
        @Test
        void givenN0_shouldReturnEmptyList() {
            List<Llibre> result = llibreService.findBestRated(0);
            assertEquals(List.of(), result);
        }

        @Test
        void givenN1_shouldReturn1BestRatedLlibres() {
            List<Llibre> result = llibreService.findBestRated(1);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(1));
            assertEquals(expected, result);
        }

        @Test
        void givenN2_shouldReturn2BestRatedLlibres() {
            List<Llibre> result = llibreService.findBestRated(2);
            List<Llibre> expected = List.of(LLIBRE_LIST.get(1), LLIBRE_LIST.get(0));
            assertEquals(expected, result);
        }
    }
}
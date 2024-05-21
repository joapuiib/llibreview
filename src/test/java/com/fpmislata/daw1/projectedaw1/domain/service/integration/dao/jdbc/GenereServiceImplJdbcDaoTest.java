package com.fpmislata.daw1.projectedaw1.domain.service.integration.dao.jdbc;

import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.GenereServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.GenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.LlibreGenereDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.GenereRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GenereServiceImplJdbcDaoTest extends JdbcTest {

    private final GenereService genereService = new GenereServiceImpl(
            new GenereRepositoryImpl(
                    new GenereDaoJdbc(),
                    new LlibreGenereDaoJdbc()
            )
    );

    private final List<Genere> GENERE_LIST = GenereData.GENERE_LIST;
    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;

    @Nested
    class FindAll {
        @Test
        void givenAllGeneres_shouldReturnAllGeneres() {
            List<Genere> result = genereService.findAll();
            assertEquals(GENERE_LIST, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void givenGenereId_shouldReturnGenere() {
            Genere expectedGenere = GENERE_LIST.get(0);
            Genere result = genereService.findById(1);
            assertEquals(expectedGenere, result);
        }

        @Test
        void givenDifferentGenereId_shouldReturnDifferentGenere() {
            Genere expectedGenere = GENERE_LIST.get(1);
            Genere result = genereService.findById(2);
            assertEquals(expectedGenere, result);
        }

        @Test
        void givenNonExistentGenereId_shouldReturnNull() {
            Genere result = genereService.findById(-1);
            assertNull(result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void givenLlibreWithNoGeneres_shouldReturnEmptyList() {
            Llibre llibre = LLIBRE_LIST.get(2);
            List<Genere> result = genereService.findByLlibre(llibre);
            assertEquals(List.of(), result);
        }

        @Test
        void givenLlibreWithSingleGenere_shouldReturnListSingleGenere() {
            Llibre llibre = LLIBRE_LIST.get(1);
            List<Genere> expectedGenereList = List.of(GENERE_LIST.get(0));
            List<Genere> result = genereService.findByLlibre(llibre);
            assertEquals(expectedGenereList, result);
        }

        @Test
        void givenLlibreWithMultipleGeneres_shouldReturnListMultipleGeneres() {
            Llibre llibre = LLIBRE_LIST.get(0);
            List<Genere> expectedGenereList = List.of(GENERE_LIST.get(0), GENERE_LIST.get(1));
            List<Genere> result = genereService.findByLlibre(llibre);
            assertEquals(expectedGenereList, result);
        }
    }
}
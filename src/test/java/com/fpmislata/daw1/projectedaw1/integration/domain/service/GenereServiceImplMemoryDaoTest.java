package com.fpmislata.daw1.projectedaw1.integration.domain.service;

import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.LlibreGenereData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.GenereServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.GenereDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.LlibreGenereDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.GenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreGenereTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.GenereRepositoryImpl;
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
class GenereServiceImplMemoryDaoTest {
    private final GenereTableMemory genereTableMemory = Mockito.mock(GenereTableMemory.class);
    private final LlibreGenereTableMemory llibreGenereTableMemory = Mockito.mock(LlibreGenereTableMemory.class);

    private final GenereService genereService = new GenereServiceImpl(
            new GenereRepositoryImpl(
                    new GenereDaoMemory(genereTableMemory),
                    new LlibreGenereDaoMemory(llibreGenereTableMemory, null, genereTableMemory)
            )
    );

    private final List<Genere> GENERE_LIST = GenereData.GENERE_LIST;
    private final List<Llibre> LLIBRE_LIST = LlibreData.LLIBRE_LIST;

    @BeforeEach
    void setup() {
        when(genereTableMemory.get()).thenReturn(GenereData.GENERE_RECORD_LIST);
        when(llibreGenereTableMemory.get()).thenReturn(LlibreGenereData.LLIBRE_GENERE_RECORD_LIST);
    }

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
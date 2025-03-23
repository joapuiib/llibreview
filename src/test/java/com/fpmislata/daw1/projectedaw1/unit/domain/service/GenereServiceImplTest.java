package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.GenereServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.GenereRepository;
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
class GenereServiceImplTest {
    @Mock
    private GenereRepository genereRepository;

    @InjectMocks
    private GenereServiceImpl genereService;

    final List<Genere> genereList = GenereData.GENERE_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnEmptyList() {
            when(genereRepository.findAll()).thenReturn(List.of());

            List<Genere> result = genereService.findAll();
            assertTrue(result.isEmpty());
        }

        @Test
        void findAll_shouldReturnAllGeneres() {
            when(genereRepository.findAll()).thenReturn(genereList);

            List<Genere> result = genereService.findAll();
            assertEquals(genereList, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void findById_givenIdNotFound_shouldReturnNull() {
            int id = 1;
            when(genereRepository.findById(id)).thenReturn(null);

            Genere result = genereService.findById(id);
            assertNull(result);
        }

        @Test
        void findById_givenId_shouldReturnGenere() {
            Genere expectedGenere = genereList.getFirst();
            when(genereRepository.findById(expectedGenere.getId())).thenReturn(expectedGenere);

            Genere result = genereService.findById(expectedGenere.getId());
            assertSame(expectedGenere, result);
        }

        @Test
        void findById_givenDifferentId_shouldReturnDifferentGenere() {
            Genere expectedGenere = genereList.get(1);
            when(genereRepository.findById(expectedGenere.getId())).thenReturn(expectedGenere);

            Genere result = genereService.findById(expectedGenere.getId());
            assertSame(expectedGenere, result);
        }
    }

    @Nested
    class FindByLlibre {
        @Test
        void findByLlibre_givenLlibreNotFound_shouldReturnEmptyList() {
            Llibre llibre = new Llibre();
            llibre.setIsbn("isbn");

            when(genereRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(List.of());

            List<Genere> result = genereService.findByLlibre(llibre);
            assertTrue(result.isEmpty());
        }

        @Test
        void findByLlibre_shouldReturnGeneres() {
            Llibre llibre = new Llibre();
            llibre.setIsbn("isbn");

            List<Genere> expectedGenereList = List.of(
                    genereList.get(0),
                    genereList.get(1)
            );
            when(genereRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expectedGenereList);

            List<Genere> result = genereService.findByLlibre(llibre);
            assertSame(expectedGenereList, result);
        }
    }
}
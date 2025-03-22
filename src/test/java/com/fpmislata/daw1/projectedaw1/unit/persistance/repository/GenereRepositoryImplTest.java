package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.GenereData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.GenereRepositoryImpl;
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
class GenereRepositoryImplTest {

    @Mock
    private GenereDao genereDao;

    @InjectMocks
    private GenereRepositoryImpl genereRepository;

    private final List<Genere> genereList = GenereData.GENERE_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnEmptyList() {
            when(genereDao.findAll()).thenReturn(List.of());

            List<Genere> result = genereRepository.findAll();
            assertTrue(result.isEmpty());
        }

        @Test
        void findAll_shouldReturnAllGeneres() {
            when(genereDao.findAll()).thenReturn(genereList);

            List<Genere> result = genereRepository.findAll();
            assertEquals(genereList, result);
        }
    }

    @Nested
    class FindById {
        @Test
        void findById_givenIdNotFound_shouldReturnNull() {
            int id = 1;
            when(genereDao.findById(id)).thenReturn(null);

            Genere result = genereRepository.findById(id);
            assertNull(result);
        }

        @Test
        void findById_givenId_shouldReturnGenere() {
            Genere expectedGenere = genereList.getFirst();
            when(genereDao.findById(expectedGenere.getId())).thenReturn(expectedGenere);

            Genere result = genereRepository.findById(expectedGenere.getId());
            assertSame(expectedGenere, result);
        }

        @Test
        void findById_givenDifferentId_shouldReturnDifferentGenere() {
            Genere expectedGenere = genereList.get(1);
            when(genereDao.findById(expectedGenere.getId())).thenReturn(expectedGenere);

            Genere result = genereRepository.findById(expectedGenere.getId());
            assertSame(expectedGenere, result);
        }
    }

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_givenLlibreWithNoGeneres_shouldReturnEmptyList() {
            String isbn = "0";
            when(genereDao.findGeneresByLlibreIsbn(isbn)).thenReturn(List.of());

            List<Genere> result = genereRepository.findByLlibreIsbn(isbn);
            assertTrue(result.isEmpty());
        }

        @Test
        void findByIsbn_givenLlibreWithSingleGenere_shouldReturnGeneres() {
            String isbn = "1";
            List<Genere> expectedGeneres = List.of(genereList.getFirst());
            when(genereDao.findGeneresByLlibreIsbn(isbn)).thenReturn(expectedGeneres);

            List<Genere> result = genereRepository.findByLlibreIsbn(isbn);
            assertEquals(expectedGeneres, result);
        }

        @Test
        void findByIsbn_givenLlibreWithMultipleGeneres_shouldReturnGeneres() {
            String isbn = "2";
            List<Genere> expectedGeneres = List.of(genereList.get(0), genereList.get(1));
            when(genereDao.findGeneresByLlibreIsbn(isbn)).thenReturn(expectedGeneres);

            List<Genere> result = genereRepository.findByLlibreIsbn(isbn);
            assertEquals(expectedGeneres, result);
        }
    }
}
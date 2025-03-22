package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LlibreRepositoryImplTest {
    @Mock
    private LlibreDao llibreDao;

    @InjectMocks
    private LlibreRepositoryImpl llibreRepository;

    private final List<Llibre> expectedLlibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllLlibres() {
            when(llibreDao.findAll()).thenReturn(expectedLlibreList);

            List<Llibre> result = llibreRepository.findAll();
            assertEquals(expectedLlibreList, result);
        }
    }

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_shouldReturnLlibre() {
            Llibre expected = expectedLlibreList.getFirst();
            when(llibreDao.findByIsbn("isbn1")).thenReturn(expected);

            Llibre result = llibreRepository.findByIsbn("isbn1");
            assertEquals(expected, result);
        }

        @Test
        void findByDifferentIsbn_shouldReturnDifferentLlibre() {
            Llibre expected = expectedLlibreList.get(1);
            when(llibreDao.findByIsbn("isbn2")).thenReturn(expected);

            Llibre result = llibreRepository.findByIsbn("isbn2");
            assertEquals(expected, result);
        }

        @Test
        void findByNonExistingIsbn_shouldReturnNull() {
            when(llibreDao.findByIsbn("-1")).thenReturn(null);

            Llibre result = llibreRepository.findByIsbn("-1");
            assertNull(result);
        }
    }

    @Nested
    class FindByAutorId {
        @Test
        void givenAutorIdWithNoLlibres_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(llibreDao.findLlibresByAutorId(3)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findByAutorId(3);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorIdWithSingleLlibre_shouldReturnListWithSingleLlibre() {
            List<Llibre> expected = List.of(expectedLlibreList.get(2));
            when(llibreDao.findLlibresByAutorId(2)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findByAutorId(2);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorIdWithMultipleLlibres_shouldReturnListWithMultipleLlibres() {
            List<Llibre> expected = List.of(expectedLlibreList.get(1), expectedLlibreList.get(2));
            when(llibreDao.findLlibresByAutorId(1)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findByAutorId(1);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByGenereId {
        @Test
        void givenGenereIdWithNoLlibres_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(llibreDao.findLlibresByGenereId(3)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findByGenereId(3);
            assertEquals(expected, result);
        }

        @Test
        void givenGenereIdWithSingleLlibre_shouldReturnListWithSingleLlibre() {
            List<Llibre> expected = List.of(expectedLlibreList.get(2));
            when(llibreDao.findLlibresByGenereId(2)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findByGenereId(2);
            assertEquals(expected, result);
        }

        @Test
        void givenGenereIdWithMultipleLlibres_shouldReturnListWithMultipleLlibres() {
            List<Llibre> expected = List.of(expectedLlibreList.get(1), expectedLlibreList.get(2));
            when(llibreDao.findLlibresByGenereId(1)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findByGenereId(1);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindLatest {
        @Test
        void given0_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(llibreDao.findLatest(0)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findLatest(0);
            assertEquals(expected, result);
        }

        @Test
        void given1_shouldReturnLatestBook() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5));
            when(llibreDao.findLatest(1)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findLatest(1);
            assertEquals(expected, result);
        }

        @Test
        void given3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5), expectedLlibreList.get(4), expectedLlibreList.get(3));
            when(llibreDao.findLatest(3)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findLatest(3);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindMostRead {
        @Test
        void given0_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(llibreDao.findMostRead(0)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findMostRead(0);
            assertEquals(expected, result);
        }

        @Test
        void given1_shouldReturnLatestBook() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5));
            when(llibreDao.findMostRead(1)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findMostRead(1);
            assertEquals(expected, result);
        }

        @Test
        void given3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5), expectedLlibreList.get(4), expectedLlibreList.get(3));
            when(llibreDao.findMostRead(3)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findMostRead(3);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindBestRated {
        @Test
        void given0_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(llibreDao.findBestRated(0)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findBestRated(0);
            assertEquals(expected, result);
        }

        @Test
        void given1_shouldReturnLatestBook() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5));
            when(llibreDao.findBestRated(1)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findBestRated(1);
            assertEquals(expected, result);
        }

        @Test
        void given3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5), expectedLlibreList.get(4), expectedLlibreList.get(3));
            when(llibreDao.findBestRated(3)).thenReturn(expected);

            List<Llibre> result = llibreRepository.findBestRated(3);
            assertEquals(expected, result);
        }
    }
}
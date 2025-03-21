package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
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
class LlibreRepostoryImplTest {
    @Mock
    private LlibreDao llibreDao;

    @Mock
    private EscriuDao escriuDao;

    @InjectMocks
    private LlibreRepositoryImpl llibreRepostory;

    private final List<Llibre> expectedLlibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllLlibres() {
            when(llibreDao.findAll()).thenReturn(expectedLlibreList);

            List<Llibre> result = llibreRepostory.findAll();
            assertEquals(expectedLlibreList, result);
        }
    }

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_shouldReturnLlibre() {
            Llibre expected = expectedLlibreList.get(0);
            when(llibreDao.findByIsbn("isbn1")).thenReturn(expected);

            Llibre result = llibreRepostory.findByIsbn("isbn1");
            assertEquals(expected, result);
        }

        @Test
        void findByDifferentIsbn_shouldReturnDifferentLlibre() {
            Llibre expected = expectedLlibreList.get(1);
            when(llibreDao.findByIsbn("isbn2")).thenReturn(expected);

            Llibre result = llibreRepostory.findByIsbn("isbn2");
            assertEquals(expected, result);
        }

        @Test
        void findByNonExistingIsbn_shouldReturnNull() {
            when(llibreDao.findByIsbn("-1")).thenReturn(null);

            Llibre result = llibreRepostory.findByIsbn("-1");
            assertNull(result);
        }
    }

    @Nested
    class FindLatest {
        @Test
        void findLatest_0_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(llibreDao.findLatest(0)).thenReturn(expected);

            List<Llibre> result = llibreRepostory.findLatest(0);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_1_shouldReturnLatestBook() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5));
            when(llibreDao.findLatest(1)).thenReturn(expected);

            List<Llibre> result = llibreRepostory.findLatest(1);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5), expectedLlibreList.get(4), expectedLlibreList.get(3));
            when(llibreDao.findLatest(3)).thenReturn(expected);

            List<Llibre> result = llibreRepostory.findLatest(3);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByAutorId {
        @Test
        void givenAutorIdWithNoLlibres_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            when(escriuDao.findLlibresByAutorId(3)).thenReturn(expected);

            List<Llibre> result = llibreRepostory.findByAutorId(3);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorIdWithSingleLlibre_shouldReturnListWithSingleLlibre() {
            List<Llibre> expected = List.of(expectedLlibreList.get(2));
            when(escriuDao.findLlibresByAutorId(2)).thenReturn(expected);

            List<Llibre> result = llibreRepostory.findByAutorId(2);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorIdWithMultipleLlibres_shouldReturnListWithMultipleLlibres() {
            List<Llibre> expected = List.of(expectedLlibreList.get(1), expectedLlibreList.get(2));
            when(escriuDao.findLlibresByAutorId(1)).thenReturn(expected);

            List<Llibre> result = llibreRepostory.findByAutorId(1);
            assertEquals(expected, result);
        }
    }
}
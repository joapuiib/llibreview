package com.fpmislata.daw1.projectedaw1.persistance.repository.unit;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.mock.persistance.dao.EscriuDaoMock;
import com.fpmislata.daw1.projectedaw1.mock.persistance.dao.LlibreDaoMock;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LlibreRepostoryImplTest {

    private final LlibreRepositoryImpl llibreRepostory = new LlibreRepositoryImpl(
            new LlibreDaoMock(),
            new EscriuDaoMock(),
            null
    );
    private final List<Llibre> expectedLlibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindAll {
        @Test
        void findAll_shouldReturnAllLlibres() {
            List<Llibre> result = llibreRepostory.findAll();
            assertEquals(expectedLlibreList, result);
        }
    }

    @Nested
    class FindByIsbn {
        @Test
        void findByIsbn_shouldReturnLlibre() {
            Llibre result = llibreRepostory.findByIsbn("isbn1");
            assertEquals(expectedLlibreList.get(0), result);
        }

        @Test
        void findByDifferentIsbn_shouldReturnDifferentLlibre() {
            Llibre result = llibreRepostory.findByIsbn("isbn2");
            assertEquals(expectedLlibreList.get(1), result);
        }

        @Test
        void findByNonExistingIsbn_shouldReturnNull() {
            Llibre result = llibreRepostory.findByIsbn("-1");
            assertNull(result);
        }
    }

    @Nested
    class FindLatest {
        @Test
        void findLatest_0_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            List<Llibre> result = llibreRepostory.findLatest(0);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_1_shouldReturnLatestBook() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5));
            List<Llibre> result = llibreRepostory.findLatest(1);
            assertEquals(expected, result);
        }

        @Test
        void findLatest_3_shouldReturnThreeLatestBooks() {
            List<Llibre> expected = List.of(expectedLlibreList.get(5), expectedLlibreList.get(4), expectedLlibreList.get(3));
            List<Llibre> result = llibreRepostory.findLatest(3);
            assertEquals(expected, result);
        }
    }

    @Nested
    class FindByAutorId {
        @Test
        void givenAutorIdWithNoLlibres_shouldReturnEmptyList() {
            List<Llibre> expected = List.of();
            List<Llibre> result = llibreRepostory.findByAutorId(3);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorIdWithSingleLlibre_shouldReturnListWithSingleLlibre() {
            List<Llibre> expected = List.of(expectedLlibreList.get(2));
            List<Llibre> result = llibreRepostory.findByAutorId(2);
            assertEquals(expected, result);
        }

        @Test
        void givenAutorIdWithMultipleLlibres_shouldReturnListWithMultipleLlibres() {
            List<Llibre> expected = List.of(expectedLlibreList.get(1), expectedLlibreList.get(2));
            List<Llibre> result = llibreRepostory.findByAutorId(1);
            assertEquals(expected, result);
        }
    }
}
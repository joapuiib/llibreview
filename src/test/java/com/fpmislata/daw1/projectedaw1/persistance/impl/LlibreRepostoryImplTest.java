package com.fpmislata.daw1.projectedaw1.persistance.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.RepositoryTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fpmislata.daw1.projectedaw1.persistance.RepositoryTest.getTestDatasource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LlibreRepostoryImplTest {
    private static LlibreRepostoryImpl llibreRepostory;

    @BeforeAll
    static void setup(){
        llibreRepostory = new LlibreRepostoryImpl();
        llibreRepostory.setDatasource(getTestDatasource());
    }


    @Test
    public void findAll_shouldReturnAllLlibres() {
        // Call method under test
        List<Llibre> llibres = llibreRepostory.findAll();
        assertEquals(6, llibres.size());
    }

    @Test
    public void findLatest_1_shouldReturnLatestBook() {
        // Call method under test
        List<Llibre> llibres = llibreRepostory.findLatest(1);
        assertAll(
            () -> assertEquals(1, llibres.size()),
            () -> assertEquals("3", llibres.get(0).getIsbn())
        );
    }

    @Test
    public void findLatest_3_shouldReturnThreeLatestBooks() {
        // Call method under test
        List<Llibre> llibres = llibreRepostory.findLatest(3);
        assertAll(
                () -> assertEquals(3, llibres.size()),
                () -> assertEquals("3", llibres.get(0).getIsbn()),
                () -> assertEquals("2", llibres.get(1).getIsbn()),
                () -> assertEquals("1", llibres.get(2).getIsbn())
        );
    }
}
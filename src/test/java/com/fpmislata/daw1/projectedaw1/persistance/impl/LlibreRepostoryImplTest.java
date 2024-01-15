package com.fpmislata.daw1.projectedaw1.persistance.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LlibreRepostoryImplTest {

    @Autowired
    private LlibreRepostoryImpl llibreRepostory;

    @Test
    public void findAll_shouldReturnAllLlibres() {
        List<Llibre> llibres = llibreRepostory.findAll();
        assertEquals(6, llibres.size());
    }

    @Test
    public void findLatest_1_shouldReturnLatestBook() {
        List<Llibre> llibres = llibreRepostory.findLatest(1);
        assertAll(
            () -> assertEquals(1, llibres.size()),
            () -> assertEquals("3", llibres.get(0).getIsbn())
        );
    }

    @Test
    public void findLatest_3_shouldReturnThreeLatestBooks() {
        List<Llibre> llibres = llibreRepostory.findLatest(3);
        assertAll(
                () -> assertEquals(3, llibres.size()),
                () -> assertEquals("3", llibres.get(0).getIsbn()),
                () -> assertEquals("2", llibres.get(1).getIsbn()),
                () -> assertEquals("1", llibres.get(2).getIsbn())
        );
    }
}
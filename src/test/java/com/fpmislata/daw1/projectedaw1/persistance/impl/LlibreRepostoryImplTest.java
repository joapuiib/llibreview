package com.fpmislata.daw1.projectedaw1.persistance.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.RepositoryTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LlibreRepostoryImplTest extends RepositoryTest {
    LlibreRepostoryImpl llibreRepostory;
    public LlibreRepostoryImplTest() {
        super();
        llibreRepostory = new LlibreRepostoryImpl();
        llibreRepostory.setDatasource(testDataSource);
    }

    @Test
    public void findAll_shouldReturnAllLlibres() {
        // Call method under test
        List<Llibre> llibres = llibreRepostory.findAll();
        assertEquals(1, llibres.size());
    }
}
package com.fpmislata.daw1.projectedaw1.unit.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.mock.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.mock.persistance.dao.LlibreDaoMock;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.LlibreRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LlibreRepostoryImplTest {

    private final LlibreRepositoryImpl llibreRepostory = new LlibreRepositoryImpl(new LlibreDaoMock(), null);
    private final List<Llibre> expectedLlibreList = LlibreData.llibreList;

    @Test
    public void findAll_shouldReturnAllLlibres() {
        List<Llibre> result = llibreRepostory.findAll();
        assertEquals(expectedLlibreList, result);
    }

    @Test
    public void findLatest_1_shouldReturnLatestBook() {
        List<Llibre> result = llibreRepostory.findLatest(1);
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals(expectedLlibreList.get(5), result.get(0))
        );
    }

    @Test
    public void findLatest_3_shouldReturnThreeLatestBooks() {
        List<Llibre> result = llibreRepostory.findLatest(3);
        assertAll(
                () -> assertEquals(3, result.size()),
                () -> assertEquals(expectedLlibreList.get(5), result.get(0)),
                () -> assertEquals(expectedLlibreList.get(4), result.get(1)),
                () -> assertEquals(expectedLlibreList.get(3), result.get(2))
        );
    }
}
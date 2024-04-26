package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.mock.persistance.repository.LlibreRepositoryMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LlibreServiceImplTest {
    private final LlibreServiceImpl llibreService = new LlibreServiceImpl(new LlibreRepositoryMock());

    private final List<Llibre> llibreList = LlibreData.llibreList;

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Llibre> result = llibreService.findAll();
        assertEquals(llibreList, result);
    }
}
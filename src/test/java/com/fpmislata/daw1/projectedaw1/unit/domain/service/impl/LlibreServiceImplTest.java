package com.fpmislata.daw1.projectedaw1.unit.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
import com.fpmislata.daw1.projectedaw1.mock.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.mock.persistance.repository.LlibreRepositoryMock;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LlibreServiceImplTest {
    private final LlibreServiceImpl llibreService = new LlibreServiceImpl(new LlibreRepositoryMock());

    private final List<Llibre> expectedLlibreList = LlibreData.llibreList;

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Llibre> result = llibreService.findAll();
        assertEquals(expectedLlibreList, result);
    }
}
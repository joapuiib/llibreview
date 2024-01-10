package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.LlibreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LlibreServiceImplTest {
    @Mock
    private LlibreRepository llibreRepository;

    @InjectMocks
    private LlibreServiceImpl llibreService;

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Llibre> llibres = llibreService.findAll();
    }
}
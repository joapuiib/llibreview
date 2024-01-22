package com.fpmislata.daw1.projectedaw1.unit.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.LlibreServiceImpl;
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
    @Mock
    private LlibreRepository llibreRepository;

    @InjectMocks
    private LlibreServiceImpl llibreService;

    private static List<Llibre> expectedAllLlibres;


    @BeforeAll
    static void setup(){
        expectedAllLlibres = new ArrayList<>();
        expectedAllLlibres.add(new Llibre("1", "Llibre 1"));
        expectedAllLlibres.add(new Llibre("2", "Llibre 2"));
        expectedAllLlibres.add(new Llibre("3", "Llibre 3"));
    }

    @Test
    void findAll_shouldReturnAllLlibres() {
        when(llibreRepository.findAll()).thenReturn(expectedAllLlibres);
        List<Llibre> llibres = llibreService.findAll();
        assertEquals(expectedAllLlibres, llibres);
    }
}
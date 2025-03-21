package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.ValoracioServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValoracioServiceImplTest {
    @Mock
    private ValoracioRepository valoracioRepository;

    @InjectMocks
    private ValoracioServiceImpl valoracioService;

    private final List<Valoracio> valoracioList = ValoracioData.VALORACIO_LIST;
    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;

    @Nested
    class FindByLlibre {
        @Test
        void givenBookWithNoValoracios_thenReturnEmptyList() {
            Llibre llibre = llibreList.get(2);
            List<Valoracio> expected = List.of();
            when(valoracioRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithSingleValoracios_thenReturnValoracioList() {
            Llibre llibre = llibreList.get(0);
            List<Valoracio> expected = List.of(valoracioList.get(0));
            when(valoracioRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }

        @Test
        void givenBookWithMultipleValoracios_thenReturnValoracioList() {
            Llibre llibre = llibreList.get(1);
            List<Valoracio> expected = List.of(valoracioList.get(1), valoracioList.get(2));
            when(valoracioRepository.findByLlibreIsbn(llibre.getIsbn())).thenReturn(expected);

            List<Valoracio> result = valoracioService.findByLlibre(llibre);

            assertEquals(expected, result);
        }
    }
}
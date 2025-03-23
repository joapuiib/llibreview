package com.fpmislata.daw1.projectedaw1.unit.domain.service;

import com.fpmislata.daw1.projectedaw1.data.RessenyaData;
import com.fpmislata.daw1.projectedaw1.data.ValoracioData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.RessenyaServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RessenyaServiceImplTest {
    @Mock
    private RessenyaRepository ressenyaRepository;

    @InjectMocks
    private RessenyaServiceImpl ressenyaService;

    final List<Ressenya> ressenyaList = RessenyaData.RESSENYA_LIST;
    final List<Valoracio> valoracioList = ValoracioData.VALORACIO_LIST;


    @Nested
    class FindByValoracio {
        @Test
        void givenNoRessenyaForValoracio_shouldReturnNull() {
            Valoracio valoracio = valoracioList.getFirst();
            when(ressenyaRepository.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(null);

            Ressenya result = ressenyaService.findByValoracio(valoracio);
            assertNull(result);
        }

        @Test
        void givenRessenyaForValoracio_shouldReturnRessenya() {
            Ressenya expectedRessenya = ressenyaList.getFirst();
            Valoracio valoracio = valoracioList.getFirst();
            when(ressenyaRepository.findByLlibreIsbnAndUsername(valoracio.getIsbn(), valoracio.getUsername())).thenReturn(expectedRessenya);

            Ressenya result = ressenyaService.findByValoracio(valoracio);
            assertSame(expectedRessenya, result);
        }
    }

    @Nested
    class SaveTests {
        @Test
        void givenNewRessenya_shouldSaveRessenya() {
            Ressenya ressenya = ressenyaList.getFirst();

            ressenyaService.save(ressenya);
            verify(ressenyaRepository).save(ressenya);
        }

    }

    @Nested
    class DeleteTests {
        @Test
        void givenIsbnAndUsername_shouldDeleteRessenya() {
            Ressenya ressenya = ressenyaList.getFirst();

            ressenyaService.delete(ressenya.getIsbn(), ressenya.getUsername());
            verify(ressenyaRepository).delete(ressenya.getIsbn(), ressenya.getUsername());
        }
    }
}
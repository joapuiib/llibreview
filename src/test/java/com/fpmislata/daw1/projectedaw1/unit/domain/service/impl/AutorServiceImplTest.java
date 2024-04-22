package com.fpmislata.daw1.projectedaw1.unit.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplTest {
    @Mock
    AutorRepository autorRepository;

    @InjectMocks
    private AutorServiceImpl autorService;

    private final List<Autor> expectedAutorList = List.of(
            new Autor(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "rutaImatge1"),
            new Autor(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "rutaImatge2"),
            new Autor(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "rutaImatge3")
    );

    @Test
    void findAll_shouldReturnAllLlibres() {
        when(autorRepository.findAll()).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findAll();
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findById_shouldReturnLlibre() {
        Autor expectedAutor = expectedAutorList.get(0);
        when(autorRepository.findById(expectedAutor.getId())).thenReturn(expectedAutor);

        Autor result = autorService.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    void findByLlibre_shouldReturnLlibres() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("isbn");

        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0),
                this.expectedAutorList.get(1)
        );
        when(autorRepository.findByIsbn(llibre.getIsbn())).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }
}
package com.fpmislata.daw1.projectedaw1.integration.domain.service;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.AutorDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutorServiceImplRepositoryTest {
    private final AutorDao autorDao = Mockito.mock(AutorDao.class);
    private final EscriuDao escriuDao = Mockito.mock(EscriuDao.class);
    private final AutorRepositoryImpl autorRepository = new AutorRepositoryImpl(autorDao, escriuDao);
    private final AutorService autorService = new AutorServiceImpl(autorRepository);

    public final List<Autor> expectedAutorList = AutorData.autorList;

    @Test
    void findAll_shouldReturnAllLlibres() {
        when(autorDao.findAll()).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findAll();
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findById_shouldReturnLlibre() {
        Autor expectedAutor = expectedAutorList.get(0);
        when(autorDao.findById(expectedAutor.getId())).thenReturn(expectedAutor);

        Autor result = autorService.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    void findByDifferentId_shouldReturnDifferentAutor() {
        Autor expectedAutor = expectedAutorList.get(1);
        when(autorDao.findById(expectedAutor.getId())).thenReturn(expectedAutor);

        Autor result = autorService.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    void findByNonExistentId_shouldReturnNull() {
        when(autorDao.findById(4)).thenReturn(null);

        Autor result = autorService.findById(4);
        assertNull(result);
    }

    @Test
    void findByLlibre_givenLlibreWithNoActors_shouldReturnEmptyList() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("1");

        when(escriuDao.findAutorsByIsbn(llibre.getIsbn())).thenReturn(List.of());

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(List.of(), result);
    }

    @Test
    void findByLlibre_givenLlibreWithSingleActor_shouldReturnSingleActor() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("2");


        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0)
        );
        when(escriuDao.findAutorsByIsbn(llibre.getIsbn())).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findByLlibre_givenLlibreWithMultipleActors_shouldReturnMultipleActors() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("3");

        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0),
                this.expectedAutorList.get(1)
        );
        when(escriuDao.findAutorsByIsbn(llibre.getIsbn())).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }
}
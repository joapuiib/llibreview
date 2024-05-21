package com.fpmislata.daw1.projectedaw1.domain.service.integration.repository;

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
    private final AutorService autorService = new AutorServiceImpl(
            new AutorRepositoryImpl(
                    autorDao,
                    escriuDao
            )
    );

    public final List<Autor> AUTOR_LIST = AutorData.AUTOR_LIST;

    @Test
    void findAll_shouldReturnAllLlibres() {
        when(autorDao.findAll()).thenReturn(AUTOR_LIST);

        List<Autor> result = autorService.findAll();
        assertEquals(AUTOR_LIST, result);
    }

    @Test
    void findById_shouldReturnLlibre() {
        Autor expectedAutor = AUTOR_LIST.get(0);
        when(autorDao.findById(expectedAutor.getId())).thenReturn(expectedAutor);

        Autor result = autorService.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    void findByDifferentId_shouldReturnDifferentAutor() {
        Autor expectedAutor = AUTOR_LIST.get(1);
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

        when(escriuDao.findAutorsByLlibreIsbn(llibre.getIsbn())).thenReturn(List.of());

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(List.of(), result);
    }

    @Test
    void findByLlibre_givenLlibreWithSingleActor_shouldReturnSingleActor() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("2");


        List<Autor> expectedAutorList = List.of(
                this.AUTOR_LIST.get(0)
        );
        when(escriuDao.findAutorsByLlibreIsbn(llibre.getIsbn())).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findByLlibre_givenLlibreWithMultipleActors_shouldReturnMultipleActors() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("3");

        List<Autor> expectedAutorList = List.of(
                this.AUTOR_LIST.get(0),
                this.AUTOR_LIST.get(1)
        );
        when(escriuDao.findAutorsByLlibreIsbn(llibre.getIsbn())).thenReturn(expectedAutorList);

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }
}
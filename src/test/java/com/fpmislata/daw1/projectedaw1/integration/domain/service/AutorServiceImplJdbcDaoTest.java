package com.fpmislata.daw1.projectedaw1.integration.domain.service;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.AutorServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.AutorDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AutorServiceImplJdbcDaoTest extends JdbcTest {
    private final AutorService autorService = new AutorServiceImpl(
            new AutorRepositoryImpl(
                    new AutorDaoJdbc(), new EscriuDaoJdbc()
            )
    );
    public final List<Autor> expectedAutorList = AutorData.autorList;

    @Test
    void findAll_shouldReturnAllLlibres() {
        List<Autor> result = autorService.findAll();
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findById_shouldReturnLlibre() {
        Autor expectedAutor = expectedAutorList.get(0);

        Autor result = autorService.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    void findByDifferentId_shouldReturnDifferentAutor() {
        Autor expectedAutor = expectedAutorList.get(1);

        Autor result = autorService.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    void findByNonExistentId_shouldReturnNull() {
        Autor result = autorService.findById(4);
        assertNull(result);
    }

    @Test
    void findByLlibre_givenLlibreWithNoActors_shouldReturnEmptyList() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("isbn1");

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(List.of(), result);
    }

    @Test
    void findByLlibre_givenLlibreWithSingleActor_shouldReturnSingleActor() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("isbn2");

        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0)
        );

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }

    @Test
    void findByLlibre_givenLlibreWithMultipleActors_shouldReturnMultipleActors() {
        Llibre llibre = new Llibre();
        llibre.setIsbn("isbn3");

        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0),
                this.expectedAutorList.get(1)
        );

        List<Autor> result = autorService.findByLlibre(llibre);
        assertEquals(expectedAutorList, result);
    }
}
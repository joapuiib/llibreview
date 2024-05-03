package com.fpmislata.daw1.projectedaw1.integration.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.AutorData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.AutorDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.EscriuDaoJdbc;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.AutorRepositoryImpl;
import com.fpmislata.daw1.projectedaw1.util.JdbcTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AutorRepostoryImplJdbcDaoTest extends JdbcTest {

    private final AutorRepositoryImpl autorRepository = new AutorRepositoryImpl(
            new AutorDaoJdbc(), new EscriuDaoJdbc()
    );

    private final List<Autor> expectedAutorList = AutorData.autorList;

    @Test
    public void findAll_shouldReturnAllLlibres() {
        List<Autor> result = autorRepository.findAll();
        assertEquals(expectedAutorList, result);
    }

    @Test
    public void findById_shouldReturnAutor() {
        Autor expectedAutor = expectedAutorList.get(0);

        Autor result = autorRepository.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    public void findByDifferentId_shouldReturnDifferentAutor() {
        Autor expectedAutor = expectedAutorList.get(1);

        Autor result = autorRepository.findById(expectedAutor.getId());
        assertEquals(expectedAutor, result);
    }

    @Test
    public void findByNonExistentId_shouldReturnNull() {
        Autor result = autorRepository.findById(4);
        assertNull(result);
    }

    @Test
    public void findByIsbn_givenLlibreWithNoActors_shouldReturnEmptyList() {
        String isbn = "isbn1";
        List<Autor> expectedAutorList = List.of();

        List<Autor> result = autorRepository.findByLlibreIsbn(isbn);
        assertEquals(expectedAutorList, result);
    }

    @Test
    public void findByIsbn_givenLlibreWithSingleActor_shouldReturnListWithSingleActor() {
        String isbn = "isbn2";
        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0)
        );

        List<Autor> result = autorRepository.findByLlibreIsbn(isbn);
        assertEquals(expectedAutorList, result);
    }

    @Test
    public void findByIsbn_givenLlibreWithMultipleActors_shouldReturnListWithMultipleActors() {
        String isbn = "isbn3";
        List<Autor> expectedAutorList = List.of(
                this.expectedAutorList.get(0),
                this.expectedAutorList.get(1)
        );

        List<Autor> result = autorRepository.findByLlibreIsbn(isbn);
        assertEquals(expectedAutorList, result);
    }
}
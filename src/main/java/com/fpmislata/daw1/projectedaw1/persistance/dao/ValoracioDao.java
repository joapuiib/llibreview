package com.fpmislata.daw1.projectedaw1.persistance.dao;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

public interface ValoracioDao {
    Valoracio findByLlibreIsbnAndUsername(String isbn, String username);
    List<Valoracio> findByLlibreIsbn(String isbn);
    double getMitjanaByLlibreIsbn(String isbn);
    List<Valoracio> findByUsername(String username);
    int insert(Valoracio valoracio);
    int update(Valoracio valoracio);
    int delete(String isbn, String username);
}

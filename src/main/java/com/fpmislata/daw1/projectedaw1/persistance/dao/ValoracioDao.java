package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.util.List;

public interface ValoracioDao {
    Valoracio findByLlibreIsbnAndUsername(String isbn, String username);
    List<Valoracio> findByLlibreIsbn(String isbn);
    List<Valoracio> findByUsername(String username);
    void save(Valoracio valoracio);
    void insert(Valoracio valoracio);
    void update(Valoracio valoracio);
    void delete(String isbn, String username);
}

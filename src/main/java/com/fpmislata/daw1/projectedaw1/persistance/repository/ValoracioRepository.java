package com.fpmislata.daw1.projectedaw1.persistance.repository;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

public interface ValoracioRepository {
    Valoracio findByLlibreIsbnAndUsername(String isbn, String username);
    List<Valoracio> findByIsbn(String isbn);
    List<Valoracio> findByUsername(String username);
    boolean exists(String isbn, String username);
    boolean save(Valoracio valoracio);
    boolean delete(String isbn, String username);
}

package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;

import java.util.List;

public interface RessenyaRepository {
    Ressenya findByLlibreIsbnAndUsername(String isbn, String username);
    List<Ressenya> findByLlibreIsbn(String isbn);
    List<Ressenya> findByUsername(String username);
    boolean exists(String isbn, String username);
    boolean save(Ressenya ressenya);
    boolean delete(Ressenya ressenya);
}

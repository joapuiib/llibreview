package com.fpmislata.daw1.projectedaw1.persistance.repository;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;

public interface GenereRepository {
    Genere findById(int id);
    List<Genere> findAll();
    List<Genere> findByLlibreIsbn(String isbn);
}

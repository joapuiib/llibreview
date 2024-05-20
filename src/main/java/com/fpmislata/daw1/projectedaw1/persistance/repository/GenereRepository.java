package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;

import java.util.List;

public interface GenereRepository {
    Genere findById(int id);
    List<Genere> findAll();
    List<Genere> findByLlibreIsbn(String isbn);
}

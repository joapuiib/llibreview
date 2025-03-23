package com.fpmislata.daw1.projectedaw1.persistance.dao;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;

public interface GenereDao {
    Genere findById(int id);
    List<Genere> findAll();
    List<Genere> findGeneresByLlibreIsbn(String isbn);
}
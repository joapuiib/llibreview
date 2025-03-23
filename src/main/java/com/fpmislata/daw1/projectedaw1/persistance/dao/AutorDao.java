package com.fpmislata.daw1.projectedaw1.persistance.dao;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

public interface AutorDao {
    Autor findById(int id);
    List<Autor> findAll();
    List<Autor> findAutorsByLlibreIsbn(String isbn);
}

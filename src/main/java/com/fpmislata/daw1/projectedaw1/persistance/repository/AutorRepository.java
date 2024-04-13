package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

import java.util.List;

public interface AutorRepository {
    Autor findById(int id);
    List<Autor> findAll();
}

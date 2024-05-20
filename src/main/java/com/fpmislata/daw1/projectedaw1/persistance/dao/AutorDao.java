package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

import java.util.List;

public interface AutorDao {
    Autor findById(int id);
    List<Autor> findAll();
}

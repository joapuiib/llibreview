package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

import java.util.List;

public interface AutorService {
    Autor findById(int id);
    List<Autor> findAll();
}

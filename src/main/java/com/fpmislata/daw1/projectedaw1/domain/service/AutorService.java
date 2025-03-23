package com.fpmislata.daw1.projectedaw1.domain.service;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

public interface AutorService {
    Autor findById(int id);
    List<Autor> findAll();
    List<Autor> findByLlibre(Llibre llibre);
}

package com.fpmislata.daw1.projectedaw1.domain.service;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

public interface GenereService {
    Genere findById(int id);
    List<Genere> findAll();
    List<Genere> findByLlibre(Llibre llibre);
}

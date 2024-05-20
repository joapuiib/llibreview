package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface GenereService {
    Genere findById(int id);
    List<Genere> findAll();
    List<Genere> findByLlibre(Llibre llibre);
}

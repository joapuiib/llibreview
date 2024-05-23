package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

public interface RessenyaRepository {
    Ressenya findByValoracio(Valoracio valoracio);
    void save(Ressenya ressenya);
    void delete(String isbn, String username);
}

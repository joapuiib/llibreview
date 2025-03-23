package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

public interface RessenyaService {
    Ressenya findByValoracio(Valoracio valoracio);
    void save(Ressenya ressenya);
    void delete(String isbn, String username);
}

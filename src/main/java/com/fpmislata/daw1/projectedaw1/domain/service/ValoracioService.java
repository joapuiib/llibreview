package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.util.List;

public interface ValoracioService {
    Valoracio findByLlibreAndUser(Llibre llibre, Usuari usuari);
    List<Valoracio> findByLlibre(Llibre llibre);
    List<Valoracio> findByUser(Usuari usuari);
    void save(Valoracio valoracio);
    void delete(String isbn, String username);
}

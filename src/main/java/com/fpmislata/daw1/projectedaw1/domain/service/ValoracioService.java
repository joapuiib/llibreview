package com.fpmislata.daw1.projectedaw1.domain.service;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

public interface ValoracioService {
    Valoracio findByLlibreAndUser(Llibre llibre, Usuari usuari);
    List<Valoracio> findByLlibre(Llibre llibre);
    List<Valoracio> findByUser(Usuari usuari);
    void save(Valoracio valoracio);
    void delete(String isbn, String username);
}

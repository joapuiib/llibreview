package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

import java.util.List;

public interface RessenyaService {
    Ressenya findByLlibreAndUsuari(Llibre llibre, Usuari usuari);
    List<Ressenya> findByLlibre(Llibre llibre);
    List<Ressenya> findByUsuari(Usuari usuari);
    int countByUsuari(Usuari usuari);
    boolean save(Ressenya ressenya);
    boolean delete(Ressenya ressenya);
}

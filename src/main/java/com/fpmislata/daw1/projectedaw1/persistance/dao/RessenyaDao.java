package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.util.List;

public interface RessenyaDao {
    Ressenya findByLlibreIsbnAndUsername(String isbn, String username);
    void save(Ressenya ressenya);
    void insert(Ressenya ressenya);
    void update(Ressenya ressenya);
    void delete(String isbn, String username);
}

package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;

import java.util.List;

public interface GenereDao {
    Genere findById(int id);
    List<Genere> findAll();
}

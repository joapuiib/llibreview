package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreDao {
    List<Llibre> findAll();

    Llibre find(int id);
}

package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;

import java.util.List;

public interface RessenyaDao {
    Ressenya findByLlibreIsbnAndUsername(String isbn, String username);
    List<Ressenya> findByLlibreIsbn(String isbn);
    List<Ressenya> findByUsername(String username);
    int countByUsername(String username);
    int insert(Ressenya ressenya);
    int update(Ressenya ressenya);
    int delete(String isbn, String username);
}

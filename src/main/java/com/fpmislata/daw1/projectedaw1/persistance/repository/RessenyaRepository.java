package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;

public interface RessenyaRepository {
    Ressenya findByLlibreIsbnAndUsername(String isbn, String username);
    boolean exists(String isbn, String username);
    boolean save(Ressenya ressenya);
    boolean delete(String isbn, String username);
}

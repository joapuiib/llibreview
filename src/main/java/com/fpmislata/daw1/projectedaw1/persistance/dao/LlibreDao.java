package com.fpmislata.daw1.projectedaw1.persistance.dao;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

public interface LlibreDao {
    Llibre findByIsbn(String isbn);
    List<Llibre> findAll();
    List<Llibre> findLlibresByAutorId(int id);
    List<Llibre> findLlibresByGenereId(int id);
    List<Llibre> findLatest(int n);
    List<Llibre> findMostRead(int n);
    List<Llibre> findBestRated(int n);
}

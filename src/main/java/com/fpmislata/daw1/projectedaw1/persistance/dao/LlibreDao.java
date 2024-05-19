package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreDao {
    List<Llibre> findAll();
    Llibre findByIsbn(String isbn);
    List<Llibre> findLatest(int n);
    List<Llibre> findMostRead(int n);
    List<Llibre> findBestRating(int n);
    boolean insert(Llibre llibre);
    Llibre delete(String isbn);
}

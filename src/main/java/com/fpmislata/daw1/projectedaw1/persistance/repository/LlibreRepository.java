package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreRepository {
    List<Llibre> findAll();
    List<Llibre> findLatest(int n);
    List<Llibre> findMostRead(int n);
    List<Llibre> findBestReview(int n);
}

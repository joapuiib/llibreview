package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreService {
    List<Llibre> findAll();
    List<Llibre> findLatest(int n);
    List<Llibre> findMostRead(int n);
    List<Llibre> findBestReview(int n);
}

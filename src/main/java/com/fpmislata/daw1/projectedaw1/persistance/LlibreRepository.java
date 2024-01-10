package com.fpmislata.daw1.projectedaw1.persistance;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreRepository {
    List<Llibre> findAll();
    List<Llibre> findLatest();
    List<Llibre> findMostRead();
    List<Llibre> findBestReview();
}

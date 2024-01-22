package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;

import java.util.List;

public class LlibreServiceImpl implements LlibreService {
    private LlibreRepository llibreRepository;

    public LlibreServiceImpl(LlibreRepository llibreRepository) {
        this.llibreRepository = llibreRepository;
    }

    public List<Llibre> findAll() {
        return llibreRepository.findAll();
    }

    public List<Llibre> findLatest(int n) {
        return llibreRepository.findLatest(n);
    }

    public List<Llibre> findMostRead(int n) {
        return llibreRepository.findMostRead(n);
    }

    public List<Llibre> findBestReview(int n) {
        return llibreRepository.findBestReview(n);
    }
}

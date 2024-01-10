package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.persistance.LlibreRepository;

import java.util.List;

public class LlibreServiceImpl implements LlibreService {
    private LlibreRepository llibreRepository;
    @Override
    public List<Llibre> findAll() {
        return llibreRepository.findAll();
    }

    @Override
    public List<Llibre> findLatest() {
        return llibreRepository.findLatest();
    }

    @Override
    public List<Llibre> findMostRead() {
        return llibreRepository.findMostRead();
    }

    @Override
    public List<Llibre> findBestReview() {
        return llibreRepository.findBestReview();
    }
}

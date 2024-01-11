package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.persistance.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.impl.LlibreRepostoryImpl;

import java.util.List;

public class LlibreServiceImpl implements LlibreService {
    private LlibreRepository llibreRepository = new LlibreRepostoryImpl();
    @Override
    public List<Llibre> findAll() {
        return llibreRepository.findAll();
    }

    @Override
    public List<Llibre> findLatest(int n) {
        return llibreRepository.findLatest(n);
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        return llibreRepository.findMostRead(n);
    }

    @Override
    public List<Llibre> findBestReview(int n) {
        return llibreRepository.findBestReview(n);
    }
}

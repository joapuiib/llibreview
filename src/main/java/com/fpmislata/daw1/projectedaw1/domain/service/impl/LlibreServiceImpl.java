package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.persistance.LlibreRepository;
import com.fpmislata.daw1.projectedaw1.persistance.impl.LlibreRepostoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LlibreServiceImpl implements LlibreService {
    @Autowired
    private LlibreRepository llibreRepository;
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

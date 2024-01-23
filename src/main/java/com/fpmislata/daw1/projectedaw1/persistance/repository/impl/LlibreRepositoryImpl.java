package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;
import java.util.List;

public class LlibreRepositoryImpl implements LlibreRepository {
    private final LlibreDao llibreDao;

    public LlibreRepositoryImpl(LlibreDao llibreDao) {
        this.llibreDao = llibreDao;
    }

    @Override
    public List<Llibre> findAll() {
        return llibreDao.findAll();
    }

    @Override
    public List<Llibre> findLatest(int n) {
        return llibreDao.findLatest(n);
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        return llibreDao.findMostRead(n);
    }

    @Override
    public List<Llibre> findBestReview(int n) {
        return llibreDao.findBestReview(n);
    }
}
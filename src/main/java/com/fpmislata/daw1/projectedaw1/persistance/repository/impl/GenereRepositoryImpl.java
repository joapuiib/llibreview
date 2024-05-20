package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreGenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.GenereRepository;

import java.util.List;

public class GenereRepositoryImpl implements GenereRepository {
    private final GenereDao genereDao;
    private final LlibreGenereDao llibreGenereDao;

    public GenereRepositoryImpl(GenereDao genereDao, LlibreGenereDao llibreGenereDao) {
        this.genereDao = genereDao;
        this.llibreGenereDao = llibreGenereDao;
    }

    @Override
    public Genere findById(int id) {
        return genereDao.findById(id);
    }

    @Override
    public List<Genere> findAll() {
        return genereDao.findAll();
    }

    @Override
    public List<Genere> findByLlibreIsbn(String isbn) {
        return llibreGenereDao.findGeneresByLlibreIsbn(isbn);
    }
}
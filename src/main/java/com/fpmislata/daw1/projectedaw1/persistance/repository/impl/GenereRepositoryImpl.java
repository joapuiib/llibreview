package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.GenereDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.GenereRepository;

public class GenereRepositoryImpl implements GenereRepository {
    private final GenereDao genereDao;

    public GenereRepositoryImpl(GenereDao genereDao) {
        this.genereDao = genereDao;
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
        return genereDao.findGeneresByLlibreIsbn(isbn);
    }
}
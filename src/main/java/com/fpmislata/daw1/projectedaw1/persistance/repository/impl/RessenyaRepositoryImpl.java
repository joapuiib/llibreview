package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;

public class RessenyaRepositoryImpl implements RessenyaRepository {
    private final RessenyaDao ressenyaDao;

    public RessenyaRepositoryImpl(RessenyaDao ressenyaDao) {
        this.ressenyaDao = ressenyaDao;
    }


    @Override
    public Ressenya findByIsbnUsername(String isbn, String username) {
        return ressenyaDao.findByLlibreIsbnAndUsername(isbn, username);
    }

    @Override
    public void save(Ressenya ressenya) {
        ressenyaDao.save(ressenya);
    }

    @Override
    public void delete(String isbn, String username) {
        ressenyaDao.delete(isbn, username);
    }
}
package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
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
    public boolean exists(String isbn, String username) {
        return ressenyaDao.findByLlibreIsbnAndUsername(isbn, username) != null;
    }

    @Override
    public boolean save(Ressenya ressenya) {
        if (this.exists(ressenya.getIsbn(), ressenya.getUsername())) {
            return ressenyaDao.update(ressenya) == 1;
        } else {
            return ressenyaDao.insert(ressenya) == 1;
        }
    }

    @Override
    public boolean delete(String isbn, String username) {
        return ressenyaDao.delete(isbn, username) == 1;
    }
}
package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;

public class RessenyaRepositoryImpl implements RessenyaRepository {
    private final RessenyaDao ressenyaDao;

    public RessenyaRepositoryImpl(RessenyaDao ressenyaDao) {
        this.ressenyaDao = ressenyaDao;
    }


    @Override
    public Ressenya findByLlibreIsbnAndUsername(String isbn, String username) {
        return ressenyaDao.findByLlibreIsbnAndUsername(isbn, username);
    }

    @Override
    public List<Ressenya> findByLlibreIsbn(String isbn) {
        return ressenyaDao.findByLlibreIsbn(isbn);
    }

    @Override
    public List<Ressenya> findByUsername(String username) {
        return ressenyaDao.findByUsername(username);
    }

    @Override
    public int countByUsername(String username) {
        return ressenyaDao.countByUsername(username);
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
    public boolean delete(Ressenya ressenya) {
        String isbn = ressenya.getIsbn();
        String username = ressenya.getUsername();
        return ressenyaDao.delete(isbn, username) == 1;
    }
}
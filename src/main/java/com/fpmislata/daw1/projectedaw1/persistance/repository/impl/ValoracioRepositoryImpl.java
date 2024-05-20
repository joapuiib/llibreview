package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;

import java.util.List;

public class ValoracioRepositoryImpl implements ValoracioRepository {
    private final ValoracioDao valoracioDao;

    public ValoracioRepositoryImpl(ValoracioDao valoracioDao) {
        this.valoracioDao = valoracioDao;
    }
    @Override
    public Valoracio findByLlibreIsbnAndUsername(String isbn, String username) {
        return valoracioDao.findByLlibreIsbnAndUsername(isbn, username);
    }

    @Override
    public List<Valoracio> findByLlibreIsbn(String isbn) {
        return valoracioDao.findByLlibreIsbn(isbn);
    }

    @Override
    public List<Valoracio> findByUsername(String username) {
        return valoracioDao.findByUsername(username);
    }

    @Override
    public void save(Valoracio valoracio) {
        valoracioDao.save(valoracio);
    }

    @Override
    public void delete(String isbn, String username) {
        valoracioDao.delete(isbn, username);
    }
}
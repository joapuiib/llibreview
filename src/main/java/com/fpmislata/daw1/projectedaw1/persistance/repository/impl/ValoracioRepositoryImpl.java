package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RessenyaDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ValoracioDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;

import java.util.List;

public class ValoracioRepositoryImpl implements ValoracioRepository {
    private final ValoracioDao valoracioDao;
    private final RessenyaDao ressenyaDao;

    public ValoracioRepositoryImpl(ValoracioDao valoracioDao, RessenyaDao ressenyaDao) {
        this.valoracioDao = valoracioDao;
        this.ressenyaDao = ressenyaDao;
    }
    @Override
    public Valoracio findByLlibreIsbnAndUsername(String isbn, String username) {
        Valoracio valoracio = valoracioDao.findByLlibreIsbnAndUsername(isbn, username);
        Ressenya ressenya = ressenyaDao.findByLlibreIsbnAndUsername(isbn, username);
        valoracio.setRessenya(ressenya);
        return valoracio;
    }

    @Override
    public List<Valoracio> findByIsbn(String isbn) {
        List<Valoracio> valoracios = valoracioDao.findByLlibreIsbn(isbn);
        for (Valoracio valoracio : valoracios) {
            Ressenya r = ressenyaDao.findByLlibreIsbnAndUsername(isbn, valoracio.getUsername());
            valoracio.setRessenya(r);
        }
        return valoracios;
    }

    @Override
    public List<Valoracio> findByUsername(String username) {
        return valoracioDao.findByUsername(username);
    }

    @Override
    public boolean exists(String isbn, String username) {
        return this.findByLlibreIsbnAndUsername(isbn, username) != null;
    }

    @Override
    public boolean save(Valoracio valoracio) {
        if (this.exists(valoracio.getIsbn(), valoracio.getUsername())) {
            return valoracioDao.update(valoracio) == 1;
        } else {
            return valoracioDao.insert(valoracio) == 1;
        }
    }

    @Override
    public boolean delete(String isbn, String username) {
        return valoracioDao.delete(isbn, username) == 1;
    }
}
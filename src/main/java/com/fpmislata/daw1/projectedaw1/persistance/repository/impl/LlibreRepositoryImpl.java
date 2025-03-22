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
    public Llibre findByIsbn(String isbn) {
        return llibreDao.findByIsbn(isbn);
    }

    @Override
    public List<Llibre> findAll() {
        return llibreDao.findAll();
    }

    @Override
    public List<Llibre> findByAutorId(int idAutor) {
        return llibreDao.findLlibresByAutorId(idAutor);
    }

    @Override
    public List<Llibre> findByGenereId(int idGenere) {
        return llibreDao.findLlibresByGenereId(idGenere);
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
    public List<Llibre> findBestRated(int n) {
        return llibreDao.findBestRated(n);
    }
}
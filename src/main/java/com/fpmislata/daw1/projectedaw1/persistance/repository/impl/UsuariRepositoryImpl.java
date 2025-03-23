package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.persistance.dao.UsuariDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UsuariRepository;

public class UsuariRepositoryImpl implements UsuariRepository {
    private final UsuariDao usuariDao;

    public UsuariRepositoryImpl(UsuariDao usuariDao) {
        this.usuariDao = usuariDao;
    }

    @Override
    public Usuari findByUsername(String username) {
        return usuariDao.findByUsername(username);
    }

    @Override
    public Usuari findByEmail(String email) {
        return usuariDao.findByEmail(email);
    }

    @Override
    public boolean create(Usuari usuari, String password) {
        return usuariDao.insert(usuari, password) == 1;
    }

    @Override
    public boolean login(String username, String password) {
        return usuariDao.login(username, password);
    }
}
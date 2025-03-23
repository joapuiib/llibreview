package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

public interface UsuariDao {
    Usuari findByUsername(String username);
    Usuari findByEmail(String email);
    int insert(Usuari usuari, String passwordHash);
}

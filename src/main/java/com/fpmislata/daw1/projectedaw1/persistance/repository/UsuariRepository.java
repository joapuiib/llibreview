package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

public interface UsuariRepository {
    Usuari findByUsername(String username);
    Usuari findByEmail(String email);
    boolean create(Usuari usuari, String password);
}

package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

public interface UsuariRepository {
    Usuari findByUsername(String username);
    Usuari findByEmail(String email);
    void create(Usuari usuari, String password);
    boolean login(String username, String password);
}

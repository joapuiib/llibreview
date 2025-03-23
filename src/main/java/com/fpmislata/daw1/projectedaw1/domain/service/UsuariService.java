package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

public interface UsuariService {
    Usuari findByUsername(String username);
    Usuari findByEmail(String email);
    void create(Usuari user, String password, String passwordConfirmation);
    boolean login(String username, String password);
}

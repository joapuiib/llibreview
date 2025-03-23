package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.service.UsuariService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.UsuariRepository;
import com.fpmislata.daw1.projectedaw1.security.UserSession;

import java.time.LocalDate;

public class UsuariServiceImpl implements UsuariService {
    private final UsuariRepository usuariRepository;

    public UsuariServiceImpl(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @Override
    public Usuari findByUsername(String username) {
        return usuariRepository.findByUsername(username);
    }

    @Override
    public Usuari findByEmail(String email) {
        return usuariRepository.findByEmail(email);
    }

    @Override
    public void create(Usuari usuari, String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new RuntimeException("Les contrasenyes no coincideixen.");
        } else if (findByUsername(usuari.getUsername()) != null) {
            throw new RuntimeException("Ja hi ha un usuari amb aquest nom d'usuari.");
        } else if (findByEmail(usuari.getEmail()) != null) {
            throw new RuntimeException("Ja hi ha un usuari associat a aquest correu electrònic.");
        }

        usuari.setDataRegistre(LocalDate.now());
        usuariRepository.create(usuari, password);
    }

    @Override
    public boolean login(String username, String password) {
        boolean logged = usuariRepository.login(username, password);
        if (logged){
            Usuari currentUsuari = usuariRepository.findByUsername(username);
            UserSession.setUser(currentUsuari);
        }
        return logged;
    }

}

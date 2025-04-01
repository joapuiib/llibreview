package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;

import java.util.List;

public class ValoracioServiceImpl implements ValoracioService {
    private final ValoracioRepository valoracioRepository;

    public ValoracioServiceImpl(ValoracioRepository valoracioRepository) {
        this.valoracioRepository = valoracioRepository;
    }

    @Override
    public Valoracio findByLlibreAndUsuari(Llibre llibre, Usuari usuari) {
        String isbn = llibre.getIsbn();
        String username = usuari.getUsername();

        return valoracioRepository.findByLlibreIsbnAndUsername(
                isbn,
                username
        );
    }

    @Override
    public List<Valoracio> findByLlibre(Llibre llibre) {
        String isbn = llibre.getIsbn();
        return valoracioRepository.findByIsbn(isbn);
    }

    @Override
    public double getMitjanaByLlibre(Llibre llibre) {
        String isbn = llibre.getIsbn();
        return valoracioRepository.getMitjanaByLlibreIsbn(isbn);
    }

    @Override
    public List<Valoracio> findByUsuari(Usuari usuari) {
        String username = usuari.getUsername();
        return valoracioRepository.findByUsername(username);
    }

    @Override
    public void save(Valoracio valoracio) {
        valoracioRepository.save(valoracio);
    }

    @Override
    public void delete(String isbn, String username) {
        valoracioRepository.delete(isbn, username);
    }
}

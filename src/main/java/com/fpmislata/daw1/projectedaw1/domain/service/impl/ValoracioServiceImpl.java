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
    public Valoracio findByLlibreAndUser(Llibre llibre, Usuari usuari) {
        return valoracioRepository.findByIsbnAndUsername(llibre.getIsbn(), usuari.getUsername());
    }

    @Override
    public List<Valoracio> findByLlibre(Llibre llibre) {
        return valoracioRepository.findByIsbn(llibre.getIsbn());
    }

    @Override
    public List<Valoracio> findByUser(Usuari usuari) {
        return valoracioRepository.findByUsername(usuari.getUsername());
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

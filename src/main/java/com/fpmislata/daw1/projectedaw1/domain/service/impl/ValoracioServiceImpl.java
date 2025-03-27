package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import java.util.List;
import java.util.Objects;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.ValoracioService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ValoracioRepository;

public class ValoracioServiceImpl implements ValoracioService {
    private final ValoracioRepository valoracioRepository;
    private final RessenyaRepository ressenyaRepository;

    public ValoracioServiceImpl(
            ValoracioRepository valoracioRepository,
            RessenyaRepository ressenyaRepository)
    {
        this.valoracioRepository = valoracioRepository;
        this.ressenyaRepository = ressenyaRepository;
    }

    @Override
    public Valoracio findByLlibreAndUser(Llibre llibre, Usuari usuari) {
        String isbn = llibre.getIsbn();
        String username = usuari.getUsername();
        Valoracio valoracio = valoracioRepository.findByLlibreIsbnAndUsername(
                isbn,
                username
        );
        if (valoracio != null)
            this.setRessenya(valoracio);

        return valoracio;
    }

    private void setRessenya(Valoracio valoracio) {
        String isbn = valoracio.getIsbn();
        String username = valoracio.getUsername();
        Ressenya r = ressenyaRepository.findByLlibreIsbnAndUsername(isbn, username);
        valoracio.setRessenya(r);
    }

    @Override
    public List<Valoracio> findByLlibre(Llibre llibre) {
        String isbn = llibre.getIsbn();
        List<Valoracio> valoracions = valoracioRepository.findByIsbn(isbn);
        valoracions.stream().filter(Objects::nonNull).forEach(this::setRessenya);
        return valoracions;
    }

    @Override
    public List<Valoracio> findByUser(Usuari usuari) {
        String username = usuari.getUsername();
        List<Valoracio> valoracions = valoracioRepository.findByUsername(username);
        valoracions.stream().filter(Objects::nonNull).forEach(this::setRessenya);
        return valoracions;
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

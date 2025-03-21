package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;
import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;

public class RessenyaServiceImpl implements RessenyaService {
    private final RessenyaRepository ressenyaRepository;

    public RessenyaServiceImpl(RessenyaRepository ressenyaRepository) {
        this.ressenyaRepository = ressenyaRepository;
    }

    @Override
    public Ressenya findByValoracio(Valoracio valoracio) {
        String isbn = valoracio.getIsbn();
        String username = valoracio.getUsername();
        return ressenyaRepository.findByIsbnUsername(isbn, username);
    }

    @Override
    public void save(Ressenya ressenya) {
        ressenyaRepository.save(ressenya);
    }

    @Override
    public void delete(String isbn, String username) {
        ressenyaRepository.delete(isbn, username);
    }
}

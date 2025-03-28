package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;
import com.fpmislata.daw1.projectedaw1.domain.service.RessenyaService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RessenyaRepository;

public class RessenyaServiceImpl implements RessenyaService {
    private final RessenyaRepository ressenyaRepository;

    public RessenyaServiceImpl(RessenyaRepository ressenyaRepository) {
        this.ressenyaRepository = ressenyaRepository;
    }

    @Override
    public Ressenya findByLlibreAndUsuari(Llibre llibre, Usuari usuari) {
        String isbn = llibre.getIsbn();
        String username = usuari.getUsername();
        return ressenyaRepository.findByLlibreIsbnAndUsername(isbn, username);
    }

    @Override
    public List<Ressenya> findByLlibre(Llibre llibre) {
        String isbn = llibre.getIsbn();
        return ressenyaRepository.findByLlibreIsbn(isbn);
    }

    @Override
    public List<Ressenya> findByUsuari(Usuari usuari) {
        String username = usuari.getUsername();
        return ressenyaRepository.findByUsername(username);
    }

    @Override
    public int countByUsuari(Usuari usuari) {
        String username = usuari.getUsername();
        return ressenyaRepository.countByUsername(username);
    }

    @Override
    public boolean save(Ressenya ressenya) {
        return ressenyaRepository.save(ressenya);
    }

    @Override
    public boolean delete(Ressenya ressenya) {
        return ressenyaRepository.delete(ressenya);
    }
}

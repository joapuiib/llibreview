package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.GenereService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.GenereRepository;

import java.util.List;

public class GenereServiceImpl implements GenereService {
    private final GenereRepository genereRepository;

    public GenereServiceImpl(GenereRepository genereRepository) {
        this.genereRepository = genereRepository;
    }

    @Override
    public Genere findById(int id) {
        return genereRepository.findById(id);
    }

    @Override
    public List<Genere> findAll() {
        return genereRepository.findAll();
    }

    @Override
    public List<Genere> findByLlibre(Llibre llibre) {
        String isbn = llibre.getIsbn();
        return genereRepository.findByLlibreIsbn(isbn);
    }
}

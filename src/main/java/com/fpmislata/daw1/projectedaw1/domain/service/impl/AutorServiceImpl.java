package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.AutorService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.AutorRepository;

public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public Autor findById(int id) {
        return this.autorRepository.findById(id);
    }

    @Override
    public List<Autor> findAll() {
        return this.autorRepository.findAll();
    }

    @Override
    public List<Autor> findByLlibre(Llibre llibre) {
        return this.autorRepository.findByLlibreIsbn(llibre.getIsbn());
    }
}

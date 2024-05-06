package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.service.LlibreService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;

import java.util.List;

public class LlibreServiceImpl implements LlibreService {
    private final LlibreRepository llibreRepository;

    public LlibreServiceImpl(LlibreRepository llibreRepository) {
        this.llibreRepository = llibreRepository;
    }

    @Override
    public Llibre findByIsbn(String isbn) {
        return llibreRepository.findByIsbn(isbn);
    }

    public List<Llibre> findAll() {
        return llibreRepository.findAll();
    }

    @Override
    public List<Llibre> findByAutor(Autor autor) {
        return llibreRepository.findByAutorId(autor.getId());
    }

    @Override
    public List<Llibre> findByGenere(Genere genere) {
        return llibreRepository.findByGenereId(genere.getId());
    }

    public List<Llibre> findLatest(int n) {
        return llibreRepository.findLatest(n);
    }

    public List<Llibre> findMostRead(int n) {
        return llibreRepository.findMostRead(n);
    }

    public List<Llibre> findBestReview(int n) {
        return llibreRepository.findBestReview(n);
    }
}

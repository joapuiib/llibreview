package com.fpmislata.daw1.projectedaw1.domain.service;

import java.util.List;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

public interface LlibreService {
    Llibre findByIsbn(String isbn);
    List<Llibre> findAll();
    List<Llibre> findLatest(int n);
    List<Llibre> findByAutor(Autor autor);
    List<Llibre> findByGenere(Genere genere);
    List<Llibre> findMostRead(int n);
    List<Llibre> findBestRated(int n);
}

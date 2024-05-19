package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreRepository {

    Llibre findByIsbn(String isbn);
    List<Llibre> findAll();
    List<Llibre> findByAutorId(int idAutor);
    List<Llibre> findByGenereId(int idGenere);
    List<Llibre> findLatest(int n);
    List<Llibre> findMostRead(int n);
    List<Llibre> findBestRating(int n);
}

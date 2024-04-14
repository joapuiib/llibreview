package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface EscriuDao {
    List<Autor> findAutorsByIsbn(String isbn);
    List<Llibre> findLlibresByAutor(int idAutor);
}

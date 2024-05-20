package com.fpmislata.daw1.projectedaw1.persistance.dao;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.util.List;

public interface LlibreGenereDao {
    List<Llibre> findLlibresByGenereId(int id);
    List<Genere> findGeneresByLlibreIsbn(String isbn);
}

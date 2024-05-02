package com.fpmislata.daw1.projectedaw1.mock.persistance.dao;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.EscriuDao;

import java.util.List;

public class EscriuDaoMock implements EscriuDao {

    private final List<Llibre> llibreList = LlibreData.llibreList;
    @Override
    public List<Autor> findAutorsByIsbn(String isbn) {
        return null;
    }

    @Override
    public List<Llibre> findLlibresByAutor(int idAutor) {
        if (idAutor == 1) {
            return List.of(llibreList.get(1), llibreList.get(2));
        } else if (idAutor == 2) {
            return List.of(llibreList.get(2));
        } else {
            return List.of();
        }
    }
}

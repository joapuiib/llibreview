package com.fpmislata.daw1.projectedaw1.mock.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;

import java.util.List;

public class LlibreRepositoryMock implements LlibreRepository {

    private final List<Llibre> llibreList = LlibreData.LLIBRE_LIST;

    @Override
    public Llibre findByIsbn(String isbn) {
        if(isbn.equals("isbn1")) {
            return llibreList.get(0);
        } else if(isbn.equals("isbn2")) {
            return llibreList.get(1);
        } else {
            return null;
        }
    }

    @Override
    public List<Llibre> findAll() {
        return llibreList;
    }

    @Override
    public List<Llibre> findLatest(int n) {
        if (n == 1) {
            return List.of(llibreList.get(5));
        } else if (n == 2) {
            return List.of(llibreList.get(5), llibreList.get(4));
        }
        return List.of();
    }

    @Override
    public List<Llibre> findByAutorId(int idAutor) {
        if (idAutor == 1) {
            return List.of(llibreList.get(1), llibreList.get(2));
        } else if (idAutor == 2) {
            return List.of(llibreList.get(2));
        }
        return List.of();
    }

    @Override
    public List<Llibre> findByGenereId(int idGenere) {
        return List.of();
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        return null;
    }

    @Override
    public List<Llibre> findBestRated(int n) {
        return null;
    }
}

package com.fpmislata.daw1.projectedaw1.mock.persistance.repository;

import com.fpmislata.daw1.projectedaw1.data.LlibreData;
import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.repository.LlibreRepository;

import java.util.List;

public class LlibreRepositoryMock implements LlibreRepository {

    private final List<Llibre> llibreList = LlibreData.llibreList;

    @Override
    public Llibre findByIsbn(String isbn) {
        return llibreList.stream()
                .filter(llibre -> llibre.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Llibre> findAll() {
        return llibreList;
    }

    @Override
    public List<Llibre> findByAutorId(int idAutor) {
        return null;
    }

    @Override
    public List<Llibre> findLatest(int n) {
        return llibreList.stream()
                .sorted((l1, l2) -> l2.getDataPublicacio().compareTo(l1.getDataPublicacio()))
                .limit(n)
                .toList();
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        return null;
    }

    @Override
    public List<Llibre> findBestReview(int n) {
        return null;
    }
}

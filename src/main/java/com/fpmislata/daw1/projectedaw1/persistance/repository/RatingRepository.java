package com.fpmislata.daw1.projectedaw1.persistance.repository;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;

import java.util.List;

public interface RatingRepository {
    Rating findByLlibreIsbnAndUsername(String isbn, String username);
    List<Rating> findByLlibreIsbn(String isbn);
    List<Rating> findByUsername(String username);
}

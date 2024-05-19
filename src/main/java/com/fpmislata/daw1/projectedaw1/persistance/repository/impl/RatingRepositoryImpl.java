package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RatingDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RatingRepository;

import java.util.List;

public class RatingRepositoryImpl implements RatingRepository {
    private final RatingDao ratingDao;

    public RatingRepositoryImpl(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }
    @Override
    public Rating findByLlibreIsbnAndUsername(String isbn, String username) {
        return ratingDao.findByLlibreIsbnAndUsername(isbn, username);
    }

    @Override
    public List<Rating> findByLlibreIsbn(String isbn) {
        return ratingDao.findByLlibreIsbn(isbn);
    }

    @Override
    public List<Rating> findByUsername(String username) {
        return ratingDao.findByUsername(username);
    }
}
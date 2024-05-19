package com.fpmislata.daw1.projectedaw1.persistance.repository.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Review;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ReviewDao;
import com.fpmislata.daw1.projectedaw1.persistance.repository.ReviewRepository;

import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewDao reviewDao;

    public ReviewRepositoryImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
    @Override
    public Review findByLlibreIsbnAndUsername(String isbn, String username) {
        return reviewDao.findByLlibreIsbnAndUsername(isbn, username);
    }

    @Override
    public List<Review> findByLlibreIsbn(String isbn) {
        return reviewDao.findByLlibreIsbn(isbn);
    }

    @Override
    public List<Review> findByUsername(String username) {
        return reviewDao.findByUsername(username);
    }
}
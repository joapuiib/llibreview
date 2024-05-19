package com.fpmislata.daw1.projectedaw1.domain.service.impl;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.domain.service.RatingService;
import com.fpmislata.daw1.projectedaw1.persistance.repository.RatingRepository;

import java.util.List;

public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating findByLlibreAndUser(Llibre llibre, User user) {
        return ratingRepository.findByLlibreIsbnAndUsername(llibre.getIsbn(), user.getUsername());
    }

    @Override
    public List<Rating> findByLlibre(Llibre llibre) {
        return ratingRepository.findByLlibreIsbn(llibre.getIsbn());
    }

    @Override
    public List<Rating> findByUser(User user) {
        return ratingRepository.findByUsername(user.getUsername());
    }
}

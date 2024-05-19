package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;

import java.util.List;

public interface RatingService {
    Rating findByLlibreAndUser(Llibre llibre, User user);
    List<Rating> findByLlibre(Llibre llibre);
    List<Rating> findByUser(User user);
}

package com.fpmislata.daw1.projectedaw1.domain.service;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.domain.entity.Review;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;

import java.util.List;

public interface ReviewService {
    Review findByLlibreAndUser(Llibre llibre, User user);
    List<Review> findByLlibre(Llibre llibre);
    List<Review> findByUser(User user);
}

package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Review;
import com.fpmislata.daw1.projectedaw1.persistance.dao.ReviewDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.ReviewTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.ReviewMapper;

import java.util.List;

public class ReviewDaoMemory implements ReviewDao {

    private final ReviewTableMemory reviewTableMemory;
    private final ReviewMapper reviewMapper = new ReviewMapper();

    public ReviewDaoMemory(ReviewTableMemory reviewTableMemory) {
        this.reviewTableMemory = reviewTableMemory;
    }

    @Override
    public Review findByLlibreIsbnAndUsername(String isbn, String username) {
        return reviewTableMemory.get().stream()
                .filter(reviewRecord -> reviewRecord.getIsbn().equals(isbn) && reviewRecord.getUsername().equals(username))
                .map(reviewMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Review> findByLlibreIsbn(String isbn) {
        return reviewTableMemory.get().stream()
                .filter(reviewRecord -> reviewRecord.getIsbn().equals(isbn))
                .map(reviewMapper::map)
                .toList();
    }

    @Override
    public List<Review> findByUsername(String username) {
        return reviewTableMemory.get().stream()
                .filter(reviewRecord -> reviewRecord.getUsername().equals(username))
                .map(reviewMapper::map)
                .toList();
    }
}

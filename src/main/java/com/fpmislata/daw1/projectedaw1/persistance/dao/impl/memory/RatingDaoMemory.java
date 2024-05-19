package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;
import com.fpmislata.daw1.projectedaw1.persistance.dao.RatingDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.RatingTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.RatingMapper;

import java.util.List;

public class RatingDaoMemory implements RatingDao {

    private final RatingTableMemory ratingTableMemory;
    private final RatingMapper ratingMapper = new RatingMapper();

    public RatingDaoMemory(RatingTableMemory ratingTableMemory) {
        this.ratingTableMemory = ratingTableMemory;
    }

    @Override
    public Rating findByLlibreIsbnAndUsername(String isbn, String username) {
        return ratingTableMemory.get().stream()
                .filter(ratingRecord -> ratingRecord.getIsbn().equals(isbn) && ratingRecord.getUsername().equals(username))
                .map(ratingMapper::map)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Rating> findByLlibreIsbn(String isbn) {
        return ratingTableMemory.get().stream()
                .filter(ratingRecord -> ratingRecord.getIsbn().equals(isbn))
                .map(ratingMapper::map)
                .toList();
    }

    @Override
    public List<Rating> findByUsername(String username) {
        return ratingTableMemory.get().stream()
                .filter(ratingRecord -> ratingRecord.getUsername().equals(username))
                .map(ratingMapper::map)
                .toList();
    }
}

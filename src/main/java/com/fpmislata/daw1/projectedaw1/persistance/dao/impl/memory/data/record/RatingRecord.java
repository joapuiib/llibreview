package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RatingRecord {
    private String isbn;
    private String username;
    private LocalDate ratingDate;
    private int rating;

    public RatingRecord(String isbn, String username, int rating, LocalDate ratingDate) {
        this.isbn = isbn;
        this.username = username;
        this.rating = rating;
        this.ratingDate = ratingDate;
    }

    public RatingRecord() {
    }
}

package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RatingRecord {
    private String isbn;
    private String username;
    private LocalDate date;
    private int rating;

    public RatingRecord(String isbn, String username, int rating, LocalDate date) {
        this.isbn = isbn;
        this.username = username;
        this.rating = rating;
        this.date = date;
    }

    public RatingRecord() {
    }
}

package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewRecord {
    private String isbn;
    private String username;
    private LocalDate reviewDate;
    private int grade;

    public ReviewRecord(String isbn, String username, LocalDate reviewDate, int grade) {
        this.isbn = isbn;
        this.username = username;
        this.reviewDate = reviewDate;
        this.grade = grade;
    }

    public ReviewRecord() {
    }
}

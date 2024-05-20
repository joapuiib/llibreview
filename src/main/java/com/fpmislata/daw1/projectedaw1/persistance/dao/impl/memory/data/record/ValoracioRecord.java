package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ValoracioRecord {
    private String isbn;
    private String username;
    private LocalDate date;
    private int valoracio;

    public ValoracioRecord(String isbn, String username, int valoracio, LocalDate date) {
        this.isbn = isbn;
        this.username = username;
        this.valoracio = valoracio;
        this.date = date;
    }

    public ValoracioRecord() {
    }
}

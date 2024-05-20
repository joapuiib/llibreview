package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscriuRecord {
    private String isbn;
    private int idAutor;

    public EscriuRecord(String isbn, int idAutor) {
        this.isbn = isbn;
        this.idAutor = idAutor;
    }

    public EscriuRecord() {
    }
}

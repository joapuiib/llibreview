package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LlibreGenereRecord {
    private String isbn;
    private int genereId;

    public LlibreGenereRecord(String isbn, int genereId) {
        this.isbn = isbn;
        this.genereId = genereId;
    }

    public LlibreGenereRecord() {
    }
}

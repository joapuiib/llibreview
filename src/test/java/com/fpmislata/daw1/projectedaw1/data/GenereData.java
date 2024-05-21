package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;

import java.util.List;

public class GenereData {
    public static final List<Genere> GENERE_LIST = List.of(
            new Genere( 1, "Genere 1"),
            new Genere( 2, "Genere 2"),
            new Genere( 3, "Genere 3")
    );

    public static final List<GenereRecord> GENERE_RECORD_LIST = List.of(
            new GenereRecord( 1, "Genere 1", "Genre 1"),
            new GenereRecord( 2, "Genere 2", "Genre 2"),
            new GenereRecord( 3, "Genere 3", "Genre 3")
    );
}

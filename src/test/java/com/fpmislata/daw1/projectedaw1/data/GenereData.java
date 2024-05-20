package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.GenereRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;

import java.time.LocalDate;
import java.util.List;

public class GenereData {
    public static final List<Genere> GENERE_LIST = List.of(
            new Genere( 1, "Genere 1", "genere1.png"),
            new Genere( 2, "Genere 2", "genere2.png")
    );

    public static final List<GenereRecord> GENERE_RECORD_LIST = List.of(
            new GenereRecord( 1, "Genere 1", "Genre 1", "genere1.png"),
            new GenereRecord( 2, "Genere 2", "Genre 2", "genere2.png")
    );
}

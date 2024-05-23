package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;

import java.time.LocalDate;
import java.util.List;

public class RessenyaData {
    public static final List<Ressenya> RESSENYA_LIST = List.of(
            new Ressenya("isbn1", "user1", "Ressenya 1", LocalDate.parse("2024-05-02")),
            new Ressenya("isbn2", "user1", "Ressenya 2", LocalDate.parse("2024-05-03"))
    );
}

package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.time.LocalDate;
import java.util.List;

public class RessenyaData {
    private static final List<Valoracio> VALORACIO_LIST = ValoracioData.VALORACIO_LIST;

    public static final List<Ressenya> RESSENYA_LIST = List.of(
            new Ressenya("isbn1", "user1", "Ressenya 1", LocalDate.parse("2024-05-02"), VALORACIO_LIST.getFirst()),
            new Ressenya("isbn2", "user1", "Ressenya 2", LocalDate.parse("2024-05-03"), VALORACIO_LIST.get(1))
    );
}

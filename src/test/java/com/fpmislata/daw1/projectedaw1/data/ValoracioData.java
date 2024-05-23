package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.time.LocalDate;
import java.util.List;

public class ValoracioData {
    public static final List<Valoracio> VALORACIO_LIST = List.of(
            new Valoracio("isbn1", "user1", 7, LocalDate.parse("2024-05-01")),
            new Valoracio("isbn2", "user1", 7, LocalDate.parse("2024-05-01")),
            new Valoracio("isbn2", "user2", 8, LocalDate.parse("2024-05-01"))
    );
}

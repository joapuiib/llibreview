package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.time.LocalDate;
import java.util.List;

public class LlibreData {
    public static final List<Llibre> LLIBRE_LIST = List.of(
            new Llibre( "isbn1", "Llibre 1", "Resum 1", LocalDate.parse("2024-01-01"), 100, "imatge1.png" ),
            new Llibre( "isbn2", "Llibre 2", "Resum 2", LocalDate.parse("2024-01-02"), 200, "imatge2.png" ),
            new Llibre( "isbn3", "Llibre 3", "Resum 3", LocalDate.parse("2024-01-03"), 300, "imatge3.png" ),
            new Llibre( "isbn4", "Llibre 4", "Resum 4", LocalDate.parse("2024-01-04"), 400, "imatge4.png" ),
            new Llibre( "isbn5", "Llibre 5", "Resum 5", LocalDate.parse("2024-01-05"), 500, "imatge5.png" ),
            new Llibre( "isbn6", "Llibre 6", "Resum 6", LocalDate.parse("2024-01-06"), 600, "imatge6.png" )
    );
}

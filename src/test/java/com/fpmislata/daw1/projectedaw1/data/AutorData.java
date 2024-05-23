package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

import java.time.LocalDate;
import java.util.List;

public class AutorData {
    public static final List<Autor> AUTOR_LIST = List.of(
            new Autor(1, "Autor 1", "Biografia 1", LocalDate.parse("2000-01-01"), "imatge1.png"),
            new Autor(2, "Autor 2", "Biografia 2", LocalDate.parse("2000-01-02"), "imatge2.png"),
            new Autor(3, "Autor 3", "Biografia 3", LocalDate.parse("2000-01-03"), "imatge3.png")
    );
}

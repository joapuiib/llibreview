package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutorRecord {
    private int id;
    private String nom;
    private String biografia;
    private LocalDate dataNaixement;

    private String rutaImatge;

    public AutorRecord(int id, String nom, String biografia, LocalDate dataNaixement, String rutaImatge) {
        this.id = id;
        this.nom = nom;
        this.biografia = biografia;
        this.dataNaixement = dataNaixement;
        this.rutaImatge = rutaImatge;
    }

    public AutorRecord() {
    }
}

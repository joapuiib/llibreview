package com.fpmislata.daw1.projectedaw1.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class Autor {
    private int id;
    private String nom;
    private String biografia;
    private LocalDate dataNaixement;
    private String rutaImatge;

    public Autor(int id, String nom, String biografia, LocalDate dataNaixement, String rutaImatge) {
        this.id = id;
        this.nom = nom;
        this.biografia = biografia;
        this.dataNaixement = dataNaixement;
        this.rutaImatge = rutaImatge;
    }

    public Autor() {
    }

    public String getPrettyDataNaixement() {
        return dataNaixement.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}

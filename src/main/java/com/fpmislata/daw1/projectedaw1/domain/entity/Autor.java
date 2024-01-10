package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.Date;

public class Autor {
    private String nom;
    private Date dataNaixement;

    public Autor(String nom, Date dataNaixement) {
        this.nom = nom;
        this.dataNaixement = dataNaixement;
    }

    public String getNom() {
        return nom;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }
}

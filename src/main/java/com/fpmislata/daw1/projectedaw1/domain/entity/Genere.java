package com.fpmislata.daw1.projectedaw1.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Genere {
    private int id;
    private String nom;
    private String rutaImatge;

    public Genere(int id, String nom, String rutaImatge) {
        this.id = id;
        this.nom = nom;
        this.rutaImatge = rutaImatge;
    }

    public Genere() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genere genere = (Genere) o;
        return id == genere.id && Objects.equals(nom, genere.nom) && Objects.equals(rutaImatge, genere.rutaImatge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, rutaImatge);
    }
}

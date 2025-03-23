package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id
                && Objects.equals(nom, autor.nom)
                && Objects.equals(biografia, autor.biografia)
                && Objects.equals(dataNaixement, autor.dataNaixement)
                && Objects.equals(rutaImatge, autor.rutaImatge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, biografia, dataNaixement, rutaImatge);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", biografia='" + biografia + '\'' +
                ", dataNaixement=" + dataNaixement +
                ", rutaImatge='" + rutaImatge + '\'' +
                '}';
    }
}

package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Autor {
    private int id;
    private String nom;
    private String biografia;
    private LocalDate dataNaixement;
    private String rutaImatge;

    private List<Llibre> llibres;

    public Autor(int id, String nom, String biografia, LocalDate dataNaixement, String rutaImatge) {
        this.id = id;
        this.nom = nom;
        this.biografia = biografia;
        this.dataNaixement = dataNaixement;
        this.rutaImatge = rutaImatge;
    }

    public Autor() {
    }

    public List<Llibre> getLlibres() {
        llibres = LlibreIoc.createService().findByAutor(this);
        return llibres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id && Objects.equals(nom, autor.nom) && Objects.equals(biografia, autor.biografia) && Objects.equals(dataNaixement, autor.dataNaixement) && Objects.equals(rutaImatge, autor.rutaImatge);
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

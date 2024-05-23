package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Genere {
    private int id;
    private String nom;

    private List<Llibre> llibres;

    public Genere(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Genere() {
    }

    public List<Llibre> getLlibres() {
        llibres = LlibreIoc.createService().findByGenere(this);
        return llibres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genere genere = (Genere) o;
        return id == genere.id && Objects.equals(nom, genere.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
}

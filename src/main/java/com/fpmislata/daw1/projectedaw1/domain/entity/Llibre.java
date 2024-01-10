package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Llibre {
    private String isbn;
    private String titol;

    private List<Autor> autors;
    private List<Genere> generes;

    public Llibre(String isbn, String titol) {
        this.isbn = isbn;
        this.titol = titol;
        autors = new ArrayList<>();
        generes = new ArrayList<>();
    }

    public void addAutor(Autor autor) {
        autors.add(autor);
    }

    public void addGenere(Genere genere) {
        generes.add(genere);
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitol() {
        return titol;
    }

    public List<Autor> getAutors() {
        return autors;
    }
}

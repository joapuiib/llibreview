package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Llibre {
    private String isbn;
    private String titol;
    private String resum;
    private LocalDate dataPublicacio;
    private int nombrePagines;

    private List<Autor> autors;
    private List<Genere> generes;

    public Llibre() {
        autors = new ArrayList<>();
        generes = new ArrayList<>();
    }

    public Llibre(String isbn, String titol){
        this();
        this.isbn = isbn;
        this.titol = titol;
    }

    public Llibre(String isbn, String titol, String resum, LocalDate dataPublicacio, int nombrePagines) {
        this();
        this.isbn = isbn;
        this.titol = titol;
        this.resum = resum;
        this.dataPublicacio = dataPublicacio;
        this.nombrePagines = nombrePagines;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }


    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getResum() {
        return resum;
    }

    public void setResum(String resum) {
        this.resum = resum;
    }

    public LocalDate getDataPublicacio() {
        return dataPublicacio;
    }

    public void setDataPublicacio(LocalDate dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
    }

    public int getNombrePagines() {
        return nombrePagines;
    }

    public void setNombrePagines(int nombrePagines) {
        this.nombrePagines = nombrePagines;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void addAutor(Autor autor) {
        autors.add(autor);
    }

    public List<Genere> getGeneres() {
        return generes;
    }

    public void addGenere(Genere genere) {
        generes.add(genere);
    }

    @Override
    public String toString() {
        return "Llibre{" +
                "isbn='" + isbn + '\'' +
                ", titol='" + titol + '\'' +
                ", data_publicacio='" + dataPublicacio + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Llibre llibre = (Llibre) o;
        return nombrePagines == llibre.nombrePagines
                && Objects.equals(isbn, llibre.isbn)
                && Objects.equals(titol, llibre.titol)
                && Objects.equals(resum, llibre.resum)
                && Objects.equals(dataPublicacio, llibre.dataPublicacio)
                && Objects.equals(autors, llibre.autors)
                && Objects.equals(generes, llibre.generes);
    }
}

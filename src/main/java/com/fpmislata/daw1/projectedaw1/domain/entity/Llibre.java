package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Llibre {
    private String isbn;
    private String titol;
    private String resum;
    private Date dataPublicacio;

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

    public Date getDataPublicacio() {
        return dataPublicacio;
    }

    public void setDataPublicacio(Date dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
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
}

package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.AutorIoc;
import com.fpmislata.daw1.projectedaw1.common.container.GenereIoc;
import com.fpmislata.daw1.projectedaw1.common.container.ValoracioIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Llibre {
    private String isbn;
    private String titol;
    private String resum;
    private LocalDate dataPublicacio;
    private int nombrePagines;
    private String rutaImatge;

    private List<Autor> autors;
    private List<Genere> generes;
    private List<Valoracio> valoracios;

    public Llibre() {
    }

    public Llibre(String isbn, String titol){
        this.isbn = isbn;
        this.titol = titol;
    }

    public Llibre(String isbn, String titol, String resum, LocalDate dataPublicacio, int nombrePagines, String rutaImatge) {
        this.isbn = isbn;
        this.titol = titol;
        this.resum = resum;
        this.dataPublicacio = dataPublicacio;
        this.nombrePagines = nombrePagines;
        this.rutaImatge = rutaImatge;
    }

    public List<Autor> getAutors() {
        if (autors == null)
            autors =  AutorIoc.createService().findByLlibre(this);
        return autors;
    }

    public void addAutor(Autor autor) {
        autors.add(autor);
    }

    public List<Genere> getGeneres() {
        if (generes == null)
            generes = GenereIoc.createService().findByLlibre(this);
        return generes;
    }

    public void addGenere(Genere genere) {
        generes.add(genere);
    }

    public List<Valoracio> getValoracios() {
        if (valoracios == null)
            valoracios = ValoracioIoc.createService().findByLlibre(this);
        return valoracios;
    }

    public int getNombreValoracios() {
        return getValoracios().size();
    }

    public double getAverageValoracio() {
        return getValoracios().stream().mapToDouble(Valoracio::getValoracio).average().orElse(0);
    }

    public int getRoundedAverageValoracio() {
        return (int) Math.round(getAverageValoracio());
    }

    public Valoracio getValoracioFromUser(Usuari usuari){
        return ValoracioIoc.createService().findByLlibreAndUser(this, usuari);
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

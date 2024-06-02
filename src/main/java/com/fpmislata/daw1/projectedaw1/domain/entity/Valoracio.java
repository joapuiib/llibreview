package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.RessenyaIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UsuariIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Valoracio {
    private String isbn;
    private String username;
    private LocalDate data;
    private int puntuacio;

    private Llibre llibre;
    private Usuari usuari;
    private Ressenya ressenya;

    public Valoracio(String isbn, String username, int puntuacio, LocalDate data) {
        this.isbn = isbn;
        this.username = username;
        this.puntuacio = puntuacio;
        this.data = data;
    }

    public Valoracio(Llibre llibre, Usuari usuari, int puntuacio, LocalDate data) {
        this.isbn = llibre.getIsbn();
        this.username = usuari.getUsername();
        this.puntuacio = puntuacio;
        this.data = data;
    }

    public Valoracio() {
    }

    public Llibre getLlibre(){
        if (llibre == null)
            llibre =  LlibreIoc.createService().findByIsbn(isbn);
        return llibre;
    }

    public Usuari getUsuari(){
        if (usuari == null)
            usuari =  UsuariIoc.getUserService().findByUsername(username);
        return usuari;
    }

    public Ressenya getRessenya(){
        if (ressenya == null)
            ressenya = RessenyaIoc.createService().findByValoracio(this);
        return ressenya;
    }

    @Override
    public String toString() {
        return "Valoracio{" +
                "grade=" + puntuacio +
                ", valoracioDate=" + data +
                ", userame='" + username + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valoracio valoracio1 = (Valoracio) o;
        return puntuacio == valoracio1.puntuacio && Objects.equals(isbn, valoracio1.isbn) && Objects.equals(username, valoracio1.username) && Objects.equals(data, valoracio1.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, username, data, puntuacio);
    }
}

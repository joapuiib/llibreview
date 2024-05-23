package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UsuariIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Ressenya {
    private String isbn;
    private String username;
    private String comentari;
    private LocalDate data;

    private Valoracio valoracio;

    public Ressenya(String isbn, String username, String comentari, LocalDate data) {
        this.isbn = isbn;
        this.username = username;
        this.comentari = comentari;
        this.data = data;
    }

    public Ressenya(Llibre llibre, Usuari usuari, String comentari, LocalDate data) {
        this.isbn = llibre.getIsbn();
        this.username = usuari.getUsername();
        this.comentari = comentari;
        this.data = data;
    }

    public Ressenya() {
    }

    @Override
    public String toString() {
        return "Ressenya{" +
                "isbn='" + isbn + '\'' +
                ", username='" + username + '\'' +
                ", comentari='" + comentari + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ressenya ressenya = (Ressenya) o;
        return Objects.equals(isbn, ressenya.isbn) && Objects.equals(username, ressenya.username) && Objects.equals(comentari, ressenya.comentari) && Objects.equals(data, ressenya.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, username, comentari, data);
    }
}

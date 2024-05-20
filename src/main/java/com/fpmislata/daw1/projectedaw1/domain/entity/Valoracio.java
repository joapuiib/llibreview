package com.fpmislata.daw1.projectedaw1.domain.entity;

import com.fpmislata.daw1.projectedaw1.common.container.LlibreIoc;
import com.fpmislata.daw1.projectedaw1.common.container.UsuariIoc;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Valoracio {
    private String isbn;
    private String username;
    private LocalDate data;
    private int valoracio;

    private Llibre llibre;
    private Usuari usuari;

    public Valoracio(String isbn, String username, int valoracio, LocalDate data) {
        this.isbn = isbn;
        this.username = username;
        this.valoracio = valoracio;
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

    @Override
    public String toString() {
        return "Valoracio{" +
                "grade=" + valoracio +
                ", valoracioDate=" + data +
                ", userame='" + username + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

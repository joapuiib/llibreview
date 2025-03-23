package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Llista {
    private String nom;
    private Usuari usuari;
    private List<Llibre> llibres;

    public Llista(String nom, Usuari usuari) {
        this.nom = nom;
        this.usuari = usuari;
        llibres = new ArrayList<>();
    }
}

package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Llista {
    private String nom;
    private Usuari usuari;
    private List<Llibre> llibres;

    public Llista(String nom, Usuari usuari) {
        this.nom = nom;
        this.usuari = usuari;
        llibres = new ArrayList<>();
    }

    public void addLlibre(Llibre llibre) {
        llibres.add(llibre);
    }

    public String getNom() {
        return nom;
    }

    public Usuari getUsuari() {
        return usuari;
    }
}

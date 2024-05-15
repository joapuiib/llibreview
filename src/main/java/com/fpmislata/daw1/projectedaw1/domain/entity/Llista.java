package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Llista {
    private String nom;
    private User user;
    private List<Llibre> llibres;

    public Llista(String nom, User user) {
        this.nom = nom;
        this.user = user;
        llibres = new ArrayList<>();
    }

    public void addLlibre(Llibre llibre) {
        llibres.add(llibre);
    }

    public String getNom() {
        return nom;
    }

    public User getUsuari() {
        return user;
    }
}

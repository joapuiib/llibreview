package com.fpmislata.daw1.projectedaw1.domain.entity;

public class Ressenya {
    private User user;
    private Llibre llibre;
    private String text;
    private int puntuacio;

    public Ressenya(User user, Llibre llibre, String text, int puntuacio) {
        this.user = user;
        this.llibre = llibre;
        this.text = text;
        this.puntuacio = puntuacio;
    }

    public User getUsuari() {
        return user;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    public String getText() {
        return text;
    }

    public int getPuntuacio() {
        return puntuacio;
    }
}

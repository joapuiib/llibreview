package com.fpmislata.daw1.projectedaw1.domain.entity;

public class Ressenya {
    private Usuari usuari;
    private Llibre llibre;
    private String text;
    private int puntuacio;

    public Ressenya(Usuari usuari, Llibre llibre, String text, int puntuacio) {
        this.usuari = usuari;
        this.llibre = llibre;
        this.text = text;
        this.puntuacio = puntuacio;
    }

    public Usuari getUsuari() {
        return usuari;
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

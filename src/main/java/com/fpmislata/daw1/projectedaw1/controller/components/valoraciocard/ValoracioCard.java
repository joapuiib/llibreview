package com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValoracioCard {
    private String url;
    private String imatgeUrl;
    private double mitjana;
    private double puntuacio;

    public ValoracioCard() {
    }

    public ValoracioCard(String url, String imatgeUrl, double mitjana, double puntuacio) {
        this.url = url;
        this.imatgeUrl = imatgeUrl;
        this.mitjana = mitjana;
        this.puntuacio = puntuacio;
    }
}

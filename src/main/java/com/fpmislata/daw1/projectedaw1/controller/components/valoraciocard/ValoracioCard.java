package com.fpmislata.daw1.projectedaw1.controller.components.valoraciocard;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ValoracioCard {
    private LocalDate data;
    private String url;
    private String imatgeUrl;
    private double mitjana;
    private double puntuacio;

    public ValoracioCard() {
    }

    public ValoracioCard(
            LocalDate data,
            String url,
            String imatgeUrl,
            double mitjana,
            double puntuacio
    ) {
        this.data = data;
        this.url = url;
        this.imatgeUrl = imatgeUrl;
        this.mitjana = mitjana;
        this.puntuacio = puntuacio;
    }
}

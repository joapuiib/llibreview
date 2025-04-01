package com.fpmislata.daw1.projectedaw1.domain.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class EstadistiquesValoracio {
    private int count;
    private double average;

    private final Map<Integer, Integer> histogram;


    public EstadistiquesValoracio() {
        this.count = 0;
        this.average = 0;
        this.histogram = new HashMap<>();
    }

    public EstadistiquesValoracio(List<Valoracio> valoracions) {
        this();

        for (Valoracio valoracio : valoracions)
            addValoracio(valoracio);
    }

    public void addValoracio(Valoracio valoracio) {
        this.count++;
        this.average = (this.average * (this.count - 1) + valoracio.getPuntuacio()) / this.count;
        updateHistogram(valoracio);
    }

    private void updateHistogram(Valoracio valoracio) {
        int puntuacio = valoracio.getPuntuacio();
        int valoracioCount = getCount(puntuacio);
        histogram.put(puntuacio, valoracioCount + 1);
    }

    public int getCount(int puntuacio) {
        return histogram.getOrDefault(puntuacio, 0);
    }

    public int getValoracioMaxima() {
        return this.histogram.values().stream().max(Integer::compareTo).orElse(0);
    }

    /**
     * Retorna el percentatge de valoracions amb la puntuació donada respecte
     * al màxim de registres d'una puntuació.
     * @param puntuacio Puntuació (1-10)
     * @return Percentatge de valoracions amb la puntuació donada.
     */
    public double getPercentatgePuntuacio(int puntuacio) {
        if (count == 0) return 0;
        return (double) getCount(puntuacio) / getValoracioMaxima() * 100;
    }
}

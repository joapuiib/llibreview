package com.fpmislata.daw1.projectedaw1.domain.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValoracioStats {
    private int count;
    private double average;

    private final Map<Integer, Integer> histogram;


    public ValoracioStats() {
        this.count = 0;
        this.average = 0;
        this.histogram = new HashMap<>();
    }

    public void addValoracio(Valoracio valoracio) {
        this.count++;
        this.average = (this.average * (this.count - 1) + valoracio.getValoracio()) / this.count;

        if (histogram.containsKey(valoracio.getValoracio())) {
            histogram.put(valoracio.getValoracio(), histogram.get(valoracio.getValoracio()) + 1);
        } else {
            histogram.put(valoracio.getValoracio(), 1);
        }
    }

    public int getCount(int valoracio) {
        return histogram.getOrDefault(valoracio, 0);
    }

    public int getValoracioMaxima() {
        return this.histogram.values().stream().max(Integer::compareTo).orElse(0);
    }

    /**
     * Retorna el percentatge de valoracions amb la valoració donada respecte
     * al màxim de registres d'una valoració.
     * @param valoracio
     * @return
     */
    public double getPercentatgeValoracio(int valoracio) {
        if (count == 0) return 0;
        return (double) getCount(valoracio) / getValoracioMaxima() * 100;
    }
}

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

    public double getPercentatgeValoracio(int valoracio) {
        if (count == 0) return 0;
        return (double) getCount(valoracio) / count * 100;
    }
}

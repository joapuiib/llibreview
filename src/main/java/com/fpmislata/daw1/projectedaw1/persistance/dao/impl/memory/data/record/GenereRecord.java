package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenereRecord {
    private int id;
    private String nom_ca;
    private String nom_en;
    private String rutaImatge;

    public GenereRecord(int id, String nom_ca, String nom_en, String rutaImatge) {
        this.id = id;
        this.nom_ca = nom_ca;
        this.nom_en = nom_en;
        this.rutaImatge = rutaImatge;
    }

    public GenereRecord() {
    }
}

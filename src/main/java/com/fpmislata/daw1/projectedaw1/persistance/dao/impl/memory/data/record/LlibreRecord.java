package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LlibreRecord {
    private String isbn;
    private String titol_ca;
    private String titol_en;
    private String resum_ca;
    private String resum_en;
    private LocalDate dataPublicacio;
    private int nombrePagines;
    private String rutaImatge;

    public LlibreRecord(String isbn, String titol_ca, String titol_en, String resum_ca, String resum_en, LocalDate dataPublicacio, int nombrePagines, String rutaImatge) {
        this.isbn = isbn;
        this.titol_ca = titol_ca;
        this.titol_en = titol_en;
        this.resum_ca = resum_ca;
        this.resum_en = resum_en;
        this.dataPublicacio = dataPublicacio;
        this.nombrePagines = nombrePagines;
        this.rutaImatge = rutaImatge;
    }

    public LlibreRecord() {
    }
}

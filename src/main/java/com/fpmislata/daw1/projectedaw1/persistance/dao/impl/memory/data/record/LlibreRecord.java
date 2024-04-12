package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record;

import java.time.LocalDate;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitol_ca() {
        return titol_ca;
    }

    public void setTitol_ca(String titol_ca) {
        this.titol_ca = titol_ca;
    }

    public String getTitol_en() {
        return titol_en;
    }

    public void setTitol_en(String titol_en) {
        this.titol_en = titol_en;
    }

    public String getResum_ca() {
        return resum_ca;
    }

    public void setResum_ca(String resum_ca) {
        this.resum_ca = resum_ca;
    }

    public String getResum_en() {
        return resum_en;
    }

    public void setResum_en(String resum_en) {
        this.resum_en = resum_en;
    }

    public LocalDate getDataPublicacio() {
        return dataPublicacio;
    }

    public void setDataPublicacio(LocalDate dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
    }

    public int getNombrePagines() {
        return nombrePagines;
    }

    public void setNombrePagines(int nombrePagines) {
        this.nombrePagines = nombrePagines;
    }

    public String getRutaImatge() {
        return rutaImatge;
    }

    public void setRutaImatge(String rutaImatge) {
        this.rutaImatge = rutaImatge;
    }
}

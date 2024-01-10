package com.fpmislata.daw1.projectedaw1.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Usuari {
    private String username;
    private String correuElectronic;
    private List<Rol> rols;
    private Date dataAlta;

    public Usuari(String username, String correuElectronic, Date dataAlta) {
        this.username = username;
        this.correuElectronic = correuElectronic;
        this.dataAlta = dataAlta;
        rols = new ArrayList<>();
    }

    public void addRol(Rol rol) {
        rols.add(rol);
    }

    public String getUsername() {
        return username;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public List<Rol> getRols() {
        return rols;
    }

    public Date getDataAlta() {
        return dataAlta;
    }
}

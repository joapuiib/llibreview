package com.fpmislata.daw1.projectedaw1.persistance.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LlibreRowMapper extends RowMapper<Llibre> {
    @Override
    public Llibre mapItem(ResultSet rs) throws SQLException {
        Llibre llibre = new Llibre();
        llibre.setIsbn(rs.getString("isbn"));
        llibre.setTitol(rs.getString("titol"));
        llibre.setResum(rs.getString("resum"));
        llibre.setDataPublicacio(rs.getDate("data_publicacio").toLocalDate());
        llibre.setNombrePagines(rs.getInt("nombre_pagines"));
        llibre.setRutaImatge(rs.getString("ruta_imatge"));
        return llibre;
    }
}
package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LlibreRowMapper implements RowMapper<Llibre> {
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

    public List<Llibre> map(ResultSet resultSet) throws SQLException {
        if(resultSet ==  null) {
            return null;
        }
        List<Llibre> llibreList = new ArrayList<>();
        while (resultSet.next()) {
            llibreList.add(mapItem(resultSet));
        }
        return llibreList;
    }
}
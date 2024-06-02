package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ValoracioRowMapper extends RowMapper<Valoracio> {
    @Override
    public Valoracio mapItem(ResultSet rs) throws SQLException {
        Valoracio valoracio = new Valoracio();
        valoracio.setIsbn(rs.getString("isbn"));
        valoracio.setUsername(rs.getString("username"));
        valoracio.setPuntuacio(rs.getInt("puntuacio"));
        valoracio.setData(rs.getDate("data").toLocalDate());
        return valoracio;
    }
}
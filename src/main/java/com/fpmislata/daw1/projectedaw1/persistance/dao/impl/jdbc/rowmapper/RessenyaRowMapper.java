package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RessenyaRowMapper extends RowMapper<Ressenya> {
    @Override
    public Ressenya mapItem(ResultSet rs) throws SQLException {
        Ressenya ressenya = new Ressenya();
        ressenya.setIsbn(rs.getString("isbn"));
        ressenya.setUsername(rs.getString("username"));
        ressenya.setComentari(rs.getString("comentari"));
        ressenya.setData(rs.getDate("data").toLocalDate());
        return ressenya;
    }
}
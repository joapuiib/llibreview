package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenereRowMapper extends RowMapper<Genere> {
    @Override
    public Genere mapItem(ResultSet rs) throws SQLException {
        Genere genere = new Genere();
        genere.setId(rs.getInt("id"));
        genere.setNom(rs.getString("nom"));
        genere.setRutaImatge(rs.getString("ruta_imatge"));
        return genere;
    }
}
package com.fpmislata.daw1.projectedaw1.persistance.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Ressenya;
import com.fpmislata.daw1.projectedaw1.domain.entity.Valoracio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RessenyaRowMapper extends RowMapper<Ressenya> {
    @Override
    public Ressenya mapItem(ResultSet rs) throws SQLException {
        Valoracio valoracio = new Valoracio();
        Ressenya ressenya = new Ressenya();
        ressenya.setValoracio(valoracio);

        String isbn = rs.getString("isbn");
        ressenya.setIsbn(isbn);
        valoracio.setIsbn(isbn);

        String username = rs.getString("username");
        ressenya.setUsername(username);
        valoracio.setUsername(username);

        ressenya.setComentari(rs.getString("comentari"));
        ressenya.setData(rs.getDate("data").toLocalDate());

        valoracio.setPuntuacio(rs.getInt("puntuacio"));
        valoracio.setData(rs.getDate("valoracio_data").toLocalDate());
        return ressenya;
    }
}
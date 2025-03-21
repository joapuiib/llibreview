package com.fpmislata.daw1.projectedaw1.persistance.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariRowMapper extends RowMapper<Usuari> {
    @Override
    public Usuari mapItem(ResultSet rs) throws SQLException {
        Usuari usuari = new Usuari();
        usuari.setUsername(rs.getString("username"));
        usuari.setEmail(rs.getString("email"));
        usuari.setDataRegistre(rs.getDate("data_registre").toLocalDate());
        usuari.setContrasenyaHash(rs.getString("password"));
        return usuari;
    }
}
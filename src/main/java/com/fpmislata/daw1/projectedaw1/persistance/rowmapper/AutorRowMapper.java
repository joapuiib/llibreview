package com.fpmislata.daw1.projectedaw1.persistance.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorRowMapper extends RowMapper<Autor> {
    @Override
    public Autor mapItem(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setId(rs.getInt("id_autor"));
        autor.setNom(rs.getString("nom"));
        autor.setBiografia(rs.getString("biografia"));
        autor.setDataNaixement(rs.getDate("data_naixement").toLocalDate());
        autor.setRutaImatge(rs.getString("ruta_imatge"));
        return autor;
    }
}
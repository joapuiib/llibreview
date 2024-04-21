package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Autor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AutorRowMapper implements RowMapper<Autor> {
    public Autor mapItem(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setId(rs.getInt("id_autor"));
        autor.setNom(rs.getString("nom"));
        autor.setBiografia(rs.getString("biografia"));
        autor.setDataNaixement(rs.getDate("data_naixement").toLocalDate());
        autor.setRutaImatge(rs.getString("ruta_imatge"));
        return autor;
    }

    public List<Autor> map(ResultSet resultSet) throws SQLException {
        if(resultSet ==  null) {
            return null;
        }
        List<Autor> autorList = new ArrayList<>();
        while (resultSet.next()) {
            autorList.add(mapItem(resultSet));
        }
        return autorList;
    }
}
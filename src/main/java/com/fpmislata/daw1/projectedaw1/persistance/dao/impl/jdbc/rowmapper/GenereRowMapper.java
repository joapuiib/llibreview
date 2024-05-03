package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Genere;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenereRowMapper implements RowMapper<Genere> {
    public Genere mapItem(ResultSet rs) throws SQLException {
        Genere genere = new Genere();
        genere.setId(rs.getInt("id"));
        genere.setNom(rs.getString("nom"));
        genere.setRutaImatge(rs.getString("ruta_imatge"));
        return genere;
    }

    public List<Genere> map(ResultSet resultSet) throws SQLException {
        if(resultSet ==  null) {
            return null;
        }
        List<Genere> genereList = new ArrayList<>();
        while (resultSet.next()) {
            genereList.add(mapItem(resultSet));
        }
        return genereList;
    }
}
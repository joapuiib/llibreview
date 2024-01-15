package com.fpmislata.daw1.projectedaw1.persistance.impl.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LlibreRowMapper {
    public Llibre mapRow(ResultSet rs) throws SQLException {
        Llibre llibre = new Llibre();
        llibre.setIsbn(rs.getString("isbn"));
        llibre.setTitol(rs.getString("titol"));
        llibre.setResum(rs.getString("resum"));
        llibre.setDataPublicacio(rs.getDate("data_publicacio"));
        return llibre;
    }

    public List<Llibre> toLlibreList(ResultSet resultSet) {
        if(resultSet ==  null) {
            return null;
        }
        List<Llibre> llibreList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                llibreList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Algo no ha funcionado: " + e.getMessage());
        }
        return llibreList;
    }
}

package com.fpmislata.daw1.projectedaw1.persistance.impl.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LlibreRowMapper implements RowMapper<Llibre> {
    @Override
    public Llibre mapRow(ResultSet rs, int rowNum) throws SQLException {
        String isbn = rs.getString("isbn");
        String titol = rs.getString("titol");
        Llibre llibre = new Llibre(isbn, titol);
        return llibre;
    }
}

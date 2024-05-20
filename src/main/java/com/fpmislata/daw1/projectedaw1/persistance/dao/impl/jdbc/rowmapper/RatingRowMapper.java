package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper extends RowMapper<Rating> {
    @Override
    public Rating mapItem(ResultSet rs) throws SQLException {
        Rating rating = new Rating();
        rating.setIsbn(rs.getString("isbn"));
        rating.setUsername(rs.getString("username"));
        rating.setRating(rs.getInt("rating"));
        rating.setDate(rs.getDate("date").toLocalDate());
        return rating;
    }
}
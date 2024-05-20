package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.jdbc.rowmapper;

import com.fpmislata.daw1.projectedaw1.domain.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper extends RowMapper<User> {
    @Override
    public User mapItem(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setDataRegistre(rs.getDate("data_registre").toLocalDate());
        return user;
    }
}